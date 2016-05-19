package com.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://i.tianqi.com/index.php?c=code&id=1 ���������ҳ  ��ȡ���������͵ص���Ϣ
 */
public class Weather {
	final static String URL = "http://i.tianqi.com/index.php?c=code&id=1";
	static String html = getHtmlResource(URL, "GBK");
	
	private static String findMessage(String regex){
		String message = null;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);
		if (matcher.find()) {
  		System.out.println(matcher.group(0)); //��������ƥ���� ��<img src="">
//			System.out.println(matcher.group(1)); // ()���ƥ����
			message = matcher.group(1);
		}

		return message;
	}
	
	private static String getHtmlResource(String pageUrl,String encoding){  
        StringBuffer sb = new StringBuffer();  
        try {  
            //����һURL����  
            URL url = new URL(pageUrl);  
            //ʹ��openStream�õ�һ���������ɴ˹���һ��BufferedReader����  
            BufferedReader in = new BufferedReader(new InputStreamReader(url  
                    .openStream(), encoding));  
            String line;  
            //��ȡwww��Դ  
            while ((line = in.readLine()) != null) {  
                sb.append(line);  
                sb.append("\n");
            }  
            in.close();  
        } catch (Exception ex) {  
            System.err.println(ex);  
        }  
        return sb.toString();  
	}
	
	public static String getCity(){
    	String regex = "<div class=\"cityname\">([^</div>]*)</div>";
    	return findMessage(regex);
    }
	
	public static String getWeatherImagePath(){
	   	String regex = "img class='pngtqico'[^>]*src='([^']*)'";
    	return findMessage(regex);
	}
	
	public static String getWeatherInfo(){
       	String regex = "<img class='pngtqico'[^<]*/>([^\n]*)";
    	return findMessage(regex);
		
	}
//	<span class="cc30 f1">28��</span>��<span class="c390 f1">19��</span><span class="wind" style="padding:0 2px;">�Ϸ� 5-6�� </span>
	public static String getTemperature(){
       	String regexHeigh = "<span class=\"cc30 f1\">([^<]*)</span>";
       	String regexLow = "<span class=\"c390 f1\">([^<]*)</span>";
    	return findMessage(regexHeigh) + "~" + findMessage(regexLow);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCity());
		System.out.println(getWeatherInfo());
		System.out.println(getWeatherImagePath());
		System.out.println(Timer.getWeek());
		System.out.println(getTemperature());
	}

}
