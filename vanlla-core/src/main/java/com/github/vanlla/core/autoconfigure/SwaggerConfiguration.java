package com.github.vanlla.core.autoconfigure;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


/**
 * Swagger2 生成api文档
 *
 * @author Vanlla
 * @since 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    // 定义分隔符
    private static final String SPLITOR = ";";

    private static final String TITLE = "Vanlla RESTful APIs";

    private static final String DESCRIPTION = "Springboot快速开发脚手架";

    private static final String VERSION = "1.0";

    @Autowired
    private VanllaSwaggerProperties vanllaSwaggerProperties;

    @Bean
    public Docket api(String swaggerBasePackages) {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(basePackage(swaggerBasePackages))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    private ApiInfo getApiInfo() {
        String title = ObjectUtil.isEmpty(vanllaSwaggerProperties.getTitle()) ? TITLE : vanllaSwaggerProperties.getTitle();
        String description = ObjectUtil.isEmpty(vanllaSwaggerProperties.getDescription()) ? DESCRIPTION : vanllaSwaggerProperties.getDescription();
        String version = ObjectUtil.isEmpty(vanllaSwaggerProperties.getVersion()) ? VERSION : vanllaSwaggerProperties.getVersion();
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                //            .license("Apache 2.0")
                //            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {

        return Lists.newArrayList(new ApiKey("Authorization", "Authorization", "header"));

    }

    private List<SecurityContext> securityContexts() {

        SecurityContext context = SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();

        return Lists.newArrayList(context);


    }

    private List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Lists.newArrayList(new SecurityReference("Authorization", authorizationScopes));

    }

    @Bean
    public String swaggerBasePackages() {
        List<String> basePackages = vanllaSwaggerProperties.getBasePackages();
        basePackages.add("com.github.vanlla");
        return CollectionUtil.join(basePackages, SPLITOR);
    }


    /**
     * 支持扫描多个base路径
     *
     * @param basePackage
     * @return
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLITOR)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
}