/**
 * 
 */
package com.promineotech.business.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author nicoleevans
 *
 */
@Data
@Builder
public class Image {

	private int imagePK;
	private String imageId;
	private int businessFK;
	private int width;
	private int height;
	private ImageMimeType mimeType;
	private String name;
	private byte[] data;
	
	
}


	


