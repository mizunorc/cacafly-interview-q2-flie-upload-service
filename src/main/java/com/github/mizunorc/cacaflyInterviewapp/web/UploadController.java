package com.github.mizunorc.cacaflyInterviewapp.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.mizunorc.cacaflyInterviewapp.adapter.GoogleDriveService;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

@Controller
public class UploadController {
    
    private java.io.File convert(MultipartFile file) throws IOException {
    	java.io.File convFile = new java.io.File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public  String uploadMultipleFiles(Principal principal,@RequestParam("file") MultipartFile[] files) throws IOException {
        Drive driveService = GoogleDriveService.get(principal);
        for(MultipartFile multipartFile:files){
        	File fileMetadata = new File();
        	fileMetadata.setName(multipartFile.getOriginalFilename());
        	java.io.File file = this.convert(multipartFile);
        	FileContent mediaContent = new FileContent("image/*", this.convert(multipartFile));
        	driveService.files().create(fileMetadata, mediaContent).setFields("id").execute();
        	file.delete();
        }
        return "redirect:/";
	}

}
