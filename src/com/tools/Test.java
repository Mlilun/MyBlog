package com.tools;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "a��b, c,d,f";
		String regex = ",|��|\\s+";//������Ϊ���� ���� �ո�ָ�
		String[] types = str.split(",|��");
		for (String type : types) {
			System.out.println(type.trim());
		}
		
		
	}

}
