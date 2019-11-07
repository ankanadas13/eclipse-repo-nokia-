//CREATING MULTIPLE ISSUES IN JIRA


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

import org.json.JSONArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HttpPost1Application 
{
   public static void main(String[] args) 
	{
		SpringApplication.run(HttpPost1Application.class, args);
		
		try {
			URL url = new URL("https://jiradc.int.net.nokia.com/rest/api/2/issue/bulk");
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
		
		//BUG IS CREATED
		
		JSONObject createIssue = new JSONObject();
		
		JSONObject project = new JSONObject();
        project.put("key","DIGNENOKIAACEDDE");
        
        JSONObject issuetype1 = new JSONObject();
        issuetype1.put("name","Bug");
        
               
        JSONObject data1 = new JSONObject();
        
        String summary = "REST API for issue";
        String description = "Creating of an issue using project keys and issue type names using the REST API";
        
        data1.put("project", project);
        data1.put("summary", summary);
        data1.put("description", description);
        data1.put("issuetype", issuetype1);
        createIssue.put("fields", data1);
        
        JSONArray ja = new JSONArray();
        ja.put(createIssue);
        
        
        
        //NEW FEATURE IS CREATED
        
        JSONObject issuetype2 = new JSONObject();
        issuetype2.put("name","New Feature");
        
        JSONObject data2 = new JSONObject();
              
        data2.put("project", project);
        data2.put("summary", summary);
        data2.put("description", description);
        data2.put("issuetype", issuetype2);
        createIssue.put("fields", data2);
        ja.put(createIssue);
       
        
        
        //TASK IS CREATED
        
        JSONObject issuetype3 = new JSONObject();
        issuetype3.put("name","Task");
        
        JSONObject data3 = new JSONObject();
              
        data3.put("project", project);
        data3.put("summary", summary);
        data3.put("description", description);
        data3.put("issuetype", issuetype3);
        createIssue.put("fields", data3);
        ja.put(createIssue);
        
        
        
        //IMPROVEMENT IS CREATED
        
        JSONObject issuetype4 = new JSONObject();
        issuetype4.put("name","Improvement");
        
               
        JSONObject data4 = new JSONObject();
              
        data4.put("project", project);
        data4.put("summary", summary);
        data4.put("description", description);
        data4.put("issuetype", issuetype4);
        createIssue.put("fields", data4);
        ja.put(createIssue);
        
      
        
        //TEST IS CREATED
        
        JSONObject issuetype5 = new JSONObject();
        issuetype5.put("name","Test");
        
               
        JSONObject data5 = new JSONObject();
              
        data5.put("project", project);
        data5.put("summary", summary);
        data5.put("description", description);
        data5.put("issuetype", issuetype5);
        createIssue.put("fields", data5);
        ja.put(createIssue);
        
      
        
        //EPIC IS CREATED
        
        /*JSONObject issuetype6 = new JSONObject();
        issuetype6.put("name","Epic");
               
        JSONObject data6 = new JSONObject();
              
        data6.put("project", project);
        data6.put("summary", summary);
        data6.put("description", description);
        data6.put("issuetype", issuetype6);
        createIssue.put("fields", data6);
        ja.put(createIssue);*/
        
        
        JSONObject mainObj = new JSONObject();
		 mainObj.put("issueUpdates", ja);
		 
		return mainObj.toString();
         
        
       }
}

