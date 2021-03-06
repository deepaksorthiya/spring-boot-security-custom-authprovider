package com.example.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.usermgmt.AppUser;

/**
 * @author Deepak Katariya
 * @apiNote Dispatcher for home.html
 */
@Controller
public class HomeController {

	@RequestMapping("/home")
	public String home(Model model) {
		AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("appUser", appUser);
		return "/home/home";
	}
}
