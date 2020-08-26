package com.barros.batch.cucumber;

import com.barros.batch.config.BatchConfiguration;
import cucumber.api.java.Before;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@EnableAutoConfiguration
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class, classes = {BatchConfiguration.class})
@ActiveProfiles("test")
public class CucumberContextConfiguration {

    @Before
    public void setup_cucumber_spring_context() {

    }
}
