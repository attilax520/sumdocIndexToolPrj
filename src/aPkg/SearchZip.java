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
import com.attilax.io.FilenameUtilsT55;
import com.attilax.util.CantFindEx;
import com.google.common.collect.Maps;

import comattilax.sumdoclist.FileTraveList;

public class SearchZip {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);
	public static void main(String[] args) throws Exception {
		String dir="D:\\000atibek\\000sum doc body 4fulltext v2 t55\\blog";
		
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
				if(fpath.contains("是印度最古老的一部法律文献"))
					System.out.println("dbg");
				String string = "\t正在访问" + fpath + "文件";
				System.out.println(string);
				logger.info(string);
				ZipFile zipFile = new ZipFile(file.toFile().getAbsolutePath());
				ZipUtilV2t55.filelist(file.toFile().getAbsolutePath(), "gbk", new Consumer<ZipEntry>() {

					@Override
					public void accept(ZipEntry ZipEntry1) {
						logger.info(ZipEntry1.getName().toString());
						InputStream InputStream1 = null;
						try {
							InputStream1 = zipFile.getInputStream(ZipEntry1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//jeig sh file name
//						byte[] ba=ZipEntry1.getLocalFileDataExtra();
//						String s = null;
//						try {
//							s = new String(ba,"utf8");
//						} catch (UnsupportedEncodingException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						try {
							System.out.println(IOUtils.toString(InputStream1));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				
				
				return FileVisitResult.CONTINUE; // 没找到继续找
			}

			

		});
	}

}
