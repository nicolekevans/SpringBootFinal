/**
 * 
 */
package com.promineotech.dao;

import java.util.List;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;

/**
 * @author nicoleevans
 *
 */
public interface BusinessLookupDao {

	/**
	 * @param category
	 * @return
	 */
	
	List<Business> fetchBusinesses(Category category);

}
