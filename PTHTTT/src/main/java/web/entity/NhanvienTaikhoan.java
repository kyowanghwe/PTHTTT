package web.entity;

import lombok.Data;

@Data
public class NhanvienTaikhoan extends Nhanvien  {
	private String username;
	private String password;
}