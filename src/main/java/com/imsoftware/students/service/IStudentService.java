package com.imsoftware.students.service;

import java.util.Collection;

import com.imsoftware.students.domain.PopularSubjectDTO;
import com.imsoftware.students.domain.StudentDTO;

public interface IStudentService {
	Collection<StudentDTO> findAll();

	PopularSubjectDTO findAllAndShowIfHaveAPopularSubject();

}
