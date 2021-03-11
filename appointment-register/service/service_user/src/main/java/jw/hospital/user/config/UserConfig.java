package jw.hospital.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("jw.hospital.user.mapper")
public class UserConfig {
}
