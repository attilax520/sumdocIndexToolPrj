package aPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import com.alibaba.fastjson.JSON;
import com.attilax.coreLuni.util.jsonuti4fc;
import com.google.common.collect.Lists;

public class EmlImport {
	
	public static void main(String[] args) throws  Exception {
		//IMAPFolder
		System.out.println("--");
		Session session = Session.getDefaultInstance(System.getProperties(),null);
		Store store = session.getStore("imaps");
	 	store.connect("imap.qq.com", "1466519819@qq.com", "cfkwqvkmbtvtjfid");

		// Get default folder
		Folder folder = store.getDefaultFolder();
	 

		// Get any folder by name
		Folder[] folderList = folder.list();
		for (Folder folder2 : folderList) {
			System.out.println(folder2);
		}
		int pageSize = 1;
		List<Message> li=Lists.newArrayList();
		Folder fld_fulltxt=store.getFolder("fulltxt");
		System.out.println(fld_fulltxt.getMessageCount());
		String dir="D:\\0emlIndex\\3";
		File[] fa=new File(dir).listFiles();
		for (File f : fa) {
			System.out.println(f);
			Message m=eml2message(f);
		
			li.add(m);
		
			if(li.size()>=pageSize)
			{
				Message[] ma=  li.toArray(new  Message[li.size()]);
			//	System.out.println(" append msg:"+JSON.toJSONString(ma));
				fld_fulltxt.appendMessages(ma);
				//fld_fulltxt.copyMessages(ma, fld_fulltxt);
				li.clear();
			}
		}
		//fld_fulltxt.close();
		store.close();
	//	session.
		System.out.println("--f");
	//	System.out.println(JSON.toJSONString(fld_fulltxt));
		//folder.appendMessages(arg0);
	}

	private static Message eml2message(File f) throws Exception {
	 
			 
			Properties props = System.getProperties();
		//	props.put("mail.host", "smtp.dummydomain.com");
		//	props.put("mail.transport.protocol", "smtp");

			Session mailSession = Session.getDefaultInstance(props, null);
			InputStream source = new FileInputStream(f);
			System.out.println("source.available:"+source.available());
			MimeMessage message = new MimeMessage(mailSession, source);
		return message;
	}
	
	/**folder.list();
	 * �����ļ���
INBOX
Sent Messages
Drafts
Deleted Messages
Junk
Drafts
Sent
�ݸ�
fulltxt
	 */

}
