package com.barros.batch.config;

import com.barros.batch.model.Lote;
import com.barros.batch.model.Relatorio;
import com.barros.batch.processor.LoteProcessor;
import com.barros.batch.reader.LoteReader;
import com.barros.batch.writer.ReportWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {
	@Value("${input.folder}")
	private String inputFolder;

	@Value("${output.folder}")
	private String outputFolder;

	@Value("${invalid.folder}")
	private String invalidFolder;

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private JobLauncher jobLauncher;

	@Autowired
	public BatchConfiguration(JobBuilderFactory jobBuilderFactory,
							  StepBuilderFactory stepBuilderFactory,
							  JobLauncher jobLauncher) {

		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.jobLauncher = jobLauncher;
	}

	@Scheduled(cron = "0 */1 * * * ?")
	public void perform() throws Exception {
		JobParameters params = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job(), params);
	}

	@Bean
	public ItemReader<Lote> reader()  {
		return new LoteReader(inputFolder);
	}

	@Bean
	public LoteProcessor processor() {
		return new LoteProcessor();
	}

	@Bean
	public ItemWriter<Relatorio> itemWriter() {
		return new ReportWriter(inputFolder, outputFolder, invalidFolder);
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.flow(step())
				.end()
				.build();
	}

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step1")
			.<Lote, Relatorio> chunk(1)
			.reader(reader())
			.processor(processor())
			.writer(itemWriter())
			.build();
	}
}
