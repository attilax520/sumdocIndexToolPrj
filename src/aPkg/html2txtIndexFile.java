package aPkg;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSON;

import comattilax.sumdoclist.FileTraveList;
@SuppressWarnings("all")
public class html2txtIndexFile {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);
	public static void main(String[] args) throws Exception {
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		Transferable Transferable1_clipboardContent = clipboard.getContents(null);
		// 获取文本中的Transferable对象
	//	DataFlavor.

		
	//	List<File> list = (List<File>) (Transferable1_clipboardContent.getTransferData(DataFlavor.javaFileListFlavor));
		 
		
		String dir =  (String) Transferable1_clipboardContent.getTransferData(DataFlavor.stringFlavor);
		Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {

		 
			// 澶勭悊鏂囦欢
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				// return super.visitFile(file, attrs);
				try {
					String fpath = file.toString();
					String ext=FilenameUtils.getExtension(fpath);
					if(ext.toLowerCase().equals("htm") || ext.toLowerCase().equals("html") )
					{
						logger.info(fpath);
						String fname=FilenameUtils.getName(fpath);

						String t=FileUtils.readFileToString(file.toFile(),"utf8");
						String text = Jsoup.parse(t).text();
						System.out.println(text);	
						
						FileUtils.writeStringToFile(new File( "d:\\0outputEvernoteAccAtti2\\EvernoteAccAtt2.txt"), text+"\r\n\r\n--------------------\r\n\r\n", true);

					}
			
					//FileUtils.writeStringToFile(new File(listrztfile), fpath2 + "\r\n", true);
				
				 
				} catch (Exception e) {
					logger.error(e);
				}
				
				return FileVisitResult.CONTINUE; // 娌℃壘鍒扮户缁壘
			}

		});
		logger.info("--fi");

	}

}
