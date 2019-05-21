package aPkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmlCreate2 {

	public static void main(String[] args) throws Exception {
		Message message = new MimeMessage(Session.getInstance(System.getProperties()));
		// message.setFrom(new InternetAddress(from));
		// message.setRecipients(Message.RecipientType.TO,
		// InternetAddress.parse(to));
		message.setSubject("subject");
		message.setText("emltxt");

		String dir="C:\\Users\\Administrator\\Documents\\emlIndexdir";
		message.writeTo(new FileOutputStream(new File(dir+"/"+"mail.eml")));
		System.out.println("--ff");
	}

}
