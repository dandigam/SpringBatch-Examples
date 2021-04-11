package com.dandigam.controller;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dandigam.batchlauncher.FileReaderBatchJobLauncher;

@RestController
public class FileReaderController {

	@Autowired
	private FileReaderBatchJobLauncher fileReaderBatchJobLauncher;

	@GetMapping
	public ResponseEntity<String> fileReader() {
		int count =0, skip = 0;
		JobParameters parameter = new JobParametersBuilder().addString("fileName", "Test").toJobParameters();

		try {
			JobExecution execution = fileReaderBatchJobLauncher.jobRun(parameter);
			for (StepExecution stepExecution : execution.getStepExecutions()) {
				count += stepExecution.getWriteCount();
				skip += stepExecution.getSkipCount();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("File successfully processed total write count " +count+ " and total skiped count "+skip ,HttpStatus.OK);

	}

}
