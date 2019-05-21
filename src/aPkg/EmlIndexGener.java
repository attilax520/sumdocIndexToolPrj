package aPkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.alibaba.fastjson.JSON;
import com.attilax.compress.ZipUtilV2t55;
import com.attilax.coreLuni.util.ExUtil;
import com.attilax.data.csv.htmlJsoupUtil;
import com.attilax.search.SearchScanUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import comattilax.sumdoclist.FileTraveList;

public class EmlIndexGener {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);

	public static void main(String[] args) throws Exception {

		String dir = "D:\\000atibek\\000sum doc body 4fulltext v3 t55";
	 

		// 处理下级多层目录
		Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {

			// 处理文件
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				// walkFile log
				String fpath = file.toString();
				if (fpath.contains("是印度最古老的一部法律文献"))
					System.out.println("dbg");
				String string = "\t正在访问" + fpath + "文件";
				System.out.println(string);
				logger.info(string);
				// ZipFile zipFile = new
				// ZipFile(file.toFile().getAbsolutePath(),"gbk");
				String absolutePath = file.toFile().getAbsolutePath();
				String ext = FilenameUtils.getExtension(absolutePath);
				if (ext.equals("zip"))
					processZipfile( absolutePath);
				else if (ext.equals("docx")) {

				} else {
					String filecon = FileUtils.readFileToString(file.toFile());
					// txt
					try {
						processCoretxt(file.toFile().getAbsolutePath(), file.toFile().getAbsolutePath().toString(),
								filecon);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return FileVisitResult.CONTINUE; // 没找到继续找
			}

		});
		Map trace_m = Maps.newConcurrentMap();
		// trace_m.put("hitDocCount", hitDocCount);
		trace_m.put("rzt", rzt);
		System.out.println(JSON.toJSONString(trace_m, true));

	}

	static List rzt = Lists.newArrayList();

	private static void processZipfile( String absolutePath) {
		ZipUtilV2t55.filelist(absolutePath, "gbk", new Consumer<Map>() {

			@Override
			public void accept(Map m) {
				try {
					ZipEntry ZipEntry1 = (ZipEntry) m.get("zipEntry");
					ZipFile ZipFile1 = (ZipFile) m.get("zipFile");
					logger.info("ZipEntry1.getName:" + ZipEntry1.getName().toString());

					InputStream InputStream1 = null;

					InputStream1 = ZipFile1.getInputStream(ZipEntry1);

					String filecon = IOUtils.toString(InputStream1);
					if (ZipEntry1.getName().endsWith(".html") || ZipEntry1.getName().endsWith(".htm")) {
						String ZipEntry1txt = htmlJsoupUtil.html2txt(filecon);
						processCoretxt( absolutePath, ZipEntry1.toString(), ZipEntry1txt);

					} else // txt
					{
						processCoretxt( absolutePath, ZipEntry1.toString(), filecon);
					}
					// System.out.println(filecon);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ExUtil.throwExV2(e);
				}

			}

		});
	}

	private static void processCoretxt(  String zipfile2, String ZipEntry1, String txt) throws  Exception {
	 
		//	List<Map> li = SearchScanUtil.searchDefPad(txt, key);
			Map trace_m_cur = Maps.newLinkedHashMap();

		//	trace_m_cur.put("key", key);
			trace_m_cur.put("ZipFile1", zipfile2.toString());
			// trace_m_cur.put("hitDocCount_cur", hitDocCount);
			trace_m_cur.put("ZipEntry1", ZipEntry1);
		//	trace_m_cur.put("hit line", li);
			// hitDocCount++;
			System.out.println(txt);
			System.out.println(JSON.toJSONString(trace_m_cur, true));
		//	rzt.add(trace_m_cur);
			
			
			Message message = new MimeMessage(Session.getInstance(System.getProperties()));
			// message.setFrom(new InternetAddress(from));
			// message.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse(to));
			message.setSubject(ZipEntry1);
			message.setText(txt+"\r\n"+ZipEntry1);

			String dir="d:\\0emlIndex";
			String name=FilenameUtils.getName(ZipEntry1);
		//	String name=;
			message.writeTo(new FileOutputStream(new File(dir+"/"+name+".eml")));

	 
	}

}
