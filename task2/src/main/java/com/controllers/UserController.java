package com.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.models.UserDTO;
import com.responses.UploadFileResponse;
import com.services.FileStorageService;
import com.services.UserService;
import com.transoformers.UserUserDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v3")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private UserUserDTO transformer;

	@PostMapping
	public void save(@RequestBody UserDTO userDTO) {
		userService.save(transformer.ConvertToUser(userDTO));
	}

	@GetMapping("{id}")
	public UserDTO getById(@PathVariable Long id) {
		return transformer.ConvertToUserDTO(userService.getbyID(id));
	}

	@PutMapping
	public void update(@RequestBody UserDTO userDTO) {
		userService.update(transformer.ConvertToUser(userDTO));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		log.info("File uploaded succsessfulv {}", fileName);
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request)
			throws MalformedURLException {

		Resource resource = fileStorageService.loadFileAsResourse(fileName);

		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			log.info("Could not determine file type."+fileName+ex);
			throw new RuntimeException(ex.getMessage());
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		log.info("File downloaded succsessful"+fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
