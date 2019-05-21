package com.attilax.io;

import java.io.IOException;
import java.io.InputStream;

import com.attilax.Charset.EncodingDetect;

public class FileEncodeUtil {

	/**
	 * gbk just include gbk and nobomUtf8
	 * @param InputStream1
	 * @return
	 * @throws IOException
	 */
	public static String getTxtEncode(InputStream InputStream1) throws IOException  {  
        byte[] head = new byte[3];    
        InputStream1.read(head);      
        String code = "GBK";    
        if (head[0] == -1 && head[1] == -2 )    
            code = "UTF-16";    
        if (head[0] == -2 && head[1] == -1 )    
            code = "Unicode";  
        //´øBOM  
        if(head[0]==-17 && head[1]==-69 && head[2] ==-65)    
            code = "UTF-8";    
        if("Unicode".equals(code)){  
         code = "UTF-16";  
        }  
        return code;  
 }

	public static String getTxtEncode(InputStream inputStream, long size) {
		 
		return EncodingDetect.getJavaEncode(inputStream, size);
	}

	public static String getTxtEncode(byte[] ba) {
		// TODO Auto-generated method stub
		return EncodingDetect.getJavaEncode(ba);
	}  

}
