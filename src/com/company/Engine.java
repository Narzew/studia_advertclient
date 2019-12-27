package com.company;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Engine {

    public static String hash_password(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

}
