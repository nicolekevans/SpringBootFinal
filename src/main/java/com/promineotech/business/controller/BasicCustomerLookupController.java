/**
 * 
 */
package com.promineotech.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;
import com.promineotech.business.entity.Customer;
import com.promineotech.business.entity.Image;
import com.promineotech.business.service.BusinessLookupService;
import com.promineotech.business.service.CustomerLookupService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */
@RestController
@Slf4j
public class BasicCustomerLookupController implements CustomerLookupController {

	@Autowired
	private CustomerLookupService customerLookupService;
	
	

	@Override
	public List<Customer> fetchCustomer(String firstName1) {
		String firstName = "NICOLE";
		log.debug("Controller firstName={}", firstName);
		return customerLookupService.fetchCustomer(firstName);
	}
	

}
