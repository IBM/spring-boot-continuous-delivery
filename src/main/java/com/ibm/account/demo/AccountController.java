package com.ibm.account.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;

@Controller
public class AccountController {
	
	@Autowired
	Database account;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(path="/account", method=RequestMethod.POST)
	public String createAccount(Model model, @RequestParam(value="name", required=false, defaultValue="everyone") String name) {
		Response response = account.save(new Account(name));
		model.addAttribute("name", name);
		model.addAttribute("id", response.getId());
		return "account";
	}
}
