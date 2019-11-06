package com.example.usermgmt;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ModelMapper modelMapper;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/user")
	public UserProfile getUser() {
		AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserProfile userProfile = modelMapper.map(appUser, UserProfile.class);
		return userProfile;
	}

	@GetMapping("/admin")
	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PreAuthorize("hasRole('ADMIN')")
	public UserProfile getAdmin() {
		AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserProfile userProfile = modelMapper.map(appUser, UserProfile.class);
		return userProfile;
	}

}
