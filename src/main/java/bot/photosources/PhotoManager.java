package bot.photosources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.photosources.photo.PhotoInformation;

public abstract class PhotoManager {

	public abstract PhotoInformation getRandomPhoto();
	
	public abstract List<PhotoInformation> getAllPhotosFromPage();
	
	private List<String> getAllElementsFromPage(String url, String regExp)
	{
		List<String> result = new ArrayList<String>();
		String pageCode = "";
		try {
			pageCode = getUrlSource(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(pageCode);
		if(!matcher.find())
			System.out.println("Failed :(");
		while (matcher.find()) {
			//System.out.println(matcher.group(1));
			result.add(matcher.group(1));	//TODO: override with .replaceAll("m6/", "")+"jpg"
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
}
