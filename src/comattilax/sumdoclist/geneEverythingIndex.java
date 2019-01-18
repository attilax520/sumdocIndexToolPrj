package comattilax.sumdoclist;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.attilax.io.filex;

public class geneEverythingIndex {
	
	public static void main(String[] args) throws IOException {
		 t1("D:\\l3 sumdoc s2018 torb31 v2 t1_filelist.txt");
		String f="s9 doc compc v2 s025\\河北英创科技有限公司测评资料\\短信接口安全评测资料补充\\Atitit 项目工作常见问题与 应急预案(1).docx";
	String zipname=getZipName(f);
	
	String filename=getmainFilename(f);
			
	String newFile=zipname+"__"+filename;
			System.out.println(newFile);
	}

	private static String getmainFilename(String f) {
		String[] fa=f.split("\\\\");
		return fa[fa.length-1];
	}

	private static String getZipName(String f) {
		String[] fa=f.split("\\\\");
		return fa[0];
	}

	private static void t1(String file) throws IOException {
//		String file="D:\\00l3 sum doc s2018 until9 doc list.txt";
//		file="C:\\Users\\Administrator\\Desktop\\sumdoclist\\00l3 sum doc all doc list.txt";
//		file="";
	 
		List<String> li=FileUtils.readLines(new File(file),"utf8");
		for (String f : li) {
			//line=line.replaceAll("/", "__");
			String zipname=getZipName(f);
			
			String filename=getmainFilename(f);
					
			String newFile=zipname+"__"+filename;
			try {
				 FileUtils.writeStringToFile(new File("d:\\l4 sumdoc s20180901-1231 everthing index\\"+newFile), "aaa");
			} catch (Exception e) {
			e.printStackTrace();
			}
		
			System.out.println(newFile);
		}
	}

}
