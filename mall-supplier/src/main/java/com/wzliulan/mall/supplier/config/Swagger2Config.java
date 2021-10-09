package com.wzliulan.mall.supplier.config;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置文件
 */
@EnableSwagger2Doc
//@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean // customer-商户服务
    Docket docket1() {
        ParameterBuilder paramBuilder = new ParameterBuilder();
        paramBuilder.name("token")
                .description("访问令牌，需要时请调用登录接口获取")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build(); // header中的token参数非必填，传空也可以

        List<Parameter> params = new ArrayList<>();
        params.add(paramBuilder.build());  // 根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("supplier-商户服务")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzliulan.mall.supplier.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(params)
                .apiInfo(this.getApiInfo1());
    }
    private ApiInfo getApiInfo1() {
        return new ApiInfoBuilder()
                .title("商户服务接口文档")
                .version("Version-1.0")
                .description("商户服务模块接口文档。")
                .contact(
                        new Contact(
                                "梧州浏览科技",
                                "http://www.wzliulan.com/contact",
                                "wzliulan@qq.com")
                )
                .build();
    }

}
