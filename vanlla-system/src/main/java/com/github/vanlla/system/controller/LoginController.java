package com.github.vanlla.system.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.github.vanlla.core.exception.RestException;
import com.github.vanlla.core.web.BaseRestController;
import com.github.vanlla.shiro.oauth2.TokenGenerator;
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
import java.util.Calendar;
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
        UserTokenEntity loginToken = new UserTokenEntity();
        UserEntity user = userService.findByUserName(login.getUserName());
        if (user == null) {
            throw new RestException(401, "帐号或密码不正确,请重新输入");
        }

        //判断密码,是否正确
        String password = (new Sha256Hash(login.getPassword(), user.getSalt())).toHex();

        if (user.getPassword().equals(password)) {
            loginToken.setToken(TokenGenerator.generateValue(LoginType.MANATER));
            loginToken.setExpireTime(DateUtil.offsetHour(Calendar.getInstance().getTime(), 2));
            loginToken.setUserId(user.getUserId());
            loginToken.setType(LoginType.MANATER);
            loginToken.setId();
            userTokenService.saveOrUpdate(loginToken);
        } else {
            //TODO 添加失败登录次数
            throw new RestException(401, "帐号或密码不正确,请重新输入");
        }
        //输出到前端去掉userId和id属性
        loginToken.setUserId(null);
        loginToken.setId(null);
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
        request.getSession().setAttribute("userName", "ivan");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        BufferedImage image = this.captchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IoUtil.close(out);
    }

}
