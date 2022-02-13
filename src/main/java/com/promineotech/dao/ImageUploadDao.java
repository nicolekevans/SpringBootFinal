/**
 * 
 */
package com.promineotech.dao;

import java.util.Optional;

import com.promineotech.business.entity.Image;

/**
 * @author nicoleevans
 *
 */
public interface ImageUploadDao {

	/**
	 * @param image
	 */
	void saveImage(Image image);

	/**
	 * @param imageId
	 * @return
	 */
	Optional<Image> retrieveImage(String imageId);


	

}
