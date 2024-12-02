package khachhangmodal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class khachhangbo {
	khachhangdao khdao = new khachhangdao();
	
	public ArrayList<khachhang> getKHByCondition(int page, int pageSize, String searchValue) throws Exception {
		return khdao.getKHByCondition(page, pageSize, searchValue);
	}
	public int countKHWithSearch(String searchValue) throws Exception {
		return khdao.countKHWithSearch(searchValue);
	}
	public khachhang checkLogin(String tenDn, String Pass) throws Exception{
		return khdao.checkLogin(tenDn, encrytPassword(Pass));
	}
	
	public int themKH(String hoten, String diachi, String sodt, String email, String tendn, String pass) throws Exception {
		return khdao.themKH(hoten, diachi, sodt, email, tendn, encrytPassword(pass));
	}
	
	public String encrytPassword(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(input.getBytes());
		BigInteger bigInt = new BigInteger(1, messageDigest);
		return bigInt.toString(16);
	}
	
	public int countAllKH() throws Exception {
		return khdao.countAllKH();
	}
}