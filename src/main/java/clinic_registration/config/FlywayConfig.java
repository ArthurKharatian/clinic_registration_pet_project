package clinic_registration.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FlywayConfig implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Override
    public void run(String... args) {
        Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(url, username, password)
                .load()
                .migrate();
    }
}
