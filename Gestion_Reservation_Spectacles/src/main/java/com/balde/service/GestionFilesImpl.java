package com.balde.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GestionFilesImpl implements IGestionFiles{
	
	@Value("${images.dir:}")
	private String imagesDir;
	
	@Override
	public void saveFile(MultipartFile file, int idE) throws Exception {
		// TODO Auto-generated method stub
		if(!file.isEmpty())
			file.transferTo(new File(this.imagesDir+idE));
	}
	
	@Override
	public File getPhotoById(int id) throws Exception {
		// TODO Auto-generated method stub
		return new File(this.imagesDir+id);
	}

}
