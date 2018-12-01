package com.nesho.bookstoreangular.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.nesho.bookstoreangular.resource"))
                .paths(PathSelectors.any()).build().securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(securitySchema(), apiKey())).apiInfo(generateApiInfo());
    }

    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, "apiKey", "header");
    }

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));
        List<GrantType> grantTypes = new ArrayList<>();
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant("/oauth/token");
        grantTypes.add(passwordCredentialsGrant);

        return new OAuth("oauth2", authorizationScopeList, grantTypes);
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");

        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }


    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration("bookstore", "password", "", "bookstore", "Bearer access token", null, null);
    }


    private ApiInfo generateApiInfo() {
        return new ApiInfo(
                "Doctor At Home Application Test Service",
                "This service is to check the added APIs for DoctorHome backend services",
                "Version 1.0 - mw",
                "urn:tos",
                new Contact("RedShift", "", "http://www.redshift.com.eg"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

    }
}
