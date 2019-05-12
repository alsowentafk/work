package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.properties.FileStorageProperties;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			log.error("Could not store file {}. Please try again! {}", fileName, ex);
			throw new RuntimeException(ex.getMessage());
		}
	}

	public Resource loadFileAsResourse(String fileName) {
		Resource resource = null;
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			}
		} catch (MalformedURLException e) {
			log.error("Error {} {}", fileName, e);
			throw new RuntimeException(e.getMessage());
		}
		log.error("File not found " + fileName);
		return resource;
	}

}
