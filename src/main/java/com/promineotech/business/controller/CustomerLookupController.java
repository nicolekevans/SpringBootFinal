/**
 * 
 */
package com.promineotech.business.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;
import com.promineotech.business.entity.Customer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * @author nicoleevans
 *
 */
@RequestMapping("/customers")
@OpenAPIDefinition(info = @Info(title = "Local Customer Lookup Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")})
 
public interface CustomerLookupController {
	// @formatter:off
	@Operation(
			summary = "Returns a list of Customer",
			description = " Returns a list of Customer when given a first name",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "A list of Customer is returned", 
							content = @Content(
									mediaType = "application/json", 
									schema = @Schema(implementation = Customer.class))),
					@ApiResponse(
							responseCode = "400",
							description = "The request parameters are invalid",
							content = @Content(
									mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No customers found with the input critera", 
							content = @Content(
									mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500",
							description = "An unplanned error occured",
							content = @Content(
									mediaType = "application/json"))
			},
			parameters = {
				@Parameter(
						name = "first_name", 
						allowEmptyValue = false, 
						required = false, 
						description = "the customer first name, example NICOLE ")  
			}
)
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<Customer> fetchCustomer(
			@RequestParam(required=false) 
			String first_name); 
}
