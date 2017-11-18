package bot;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import bot.db.dao.MessageManager;
import bot.db.model.Message;


public class Testing {
	
	final static Logger logger = Logger.getLogger(Testing.class);

	public static void main(String[] args)
	{
		//getBoobs();
		
		//getPhotoArchive();
		//System.out.println(Util.getPhotoArchive());
		
		//List<Message> messages = new MessageManager().listMessages();
		//System.out.println(messages.get(0).getLastName());
		
	}
	
	@Test
	public void testConfig()
	{
		assertEquals(Util.getPropertyValue("test"), "test");
	}
	
	
	private static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection uc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }
	
	
	private static void getBoobs()
	{
		String pageCode = "";
		try{
			pageCode = getUrlSource("http://oboobs.ru/550/");
			/*System.out.println("Got it!");*/
			
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
			if(!matcher.find())
				System.out.println("Failed :(");
			while (matcher.find()) {
				System.out.println(matcher.group(1));
				
			}
		}
	}
	
	
}
