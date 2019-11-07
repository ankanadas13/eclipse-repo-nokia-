//CREATING EPIC, TASK AND NEE FEATURE USING ADDITIONAL FIELDS

package com.nokia.jiraauth;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

import org.json.JSONArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HttpSearch3Application 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(HttpSearch3Application.class, args);
			
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
            
             try 
             {
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
		
		JSONObject data1 = new JSONObject();
		JSONObject data2 = new JSONObject();
		JSONObject data3 = new JSONObject();
		
		JSONObject project = new JSONObject();
        project.put("key","DIGNENOKIAACEDDE");
        
        JSONObject assignee = new JSONObject();
        assignee.put("name", "kharge");
        
        JSONObject priority = new JSONObject();
        priority.put("name", "Minor");
        
        String description="";
        
        
        //EPIC CREATION
        
        String summary1="test issue 1";
        
        JSONObject issuetype1 = new JSONObject();
        issuetype1.put("name", "Epic");     
        
        JSONArray labels1 = new JSONArray();
        labels1.put("0");
        
        JSONObject c1 = new JSONObject();
        c1.put("name", "AEp");
        
        JSONArray components1 = new JSONArray();
        components1.put(c1);
        
    
        data1.put("project",project);
        data1.put("summary", summary1);
        data1.put("issuetype", issuetype1);
        data1.put("assignee", assignee);
        data1.put("priority", priority);
        data1.put("labels", labels1);
        data1.put("description", description);
        data1.put("components", components1);
        createIssue.put("fields", data1);
        
        
        //TASK CREATION
        
        String summary2="test issue 3";
        
        JSONObject issuetype2 = new JSONObject();
        issuetype2.put("name", "Task");     
        
        JSONArray labels2 = new JSONArray();
        labels2.put("3");
        
        JSONObject c2 = new JSONObject();
        c2.put("name", "AE");
        
        JSONArray components2 = new JSONArray();
        components2.put(c2);
        
    
        data2.put("project",project);
        data2.put("summary", summary2);
        data2.put("issuetype", issuetype2);
        data2.put("assignee", assignee);
        data2.put("priority", priority);
        data2.put("labels", labels2);
        data2.put("description", description);
        data2.put("components", components2);
        createIssue.put("fields", data2);
        
        
        //NEW FEATURE CREATION
        
        String summary3= "test issue 4";
        
        JSONObject issuetype3 = new JSONObject();
        issuetype3.put("name", "New feature");     
        
        JSONArray labels3 = new JSONArray();
        labels3.put("3");
        
        JSONObject c3 = new JSONObject();
        c3.put("name", "AE");
        
        JSONArray components3 = new JSONArray();
        components3.put(c3);
        
    
        data3.put("project",project);
        data3.put("summary", summary3);
        data3.put("issuetype", issuetype2);
        data3.put("assignee", assignee);
        data3.put("priority", priority);
        data3.put("labels", labels3);
        data3.put("description", description);
        data3.put("components", components3);
        createIssue.put("fields", data3);
        
        JSONArray ja = new JSONArray();
        ja.put(createIssue);
        
        
        JSONObject mainObj = new JSONObject();
		 mainObj.put("issueUpdates", ja);
		 
		return mainObj.toString();
        
           
       }
}


