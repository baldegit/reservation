package com.balde.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;

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
		try {
			if(file.isEmpty()) throw new Exception("The file is Empty");
			
//			File directory = new File(this.imagesDir);
//			
//			System.out.println("@@@1" +directory.isDirectory());
//			if(!directory.isDirectory()) {
//				System.out.println("@@@2" +directory.isDirectory());
//				directory.mkdirs();
//				System.out.println("@@@3" +directory.isDirectory());
//			}
//			
//			
//			System.out.println(this.imagesDir+"  "+new File(this.imagesDir).isDirectory());
			
			System.out.println("this.imagesDir+idE "+this.imagesDir+idE);
			
			file.transferTo(new File(this.imagesDir+idE));
			
			System.out.println("this.imagesDir+idE "+this.imagesDir+idE);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
	}
	
	@Override
	public File getPhotoById(int id) throws Exception {
		// TODO Auto-generated method stub
		return new File(this.imagesDir+id);
	}

}
