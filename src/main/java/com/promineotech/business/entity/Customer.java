/**
 * 
 */
package com.promineotech.business.entity;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nicoleevans
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Comparable<Customer> {
	
	private int customerPK;
	private String customerId;
	private String firstName;
	private String lastName;
	
	@JsonIgnore
	public int getCustomerPK() {
		return customerPK;
	}
	
	@Override
	public int compareTo(Customer that) {
		//@formatter off
		return Comparator
				.comparing(Customer::getCustomerId)
				.thenComparing(Customer::getFirstName)
				.thenComparing(Customer::getLastName)
				.compare(this, that);
		//@formatter on
	}
	
}
