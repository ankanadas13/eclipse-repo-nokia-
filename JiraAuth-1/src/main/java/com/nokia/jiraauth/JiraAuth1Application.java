//JIRA LOGIN PAGE


package com.nokia.jiraauth;


import java.io.IOException;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JiraAuth1Application 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(JiraAuth1Application.class, args);
		
		//Jira login page

String data = "{\'username\':{\'kharge':{\'password\':\'Digital@123'}}}";
        
        try{
        	URL url = new URL("https://jiradc.int.net.nokia.com/login.jsp");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            String userName="kharge";
            String password="Digital@123";
            String userpass = userName + ":" + password; 
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
            connection.setRequestProperty ("Authorization", basicAuth); 
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
