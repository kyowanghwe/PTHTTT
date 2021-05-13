package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.entity.Nhanvien;
import web.entity.NhanvienTaikhoan;
import web.entity.TaiKhoan;
import web.repo._NhanvienRepository;


@Controller 
@RequestMapping("/nhanvien")
public class Nhanvien_Controller {
	private _NhanvienRepository nhanvienRepo;
	
	@Autowired
	public Nhanvien_Controller(_NhanvienRepository nhanvienRepo) {
		this.nhanvienRepo = nhanvienRepo;
	}
	
	@GetMapping("/themNV")
	public String showGDThemNV(Model model) {
		model.addAttribute("nhanvien_taikhoan", new NhanvienTaikhoan());
		return "GDThemNV";
	}
	
	@PostMapping("/themNV")
	public String themNV(@ModelAttribute NhanvienTaikhoan nhanvien_taikhoan) {
		Nhanvien nhanvien = new Nhanvien();
		nhanvien.setTen(nhanvien_taikhoan.getTen());
		nhanvien.setDiaChi(nhanvien_taikhoan.getDiaChi());
		nhanvien.setEmail(nhanvien_taikhoan.getEmail());
		nhanvien.setVaitro(nhanvien_taikhoan.getVaitro());
		nhanvien.setLuong(nhanvien_taikhoan.getLuong());
		nhanvien.setPhone(nhanvien_taikhoan.getPhone());
		Long nhanviens_id = nhanvienRepo.saveAndReturnId(nhanvien);
		TaiKhoan taikhoan = new TaiKhoan();
		taikhoan.setUsername(nhanvien_taikhoan.getUsername());
		taikhoan.setPassword(nhanvien_taikhoan.getPassword());
		nhanvienRepo.save(taikhoan, nhanviens_id);
		return "GDThemNV_OK";
	}
	
	@GetMapping("/home")
	public String showGDTrangchu() {
		return "redirect:/";
	}
	
}