package com.example.students.management;

import com.example.students.management.entity.EducationalClassEntity;
import com.example.students.management.entity.LectureEntity;
import com.example.students.management.entity.MaterialEntity;
import com.example.students.management.entity.StudentEntity;
import com.example.students.management.repository.EducationalClassEntityRepository;
import com.example.students.management.repository.LectureEntityRepository;
import com.example.students.management.repository.MaterialEntityRepository;
import com.example.students.management.repository.StudentEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


@SpringBootApplication
@EnableJpaAuditing
public class StudentsManagementApplication{

	public static void main(String[] args) {
		SpringApplication.run(StudentsManagementApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner run(StudentEntityRepository studentEntityRepository,
								 EducationalClassEntityRepository educationalClassEntityRepository,
								 MaterialEntityRepository materialEntityRepository, LectureEntityRepository lectureEntityRepository){
		return args -> {
			// Create Class
			EducationalClassEntity L3Info = new EducationalClassEntity("L3 Info");
			L3Info = educationalClassEntityRepository.save(L3Info);
			// Create Materials
			String [] materials = {"App Mobile","Si","Projet","IA","DSS","Math","Physics"};
			EducationalClassEntity finalL3Info = L3Info;
			Arrays.stream(materials).forEach(material->{
				MaterialEntity materialEntity = new MaterialEntity(material);
				materialEntity.setEducationalClassEntity(finalL3Info);
				materialEntityRepository.save(materialEntity);
			});
			// Create Students
			Map<String,String> students = Map.of("Bojak","Horsman",
											"Laila","Elmasry",
											"Tomas","Shelby",
											"Joey","Tribiani");
			students.forEach((firstname, lastname) -> {
				StudentEntity studentEntity = new StudentEntity(firstname,lastname);
				studentEntity.setEducationalClassEntity(finalL3Info);
				studentEntityRepository.save(studentEntity);
			});
			// Now Lecture
			LectureEntity lectureEntity = new LectureEntity();
			lectureEntity.setEducationalClassEntity(educationalClassEntityRepository.findById(1L)
					.orElseThrow(() -> new EntityNotFoundException("class not found.")));
			lectureEntity.setMaterialEntity(materialEntityRepository.findById(1L).
					orElseThrow(()-> new EntityNotFoundException("material not found.")));
			lectureEntity = lectureEntityRepository.save(lectureEntity);
			lectureEntity.getStudents().add(studentEntityRepository.findById(1L).
					orElseThrow(()-> new EntityNotFoundException("material not found.")));
			lectureEntityRepository.save(lectureEntity);


			// Old Lecture
			LectureEntity lectureEntity2 = new LectureEntity();
			lectureEntity2.setEducationalClassEntity(educationalClassEntityRepository.findById(1L)
					.orElseThrow(() -> new EntityNotFoundException("class not found.")));
			lectureEntity2.setMaterialEntity(materialEntityRepository.findById(5L).
					orElseThrow(()-> new EntityNotFoundException("material not found.")));
			lectureEntity2 = lectureEntityRepository.save(lectureEntity2);
			lectureEntity2.setStartDateTime(LocalDateTime.now().minusMinutes(60));
			lectureEntityRepository.save(lectureEntity2);
		};


	}
}
