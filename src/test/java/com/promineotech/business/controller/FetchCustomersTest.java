/**
 * 
 */
package com.promineotech.business.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.business.controller.support.FetchBusinessTestSupport;
import com.promineotech.business.controller.support.FetchCustomerTestSupport;
import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;
import com.promineotech.business.entity.Customer;

import lombok.extern.slf4j.Slf4j;
/**
 * @author nicoleevans
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
		scripts = {"classpath:flyway/migrations/localBusinessSchema.sql",
		"classpath:flyway/migrations/localBusinessData.sql"},
		config = @SqlConfig(encoding = "utf-8"))
@Slf4j
class FetchCustomersTest extends FetchCustomerTestSupport {

	@Test
	void testThatAListofBusinessesIsReturnedWhenAValidCategoryIsSupplied() {
		//Given: a valid category and URI
		
		String firstName = "NICOLE";
		
		String uri = String.format("%s?first_name=%s", getBaseUriForCustomer(), firstName);
		log.debug("uri={}",uri);
		//When: a connection is made to the URI
		ResponseEntity<List<Customer>> response = 
				getRestTemplate().exchange(uri, HttpMethod.GET, null,
						new ParameterizedTypeReference<>() {});
		log.debug("response={}", response);
		//Then: a success (OK - 200) is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// And: the actual list returned is the same as the expected list
		List<Customer> actual = response.getBody();
		log.debug(uri);
		List<Customer> expected = customerExpected();
		
		
		assertThat(actual).isEqualTo(expected);
	}
	
	



	

}
