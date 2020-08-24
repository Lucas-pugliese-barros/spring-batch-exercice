package com.barros.batch.integration;

import com.barros.batch.config.BatchConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class, classes = {BatchConfiguration.class})
@ActiveProfiles("test")
public class BatchApplicationTest {

    @Autowired
    private BatchConfiguration batchConfiguration;

    @Value("${output.folder}")
    private String outputFolder;

    @Before
    public void beforeTheTest() {
        System.out.println("beforeTheTest");
    }

    @Test
    public void integrationTest() throws Exception {
        String expectedFileNameOutput = "lote_um.done.dat";
        int expectedToHaveOneOutput = 1;

        //Given
        System.out.println("test");
        System.out.println(outputFolder);

        //When
        batchConfiguration.perform();
        
        //Then
        File file = new File(outputFolder);
        File[] arrayOfFiles = file.listFiles((directory, name) -> name.equals(expectedFileNameOutput));

        assertThat(arrayOfFiles.length).isEqualTo(expectedToHaveOneOutput);
    }

}
