/**
 * 
 */
package com.promineotech.business.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.promineotech.business.entity.Image;
import com.promineotech.business.entity.ImageMimeType;
import com.promineotech.dao.ImageUploadDao;

/**
 * @author nicoleevans
 *
 */
@Service
public class DefaultImageUploadService implements ImageUploadService {
	@Autowired
	private ImageUploadDao imageUploadDao;
	
	@Transactional
	@Override
	public String uploadImage(MultipartFile file,int businessFK) {
		
		String imageId = UUID.randomUUID().toString();
		
		try(InputStream inputStream = file.getInputStream()){
			 BufferedImage bufferedImage = ImageIO.read(inputStream);
			 
			 //@formatter:off
			 Image image = Image.builder()
					 .businessFK(businessFK)
					 .imageId(imageId)
					 .width(bufferedImage.getWidth())
					 .height(bufferedImage.getHeight())
					 .mimeType(ImageMimeType.IMAGE_JPEG)
					 .name(file.getOriginalFilename())
					 .data(toByteArray(bufferedImage, "jpeg"))
					 .build();
			 imageUploadDao.saveImage(image);
			 return imageId;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new UncheckedIOException(e);
		}
		
	}

	/**
	 * @param bufferedImage
	 * @param string
	 * @return
	 */
	private byte[] toByteArray(BufferedImage bufferedImage, String renderType) {
		
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			if(!ImageIO.write(bufferedImage, renderType, baos)) {
				throw new RuntimeException("Could not write to image buffer");
			}
			return baos.toByteArray();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	@Transactional(readOnly = true)
	@Override
	public Image retrieveImage(String imageId) {
		
		return imageUploadDao.retrieveImage(imageId)
				.orElseThrow(()-> new NoSuchElementException
						("Could not find an image with ID=" + imageId));
						
	}

}
