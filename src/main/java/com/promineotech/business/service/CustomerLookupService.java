/**
 * 
 */
package com.promineotech.business.service;

import java.util.List;

import com.promineotech.business.entity.Customer;

/**
 * @author nicoleevans
 *
 */
public interface CustomerLookupService {

	/**
	 * @param customer_id
	 * @return
	 */
	
	List<Customer> fetchCustomer(String firstName);
	

}
