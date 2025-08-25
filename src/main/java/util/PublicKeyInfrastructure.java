package util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PublicKeyInfrastructure {
	
	
	public static KeyPair generateKeyPair() throws Exception
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		return keyPairGenerator.generateKeyPair();
	}
	
	// 將金鑰轉為 Base64 字串（方便存檔或存 DB）
	public static String encodeKey(Key key)
	{
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	// 讀取 Base64 字串轉回 PublicKey / PrivateKey
	public static PublicKey decodePublicKey(byte[] keyBytes) throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		 return KeyFactory.getInstance("RSA")
	                .generatePublic(new java.security.spec.X509EncodedKeySpec(keyBytes));
	}
	
	public static PrivateKey decodePrivateKey(byte[] keyBytes) throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		return KeyFactory.getInstance("RSA")
                .generatePrivate(new java.security.spec.PKCS8EncodedKeySpec(keyBytes));
	}
}
