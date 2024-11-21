package com.avirantEnterprises.courseenrollment.repository;

import com.avirantEnterprises.courseenrollment.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    // You can define custom queries here if needed
}
