/**
 * 
 */
package com.promineotech.business.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;
import com.promineotech.dao.BusinessLookupDao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */
@Service
@Slf4j
public class DefaultBusinessLookupService implements BusinessLookupService {

	@Autowired
	private BusinessLookupDao businessLookupDao;
	
	@Override
	public List<Business> fetchBusinesses(Category category) {
		log.info("The fetchBusinesses method was called with category={}", category);
		 List<Business> businesses = businessLookupDao.fetchBusinesses(category);
		 
		 Collections.sort(businesses);
		 return businesses;
	}

	
	}
	
	


