package com.attilax.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class IOUtilsAti {

	public static String toStringAutoencode(InputStream inputStream1, long size) throws IOException {
		
		byte[] ba=IOUtils.toByteArray(inputStream1);
		String encode=FileEncodeUtil.getTxtEncode(ba);
		 
		return   IOUtils.toString(ba,encode );
	}

}
