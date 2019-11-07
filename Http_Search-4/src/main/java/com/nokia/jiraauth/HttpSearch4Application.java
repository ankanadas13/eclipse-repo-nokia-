package com.nokia.jiraauth;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.JSONArray;
import java.lang.reflect.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpSearch4Application {

	public static void main(String[] args) 
	{
		SpringApplication.run(HttpSearch4Application.class, args);
		
		String jsonInput="{\n" + 
				"    \"issueUpdates\": [\n" + 
				"        {\n" + 
				"            \"fields\": {\n" + 
				"                \"project\": {\n" + 
				"                    \"key\": \"DIGNENOKIAACEDDE\"\n" + 
				"                },\n" + 
				"                \"summary\": \"test issue 1\",\n" + 
				"                \"issuetype\": {\n" + 
				"                    \"name\": \"Epic\"\n" + 
				"                },\n" + 
				"                \"assignee\": {\n" + 
				"                    \"name\": \"kharge\"\n" + 
				"                },\n" + 
				"                \"priority\": {\n" + 
				"                    \"name\": \"Minor\"\n" + 
				"                },\n" + 
				"                \"labels\": [\n" + 
				"                	\"0\"\n" + 
				"                	],\n" + 
				"                \"description\": \"\",\n" + 
				"                \"components\": [{\n" + 
				"                    \"name\": \"AEp\"\n" + 
				"                }]\n" + 
				"            }\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"fields\": {\n" + 
				"                \"project\": {\n" + 
				"                    \"key\": \"DIGNENOKIAACEDDE\"\n" + 
				"                },\n" + 
				"                \"summary\": \"test issue 3\",\n" + 
				"                \"issuetype\": {\n" + 
				"                    \"name\": \"Task\"\n" + 
				"                },\n" + 
				"                \"assignee\": {\n" + 
				"                    \"name\": \"kharge\"\n" + 
				"                },\n" + 
				"                \"priority\": {\n" + 
				"                    \"name\": \"Minor\"\n" + 
				"                },\n" + 
				"                \"labels\":[\n" + 
				"                	\"3\"\n" + 
				"                	],\n" + 
				"                \"description\": \"\",\n" + 
				"                \"components\": [{\n" + 
				"                    \"name\": \"AE\"\n" + 
				"                }]\n" + 
				"            }\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"fields\": {\n" + 
				"                \"project\": {\n" + 
				"                    \"key\": \"DIGNENOKIAACEDDE\"\n" + 
				"                },\n" + 
				"                \"summary\": \"test issue 4\",\n" + 
				"                \"issuetype\": {\n" + 
				"                    \"name\": \"New Feature\"\n" + 
				"                },\n" + 
				"                \"assignee\": {\n" + 
				"                    \"name\": \"kharge\"\n" + 
				"                },\n" + 
				"                \"priority\": {\n" + 
				"                    \"name\": \"Minor\"\n" + 
				"                },\n" + 
				"                \"labels\":[\n" + 
				"                    \"3\"\n" + 
				"                	 ],\n" + 
				"                	\n" + 
				"                \"description\": \"\",\n" + 
				"                \"components\": [{\n" + 
				"                    \"name\": \"AE\"\n" + 
				"                }]\n" + 
				"            }\n" + 
				"        }\n" + 
				"    ]\n" + 
				"}";
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(jsonInput);
		
		for(int i=0;i<jsonArray.length();i++)
		{
			JSONObject objectInArray = new JSONObject();
			jsonArray.getJSONObject(i);
			objectInArray.get(jsonArray);
					
			 String[] elementNames = JSONObject.getName(objectInArray);
			 System.out.printf("%d ELEMENTS IN CURRENT OBJECT:\n", elementNames.length);
		      for (String elementName : elementNames)
		      {
		        String value = objectInArray.getString(elementName);
		        System.out.printf("name=%s, status=%s\n", elementName, value);
		      }
		      
		      System.out.println();
	    
		try {
			URL url = new URL("https://jiradc.int.net.nokia.com/rest/api/2/issue/bulk");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            conn.setRequestMethod("POST");
            
            String userName="kharge";
            String password="Digital@123";
            String userpass = userName + ":" + password; 
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
            conn.setRequestProperty ("Authorization", basicAuth); 
            
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            
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
	
		
}
}

