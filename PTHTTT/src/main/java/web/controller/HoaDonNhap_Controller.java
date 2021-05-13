package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.entity.HoaDonNhap;
import web.repo.HoaDonNhapRepository;

@Controller
@RequestMapping("/hdn")
public class HoaDonNhap_Controller {
	private final HoaDonNhapRepository hdnRepo;

	@Autowired
	public HoaDonNhap_Controller(HoaDonNhapRepository hdnRepo) {
		this.hdnRepo = hdnRepo;
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("hdn", new HoaDonNhap());
		return "qlsx/formHDN";
	}
	
	@PostMapping("/save")
	public String save(HoaDonNhap hdn) {
		hdnRepo.save(hdn);
		return "redirect:/btp/getAll";
	}

}
