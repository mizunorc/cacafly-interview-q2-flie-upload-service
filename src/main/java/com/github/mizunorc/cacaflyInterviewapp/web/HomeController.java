package com.github.mizunorc.cacaflyInterviewapp.web;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.mizunorc.cacaflyInterviewapp.adapter.GoogleDriveService;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home(Principal principal,Model model) throws IOException{
        Drive service = GoogleDriveService.get(principal);
        FileList result = service.files().list().setPageSize(50).setFields("nextPageToken, files(id, name)").execute();
        List<File> files = result.getFiles();
        model.addAttribute("fileNames",this.toStringList(files));
        
		return "home";
	}
	
	@RequestMapping("/loginroot")
	public String login(){
		return "login";
	}
	
	private List<String> toStringList(List<File> files){
		List<String> fileNames = new ArrayList<>();
		if(files!=null){
			for(File file:files){
				fileNames.add(file.getName());
			}
		}
		return fileNames;
	}
	
}
