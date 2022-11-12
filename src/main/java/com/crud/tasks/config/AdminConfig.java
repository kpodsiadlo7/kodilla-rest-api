package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.app.description}")
    private String desc;

    @Value("${info.company.goal}")
    private String goal;

    @Value("${info.company.phone}")
    private String phone;

    @Value("${info.app.version}")
    private String version;
}