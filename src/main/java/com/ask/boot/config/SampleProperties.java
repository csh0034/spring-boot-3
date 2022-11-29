package com.ask.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

// spring boot 3 부터 생성자 하나일 경우 @ConstructorBinding 추가 안해도됨.
@ConfigurationProperties("sample")
@Slf4j
public record SampleProperties(String key1, @DefaultValue("default..") String key2) {

  public SampleProperties {
    log.info("key1: {}, key2: {}", key1, key2);
  }

}
