package web.controller;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.entity.BanThanhPham;
import web.repo.BanThanhPhamRepository;
import web.repo.QuyTrinhRepository;

@Controller
@RequestMapping("/btp")
public class BanThanhPham_Controller {
	private final BanThanhPhamRepository btpRepo;
	
	@Autowired
	private EntityManager entitymanager;

	@Autowired
	public BanThanhPham_Controller(BanThanhPhamRepository btpRepo) {
		this.btpRepo = btpRepo;
	}
	
	@GetMapping("/getAll")
	public String getAll(Model model) {
		model.addAttribute("btp", btpRepo.findAll());
		return "btp";
	}


	@GetMapping("/add")
	public String addNVL(Model model) {
		model.addAttribute("btp", new BanThanhPham());
		return "formBTP";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("btp", btpRepo.findById(id).get());
		return "formBTP";
	}

	@GetMapping("/delete/{id}")
	public String deleteBTP(Model model, @PathVariable Long id) {
		btpRepo.deleteById(id);;
		return "redirect:/btp/getAll";
	}
	
	
	@GetMapping("/search")
	public String searchBTP(@Param("keyword") String keyword, Model model) {
		// hql with relationship
		Query q = entitymanager.createQuery("select b from BanThanhPham as b join b.quytrinhs as bq where bq.ten = :x");
		q.setParameter("x", keyword);
		
		List<BanThanhPham> list = (List<BanThanhPham>) q.getResultList();
		model.addAttribute("btp", list);
		
		return "btp";
	}
	
	@PostMapping("/save")
	public String addNVL(BanThanhPham btp) {
		btpRepo.save(btp);
		return "redirect:/btp/getAll";
	}
	

}
