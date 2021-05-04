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
public class HoaDonNhap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ngayNhap;
	private int soLuong;
	
	@ManyToOne(targetEntity = HoaDon.class)
	private List<HoaDon> hoadons;
	
	@ManyToOne(targetEntity = NhaCungCap.class)
	private List<NhaCungCap> nhacungcaps;
}
