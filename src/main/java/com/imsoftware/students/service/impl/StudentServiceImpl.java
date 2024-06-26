package com.imsoftware.students.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.imsoftware.students.repository.StudentRepository;
import org.springframework.stereotype.Service;

import com.imsoftware.students.domain.PopularSubjectDTO;
import com.imsoftware.students.domain.StudentDTO;
import com.imsoftware.students.entity.Student;
import com.imsoftware.students.entity.Subject;
import com.imsoftware.students.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Collection<StudentDTO> findAll() {
		return studentRepository.findAll().stream().map(new Function<Student, StudentDTO>() {
			@Override
			public StudentDTO apply(Student student) {
				List<String> programmingLanguagesKnowAbout = student.getSubjects().stream()
						.map(pl -> new String(pl.getName())).collect(Collectors.toList());
				return new StudentDTO(student.getName(), programmingLanguagesKnowAbout);
			}

		}).collect(Collectors.toList());

	}

	@Override
	public PopularSubjectDTO findAllAndShowIfHaveAPopularSubject() {
		/*
		 * Obtener la lista de todos los estudiantes e indicar la materia más concurrida
		 * existentes en la BD e indicar si el estudiante cursa o no la materia más
		 * concurrida registrado en la BD.
		 */
		PopularSubjectDTO popularSubjectDTO = new PopularSubjectDTO();
		Collection<Student> collStudent = this.studentRepository.findAll();

		String popularSubject = collStudent.stream().flatMap(student -> student.getSubjects().stream())
				.collect(Collectors.groupingBy(Subject::getName, Collectors.counting())).entrySet().stream()
				.max(Map.Entry.comparingByValue()).get().getKey();

		popularSubjectDTO.setPopularSubject(popularSubject);
		popularSubjectDTO.setStudents(collStudent.stream()
				.map(student -> new StudentDTO(student.getName(),
						student.getSubjects().stream().anyMatch(subject -> subject.getName().equals(popularSubject))))
				.collect(Collectors.toList()));

		return popularSubjectDTO;
	}

}
