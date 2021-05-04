package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.repo.DonhangRepository;

@Controller
@RequestMapping("/donhang")
public class Donhang_Controller {
	private final DonhangRepository donhangRepo;
	
	@Autowired
	public Donhang_Controller(DonhangRepository donhangRepo) {
		this.donhangRepo = donhangRepo;
	}
	
	
}
