package aPkg;

import java.io.InputStream;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.alibaba.fastjson.JSON;
import com.attilax.compress.ZipUtilV2t55;
import com.attilax.coreLuni.util.ExUtil;
import com.attilax.data.csv.htmlJsoupUtil;
import com.attilax.io.FileEncodeUtil;
import com.google.common.collect.Maps;

import comattilax.sumdoclist.FileTraveList;

public class zipUtfEncodeTest {
	static org.apache.log4j.Logger logger = Logger.getLogger(FileTraveList.class);

	public static void main(String[] args) {
		String absolutePath = "d:\\0db\\0db.zip";
		ZipUtilV2t55.filelist(absolutePath, "gbk", new Consumer<Map>() {

			@Override
			public void accept(Map m) {
				try {
					ZipEntry ZipEntry1 = (ZipEntry) m.get("zipEntry");
					ZipFile ZipFile1 = (ZipFile) m.get("zipFile");
					logger.info("ZipEntry1.getName:" + ZipEntry1.getName().toString());

					InputStream InputStream1 = null;

					InputStream1 = ZipFile1.getInputStream(ZipEntry1);  
					String encode=FileEncodeUtil.getTxtEncode(ZipFile1.getInputStream(ZipEntry1));
					String filecon = IOUtils.toString(InputStream1,encode);//def read is gbk
			//	.getClass().	filecon = IOUtils.toString
					Map trace_m_cur = Maps.newLinkedHashMap();

					// trace_m_cur.put("key", key);
					trace_m_cur.put("ZipFile1", ZipFile1.toString());					
					trace_m_cur.put("ZipEntry1", ZipEntry1);
					trace_m_cur.put("ZipEntry1filecon", filecon);
					trace_m_cur.put("encode", encode);
					// trace_m_cur.put("hit line", li);
				 
					System.out.println(JSON.toJSONString(trace_m_cur, true));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ExUtil.throwExV2(e);
				}

			}

		});

	}

}
