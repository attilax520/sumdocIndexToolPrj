package aPkg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.alibaba.fastjson.JSON;
import com.attilax.compress.ZipUtilV2t55;
import com.attilax.data.csv.htmlJsoupUtil;
import com.attilax.io.FilenameUtilsT55;
import com.attilax.search.SearchScanUtil;
import com.attilax.util.CantFindEx;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import comattilax.sumdoclist.FileTraveList;

public class SearchZip {
	static int hitDocCount;
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);

	public static void main(String[] args) throws Exception {
		String dir = "D:\\000atibek\\000sum doc body 4fulltext v2 t55";
		String key = "面向文档";

		// 处理下级多层目录
		Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {

			@Override // 处理目录
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println(dir);
				return FileVisitResult.CONTINUE;

			}

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
					ZipUtilV2t55.filelist(absolutePath, "gbk", new Consumer<Map>() {

						@Override
						public void accept(Map m) {

							ZipEntry ZipEntry1 = (ZipEntry) m.get("zipEntry");
							ZipFile ZipFile1 = (ZipFile) m.get("zipFile");
							logger.info("ZipEntry1.getName:" + ZipEntry1.getName().toString());

							// try {
							// ZipUtilV2t55.upzipSingleOutput(zipFile,ZipEntry1,new
							// File("aa"));
							// } catch (IOException e2) {
							// // TODO Auto-generated catch block
							// e2.printStackTrace();
							// }
							InputStream InputStream1 = null;
							try {
								InputStream1 = ZipFile1.getInputStream(ZipEntry1);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							// jeig sh file name
							// byte[] ba=ZipEntry1.getLocalFileDataExtra();
							// String s = null;
							// try {
							// s = new String(ba,"utf8");
							// } catch (UnsupportedEncodingException e) {
							// // TODO Auto-generated catch block
							// e.printStackTrace();
							// }
							try {
								String filecon = IOUtils.toString(InputStream1);
								if (ZipEntry1.getName().endsWith(".html") || ZipEntry1.getName().endsWith(".htm")) {
									String ZipEntry1txt = htmlJsoupUtil.html2txt(filecon);
									processCoretxt(key, file.toFile().getAbsolutePath(), ZipEntry1.toString(), ZipEntry1txt);

								} else //txt
								{
									processCoretxt(key, file.toFile().getAbsolutePath(), ZipEntry1.toString(), filecon);
								}
								//	System.out.println(filecon);

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						
					});
				else if (ext.equals("docx"))
				{
					
				}else{
					String filecon=FileUtils.readFileToString(file.toFile() );
					//txt
					processCoretxt(key, file.toFile().getAbsolutePath(), file.toFile().getAbsolutePath().toString(), filecon);
				}
				return FileVisitResult.CONTINUE; // 没找到继续找
			}

		});
		Map trace_m = Maps.newConcurrentMap();
		trace_m.put("hitDocCount", hitDocCount);trace_m.put("rzt", rzt);
		System.out.println(JSON.toJSONString(trace_m,true));

	}
	static List rzt=Lists.newArrayList();
	private static void processCoretxt(String key, String file, String ZipEntry1, String txt) {
		if (txt.contains(key)) {
			List<Map> li = SearchScanUtil.searchDefPad(txt, key);
			Map trace_m_cur = Maps.newLinkedHashMap();

			trace_m_cur.put("key", key);
			trace_m_cur.put("ZipFile1", file.toString());
			trace_m_cur.put("hitDocCount_cur", hitDocCount);
			trace_m_cur.put("ZipEntry1", ZipEntry1);
			trace_m_cur.put("hit line", li);
			hitDocCount++;
			System.out.println(txt);
			System.out.println(JSON.toJSONString(trace_m_cur, true));
			rzt.add(trace_m_cur);

		}
	}
	
//	private static void processCoretxt(String key, Path file, String ZipEntry1, String txt) {
//		if (txt.contains(key)) {
//			List<Map> li = SearchScanUtil.searchDefPad(txt, key);
//			Map trace_m_cur = Maps.newLinkedHashMap();
//
//			trace_m_cur.put("key", key);
//			trace_m_cur.put("ZipFile1", file.toAbsolutePath().toString());
//			trace_m_cur.put("hitDocCount_cur", hitDocCount);
//			trace_m_cur.put("ZipEntry1", ZipEntry1);
//			trace_m_cur.put("hit line", li);
//			hitDocCount++;
//			System.out.println(txt);
//			System.out.println(JSON.toJSONString(trace_m_cur, true));
//
//		}
//	}

}
