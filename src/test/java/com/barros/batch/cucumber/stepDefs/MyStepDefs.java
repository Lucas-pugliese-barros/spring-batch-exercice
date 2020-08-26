package com.barros.batch.cucumber.stepDefs;

import com.barros.batch.config.BatchConfiguration;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStepDefs {

    @Autowired
    private BatchConfiguration batchConfiguration;

    @Value("${scenarios.folder}")
    private String scenariosFolder;

    @Value("${input.folder}")
    private String inputFolder;

    @Value("${done.folder}")
    private String doneFolder;

    @Value("${output.folder}")
    private String outputFolder;

    @Value("${invalid.folder}")
    private String invalidFolder;

    @Before
    public void init() {
        List<String> filesToBeDeleted = new ArrayList<>() {{
            add("file_one.dat");
            add("file_one.done.dat");
            add("file_two_invalid.dat");
        }};

        deleteFilesOfContext(filesToBeDeleted, doneFolder);
        deleteFilesOfContext(filesToBeDeleted, inputFolder);
        deleteFilesOfContext(filesToBeDeleted, invalidFolder);
        deleteFilesOfContext(filesToBeDeleted, outputFolder);
    }

    @Given("^file \"([^\"]*)\" to be processed with the following content$")
    public void fileToBeProcessedWithTheFollowingContent(String fileName, String content) throws Throwable {
        String inputFile = this.inputFolder + "/" + fileName;

        FileWriter myWriter = new FileWriter(inputFile);
        myWriter.write(content);
        myWriter.close();
    }

    @When("^the batch execute the process$")
    public void theBatchExecuteTheProcess() throws Exception {
        batchConfiguration.perform();
    }


    @Then("^should exist a report named \"([^\"]*)\"$")
    public void shouldExistAReportNamed(String fileName) {
        File reportFile = getFileFromAndByName(outputFolder, fileName);

        assertThat(reportFile).isNotNull();
    }

    @Then("^should not exist a report named \"([^\"]*)\"$")
    public void shouldNotExistAReportNamed(String fileName) {
        File reportFile = getFileFromAndByName(outputFolder, fileName);

        assertThat(reportFile).isNull();
    }


    @And("^in the report \"([^\"]*)\" the quantity of clients should be \"([^\"]*)\"$")
    public void inTheReportTheQuantityOfClientsShouldBe(String fileName, String quantityOfClients) throws Throwable {
        String quantityExpression = ".*:\\s?(\\d*)";

        File reportFile = getFileFromAndByName(outputFolder, fileName);

        Scanner scanner = new Scanner(reportFile);
        String firstLine = scanner.nextLine();

        Pattern pattern = Pattern.compile(quantityExpression);
        Matcher matcher = pattern.matcher(firstLine);
        matcher.find();

        assertThat(reportFile).isNotNull();
        assertThat(matcher.group(1)).isEqualTo(quantityOfClients);
    }

    @And("^in the report \"([^\"]*)\" the quantity of salesman should be \"([^\"]*)\"$")
    public void inTheReportTheQuantityOfSalesmanShouldBe(String fileName, String quantityOfSalesman) throws Throwable {
        String quantityExpression = ".*:\\s?(\\d*)";
        int jumpOneLine = 1;

        File reportFile = getFileFromAndByName(outputFolder, fileName);

        Scanner scanner = new Scanner(reportFile);
        skipLines(scanner, jumpOneLine);
        String secondLine = scanner.nextLine();

        Pattern pattern = Pattern.compile(quantityExpression);
        Matcher matcher = pattern.matcher(secondLine);
        matcher.find();

        assertThat(reportFile).isNotNull();
        assertThat(matcher.group(1)).isEqualTo(quantityOfSalesman);
    }


    @And("^in the report \"([^\"]*)\" the best sale must have ID \"([^\"]*)\"$")
    public void inTheReportTheBestSaleMustHaveID(String fileName, String idOfTheBestSale) throws Throwable {
        String quantityExpression = ".*:\\s?(\\d*)";
        int jumpTwoLines = 2;

        File reportFile = getFileFromAndByName(outputFolder, fileName);

        Scanner scanner = new Scanner(reportFile);
        skipLines(scanner, jumpTwoLines);
        String secondLine = scanner.nextLine();

        Pattern pattern = Pattern.compile(quantityExpression);
        Matcher matcher = pattern.matcher(secondLine);
        matcher.find();

        assertThat(reportFile).isNotNull();
        assertThat(matcher.group(1)).isEqualTo(idOfTheBestSale);
    }

    @And("^in the report \"([^\"]*)\" the worst salesman should be \"([^\"]*)\"$")
    public void inTheReportTheWorstSalesmanShouldBe(String fileName, String worstSalesman) throws Throwable {
        String quantityExpression = ".*:\\s?(\\w*)";
        int jumpThreeLines = 3;

        File reportFile = getFileFromAndByName(outputFolder, fileName);

        Scanner scanner = new Scanner(reportFile);
        skipLines(scanner, jumpThreeLines);
        String secondLine = scanner.nextLine();

        Pattern pattern = Pattern.compile(quantityExpression);
        Matcher matcher = pattern.matcher(secondLine);
        matcher.find();

        assertThat(reportFile).isNotNull();
        assertThat(matcher.group(1)).isEqualTo(worstSalesman);
    }

    @And("^should move the file \"([^\"]*)\" to done folder$")
    public void shouldMoveTheFileToDoneFolder(String fileName) {
        File reportFile = getFileFromAndByName(doneFolder, fileName);

        assertThat(reportFile).isNotNull();
    }

    @And("^should move the file \"([^\"]*)\" to invalid folder$")
    public void shouldMoveTheFileToInvalidFolder(String fileName) {
        File reportFile = getFileFromAndByName(invalidFolder, fileName);

        assertThat(reportFile).isNotNull();
    }

    private File getFileFromAndByName(String folder, String fileName) {
        File fileOutputFolder = new File(folder);
        return Arrays.stream(fileOutputFolder
                .listFiles((directory, name) -> name.equals(fileName)))
                .findFirst()
                .orElse(null);
    }

    private void deleteFilesOfContext(List<String> filesToBeDeleted, String from) {
        File file = new File(from);
        File[] arrayOfFiles = file.listFiles((directory, name) -> filesToBeDeleted.contains(name));

        Arrays.stream(arrayOfFiles).forEach(File::deleteOnExit);
    }

    public static void skipLines(Scanner scanner, int lineNum){
        for(int i = 0; i < lineNum;i++) {
            if(scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }
}
