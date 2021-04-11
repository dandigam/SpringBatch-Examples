package com.dandigam.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dandigam.dto.ProjectDto;
import com.dandigam.entity.ProjectEntity;
import com.dandigam.repository.FileReaderRepository;

@Component
public class LoadFileWriter implements ItemWriter<ProjectDto> {

	@Autowired
	private FileReaderRepository repository;

	@Override
	public void write(List<? extends ProjectDto> items) {

		try {
			for (ProjectDto projectDto : items) {
				repository.save(new ProjectEntity(projectDto.getId(), projectDto.getName(), projectDto.getTechnology(),
						projectDto.getCost(), projectDto.getTeamSize()));
			}

		} catch (Exception e) {

		}

	}

}
