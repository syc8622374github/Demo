package phone.demo.com.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cyc on 2016/12/1 0001.
 */

public class DigestUtils {


    /**
     * 获取md5加密
     * @param val
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5(String val){
        byte[] m = new byte[0];
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            //加密
            m = md5.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return getString(m);
    }

    private static String getString(byte[] b){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < b.length; i ++){
            sb.append(b[i]);
        }
        return sb.toString();
    }
}
