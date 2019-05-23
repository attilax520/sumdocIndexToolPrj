package aPkg;

import java.io.File;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.BodyTerm;
import javax.mail.search.SubjectTerm;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;

 

 

 

public class ImapSearch {

	public static void main(String[] args) throws  Exception {
		Session session = Session.getDefaultInstance(System.getProperties(),null);
		Store store = session.getStore("imaps");
	 	String string =FileUtils.readFileToString(new File("d:\\0db\\empwd.txt")) ;
		store.connect("imap.qq.com", "attilax2@qq.com", string);

		// Get default folder
		Folder folder = store.getDefaultFolder();
		Folder fld_fulltxt=store.getFolder("fldFull2");
		// 以读写模式打开收件箱   
		fld_fulltxt.open(Folder.READ_WRITE); 
		System.out.println( "fld_fulltxt.getMessageCount"+fld_fulltxt.getMessageCount());
		Message[] getMessages1=fld_fulltxt.getMessages();
		Message[] ma=fld_fulltxt.search(new BodyTerm("人工智能")   );
		for (Message message : ma) {
			 System.out.println(JSON.toJSONString(message, true));
		}
		
		System.out.println("--");

	}

}
