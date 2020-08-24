package com.barros.batch.unit;

import com.barros.batch.config.BatchConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class, classes = {BatchConfiguration.class})
@ActiveProfiles("test")
public class Unit {
    @Value("${scenarios.folder}")
    protected String scenariosFolder;

    @Value("${input.folder}")
    protected String inputFolder;
}
