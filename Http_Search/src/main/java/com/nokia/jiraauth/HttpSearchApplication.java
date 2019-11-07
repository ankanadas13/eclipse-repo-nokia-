//FETCHING ALL THE ISSUES OF A PARTICULAR PROJECT

package com.nokia.jiraauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HttpSearchApplication {

	public static void main(String[] args) throws IOException 
	{
		SpringApplication.run(HttpSearchApplication.class, args);
		
		HttpSearchApplication  http = new HttpSearchApplication ();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
		
	}

	private void sendGet() throws IOException 
	{
		// TODO Auto-generated method stub
String url = "https://jiradc.int.net.nokia.com/rest/api/2/search?jql=project=DIGNENOKIAACEDDE";

		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();      
        
		
        // optional default is GET
		con.setRequestMethod("GET");
		
		String userName="kharge";
        String password="Digital@123";
        String userpass = userName + ":" + password; 
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
        con.setRequestProperty ("Authorization", basicAuth); 
                       

		//add request header
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) 
		{
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	}


