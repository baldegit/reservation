package com.balde.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.balde.service.IAdminService;

@Controller
@RequestMapping("/admin/show")
public class ShowController {
	
private static final int PAGES_SIZE = 5;
	
	@Autowired
	private IAdminService service;
}
