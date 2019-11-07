// CREATING A SINGLE ISSUE IN JIRA

package com.nokia.jiraauth;

import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.URL;
//import org.json.*;
//import org.json.simple.*;
import org.json.simple.JSONObject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HttpPostApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(HttpPostApplication.class, args);
		
		try {
			URL url = new URL("https://jiradc.int.net.nokia.com/rest/api/2/issue");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            String encodedData=getJSON_Body();
            System.out.println(encodedData);
            conn.setRequestMethod("POST");
            
            String userName="kharge";
            String password="Digital@123";
            String userpass = userName + ":" + password; 
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
            conn.setRequestProperty ("Authorization", basicAuth); 
            
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.getOutputStream().write(encodedData.getBytes());
            
            System.err.println(conn.getResponseCode());
        
            try {
            	 InputStream inputStream=conn.getInputStream();
            	 System.out.println(inputStream);
                }
            
            catch (IOException e)
            {
            	System.out.println(e.getMessage());
            }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
            	 
       

	@SuppressWarnings("unchecked")
	private static String getJSON_Body()
       {		
		  
         JSONObject createIssue = new JSONObject();
         
         JSONObject project = new JSONObject();
         project.put("key","DIGNENOKIAACEDDE");
         
         
         JSONObject issuetype = new JSONObject();
         issuetype.put("name", "Task");
                 
         JSONObject data = new JSONObject();
         
         String summary = "REST API for issue";
         String description = "Creating of an issue using project keys and issue type names using the REST API";
         
         data.put("project", project);
         data.put("summary", summary);
         data.put("description", description);
         data.put("issuetype", issuetype);
         createIssue.put("fields", data);
        
         
         return createIssue.toString();
         
        
         
       }
}






		
		
		
		
		
		
		
         
		         
		
		
		
		     
		
	     
   		
      



		
    	   
    	   
    	   	      	   
    	 
       


