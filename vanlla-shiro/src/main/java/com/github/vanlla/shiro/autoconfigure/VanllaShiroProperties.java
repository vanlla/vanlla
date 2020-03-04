package com.github.vanlla.shiro.autoconfigure;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * shiro 免登录路径
 *
 * @author Yuan Xianxian
 * @since 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "vanlla.shiro")
public class VanllaShiroProperties {

    private List<String> anonConfigs = new ArrayList<>();

}
