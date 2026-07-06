package com.study24.api.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing(생성일시/수정일시 자동 기록) 기능을 활성화하는 설정
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
