package com.nokia.jiraauth;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.ParseException;


@SpringBootApplication
public class HttpSearch5Application 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(HttpSearch5Application.class, args);
		
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("MULTIPLE ISSUE CREATION JSON BOSY.json"))
		{
			Object obj = jsonParser.parse(reader);
			 
            JSONArray issueList = (JSONArray) obj;
            System.out.println(issueList);
            
            issueList.forEach( issue -> parseIssueObject( (JSONObject) issue ) );
		}
		
		try{
        	URL url = new URL("https://jiradc.int.net.nokia.com/rest/api/2/issue/bulk");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            
            String userName="kharge";
            String password="Digital@123";
            String userpass = userName + ":" + password; 
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
            connection.setRequestProperty ("Authorization", basicAuth); 
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
	}
		catch (FileNotFoundException e) 
		{
            e.printStackTrace();
		}
		catch (MalformedURLException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
		catch (ParseException e) 
		{
            e.printStackTrace();
        }

}
}

