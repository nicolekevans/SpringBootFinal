/**
 * 
 */
package com.promineotech.business.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;
import com.promineotech.business.entity.Customer;
import com.promineotech.business.entity.Image;
import com.promineotech.dao.BusinessLookupDao;
import com.promineotech.dao.CustomerLookupDao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */
@Service
@Slf4j
public class DefaultCustomerLookupService implements CustomerLookupService {

	@Autowired
	private CustomerLookupDao customerLookupDao;
	
	
	

	@Transactional
	@Override
	public List<Customer> fetchCustomer(String firstName) {
		log.info("The fetchCustomer method was called with firstName={}", firstName);
		 List<Customer> customers = customerLookupDao.fetchCustomer(firstName);
		 
		 
		 return customers;
	}
}
	
	


