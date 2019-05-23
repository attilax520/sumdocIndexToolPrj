package aPkg;

import java.io.File;

 

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.imap.IMAP;
import org.apache.commons.net.imap.IMAP.IMAPChunkListener;
import org.apache.commons.net.imap.IMAPClient;
import org.apache.commons.net.imap.IMAPClient.SEARCH_CRITERIA;

import com.alibaba.fastjson.JSON;

 

 

 

public class ImapSearchApacheNetlib {

	public static void main(String[] args) throws  Exception {
		String string =FileUtils.readFileToString(new File("d:\\0db\\empwd.txt")) ;
		
		
		 IMAPClient IMAPClient1 = new IMAPClient();
		    IMAPClient1.connect("imap.qq.com");
		    IMAPClient1.login("attilax2@qq.com", string);
		    IMAPClient1.select("fldFull2");
		    String criteria="人工智能";
		    //= SEARCH_CRITERIA.TEXT;
		  //  IMAPClient1.
		    IMAPClient1.setChunkListener(new IMAPChunkListener() {
				
				public boolean chunkReceived(IMAP arg0) {
					System.out.println(arg0);
					return false;
				}
			});
       System.out.println(IMAPClient1.search(criteria));	 
//		Session session = Session.getDefaultInstance(System.getProperties(),null);
//		Store store = session.getStore("imaps");
//	 
//		store.connect(, , string);

		// Get default folder
//		Folder folder = store.getDefaultFolder();
//		Folder fld_fulltxt=store.getFolder("fldFull2");
//		// 以读写模式打开收件箱   
//		fld_fulltxt.open(Folder.READ_WRITE); 
//		System.out.println( "fld_fulltxt.getMessageCount"+fld_fulltxt.getMessageCount());
//		Message[] getMessages1=fld_fulltxt.getMessages();
//		Message[] ma=fld_fulltxt.search(new BodyTerm("人工智能")   );
//		for (Message message : ma) {
//			 System.out.println(JSON.toJSONString(message, true));
//		}
		
		System.out.println("--");

	}

}
