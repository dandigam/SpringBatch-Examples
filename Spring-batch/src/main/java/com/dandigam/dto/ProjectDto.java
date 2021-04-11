package com.dandigam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDto {

	private Integer id;
	private String name;
	private String technology;
	private Integer cost;
	private Integer teamSize;

}
