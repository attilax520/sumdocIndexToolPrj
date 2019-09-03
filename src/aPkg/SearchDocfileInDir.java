package aPkg;

import java.io.File;
import java.io.IOException;
import java.net.URI;
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

import com.alibaba.fastjson.JSON;

import comattilax.sumdoclist.FileTraveList;

public class SearchDocfileInDir {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);
	public static void main(String[] args) throws IOException {
		String dir = "C:\\Users\\Administrator\\Documents\\it Èí¼þÏµÁÐÊé¼®";
		Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {

		 
			// å¤„ç†æ–‡ä»¶
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				// return super.visitFile(file, attrs);
				try {
					String fpath = file.toString();
					logger.info(fpath);
					String fname=FilenameUtils.getName(fpath);
					// get rlt path
				//	String fpath2 = fpath.substring(prefix.length() +1, fpath.length());
					//System.out.println("\tæ­£åœ¨è®¿é—® relt :" + fpath2 + "æ–‡ä»¶");
					String outputDir="d:\\0outputSearchdoc";
					String newfile = outputDir+"\\"+fname+".txt";
					String cmd="D:\\doctotext\\doctotext.exe \""+fpath+"\"  >\""+newfile+"\"";
				
				//	cmd=cmd.replaceAll("@docfile@", fpath);
				//	cmd=cmd.replaceAll("@outputDir@", outputDir);
					cmd=cmd.replaceAll("@txtfile@", fname);
					System.out.println(cmd);
					logger.info(cmd);
					FileUtils.write(new File( outputDir+"\\crt.txt"), "aa");
					Process process=	Runtime.getRuntime().exec(cmd); 
					List<String> li=IOUtils.readLines(process.getInputStream());
					System.out.println(li);
					logger.info(JSON.toJSON(li));
					FileUtils.writeStringToFile(new File( "d:\\0outputSearchdoc\\cmd.bat"), cmd+"\r\n", true);

					//FileUtils.writeStringToFile(new File(listrztfile), fpath2 + "\r\n", true);
				
					String txt=FileUtils.readFileToString(new File(newfile));
					if(txt.contains("ÍâÖÃ"))
						logger.info("finded.."+newfile);
				} catch (Exception e) {
					logger.error(e);
				}
				
				return FileVisitResult.CONTINUE; // æ²¡æ‰¾åˆ°ç»§ç»­æ‰¾
			}

		});
		logger.info("--fi");

	}

}
