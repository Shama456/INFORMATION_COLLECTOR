package com.avirantEnterprises.courseenrollment.controller;

import com.avirantEnterprises.courseenrollment.model.GovtDocument;
import com.avirantEnterprises.courseenrollment.repository.GovtDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/govt")
public class GovtDocumentController {

    @Autowired
    private GovtDocumentRepository GovtDocumentRepository;

    // Handle POST request for submitting PAN and Aadhar card documents
    @PostMapping("/submit")
    public ResponseEntity<String> submitGovtDocuments(@RequestParam("name") String name,
                                                      @RequestParam("date") String date,
                                                      @RequestParam("pan_card") MultipartFile panCardFile,
                                                      @RequestParam("aadhar_card") MultipartFile aadharCardFile) {
        try {
            // Save PAN and Aadhar files to a folder
            String panCardFilePath = saveFile(panCardFile, "pan_cards");
            String aadharCardFilePath = saveFile(aadharCardFile, "aadhar_cards");

            // Save details to the database
            GovtDocument govtDocument = new GovtDocument();
            govtDocument.setName(name);
            govtDocument.setDate(date);
            govtDocument.setPanCardFilePath(panCardFilePath);
            govtDocument.setAadharCardFilePath(aadharCardFilePath);

            GovtDocumentRepository.save(govtDocument);

            return ResponseEntity.status(HttpStatus.CREATED).body("Government documents submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting documents: " + e.getMessage());
        }
    }

    // Method to save file to the server
    private String saveFile(MultipartFile file, String folder) throws IOException {
        // Create a folder if not exists
        String uploadDir = "uploads/" + folder;
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // Save the file with original name
        String filePath = uploadDir + "/" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return filePath;
    }
}
