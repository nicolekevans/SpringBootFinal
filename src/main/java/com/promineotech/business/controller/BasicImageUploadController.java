/**
 * 
 */
package com.promineotech.business.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.promineotech.business.entity.Image;
import com.promineotech.business.service.ImageUploadService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */

@RestController
@Slf4j
public class BasicImageUploadController implements ImageUploadController {
	@Autowired
	private ImageUploadService imageUploadService;
	
	@Override
	public String uploadImage(MultipartFile image, int businessFK) {
		
		String imageId = imageUploadService.uploadImage(image, businessFK);
		String json = "{\"imageId\":\"" + imageId + "\"}";
		return json;
	}

	@Override
	public ResponseEntity<byte[]> retrieveImage(String imageId) {
		Image image = imageUploadService.retrieveImage(imageId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", image.getMimeType().getMimeType());
		headers.add("Content-Length", Integer.toString(image.getData().length));
		return ResponseEntity.ok().headers(headers).body(image.getData());
		
		
	}

}
