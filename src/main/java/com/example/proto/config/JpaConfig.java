package com.example.proto.config;

import com.example.proto.config.component.AuthInterceptor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Slf4j
@Configuration
public class JpaConfig {

    @Bean
    public JPAQueryFactory queryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

    @Bean
    public AuditorAware<Integer> auditorProvider(HttpServletRequest request, AuthInterceptor interceptor) {
        return () -> {
            // TODO 유저 IDX 저장/추출 방식 조정 필요
            Object userIdxObj = interceptor.getUserIdInfo(request);

            if (userIdxObj != null) {
                try {
                    Integer userIdx = Integer.valueOf(String.valueOf(userIdxObj));
                    return Optional.of(userIdx);
                } catch (NumberFormatException e) {
                    log.error("[{}] {}", e.getClass().getName(), "유저 Idx 추출 오류");
                }
            }
            return Optional.empty();
        };
    }
}
