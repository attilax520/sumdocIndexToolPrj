package aPkg;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.Word;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;

public class segmentMMsegExtraCheniuFromLyric {
	
	public static void main(String[] args) throws IOException {
		Dictionary dic = Dictionary.getInstance();
		Seg seg = new ComplexSeg(dic); 
		Set<String> set=Sets.newLinkedHashSet();
		
		String dir="D:\\notdel\\music lyric zip v3 s525";
		File[] fa=new File(dir).listFiles();
		int file_Indx = 0;
		for (File f : fa) {
		
			file_Indx++;
			System.out.println("file_Indx"+file_Indx);
			String txt="受一股来自中西伯利亚的强冷空气影响，本市出现大风降温天气分节阅读辅以假期";
			txt=FileUtils.readFileToString(f);
			System.out.println(txt);
			MMSeg mmSeg = new MMSeg(new StringReader(txt), seg);
			Word word = null;
			System.out.println();
			while((word=mmSeg.next())!=null) {
				 
				set.add(word.getString());
			//	System.out.println(JSON.toJSONString(word));

//				System.out.print(word.getString()+" -> "+word.getStartOffset());
//				//offset += word.length;
//				System.out.println(", "+word.getEndOffset()+", "+word.getType());
//					
//				
			}
		}
		String s=Joiner.on("\r\n").join(set);
		FileUtils.write(new File("D:\\lyricCheniuSet.txt"), s);
		
		
	}

}
