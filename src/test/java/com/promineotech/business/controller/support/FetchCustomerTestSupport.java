/**
 * 
 */
package com.promineotech.business.controller.support;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



import com.promineotech.business.entity.Customer;

/**
 * @author nicoleevans
 *
 */

public class FetchCustomerTestSupport extends BaseTest{

	protected List<Customer> customerExpected() {
		List <Customer> list = new LinkedList<>();
			
			// formatter:off
		list.add(Customer.builder()
					.customerId("EVANS_NICOLE")
					.firstName("NICOLE")
					.lastName("EVANS")
					.build());
		list.add(Customer.builder()
				.customerId("DOWLING_NICOLE")
				.firstName("NICOLE")
				.lastName("DOWLING")
				.build());
			// formatter:on
		Collections.sort(list);
		return list;
			
			
		
		
	}
}
