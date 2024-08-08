package org.ere.contest.cfredisservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.ere.contest.orderstarter.repository")
@EntityScan(basePackages = "org.ere.contest.orderstarter.model.entity")
public class JpaCustomConfiguration {
}
