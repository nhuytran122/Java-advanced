package adminloginmodal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class loginadminbo {
	loginadmindao addao = new loginadmindao();
	
	public String checkLogin(String tenDn, String Pass) throws Exception {
		return addao.checkLogin(tenDn, encrytPassword(Pass));
	}
	
	public String encrytPassword(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(input.getBytes());
		BigInteger bigInt = new BigInteger(1, messageDigest);
		return bigInt.toString(16);
	}
}
