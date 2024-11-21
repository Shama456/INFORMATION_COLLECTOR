package com.avirantEnterprises.courseenrollment.controller;

import com.avirantEnterprises.courseenrollment.model.ESignDocument;
import com.avirantEnterprises.courseenrollment.repository.ESignDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

@RestController
@RequestMapping("/e-sign")
public class ESignController {

    @Autowired
    private ESignDocumentRepository eSignDocumentRepository;

    // Handle POST request for submitting the e-signature document
    @PostMapping("/submit")
    public ResponseEntity<String> submitESignature(@RequestParam("name") String name,
                                                   @RequestParam("date") String date,
                                                   @RequestParam("signature_file") MultipartFile file) {
        // Logic for saving the document, handling the file, etc.
        // Save the file to a folder
        String uploadDir = "uploads/";  // Directory where you want to store the file
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it does not exist
        }

        // Save the uploaded file to the designated folder
        Path path = Path.of(uploadDir + file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }

        // Save the document record to the database
        ESignDocument eSignDocument = new ESignDocument();
        eSignDocument.setName(name);
        eSignDocument.setDate(LocalDate.parse(date));
        eSignDocument.setFilePath(path.toString());

        eSignDocumentRepository.save(eSignDocument);  // Save the document to the DB

        // Return success response
        return ResponseEntity.status(HttpStatus.CREATED).body("E-signature submitted successfully!");
    }
}
