package com.promineotech.business.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.promineotech.business.entity.Image;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author nicoleevans
 *
 */
@Validated
@RequestMapping("/businesses")
@OpenAPIDefinition(info = @Info(title = "Business Image Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")})
 
public interface ImageUploadController {
	// @formatter:off
	
	@Operation(
			summary = "Post an image to a review",
			description = "Uploads Image to a review",
			responses = {
					@ApiResponse(
							responseCode = "201", 
							description = "The image was uploaded", 
							content = @Content(
									mediaType = "application/json", 
									schema = @Schema(implementation = Image.class))),
					@ApiResponse(
							responseCode = "400",
							description = "The request parameters are invalid",
							content = @Content(
									mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No image was found with the input critera", 
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
						name = "Image", 
						required = true, 
						description = "The image as JSON")  
			})
	@PostMapping("/{businessFK}/image")
	@ResponseStatus(code = HttpStatus.CREATED)
	String uploadImage(@RequestParam("image") MultipartFile image, 
			@PathVariable int businessFK);
	@Operation(
			summary = "View an image on a",
			description = "Gets Image From a review",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Image found", 
							content = @Content(
									mediaType = "application/json", 
									schema = @Schema(implementation = Image.class))),
					@ApiResponse(
							responseCode = "400",
							description = "The request parameters are invalid",
							content = @Content(
									mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No image was found with the input critera", 
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
						name = "Image", 
						required = true, 
						description = "The image as JSON")  
			})
	@GetMapping("/image/{imageId}")
	ResponseEntity<byte[]> retrieveImage(@PathVariable String imageId);
}
	