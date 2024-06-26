package com.imsoftware.students.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class PopularSubjectDTO {

	private String popularSubject;
	private Collection<StudentDTO> students;

	public String getPopularSubject() {
		return popularSubject;
	}

	public void setPopularSubject(String popularSubject) {
		this.popularSubject = popularSubject;
	}

	public Collection<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(Collection<StudentDTO> students) {
		this.students = students;
	}

}
