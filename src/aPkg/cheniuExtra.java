package aPkg;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.attilax.text.HeziUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class cheniuExtra {

	public static void main(String[] args) throws IOException {
		process(new Consumer<String>() {

			@Override
			public void accept(String t) {
			      try {
					FileUtils.write(new File("d:\\chenyu3w.txt"), t+"\r\n", true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} );

	}

	private static void process(Consumer<String> consumer) throws IOException {
		List<Map>  errLirzt=Lists.newArrayList();
		String f="d:\\all.txt";
		List<String> li=FileUtils.readLines(new File(f),"utf8");
		int n = 0;
		int n_correct = 0;
		for (String line : li) {
			if(line.trim().length()==0)
				continue;
		 
			
			try {
				line=line.trim();
				if(line.length()<4)
					continue;
				line=line	.substring(0, 4);
				if(line.startsWith("--"))
					continue;
				
				if(HeziUtil.count(line)<4)
					continue;
				
				n++;
				System.out.println(line);
				n_correct++;
				consumer.accept(line);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println( e.getMessage()+":??:: "+ line);
				Map m=Maps.newConcurrentMap();
				m.put("line", line);
				errLirzt.add(m);
			}
			
		}
		
		Map trace_final=Maps.newConcurrentMap();
		trace_final.put("errLi", errLirzt);
		trace_final.put("errLirzt.size()", errLirzt.size());
		trace_final.put("foreachLine", n);
		trace_final.put("n_correct", n_correct);
		System.out.println(JSON.toJSONString(trace_final, true));
		System.out.println(errLirzt.size());
	}

}
