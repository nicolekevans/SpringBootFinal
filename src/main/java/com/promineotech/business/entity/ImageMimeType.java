/**
 * 
 */
package com.promineotech.business.entity;

/**
 * @author nicoleevans
 *
 */


public enum ImageMimeType {

		IMAGE_JPEG("image/jpeg"), IMAGE_PNG("image/png");
		
		
		private String mimeType;
		
		private ImageMimeType(String mimeType) {
			this.mimeType = mimeType;
		}
		
		public String getMimeType() {
			return mimeType;
		}
		
		public static ImageMimeType fromString(String mimeType) {
			for(ImageMimeType imt : ImageMimeType.values()) {
				if(imt.getMimeType().equals(mimeType)) {
					return imt;
				}
			}
			return null;
		}
	}


