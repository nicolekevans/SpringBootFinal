/**
 * 
 */
package com.promineotech.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.promineotech.business.entity.Image;
import com.promineotech.business.entity.ImageMimeType;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */
@Component
@Slf4j
public class DefaultImageUploadDao implements ImageUploadDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void saveImage(Image image) {
		
		String sql = ""
				+ "INSERT INTO images("
				+ "image_id, business_fk, width, height, mime_type, name, data"
				+ ") VALUES ("
				+ ":image_id, :business_fk, :width, :height, :mime_type, :name, :data"
				+ ")";
		
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("image_id", image.getImageId());
		params.put("business_fk", image.getBusinessFK());
		params.put("width", image.getWidth());
		params.put("height", image.getHeight());
		params.put("mime_type", image.getMimeType().getMimeType());
		params.put("name", image.getName());
		params.put("data", image.getData());
		jdbcTemplate.update(sql, params); 
		
		
	}

	@Override
	public Optional<Image> retrieveImage(String imageId) {
		String sql = ""
				+ "SELECT * FROM images "
				+ "WHERE image_id = :image_id";
		Map<String,Object> params = new HashMap<>();
		params.put("image_id", imageId);
		
		return jdbcTemplate.query(sql, params, new ResultSetExtractor<>(){
				@Override
				public Optional<Image> extractData(ResultSet rs)
						throws SQLException{
					if(rs.next()) {
						//@formatter:off
						return Optional.of(Image.builder()
								.imagePK(rs.getInt("image_pk"))
								.imageId(rs.getString("image_id"))
								.businessFK(rs.getInt("business_fk"))
								.width(rs.getInt("width"))
								.height(rs.getInt("height"))
								.mimeType(ImageMimeType.fromString(rs.getString("mime_type")))
								.name(rs.getString("name"))
								.data(rs.getBytes("data"))
								.build());
						//@formatter:on
					}
					return Optional.empty();
				}});	


	
	}

}
