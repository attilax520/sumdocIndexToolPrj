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

public class ZipCountTool {
	static int hitDocCount;
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);

	public static void main(String[] args) throws Exception {
		String dir = "F:\\MIDI资源整理合集ziped";
		String key = "面向文档";

		// 处理下级多层目录
		Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {

			// 处理文件
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				// walkFile log
				try {
					return visitFileEvent(file);
				} catch (Exception e) {
					logger.error(e);
					return FileVisitResult.CONTINUE; // 没找到继续找
				}
				

			}

		});
		Map trace_m = Maps.newConcurrentMap();
		trace_m.put("hitDocCount", hitDocCount);
		trace_m.put("rzt", rzt);
		System.out.println(JSON.toJSONString(trace_m, true));

	}

	private static FileVisitResult visitFileEvent(Path file) {
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
		if (!ext.equals("zip"))
			return FileVisitResult.CONTINUE; // 没找到继续找

		ZipUtilV2t55.filelist(absolutePath, "gbk", new Consumer<Map>() {

			@Override
			public void accept(Map m) {

				ZipEntry ZipEntry1 = (ZipEntry) m.get("zipEntry");
				ZipFile ZipFile1 = (ZipFile) m.get("zipFile");
				if( ZipEntry1.isDirectory())
					return;
				logger.info("ZipEntry1.getName:" + ZipEntry1.getName().toString());
				hitDocCount++;
			}

		});

		return FileVisitResult.CONTINUE; // 没找到继续找
	}

	static List rzt = Lists.newArrayList();

	 
 

}
