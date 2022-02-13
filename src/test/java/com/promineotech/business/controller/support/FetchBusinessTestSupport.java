/**
 * 
 */
package com.promineotech.business.controller.support;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;

/**
 * @author nicoleevans
 *
 */
public class FetchBusinessTestSupport extends BaseTest{

	protected List<Business> businessesExpected() {
		List <Business> list = new LinkedList<>();
		
		// formatter:off
		list.add(Business.builder()
				.businessId("BISTRO 1100")
				.address("1103 E FRONT AVE, BISMARCK, ND 58501")
				.phone("(701)224-8800")
				.category(Category.AMERICAN)
				.build());
		
		list.add(Business.builder()
				.businessId("ALE WORKS")
				.address("1100 CANADA AVE, BISMARCK ND 58503")
				.phone("(701)751-8800")
				.category(Category.AMERICAN)
				.build());	
		// formatter:on
		
		Collections.sort(list);
		return list; 
		
	}
}
