package com.github.vanlla.system.controller;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.github.vanlla.core.exception.RestException;
import com.github.vanlla.core.web.BaseRestController;
import com.github.vanlla.redis.util.RedisUtils;
import com.github.vanlla.shiro.token.VanllaLoginToken;
import com.github.vanlla.shiro.util.LoginType;
import com.github.vanlla.shiro.util.Sha256Hash;
import com.github.vanlla.shiro.util.ShiroUtils;
import com.github.vanlla.system.entity.UserEntity;
import com.github.vanlla.system.entity.UserTokenEntity;
import com.github.vanlla.system.form.LoginForm;
import com.github.vanlla.system.service.ICaptchaService;
import com.github.vanlla.system.service.IMenuService;
import com.github.vanlla.system.service.IUserService;
import com.github.vanlla.system.service.IUserTokenService;
import com.github.vanlla.system.vo.MenuNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录
 *
 * @author Vanlla
 * @since 1.0
 */
@Api(tags = "登录管理")
@RestController
public class LoginController extends BaseRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserTokenService userTokenService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private ICaptchaService captchaService;

    public static String springRabbitmqHost;

    private static final String REDIS_LOGIN_PREFIX = "manager_login_";

    @Value("${spring.rabbitmq.host:127.0.0.1}")
    public void setSpringRabbitmqHost(String springRabbitmqHost) {
        LoginController.springRabbitmqHost = springRabbitmqHost;
    }

    /**
     * 登录方法，反馈token
     *
     * @return
     */
    @PostMapping("/user/login")
    public VanllaLoginToken login(@RequestBody LoginForm login) {

        //校验验证码
        if (!this.captchaService.validate(login.getUuid(), login.getCode())) {
            throw new RestException(401, "验证码不正确,请重新输入");
        }

        //判断账号是否存在
        UserEntity user = userService.findByUserName(login.getUserName());
        if (user == null) {
            throw new RestException(401, "帐号或密码不正确,请重新输入");
        }

        String failLoginStr = RedisUtils.get(REDIS_LOGIN_PREFIX + login.getUserName());
        int failLoginCount = StrUtil.isBlank(failLoginStr) ? 0 : Integer.valueOf(failLoginStr);
        if (5 <= failLoginCount) {
            throw new RestException(401, "登录失败次数过多，请稍后重试");
        }

        UserTokenEntity loginToken;
        //判断密码,是否正确
        String password = (new Sha256Hash(login.getPassword(), user.getSalt())).toHex();

        if (user.getPassword().equals(password)) {
            //刷新token
            loginToken = userTokenService.refreshToken(user.getUserId().toString(), LoginType.MANATER, null);
        } else {
            RedisUtils.set(REDIS_LOGIN_PREFIX + login.getUserName(), failLoginCount + 1, 3600L);
            throw new RestException(401, "帐号或密码不正确,请重新输入");
        }

        return loginToken;
    }


    @PostMapping("/user/info")
    public Map<String, Object> info() {

        List<MenuNode> menuList = menuService.getMenuByUserId(ShiroUtils.getUserId());
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("roles", new String[]{"admin"});
        returnMap.put("name", "admin");
        returnMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        returnMap.put("menuList", menuList);
        return returnMap;

    }

    @PostMapping("/user/logout")
    @ResponseBody
    public Object logout() {
        //登出系统
        ShiroUtils.logout();
        return "success";
    }


    @GetMapping({"/captcha.jpg"})
    @ApiOperation(
            value = "获取验证码",
            produces = "application/octet-stream"
    )
    @ApiImplicitParam(
            name = "uuid",
            value = "UUID",
            defaultValue = "do1",
            dataType = "string",
            paramType = "query"
    )
    public void captcha(HttpServletRequest request, HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        BufferedImage image = this.captchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IoUtil.close(out);
    }

}
