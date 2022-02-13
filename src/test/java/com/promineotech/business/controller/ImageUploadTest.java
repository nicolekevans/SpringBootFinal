/**
 * 
 */
package com.promineotech.business.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(
		scripts = {"classpath:flyway/migrations/localBusinessSchema.sql",
		"classpath:flyway/migrations/localBusinessData.sql"},
		config = @SqlConfig(encoding = "utf-8"))
@Slf4j
class ImageUploadTest {
	

	/**
	 * 
	 */
	private static final String IMAGE = "aleworks.jpg";
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void testThatTheServerCorrectlyReceivesAnImageAndReturnsAnOKResponse() throws Exception {
		
		String json = assertImageUpload();
		String imageId = extractImageId(json);
		assertImageRetrieval(imageId);
		
}

	/**
	 * @param imageId
	 * @throws Exception 
	 */
	private void assertImageRetrieval(String imageId) throws Exception {
		//@formatter:off
		mockMvc
		.perform(get("/businesses/image/"+ imageId))
		.andExpect(status().isOk())
		.andExpect(header().string("Content-Type", "image/jpeg"));
		
		//@formatter:on
		log.debug("assertImageRetrieval");
	}

	/**
	 * @param json
	 * @return
	 */
	private String extractImageId(String json) {
		String[] parts = json.substring(1, json.length()-1).split(":");
		return parts[1].substring(1, parts[1].length()-1);
	}

	/**
	 * @throws IOException
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	protected String assertImageUpload() throws IOException, Exception, UnsupportedEncodingException {
		int numRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "images");
		Resource image = new ClassPathResource(IMAGE);
		
		if(!image.exists()){
			fail("Could not find resource %s", IMAGE);
		}
		
		try(InputStream inputStream = image.getInputStream()){
			MockMultipartFile file = new MockMultipartFile("image", IMAGE,
					MediaType.TEXT_PLAIN_VALUE, inputStream);
		
		
		//@formatter:off
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.multipart("/businesses/5/image")
						.file(file))
						.andExpect(status().is(201))
						.andReturn();
		//@formatter:on

		String content = result.getResponse().getContentAsString();
		assertThat(content).isNotEmpty();
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "images"))
			.isEqualTo(numRows+1);
		
		return content;
	}
	}

}

