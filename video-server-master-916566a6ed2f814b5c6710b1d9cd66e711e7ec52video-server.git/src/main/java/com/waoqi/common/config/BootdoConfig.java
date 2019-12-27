package com.waoqi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="waoqi")
public class BootdoConfig {
	//上传路径
	private String uploadPath;

	private String username;

	private String password;

	private String domain;
	//密码二次加密
	private String passwordEncryption;



}
