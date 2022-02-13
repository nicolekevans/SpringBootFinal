/**
 * 
 */
package com.promineotech.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.promineotech.ComponentScanMarker;

/**
 * @author nicoleevans
 *
 */

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class BusinessLookup {
	
	public static void main(String[] args) {
		
		SpringApplication.run(BusinessLookup.class, args);
	}
	

}
