package web.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class HoaDonXuat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ngayXuat;
	private double tongtien;
	private int soLuong;
	private String donvi;
	
	@ManyToOne(targetEntity = BoPhan.class)
	private List<BoPhan> bophans;
	
//	@ManyToOne(targetEntity = HoaDon.class)
//	private List<HoaDon> hoadons;
	@ManyToOne(targetEntity = NVL.class)
	private List<NVL> nvls;
}
