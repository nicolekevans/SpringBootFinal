/**
 * 
 */
package com.promineotech.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;
import com.promineotech.business.entity.Customer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */
@Component
@Slf4j
public class DefaultCustomerLookupDao implements CustomerLookupDao{
	
	@Autowired NamedParameterJdbcTemplate jdbcTemplate;
	
		

	@Override
	public List<Customer> fetchCustomer(String firstName) {
		log.debug("dao firstName={}", firstName);
		String sql = ""
				+ "SELECT * "
				+ "FROM customers "
				+ "WHERE first_name = first_name";
		
		//@formatter:on
		Map<String,Object> params = new HashMap<>();
		params.put("first_name", firstName);
		
	
		List<Customer> test = jdbcTemplate.query(sql, params, new RowMapper<>(){
			
			@Override
			public Customer mapRow(ResultSet rs, int numRow) throws SQLException {
				//@formatter:off
				return Customer.builder()
						.customerPK(rs.getInt("customer_pk"))
						.customerId(rs.getString("customer_id"))
						.firstName(rs.getString("first_name"))
						.lastName(rs.getString("last_name"))
						.build();
				//@formatter:on
			}});
		log.debug("test={}",test);
		return test;
	
	}
	
	
}


