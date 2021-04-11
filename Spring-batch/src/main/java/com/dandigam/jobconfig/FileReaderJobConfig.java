package com.dandigam.jobconfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.dandigam.dto.ProjectDto;
import com.dandigam.writer.LoadFileWriter;

@Configuration
@EnableBatchProcessing
public class FileReaderJobConfig {

	@Autowired
	private JobBuilderFactory factory;


	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public Job loadInitialFile(){
		
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("Batch_File_Read_Job");
		Flow flow = flowBuilder.start(loadStep()).end();
		
		return factory.get("Batch_File_Read_Job")
				.incrementer(new RunIdIncrementer())
				.start(flow).from(flow).end().build();
	}

	@Bean
	public Step loadStep() {

		return stepBuilderFactory.get("Batch_File_Read_Step").<ProjectDto, ProjectDto> chunk(100)
				.reader(flatFileItemReader()).writer(itemWriter()).build();

	}

	@Bean
	@StepScope
	public FlatFileItemReader<ProjectDto> flatFileItemReader() {

		FlatFileItemReader<ProjectDto> itemReader = new FlatFileItemReader<ProjectDto>();
		itemReader.setLinesToSkip(1);
		itemReader.setResource(new FileSystemResource("src/main/resources/project_file.csv")); // read file
		itemReader.setLineMapper(lineMapper());

		return itemReader;

	}

	private LineMapper<ProjectDto> lineMapper() {
		DefaultLineMapper<ProjectDto> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter("|");
		tokenizer.setNames("id", "name","technology","cost","teamSize");

		BeanWrapperFieldSetMapper<ProjectDto> mapper = new BeanWrapperFieldSetMapper<>();
		mapper.setTargetType(ProjectDto.class);

		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(mapper);

		return lineMapper;
	}

	@Bean
	@StepScope
	public LoadFileWriter itemWriter() {

		return new LoadFileWriter();
	}
}
