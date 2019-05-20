package com.attilax.search;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SearchScanUtil {

	public static void main(String[] args) {
		String t = "软件理论方面的书籍 Atitit软件理论方面的书籍 目录 1. 计算机科学分为计算机理论和计算机应用。计算机基础理论包含以";
		String k = "理论";
//		String t = "abcdefabc";
//		String k = "abc";
		List<Map> search = search(t, k,10);
		System.out.println(JSON.toJSONString(search,true));

	}
	
	/**
	 * 
	 * @param txt
	 * @param key
	 * @param padLen start id len =80 all..so pad is 40
	 * @return
	 */
	public static List<Map> searchDefPad(String txt, String key) {
		
		 int padLen=(80-key.length())/2;
		List<Map> li = Lists.newLinkedList();
		int fromIndex = 0;
		while (true) {
			int position = txt.indexOf(key, fromIndex);
			if(position==-1)
				return li;
			Map m = Maps.newLinkedHashMap();
			m.put("txt", txt);
			m.put("key", key);
			m.put("positionStart", position);
			m.put("padLen", padLen);
			m.put("keyWithPad", getkeyWithPad(txt,position,padLen,key));
			li.add(m);
			fromIndex=position+key.length();

		}

	}

	/**
	 * 
	 * @param txt
	 * @param key
	 * @param padLen start id len =80 all..so pad is 40
	 * @return
	 */
	public static List<Map> search(String txt, String key,int padLen) {
		List<Map> li = Lists.newLinkedList();
		int fromIndex = 0;
		while (true) {
			int position = txt.indexOf(key, fromIndex);
			if(position==-1)
				return li;
			Map m = Maps.newLinkedHashMap();
			m.put("txt", txt);
			m.put("key", key);
			m.put("positionStart", position);
			m.put("padLen", padLen);
			m.put("keyWithPad", getkeyWithPad(txt,position,padLen,key));
			li.add(m);
			fromIndex=position+key.length();

		}

	}
	private static Object getkeyWithPad(String txt, int position, int padLen, String key) {
		 int beginIndex=position-padLen;
		 if(beginIndex<0)
			 beginIndex=0;
		 int endIndex =position+key.length()+padLen;
		 if(endIndex>txt.length())
			 endIndex=txt.length();
		return txt.substring(beginIndex, endIndex);
	}

	public static List<Map> search(String txt, String key) {
		List<Map> li = Lists.newLinkedList();
		int fromIndex = 0;
		while (true) {
			int position = txt.indexOf(key, fromIndex);
			if(position==-1)
				return li;
			Map m = Maps.newLinkedHashMap();
			m.put("txt", txt);
			m.put("key", key);
			m.put("positionStart", position);
			li.add(m);
			fromIndex=position+key.length();

		}

	}

}
