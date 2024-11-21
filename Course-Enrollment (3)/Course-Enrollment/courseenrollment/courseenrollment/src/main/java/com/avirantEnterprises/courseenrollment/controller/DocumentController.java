package com.avirantEnterprises.courseenrollment.controller;

import com.avirantEnterprises.courseenrollment.model.Document;
import com.avirantEnterprises.courseenrollment.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    private final String UPLOAD_DIR = "upload-dir/";

    // Show the form (use a unique URL to avoid conflicts)
    @GetMapping("/document/form1")
    public String showForm() {
        return "form1"; // Returns form1.html
    }

    // Handle file upload
    @PostMapping("/document/upload")
    public String handleFileUpload(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("document_file") MultipartFile file, // Match HTML input name
            Model model) {

        try {
            // Ensure upload directory exists
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save file to the server
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            // Save metadata to the database
            Document document = new Document();
            document.setName(name);
            document.setDescription(description);
            document.setFilePath(filePath.toString());
            documentRepository.save(document);

            model.addAttribute("message", "File uploaded successfully!");
        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
            e.printStackTrace();  // Log the error
        }
        model.addAttribute("uploadedFilePath", "/document/view/" + file.getOriginalFilename());

        return "form1"; // Return the same view with the success/error message
    }
}
