/**
 * 
 */
package com.promineotech.business.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.business.entity.Business;
import com.promineotech.business.entity.Category;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * @author nicoleevans
 *
 */
@RequestMapping("/businesses")
@OpenAPIDefinition(info = @Info(title = "Local Business Lookup Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")})
 
public interface BusinessLookupController {
	// @formatter:off
	@Operation(
			summary = "Returns a list of resturants",
			description = " Returns a resturant given a Category",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "A business is returned", 
							content = @Content(
									mediaType = "application/json", 
									schema = @Schema(implementation = Business.class))),
					@ApiResponse(
							responseCode = "400",
							description = "The request parameters are invalid",
							content = @Content(
									mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No business found with the input critera", 
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
						name = "category", 
						allowEmptyValue = false, 
						required = false, 
						description = "The business category (i.e., American)")  
			}
)
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<Business> fetchBusiness(
			@RequestParam(required=false) 
			Category category); 
	// @formatter:on
}
