package web.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.entity.NVL;
import web.repo.NVLRepository;

@Controller
@RequestMapping("/nvl")
public class NVL_Controller {
	private final NVLRepository nvlRepo;

	@Autowired
	public NVL_Controller(NVLRepository nvlRepo) {
		this.nvlRepo = nvlRepo;
	}
	
	@GetMapping("/getAll")
	public String getAll(Model model) {
		model.addAttribute("nvl", nvlRepo.findAll());
		return "nvl";
	}
	
	@GetMapping("/add")
	public String addNVL(Model model) {
		model.addAttribute("nvl", new NVL());
		return "formNVL";
	}
	
	@GetMapping("/edit/{id}")
	public String editCompany(Model model, @PathVariable Long id) {
		model.addAttribute("nvl", nvlRepo.findById(id).get());
		return "formNVL";
	}

	@GetMapping("/delete/{id}")
	public String deleteCompany(Model model, @PathVariable Long id) {
		nvlRepo.deleteById(id);;
		return "redirect:/nvl/getAll";
	}
	
	@GetMapping("/search")
	public String searchNVL(@Param("keyword") String keyword, Model model) {
		if(keyword.length()<10) {
			List<NVL> list = nvlRepo.findAllByTenContaining(keyword);
			model.addAttribute("nvl", list);
		}else {
			List<NVL> list = nvlRepo.findAllByNgayNhapContaining(keyword);
			model.addAttribute("nvl", list);
		}
		
		return "nvl";
	}
	
	@PostMapping("/save")
	public String addNVL(NVL nvl) {
		nvlRepo.save(nvl);
		return "redirect:/nvl/getAll";
	}
}
