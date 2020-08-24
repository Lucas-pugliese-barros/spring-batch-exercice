package com.barros.batch.integration;

import com.barros.batch.config.BatchConfiguration;
import com.barros.batch.model.Lote;
import org.junit.After;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class, classes = {BatchConfiguration.class})
@ActiveProfiles("test")
public class BatchApplicationTest {

    @Autowired
    private BatchConfiguration batchConfiguration;

    @Value("${scenarios.folder}")
    private String scenariosFolder;

    @Value("${input.folder}")
    private String inputFolder;

    @Value("${output.folder}")
    private String outputFolder;

    @Value("${invalid.folder}")
    private String invalidFolder;

    @After
    public void afterTheTest() {
        List<String> filesToBeDeleted = new ArrayList<>(){{
            add("lote_um.dat");
            add("lote_um.done.dat");
            add("lote_dois_invalido.dat");
        }};

        deleteFilesOfContext(filesToBeDeleted, inputFolder);
        deleteFilesOfContext(filesToBeDeleted, invalidFolder);
        deleteFilesOfContext(filesToBeDeleted, outputFolder);
    }

    @Test
    public void validFileShouldHaveAhReportAfterBatchProcess() throws Exception {
        String scenariosFile = this.scenariosFolder + "/lote_um.dat";
        String inputFile = this.inputFolder + "/lote_um.dat";

        String expectedFileNameOutput = "lote_um.done.dat";
        int expectedToHaveOneOutput = 1;

        //Given valid file to be processed
        Files.copy(Paths.get(scenariosFile), Paths.get(inputFile), StandardCopyOption.REPLACE_EXISTING);

        //When the batch execute the process
        batchConfiguration.perform();
        
        //Then should exist a report
        File file = new File(outputFolder);
        File[] arrayOfFiles = file.listFiles((directory, name) -> name.equals(expectedFileNameOutput));

        assertThat(arrayOfFiles.length).isEqualTo(expectedToHaveOneOutput);
    }

    @Test
    public void invalidFileShouldBeMovedToInvalidFolder() throws Exception {
        String scenariosFile = this.scenariosFolder + "/lote_dois_invalido.dat";
        String inputFile = this.invalidFolder + "/lote_dois_invalido.dat";

        String expectedFileNameOutput = "lote_dois_invalido.dat";
        int expectedToHaveOneOutput = 1;

        //Given invalid file to be processed
        Files.copy(Paths.get(scenariosFile), Paths.get(inputFile), StandardCopyOption.REPLACE_EXISTING);

        //When the batch execute the process
        batchConfiguration.perform();

        //Then should exist one file inside the invalid folder
        File file = new File(invalidFolder);
        File[] arrayOfFiles = file.listFiles((directory, name) -> name.equals(expectedFileNameOutput));

        assertThat(arrayOfFiles.length).isEqualTo(expectedToHaveOneOutput);
    }

    private void deleteFilesOfContext(List<String> filesToBeDeleted, String from) {
        File file = new File(from);
        File[] arrayOfFiles = file.listFiles((directory, name) -> filesToBeDeleted.contains(name));

        Arrays.stream(arrayOfFiles).forEach(File::deleteOnExit);
    }
}
