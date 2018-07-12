package com.wecash;

/**
 * Created by chengtong on 2017/4/21.
 */
public class StringUtils {
    public static native byte[] decryptPhoneData(byte[] paramArrayOfByte, int paramInt);

    public static byte[] decrypt(byte[] paramArrayOfByte, int paramInt)
            throws Exception
    {
        return decryptPhoneData(paramArrayOfByte, paramInt);
    }

    public  static  void main(String[] args){

        try{
            ///lib/armeabi/libcom_wuba_aes_ExecV3_1_0.so
            //System.loadLibrary("libcom_wuba_aes_ExecV3_1_0.so");
            //String phonenum = "13051275666";
            String phonenum = "E72E39250AF9C2F398EB7BF1CB53D10A";
            decrypt(phonenum.getBytes(),phonenum.getBytes().length);


        }catch (Exception e){
            System.out.println(e);
        }

    }
}
