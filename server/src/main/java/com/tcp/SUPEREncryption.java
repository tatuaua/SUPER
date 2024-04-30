package com.tcp;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class SUPEREncryption {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public SUPEREncryption() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

	public SUPEREncryption(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
        return cipher.doFinal(message.getBytes());
    }

    public String decrypt(byte[] encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
        return new String(cipher.doFinal(encryptedMessage));
    }

	public PrivateKey getPrivateKey(){
		return this.privateKey;
	}

	public PublicKey getPublicKey(){
		return this.publicKey;
	}
}