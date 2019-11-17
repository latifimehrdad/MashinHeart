package com.androidha.mashinheart.utility;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class MAHEncryptor {
    private SecretKey key;

    private MAHEncryptor(String yourSecretKeyPhrase) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(yourSecretKeyPhrase.getBytes("UTF8"), 10));
    }

    public static MAHEncryptor newInstance(String yourSecretKeyPhrase) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        return new MAHEncryptor(yourSecretKeyPhrase);
    }

    public String decode(String encryptedText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        byte[] encrypedPwdBytes = Base64.decode(encryptedText, 0);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, this.key);
        return new String(cipher.doFinal(encrypedPwdBytes), "UTF-8");
    }
}
