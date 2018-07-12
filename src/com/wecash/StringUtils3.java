package com.wecash;

import java.io.File;
import java.io.InputStream;

/**
 * Created by chengtong on 2017/4/21.
 */
public class StringUtils3 {
    static boolean isCopy=false;
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
            String paramContext = "/Users/chengtong/myapp/58client_v7.8.3_799";
            copySo(paramContext);
            String phonenum = "E72E39250AF9C2F398EB7BF1CB53D10A";
            decrypt(phonenum.getBytes(),phonenum.getBytes().length);


        }catch (Exception e){
            System.out.println(e);
        }

    }
    private static void copySo(String paramContext)
    {
        if (isCopy) {
            return;
        }

            try
            {
                System.out.println("System.loadLibrary() start");
                System.loadLibrary("com_wuba_aes_ExecV3_1_0");
                System.out.println( "System.loadLibrary() success");
                isCopy = true;
                return;
            }
            catch (Error localError)
            {
                System.out.println("System loadLibrary error");
                File paramContext1 = new File(paramContext + "/lib/libcom_wuba_aes_ExecV3_1_0.so");
                if (!paramContext1.exists())
                {
                    System.out.println("file:" + paramContext1.getAbsolutePath() + "not exist");
                    boolean bool = paramContext1.getParentFile().mkdirs();
                    System.out.println( "makedir:" + bool);
                }
            }
//            try
//            {
//                InputStream localInputStream = StringUtils3.class.getResourceAsStream("/lib/armeabi/libcom_wuba_aes_ExecV3_1_0.so");
//                System.out.println( "copy file start");
//                copyFile(localInputStream, paramContext);
//                LOGGER.d(TAG, "copy file success");
//                try
//                {
//                    LOGGER.d(TAG, "System load file start");
//                    System.load(paramContext.getAbsolutePath());
//                    LOGGER.d(TAG, "System load file success");
//                }
//                catch (Throwable paramContext)
//                {
//                    LOGGER.e(TAG, "System load file failed", paramContext);
//                }
//            }
//            catch (IOException localIOException)
//            {
//                for (;;)
//                {
//                    LOGGER.e(TAG, "copy file failed", localIOException);
//                }
//            }
        }

}
