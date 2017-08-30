package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

}
