package com.interactive.hana.global.config.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Bean("jasyptStringEncryptor")
    public StandardPBEStringEncryptor jasyptConfigure() {
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("dogfoot");
        jasypt.setAlgorithm("PBEWithMD5AndDES");
        return jasypt;
    }

}
