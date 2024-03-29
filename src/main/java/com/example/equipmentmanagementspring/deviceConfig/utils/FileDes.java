package com.example.equipmentmanagementspring.deviceConfig.utils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

public class FileDes {
    Key key;

    public FileDes(String str) {
        getKey(str);//生成密匙
    }

    /**
     * 根据参数生成KEY
     */
    public void getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            //防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(strKey.getBytes());

            _generator.init(56,secureRandom);
            this.key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        }


    }

    /**
     * 文件流加密
     * @param file
     * @return
     * @throws Exception
     */
    public InputStream encrypt(InputStream file) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        CipherInputStream cis = new CipherInputStream(file, cipher);
        return cis;
    }

    /**
     * 文件流解密
     * @param file
     * @return
     * @throws Exception
     */
    public InputStream decrypt(InputStream file) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        CipherInputStream cis = new CipherInputStream(file, cipher);
        return cis;
    }

    /**
     * 文件加密
     * @param file
     * @param destFile
     * @throws Exception
     */
    public void encrypt(String file, String destFile) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(destFile);
        CipherInputStream cis = new CipherInputStream(is, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = cis.read(buffer)) > 0) {
            out.write(buffer, 0, r);
        }
        cis.close();
        is.close();
        out.close();
    }

    /**
     * 文件解密
     * @param file
     * @param dest
     * @throws Exception
     */
    public void decrypt(String file, String dest) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(dest);
        CipherOutputStream cos = new CipherOutputStream(out, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = is.read(buffer)) >= 0) {
            System.out.println();
            cos.write(buffer, 0, r);
        }
        cos.close();
        out.close();
        is.close();
    }

//    public static void main(String[] args) throws Exception {
//        FileDes fileDESUtil = new FileDes("aabbccdd");//密钥
//        fileDESUtil.encrypt("D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书.doc", "D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书加密.doc"); //加密
//        fileDESUtil.decrypt("D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书加密.doc", "D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书解密.doc"); //解密
//    }


}
