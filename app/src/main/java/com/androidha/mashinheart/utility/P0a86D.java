package com.androidha.mashinheart.utility;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class P0a86D {
    public static String dataDecode(String data) {
        String decrypted = "";
        MAHEncryptor mahEncryptor = null;
        try {
            mahEncryptor = MAHEncryptor.newInstance(new String(p0a87.rcdapp6(p0a87.rcdapp5(p0a87.rcdapp4(p0a87.rcdapp3(p0a87.rcdapp2(p0a87.rcdapp1(p0a34.rcdapp1() + p0a34.rcdapp2() + p0a34.rcdapp3() + p0a34.rcdapp4() + p0a34.rcdapp5() + p0a34.rcdapp6() + p0a34.rcdapp7() + p0a34.rcdapp8() + p0a34.rcdapp9() + p0a34.rcdapp10()))))))));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }
        try {
            return mahEncryptor.decode(data);
        } catch (UnsupportedEncodingException e5) {
            e5.printStackTrace();
            return decrypted;
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
            return decrypted;
        } catch (NoSuchAlgorithmException e7) {
            e7.printStackTrace();
            return decrypted;
        } catch (InvalidKeyException e8) {
            e8.printStackTrace();
            return decrypted;
        } catch (BadPaddingException e9) {
            e9.printStackTrace();
            return decrypted;
        } catch (IllegalBlockSizeException e10) {
            e10.printStackTrace();
            return decrypted;
        }
    }
}
