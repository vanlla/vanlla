package com.github.vanlla.core.autoconfigure;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger扫描的包路径
 *
 * @author Yuan xianxian
 * @since 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "vanlla.swagger")
public class VanllaSwaggerProperties {

    private List<String> basePackages = new ArrayList<>();

    private String title;

    private String description;

    private String version;
}
