/**
 * 
 */
package com.promineotech.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;
import com.promineotech.business.service.BusinessLookupService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */
@RestController
@Slf4j
public class BasicBusinessLookupController implements BusinessLookupController {

	@Autowired
	private BusinessLookupService businessLookupService;
	
	@Override
	public List<Business> fetchBusiness(Category category) {
		log.debug("category={}", category);
		return businessLookupService.fetchBusinesses(category);
	}
	

}
