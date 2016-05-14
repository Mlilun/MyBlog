package com.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Html{    
    public static String removeTag(String content){
    	Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
    	Matcher matcher = pattern.matcher(content);
    	String string = matcher.replaceAll("");
    	return string;
    }
    
    public static String getImagePath(String content){
    	String path = null;
    	
    	Pattern pattern = Pattern.compile("<img[^>]*src*=*\"([^\"]*)\"[^>]*>");//()��ΪҪ������
    	Matcher matcher = pattern.matcher(content);
    	if (matcher.find()) {
    		System.out.println(matcher.group(0)); //��������ƥ���� ��<img src="">
    		System.out.println(matcher.group(1)); // ()���ƥ����
			path = matcher.group(1);
		}

    	return path;
    }

}