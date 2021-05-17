package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.entity.BaoCaoTonKho;
import web.repo.BaoCaoTonKhoRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bctk")
public class BaoCaoTonKho_Controller {
    private final BaoCaoTonKhoRepository bctkrepo;
    
    @Autowired
    private EntityManager entitymanager;
    
    @Autowired
    public BaoCaoTonKho_Controller(BaoCaoTonKhoRepository bctkrepo){
        this.bctkrepo=bctkrepo;
    }
    
    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("bctk", bctkrepo.findAll());
        return "qldh/bctk";
    }
    
    @GetMapping("/add")
    public String addBctk(Model model){
        model.addAttribute("bctk",new BaoCaoTonKho());
        return "qldh/formbctk";
    }
    
    @GetMapping("/edit/{id}")
    public String editBctk(Model model, @PathVariable Long id){
        model.addAttribute("bctk",bctkrepo.findById(id).get());
        return "qldh/formbctk";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteBctk(@PathVariable Long id){
        bctkrepo.deleteById(id);
        return "redirect:/bctk/getAll";
    }
    
    
    @GetMapping("/search")
    public String searchBctk(@Param("keyword_from") String keyword_from, @Param("keyword_to") String keyword_to, Model model) throws ParseException {
        // hql with relationship
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        String bd, kt;
        if(keyword_from != "" && keyword_to != "") {
            bd = sdf2.format(sdf1.parse(keyword_from));
            kt = sdf2.format(sdf1.parse(keyword_to));
            Query q= entitymanager.createQuery("select bc from BaoCaoTonKho as bc join bc.nvls as nvl where nvl.ngayNhap between :x and :y");
            q.setParameter("x", bd);
            q.setParameter("y", kt);

            List<BaoCaoTonKho> list = (List<BaoCaoTonKho>) q.getResultList();
            model.addAttribute("bctk", list);
        }
        else if(keyword_from != "" && keyword_to == "") {
            bd = sdf2.format(sdf1.parse(keyword_from));
            Query q= entitymanager.createQuery("select bc from BaoCaoTonKho as bc join bc.nvls as nvl where nvl.ngayNhap >= :x");
            q.setParameter("x", bd);

            List<BaoCaoTonKho> list = (List<BaoCaoTonKho>) q.getResultList();
            model.addAttribute("bctk", list);
        }
        else if(keyword_from == "" && keyword_to != "") {
            kt = sdf2.format(sdf1.parse(keyword_to));
            Query q= entitymanager.createQuery("select bc from BaoCaoTonKho as bc join bc.nvls as nvl where nvl.ngayNhap <= :x");
            q.setParameter("x", kt);

            List<BaoCaoTonKho> list = (List<BaoCaoTonKho>) q.getResultList();
            model.addAttribute("bctk", list);
        }
        else
            return "redirect:/bctk/getAll";


        return "qldh/bctk";

    }
    
    @PostMapping("/save")
    public String addBctp(BaoCaoTonKho bc){
        int ton=bc.getSoluongNhap()-bc.getSoluongXuat();
        bc.setTongHangTon(ton);
        bctkrepo.save(bc);
        return "redirect:/bctk/getAll";
    }
}
