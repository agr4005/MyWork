package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jo {
	
	@Id
	private int jno;
	private String jname;
	private String captain;
	private String project;
	private String slogan;
	
	@Transient
	private String cname;	//필요시 사용
} //class
