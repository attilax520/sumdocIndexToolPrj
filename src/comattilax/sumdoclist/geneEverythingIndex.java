package comattilax.sumdoclist;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.attilax.io.filex;

public class geneEverythingIndex {
	
	public static void main(String[] args) throws IOException {
		String file="D:\\00l3 sum doc s2018 until9 doc list.txt";
		file="C:\\Users\\Administrator\\Desktop\\sumdoclist\\00l3 sum doc all doc list.txt";
	 
		List<String> li=FileUtils.readLines(new File(file),"gbk");
		for (String line : li) {
			line=line.replaceAll("/", "__");
			try {
				 FileUtils.writeStringToFile(new File("d:\\l4 sumdoc s2000-201801 everthing index\\"+line), "aaa");
			} catch (Exception e) {
			e.printStackTrace();
			}
		
			System.out.println(line);
		}
	}

}
