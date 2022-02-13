/**
 * 
 */
package com.promineotech.business.service;

import org.springframework.web.multipart.MultipartFile;

import com.promineotech.business.entity.Image;



/**
 * @author nicoleevans
 *
 */
public interface ImageUploadService {

	/**
	 * @param image
	 * @param businessId
	 * @return
	 */
	String uploadImage(MultipartFile image, int businessFK);

	/**
	 * @param imageId
	 * @return
	 */
	Image retrieveImage(String imageId);

}
