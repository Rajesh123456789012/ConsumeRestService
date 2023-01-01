package com.info.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "domainApi")
public class DomainConfigProperties {

    private String baseUrl;

    private final DomainConfigProperties.AddEmp addEmp = new DomainConfigProperties.AddEmp();
    private final DomainConfigProperties.GetAllEmps allEmps = new DomainConfigProperties.GetAllEmps();

    @Getter
    @Setter
    public static class AddEmp {
        private String baseUri;
    }

    public static class GetAllEmps {
        private String baseUri;
    }
}
