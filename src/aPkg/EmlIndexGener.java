package aPkg;

import java.awt.Transparency;
import java.io.File;
import java.io.FileNotFoundException;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.alibaba.fastjson.JSON;
import com.attilax.compress.ZipUtilV2t55;
import com.attilax.coreLuni.util.ExUtil;
import com.attilax.data.csv.htmlJsoupUtil;
import com.attilax.io.FileEncodeUtil;
import com.attilax.io.FileUtilsAti;
import com.attilax.io.FilenameUtilsT55;
import com.attilax.io.IOUtilsAti;
import com.attilax.search.SearchScanUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import comattilax.sumdoclist.FileTraveList;

public class EmlIndexGener {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);
	static String dir_SOURCE = "D:\\000atibek\\000sum doc body 4fulltext v3 t55";
	static String out_dir = "d:\\0emlIndexAutoencode9";
	static int poolsize = 10;
	static ExecutorService es_pool = Executors.newFixedThreadPool(poolsize);
	static int count;
	static int count_submit2pool;
	static int count_finish;
	
	public static void main(String[] args) throws Exception {
		
		
		Map trace_m_totalFinal = Maps.newConcurrentMap();
		
		FileUtils.forceMkdir(new File(out_dir));
	
	
		
		Timer tmr=new Timer() ;
		tmr.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Map trace_m_totalFinal = Maps.newConcurrentMap();
			//	trace_m_totalFinal.put("pool_queuo_size", count);
				trace_m_totalFinal.put("count_submit2pool", count_submit2pool);
				trace_m_totalFinal.put("count_finish", count_finish);
				logger.info(JSON.toJSONString(trace_m_totalFinal, true));
			}
		}, 1000, 3000);
		// 处理下级多层目录
		Files.walkFileTree(Paths.get(dir_SOURCE), new SimpleFileVisitor<Path>() {

			// 处理文件
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
logger.info("visitFile:"+file);
				try {
					// walkFile log
					walkitem(file);
				
					return FileVisitResult.CONTINUE; // 没找到继续找

				} catch (Exception e) {
					logger.error(e);
				}
				return FileVisitResult.CONTINUE;

			}

		});
	
		// trace_m.put("hitDocCount", hitDocCount);
		 
		trace_m_totalFinal.put("rzt", rzt);
		System.out.println(JSON.toJSONString(trace_m_totalFinal, true));
	
		//tmr.
	}

	private static void walkitem(Path file) throws Exception {
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
		// if( ext.equals("zip"))
		// return FileVisitResult.CONTINUE; // 没找到继续找
		if (FilenameUtilsT55.isImgFile(ext))
			return; // 没找到继续找
		if (ext.equals("zip"))
			processZipfile(absolutePath);
		else if (ext.equals("docx")) {

		} else {

			String filecon = FileUtilsAti.readFileToStringAutoDetectEncode(file.toFile());
			// txt

			processCoretxtWithPool(file.toFile().getAbsolutePath(), file.toFile().getAbsolutePath().toString(), filecon);

		}
	}

	static List rzt = Lists.newArrayList();

	private static void processZipfile(String absolutePath) {
		ZipUtilV2t55.filelist(absolutePath, "gbk", new Consumer<Map>() {

			@Override
			public void accept(Map m) {
				try {
					ZipEntry ZipEntry1 = (ZipEntry) m.get("zipEntry");
					ZipFile ZipFile1 = (ZipFile) m.get("zipFile");
					logger.info("ZipEntry1.getName:" + ZipEntry1.getName().toString());
					if (FilenameUtilsT55.isImgFileByFullname(ZipEntry1.getName()))
						return;
					InputStream InputStream1 = null;

					InputStream1 = ZipFile1.getInputStream(ZipEntry1);
					// String
					// encode=FileEncodeUtil.getTxtEncode(ZipFile1.getInputStream(ZipEntry1));
					// String filecon = toStringMultiencodeAppend(InputStream1,
					// encode);//def read is gbk
					String filecon = IOUtilsAti.toStringAutoencode(InputStream1, ZipEntry1.getSize());
					// String filecon = IOUtils.toString(InputStream1);
					if (ZipEntry1.getName().endsWith(".html") || ZipEntry1.getName().endsWith(".htm")) {
						String ZipEntry1txt = htmlJsoupUtil.html2txt(filecon);
						processCoretxtWithPool(absolutePath, ZipEntry1.toString(), ZipEntry1txt);

					} else // txt
					{
						processCoretxtWithPool(absolutePath, ZipEntry1.toString(), filecon);
					}
					// System.out.println(filecon);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ExUtil.throwExV2(e);// for dbg
				}

			}

			private String toStringMultiencodeAppend(InputStream InputStream1, String encode) throws IOException {
				String rzt, part1, part2;
				if (encode.toUpperCase().equals("GBK")) // nobom file
				{
					part1 = IOUtils.toString(InputStream1, encode);
					part2 = IOUtils.toString(InputStream1, "utf8");
					return part1 + "\r\n" + part2;
				} else
					return IOUtils.toString(InputStream1, encode);

			}

		});
	}
	private static void processCoretxt(String zipfile2, String ZipEntry1, String txt) throws Exception {
		// List<Map> li = SearchScanUtil.searchDefPad(txt, key);
				Map trace_m_cur = Maps.newLinkedHashMap();

				// trace_m_cur.put("key", key);
				trace_m_cur.put("ZipFile1", zipfile2.toString());
				// trace_m_cur.put("hitDocCount_cur", hitDocCount);
				trace_m_cur.put("ZipEntry1", ZipEntry1);
				trace_m_cur.put("txt",StringUtils.left( txt,200));
				// hitDocCount++;
			//	System.out.println(txt);
			//	System.out.println(JSON.toJSONString(trace_m_cur, true));
				// rzt.add(trace_m_cur);
				logger.info(JSON.toJSONString(trace_m_cur, true));

				Message message = new MimeMessage(Session.getInstance(System.getProperties()));
				// message.setFrom(new InternetAddress(from));
				// message.setRecipients(Message.RecipientType.TO,
				// InternetAddress.parse(to));
				message.setSubject(ZipEntry1);
				message.setText(txt + "\r\n" + ZipEntry1);

				String name = FilenameUtils.getName(ZipEntry1);
				// String name=;
				try {

					message.writeTo(new FileOutputStream(new File(out_dir + "\\" + name + ".eml")));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					logger.error(e);
				}
	}

	private static void processCoretxtWithPool(String zipfile2, String ZipEntry1, String txt) throws Exception {

		
		FutureTask<Object> FutureTask_updateCviBdj = new FutureTask(new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				try {
					processCoretxt(zipfile2,ZipEntry1,txt);
					count++;count_finish++;
				} catch (Exception e) {
					logger.error(e);
				}
				
				return "rzt";
			}
		});
		es_pool.submit(FutureTask_updateCviBdj, "threadName");
		count_submit2pool++;
		
		
		

	}

}
