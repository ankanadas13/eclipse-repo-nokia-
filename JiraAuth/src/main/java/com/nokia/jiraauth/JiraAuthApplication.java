//OPENING JIRA DASHBOARD


package com.nokia.jiraauth;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JiraAuthApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(JiraAuthApplication.class, args);
		
		//Jira dashboard opens

String data = "Jira Dashboard opens";
        
        try{
        	URL url = new URL("https://jiradc.int.net.nokia.com/secure/Dashboard.jspa");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(data);
            osw.flush();
            osw.close();
            System.err.println(connection.getResponseCode());
            }
        
        catch (MalformedURLException e) 
        {
            e.printStackTrace();
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        System.out.println(data);
     
     }
}
