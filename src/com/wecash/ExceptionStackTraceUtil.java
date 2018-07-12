package com.wecash;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by chengtong on 2017/3/22.
 */
public class ExceptionStackTraceUtil {
    public static String getExceptionStackTrace(Throwable anexcepObj)
    {
        StringWriter sw = null;
        PrintWriter printWriter = null;
        try{
            if(anexcepObj != null)
            {
                sw = new StringWriter();
                printWriter = new PrintWriter(sw);
                anexcepObj.printStackTrace(printWriter);
                printWriter.flush();
                sw.flush();
                return sw.toString();
            }
            else
                return null;
        }finally
        {

            try
            {
                if(sw != null)
                    sw.close();
                if(printWriter != null)
                    printWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
