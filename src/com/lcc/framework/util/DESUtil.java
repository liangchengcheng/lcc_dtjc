package com.lcc.framework.util;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by lcc on 2016/11/29.
 */
public class DESUtil {
    public final static String DES_NOPADDING = "NOPADDING";
    public final static String DES_PKCS5PADDING = "PKCS5Padding";

    public static byte[] TripleDES_Encrypt(byte[] k, byte[] data) throws Exception {
        return TripleDES_Encrypt(k, data, DES_PKCS5PADDING);
    }

    public static byte[] TripleDES_Encrypt_MAC(byte[] k, byte[] data, byte[] iv) throws Exception {
        return TripleDES_Encrypt_MAC(k, data, iv, DES_PKCS5PADDING);
    }

    public static byte[] TripleDES_Encrypt(byte[] k, byte[] data, String padding) throws Exception {
        k = getKey(k);
        DESedeKeySpec keyspec = new DESedeKeySpec(k);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyfactory.generateSecret(keyspec);
        Cipher cipher = Cipher.getInstance("DESede/ECB/"+padding);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static byte[] TripleDES_Encrypt_MAC(byte[] k, byte[] data, byte[] iv, String padding) throws Exception {
        DESKeySpec keyspec = new DESKeySpec(k);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyfactory.generateSecret(keyspec);
        Cipher cipher = Cipher.getInstance("DES/CBC/"+padding);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] mac = cipher.doFinal(data);
        int len = mac.length;
        return Arrays.copyOfRange(mac, len-8, len-4);
    }

    public static byte[] TripleDES_Decrypt(byte[] k, byte[] data) throws Exception {
        return TripleDES_Decrypt(k, data, DES_PKCS5PADDING);
    }

    public static byte[] TripleDES_Decrypt(byte[] k, byte[] data, String padding) throws Exception {
        k = getKey(k);
        DESedeKeySpec keyspec = new DESedeKeySpec(k);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyfactory.generateSecret(keyspec);
        Cipher cipher = Cipher.getInstance("DESede/ECB/"+padding);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static byte[] DeliveryKey(byte[] k, byte[] seed) throws Exception {
        int klen = k.length;
        if (klen%16!=0||seed.length%8!=0) {
            throw new IllegalArgumentException();
        }
        byte[] key = new byte[klen];
        System.arraycopy(k, 0, key, 0, klen);
        byte[] newkey = new byte[16];
        for (int i = 0; i < seed.length/8; i++) {
            byte[] oneseed = Arrays.copyOfRange(seed, i*8, (i+1)*8);
            System.arraycopy(TripleDES_Encrypt(key, oneseed, DES_NOPADDING), 0, newkey, 0, 8);
            int int_one = ~DataTypeUtil.bytesToInt(Arrays.copyOfRange(oneseed, 0, 4));
            String str_one = DataTypeUtil.IntToHex(int_one, 4);
            int int_two = ~DataTypeUtil.bytesToInt(Arrays.copyOfRange(oneseed, 4, 8));
            String str_two = DataTypeUtil.IntToHex(int_two, 4);
            String str = str_one + str_two;
            System.arraycopy(TripleDES_Encrypt(key, DataTypeUtil.hexs2Bytes(str), DES_NOPADDING), 0, newkey, 8, 8);
            System.arraycopy(newkey, 0, key, 0, 16);
        }
        return key;
    }

    private static byte[] getKey(byte[] k){
        if (k.length == 24) return k;
        byte[] t = new byte[24];
        if (k.length == 8){
            System.arraycopy(k, 0, t, 0, 8);
            System.arraycopy(k, 0, t, 8, 8);
            System.arraycopy(k, 0, t, 16, 8);
            k = t;
        } else if (k.length == 16) {
            System.arraycopy(k, 0, t, 0, 16);
            System.arraycopy(k, 0, t, 16, 8);
            k = t;
        }
        return t;
    }
}
