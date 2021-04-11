package com.dandigam.batchlauncher;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dandigam.jobconfig.FileReaderJobConfig;

@Component
public class FileReaderBatchJobLauncher {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private FileReaderJobConfig fileReaderJobConfig;
	
	public JobExecution jobRun(JobParameters parameter) throws Exception {
		return jobLauncher.run(fileReaderJobConfig.loadInitialFile(), parameter);
		
	}

}
