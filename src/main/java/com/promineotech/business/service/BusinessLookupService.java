/**
 * 
 */
package com.promineotech.business.service;

import java.util.List;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;

/**
 * @author nicoleevans
 *
 */
public interface BusinessLookupService {

	/**
	 * @param category
	 * @return
	 */
	List<Business> fetchBusinesses(Category category);



}
  