package util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class DigitalSignature {
	
	//使用私鑰簽名
	public static String sign(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
	{
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(data.getBytes());
		byte[] signedBytes = signature.sign();
		return Base64.getEncoder().encodeToString(signedBytes);
	}
	
	//使用公鑰驗證
	public static boolean verify(String data, String signatureStr, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
	{
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(data.getBytes());
		byte[] signedBytes = Base64.getDecoder().decode(signatureStr);
		return signature.verify(signedBytes);
	}
	

}
