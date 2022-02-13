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
public class Business implements Comparable<Business> {
	
	private int businessPk;
	private String businessId;
	private String address;
	private String phone;
	private Category category;
	
	
	@JsonIgnore
	public int getBusinessPk() {
		return businessPk;
	}

	@Override
	public int compareTo(Business that) {
		//@formatter off
		return Comparator
				.comparing(Business::getBusinessId)
				.thenComparing(Business::getCategory)
				.compare(this, that);
		//@formatter on
	}
	
}
