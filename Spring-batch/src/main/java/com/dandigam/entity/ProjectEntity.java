package com.dandigam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pId;
	private Integer id;
	private String name;
	private String technology;
	private Integer cost;
	private Integer teamSize;
	public ProjectEntity(Integer id, String name, String technology, Integer cost, Integer teamSize) {
		super();
		this.id = id;
		this.name = name;
		this.technology = technology;
		this.cost = cost;
		this.teamSize = teamSize;
	}
}