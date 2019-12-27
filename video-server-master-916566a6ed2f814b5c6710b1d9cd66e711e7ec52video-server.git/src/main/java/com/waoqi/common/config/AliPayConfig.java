package com.waoqi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="alipay")
public class AliPayConfig {

	private String appId;

	private String privateKey;

	private String publicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjt2JDzLxAXizeiPgCg1LsXXcoP1ttdbxp4Qh8E9f9VRU/5YjdUXv5d+xXkJ+daIov/VKuDwAPM5eickN1xO6ghj0WEXZeuq6phUl/pEIsguYHgAR/m85kB0MMmMX+QTVdMdBfNSwcqX5P5ZaHDVEgMZFJIqsLUKVAF92YozVS0rW8yALMQG4wxRagLJ+vCeCVfX85HnD1HtNy4xf/3I3WiYgqj5Tau7oabTTm/UzGRVMLIsMhV/taT5feWG1PvsTtZR3W6YAKUkitCz2b8MVQ4IhisLQ1quJDauO82HnfxVMg3nj4AsPCkMGumzDSRQXkUhWp6x0HCNCdiSIAUOU1QIDAQAB";

	private String notifyUrl;


}
