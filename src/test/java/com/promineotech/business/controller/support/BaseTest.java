/**
 * 
 */
package com.promineotech.business.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import lombok.Getter;

/**
 * @author nicoleevans
 *
 */
public class BaseTest {
	@LocalServerPort
	private int serverPort;
	
	@Autowired
	@Getter
	private TestRestTemplate restTemplate;
	
	protected String getBaseUriForBusinesses() {
		return String.format("http://localhost:%d/businesses", serverPort);
	}
	
	protected String getBaseUriForImageUpload() {
		return String.format("http://localhost:%d/images", serverPort);
	}
	
	protected String getBaseUriForCustomer() {
		return String.format("http://localhost:%d/customers", serverPort);
	}
}
