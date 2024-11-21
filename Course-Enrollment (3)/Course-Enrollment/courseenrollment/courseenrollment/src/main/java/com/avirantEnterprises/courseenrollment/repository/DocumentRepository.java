package com.avirantEnterprises.courseenrollment.repository;

import com.avirantEnterprises.courseenrollment.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for performing database operations on the Document entity
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
