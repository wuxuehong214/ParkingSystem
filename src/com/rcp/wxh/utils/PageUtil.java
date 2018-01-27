package com.rcp.wxh.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ҳ����
 * @author wuxuehong  2011-11-14
 *
 */
public class PageUtil {
	
	/**
	 * ���� ҳ�� ÿҳ��ʾ��Ŀ ��ȡ��ǰҳ���󼯺�
	 * @param list
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public static List<Object> getPageContent(List<Object> list, int curPage, int pageSize){
		int startIndex = pageSize*(curPage-1);
		List<Object> rlist = new ArrayList<Object>();
		for(int i=startIndex; i<startIndex+pageSize&&i<list.size(); i++){
			rlist.add(list.get(i));
		}
		return rlist;
	}

}
