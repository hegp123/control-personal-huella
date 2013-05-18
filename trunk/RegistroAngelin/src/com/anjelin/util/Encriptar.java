/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public final class Encriptar {
    public static final String UT_F16 = "UTF-16";
    public static final String UT_F8 = "UTF-8";
    public static final String MD5 = "MD5";
    public static final String SHA = "SHA";
    public static final String SHA_1 = "SHA-1";

    public static synchronized String encrypt(String plaintext,
            String algorithm, String encoding) throws Exception {
        MessageDigest msgDigest = null;
        String hashValue = null;
        try {
            msgDigest = MessageDigest.getInstance(algorithm);
            msgDigest.update(plaintext.getBytes(encoding));
            byte rawByte[] = msgDigest.digest();
            hashValue = (new BASE64Encoder()).encode(rawByte);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm Exists");
        } catch (UnsupportedEncodingException e) {
            System.out.println("The Encoding Is Not Supported");
        }
        return hashValue;
    }

    public static void main(String args[]) throws Exception {
        String plainPassword = "SecretPassword";

        System.out.println("PlainText\tAlgo\tEncoding\tEncrypted Password");
        System.out.println(plainPassword + "\tSHA\tUTF-8\t"
                + encrypt("MySecretPassword", SHA, UT_F8));
        System.out.println(plainPassword + "\tSHA-1\tUTF-16\t"
                + encrypt("MySecretPassword", SHA_1, UT_F16));
        System.out.println(plainPassword + "\tMD5\tUTF-8\t"
                + encrypt("MySecretPassword", MD5, UT_F8));
        System.out.println(plainPassword + "\tMD5\tUTF-16\t"
                + encrypt("MySecretPassword", MD5, UT_F16));

    }
}