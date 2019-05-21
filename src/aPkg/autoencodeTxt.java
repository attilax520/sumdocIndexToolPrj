package aPkg;import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.attilax.io.FileUtilsAti;
import com.google.common.collect.Maps;

public class autoencodeTxt {

	public static void main(String[] args) throws IOException {
		String txtF="D:\\0db\\UKE HQ UKE集团总部 r71a.txt";
		
		txtF="D:\\0db\\Atitit  安卓系统的自动化GUI测试工具 （模拟操作工具）.docx.txt";//utf no bom
		String filecon = FileUtils.readFileToString(new File(txtF));
		// txt
		
		filecon=FileUtilsAti.readFileToStringAutoDetectEncode(txtF);
		System.out.println(	filecon );
		Timer tmr=new Timer() ;
tmr.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Map trace_m_totalFinal = Maps.newConcurrentMap();
			//	trace_m_totalFinal.put("pool_queuo_size", count);
			//	logger.info(JSON.toJSONString(trace_m_totalFinal, true));
				System.out.println("--");
			}
		}, 1000, 3000);

	}

}
