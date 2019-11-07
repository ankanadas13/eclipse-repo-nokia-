package com.nokia.jiraauth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.net.URLConnection;
import java.net.URLEncoder;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class JiraAuthHttp1Application 
{
	
	
	public static void main(String[] args) throws MalformedURLException, IOException 
	{
		SpringApplication.run(JiraAuthHttp1Application.class, args);
		   
			
		try {
			
			URL jiraREST_URL = new URL("https://jiradc.int.net.nokia.com/rest/api/2/issue");        
            HttpURLConnection conn = (HttpURLConnection) jiraREST_URL.openConnection();
            conn.setDoOutput(true);
            String encodedData = URLEncoder.encode(getJSON_Body(),"UTF-8");
            System.out.println(getJSON_Body() + "/" + encodedData);

            conn.setRequestMethod("POST");            
            
            String USER="kharge";
            String PASSWD="Digital@123";
            String authString = USER + ":" + PASSWD;
            Object authStringEnc = new Base64().encode(authString.getBytes());
            System.out.println("Base64 encoded auth string: " + authStringEnc);
            
                      
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            conn.getOutputStream().write(encodedData.getBytes());
            
            System.out.println(conn.getResponseCode());
         }
		
		catch (MalformedURLException e) 
        {
            e.printStackTrace();
        } 

        catch (IOException e)
           {
            System.out.println(e.getMessage());
           } 
	}

    

	private static String getJSON_Body()
	{
     File json = new File("src/main/resources/json.json");
        StringBuilder result = new StringBuilder();

        try {
            
			FileReader fileReader = new FileReader(json);
            int curChar = fileReader.read();
            while(curChar != -1){
                result.append((char) curChar );
                curChar = fileReader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    

      
return "{"
                + "\"fields\": {"
                    + "\"project\":"
                        + "{"
                        +    "\"key\": \"DIGNENOKIAACEDDE\""
                        + "},"
                    + "\"summary\": \"REST API for issues.\","
                    + "\"description\": \"Creating of an issue using project keys and issue type names using the REST API\","
                    + "\"issuetype\": \"Task.\"."
                            
                    + "}"
                + "}"; 
    }
}


	
