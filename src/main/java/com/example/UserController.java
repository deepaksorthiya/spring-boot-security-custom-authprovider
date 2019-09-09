package com.example;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author deepakk
 * @date Sep 9, 2019
 */
@RestController
public class UserController {

	@GetMapping("/user")
	public AppUser getUser() {
		return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@GetMapping("/admin")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PreAuthorize("hasRole('ADMIN')")
	public AppUser getAdmin() {
		return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
