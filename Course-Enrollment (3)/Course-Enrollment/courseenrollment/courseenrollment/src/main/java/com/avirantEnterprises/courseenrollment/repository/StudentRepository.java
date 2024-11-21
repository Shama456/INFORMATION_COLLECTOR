package com.avirantEnterprises.courseenrollment.repository;

import com.avirantEnterprises.courseenrollment.model.Certification;
import com.avirantEnterprises.courseenrollment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
