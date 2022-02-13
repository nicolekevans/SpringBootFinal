/**
 * 
 */
package com.promineotech.dao;

import java.util.List;

import com.promineotech.business.entity.Customer;

/**
 * @author nicoleevans
 *
 */
public interface CustomerLookupDao {

	/**
	 * @param firstName
	 * @return
	 */
	List<Customer> fetchCustomer(String firstName);

}
