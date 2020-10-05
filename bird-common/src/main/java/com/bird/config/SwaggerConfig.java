package com.bird.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author lipu
 * @Date 2020/9/9 10:13
 * @Description swagger2配置类
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                //配置api的描述信息
                .apiInfo(apiInfo())
                //开启swagger false则不能在浏览器中访问
                .enable(true)
                //设置扫描的包
                .select().apis(RequestHandlerSelectors.basePackage("com.bird.controller"))
                //设置过滤信息
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    //配置ApiInfo信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("李璞的SwaggerApi文档")
                .description("制作人：李璞（Bird）")
                .termsOfServiceUrl("http://swagger.io/")
                .version("2.0")
                .build();
    }

}
