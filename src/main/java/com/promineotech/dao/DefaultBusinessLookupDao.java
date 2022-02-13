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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nicoleevans
 *
 */
@Component
@Slf4j
public class DefaultBusinessLookupDao implements BusinessLookupDao{

	@Autowired NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Business> fetchBusinesses(Category category) {
		log.debug("DAO: category={}", category);
		
		
		//@formatter:off 
		String sql = ""
				+ "SELECT * "
				+ "FROM local_businesses "
				+ "WHERE category = :category";
		//@formatter:on
		
		Map<String,Object> params = new HashMap<>();
		params.put("category", category.toString());
		
	
		List<Business> test = jdbcTemplate.query(sql, params, new RowMapper<>(){
			
			@Override
			public Business mapRow(ResultSet rs, int rowNum) throws SQLException {
				//@formatter:off
				return Business.builder()
						.businessPk(rs.getInt("business_pk"))
						.businessId(rs.getString("business_id"))
						.address(rs.getString("address"))
						.phone(rs.getString("phone"))
						.category(Category.valueOf(rs.getString("category")))
						.build();
				
				//@formatter:on
			}});
		
		return test;
	}
	
	
}


