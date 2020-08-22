package com.barros.batch.config;

import com.barros.batch.model.Lote;
import com.barros.batch.processor.LoteItemProcessor;
import com.barros.batch.reader.FileReader;
import com.barros.batch.writer.ConsoleItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

	@Value("${input.folder}")
	private String inputFolder;

	@Value("${output.folder}")
	private String outputFolder;

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public ItemReader<Lote> reader()  {
		logger.info("Reading Folder: {0}", inputFolder);
		return new FileReader(inputFolder);
	}

	@Bean
	public LoteItemProcessor processor() {
		return new LoteItemProcessor();
	}

	@Bean
	public ItemWriter<Lote> itemWriter() {
		logger.info("Writing Folder: {0}", outputFolder);
		return new ConsoleItemWriter();
	}

	@Bean
	public Job job(Step step1) {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.flow(step1)
				.end()
				.build();
	}

	@Bean
	public Step step1(ItemWriter<Lote> writer) {
		return stepBuilderFactory.get("step1")
			.<Lote, Lote> chunk(10)
			.reader(reader())
			//.processor(processor())
			.writer(itemWriter())
			.build();
	}
}
