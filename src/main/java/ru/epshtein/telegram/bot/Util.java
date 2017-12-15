package ru.epshtein.telegram.bot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	public static List<String> getListOfBoobs(int pageNumber)
	{
		String pageCode = "";
		List<String> result = new ArrayList<String>();
		try{
			pageCode = getUrlSource("http://oboobs.ru/"+pageNumber+"/");
			//System.out.println("Got it!");
			
		}
		catch (Exception e) {
			pageCode = "failed";
			e.printStackTrace();
		}
		if(!pageCode.equals("failed"))
		{
			String regExp = "\\/([0-9]+)\\.jpg";
			Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(pageCode);
			while (matcher.find()) {
				result.add(matcher.group(1));
			}
		}
		
		return result;
	}
	
	
	public static String getPhotoArchive()
	{
		List<String> galleries = new ArrayList<String>();
		String pageCode = "";
		String date = Integer.toString(new Random().nextInt(12)+1)+"-"+Integer.toString(new Random().nextInt(12)+2006);
		if(date.length()==6)
			date = "0"+date;
		try{
			pageCode = getUrlSource("http://www.kindgirls.com/photo-archive/?s="+date);
			
		}
		catch (Exception e) {
			pageCode = "failed";
			e.printStackTrace();
		}		
		String regExp = "href=\"/gallery/(.*?)\"";
		Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(pageCode);
		if(!matcher.find())
			System.out.println("Failed :(");
		while (matcher.find()) {
			//System.out.println(matcher.group(1));
			galleries.add(matcher.group(1));
		}
		String galleryLink = "http://www.kindgirls.com/gallery/" + galleries.get(new Random().nextInt(galleries.size()));
		List<String> links = new ArrayList<String>();
		try{
			pageCode = getUrlSource(galleryLink);
			//System.out.println(galleryLink);
			regExp = "src=\"(.*?)jpg\"";
			pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(pageCode);
			if(!matcher.find())
				System.out.println("Failed :(");
			while (matcher.find()) {
				//System.out.println(matcher.group(1));
				links.add(matcher.group(1).replaceAll("m6/", "")+"jpg");
			}
		}
		catch (Exception e) {
			pageCode = "failed";
			e.printStackTrace();
		}		
		//System.out.println(pageCode);	
		return links.get(new Random().nextInt(links.size()))	;
	}
	
	private static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }
	
	public static void log(String firstName, String lastName, String userId, String username, String txt, String botAnswer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + firstName + " " + lastName + ". (id = " + userId + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + botAnswer);
    }
	
	public static String getPropertyValue(String propertyName)
	{
		String result = "";
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			result = prop.getProperty(propertyName);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
