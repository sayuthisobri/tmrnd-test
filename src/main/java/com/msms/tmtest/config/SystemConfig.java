package com.msms.tmtest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("system-config")
@Data
public class SystemConfig {
    String watchDir;
}
