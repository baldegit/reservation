package com.balde.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface IGestionFiles {
	public void saveFile(MultipartFile file,int idE) throws Exception;
	public File getPhotoById(int id) throws Exception;
}
