//FULL PROGRAM FOR JIRA CLOUD


package com.nokia.jiraauth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JiraApplication 
{
	 static String pwd = System.getProperty("user.dir");
     static File file = new File(pwd);
     static File parentPath = file.getParentFile();
     static String jsonFile = "data.json";

     // To check whether the proj key exist or not 
     public static String checkUrl(String url,String userName,String password) {             
                     try {
                                     URL obj3 = new URL(url);
                                     HttpURLConnection conn = (HttpURLConnection) obj3.openConnection();
                                     conn.setRequestMethod("GET");
                                     String userpass = userName + ":" + password; 
                                     String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
                                     //String basicAuth = "Basic " + userpass.getBytes("UTF-8");
                                     conn.setRequestProperty ("Authorization", basicAuth); 
                                     BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                     String line = "";
                                     while ((line = reader.readLine())!= null) 
                                     {
                                                     return line;
                                     }

                     } catch (Exception e) {
                                     return "success";
                     }
                     return "error";
                }
     
     //Create Components
     public static String createComponent(String name,String description,String leadUserName,String asigneeType,String username,String password,String projKey) {
                     System.out.println("Creating Components");
                     String componentUrl = "https://sudipabehera.atlassian.net/rest/api/3/component";
                     try {
                                     URL obj6 = new URL(componentUrl);
                                     HttpURLConnection conn = (HttpURLConnection) obj6.openConnection();
                                     conn.setRequestProperty("Content-Type", "application/json");
                                     conn.setDoOutput(true);
                                     conn.setRequestMethod("POST");
                                     String userpass = username + ":" + password; 
                                     String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
                                     conn.setRequestProperty ("Authorization", basicAuth);
                                     String data = "{\r\n" + 
                                                                     "    \"name\": \""+name+"\",\r\n" + 
                                                                     "    \"description\": \""+description+"\",\r\n" + 
                                                                     "    \"leadUserName\": \""+leadUserName+"\",\r\n" + 
                                                                     "    \"assigneeType\": \""+asigneeType+"\",\r\n" + 
                                                                     "    \"isAssigneeTypeValid\": false,\r\n" + 
                                                                     "    \"project\": \""+projKey+"\"\r\n" + 
                                                                     "              }";
                                     //System.out.println(data);
                                     OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                                     out.write(data);
                                     out.close();
                                     int code = conn.getResponseCode();
                                     if(code!=201)
                                     {
                                                     switch(code) {
                                                     case 401:System.out.println("Check the credentials.Unauthorized access");break;
                                                     case 403:System.out.println("Caller dosesnot have permission to create components");break;
                                                     case 404:System.out.println("Project doesnot exist");
                                                     }
                                                                     throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());          
                                     }
                                     else {
                                     BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                     String line = "";
                                     while ((line = reader.readLine())!= null) 
                                     {
                                                     return line;
                                     }
                                     }
                     }
                     catch(Exception e) {
                                     return "error";
                     }
                     return "success";
     }
     
   //Create issues
     public static String createIssues(String url,String rigletName,String username,String password,String key) {
                     System.out.println("Uploading Issues");
                     JSONParser parser = new JSONParser();
System.out.println("rigletname "+rigletName);
                     try {
                                     URL obj4 = new URL(url);
                                     HttpURLConnection conn = (HttpURLConnection) obj4.openConnection();
                                     conn.setRequestProperty("Content-Type", "application/json");
                                     conn.setDoOutput(true);
                                     conn.setRequestMethod("POST");
                                     String userpass = username + ":" + password;    
                                     String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
                                     conn.setRequestProperty ("Authorization", basicAuth); 
                                     Object object;
                                     object = parser.parse(new FileReader(file+"/"+rigletName+"/json/jira/uploadedJson/"+"issues-"+rigletName+".json"));
                                     JSONArray issueArr = (JSONArray) object;
                                     System.out.print(issueArr);
                                     int length = issueArr.size();
                                     int count=0;
                                     String data = "{\r\n" + 
                                                                     "\"issueUpdates\": [\r\n" ;
                                     Iterator<Object> i = issueArr.iterator();
                                     while(i.hasNext()) {
                                                     count++;
                                     JSONObject issue=(JSONObject)i.next();
                                     
                                     if(count<length) {
                                     data+="{\r\n" + 
                                                                     "\"update\":{},\r\n" + 
                                                                     "\"fields\": {\r\n" + 
                                                                     "\"project\":\r\n" + 
                                                                     "{ \r\n" + 
                                                                     " \"key\": \""+key+"\"\r\n" + 
                                                                     "},\r\n" + 
                                                                     "\"summary\": \""+issue.get("summary")+"\",\r\n" + 
                                                                     "\"description\": \""+issue.get("description")+"\",\r\n" + 
                                                                     "\"issuetype\": {\r\n" + 
                                                                     "              \"id\": \"10000\"\r\n" + 
                                                                     " },\r\n" + 
                                                                     "\"assignee\": {\r\n" + 
                                                                     "    \"name\": \""+issue.get("assignee")+"\"\r\n" + 
                                                                     "},\r\n" + 
                                                                     "\"reporter\": {\r\n" + 
                                                                     "    \"name\": \""+issue.get("reporter")+"\"\r\n" + 
                                                                     "},\r\n" + 
                                                                     "\"labels\": [\r\n" +"\"label\""+ 
                                                                     "]\r\n" + 
                                                                     "}\r\n" + 
                                                                     "},";
                                     }
                                     else {
                                                     data+="{\r\n"+
                                                                                     "\"update\":{},\r\n" + 
                                                                                     "\"fields\": {\r\n" + 
                                                                                     "\"project\":\r\n" + 
                                                                                     "{ \r\n" + 
                                                                                     " \"key\": \""+key+"\"\r\n" + 
                                                                                     "},\r\n" + 
                                                                                     "\"summary44\": \""+issue.get("summary")+"\",\r\n" + 
                                                                                     "\"description\": \""+issue.get("description")+"\",\r\n" + 
                                                                                     "\"issuetype\": {\r\n" + 
                                                                                     "              \"id\": \"10000\"\r\n" + 
                                                                                     "},\r\n" + 
                                                                                     "\"assignee\": {\r\n" + 
                                                                                     "    \"name\": \""+issue.get("assignee")+"\"\r\n" + 
                                                                                     "},\r\n" + 
                                                                                     "\"reporter\": {\r\n" + 
                                                                                     "    \"name\": \""+issue.get("reporter")+"\"\r\n" + 
                                                                                     "},\r\n" + 
                                                                                     "\"labels\": [\r\n" +"\"label\""+
                                                                                     "]\r\n" + 
                                                                                     "}\r\n" + 
                                                                                     "}"+
                                                                                     "]\r\n"+
                                                                                     "}\r\n";
                                                     }
                                     }
                                     System.out.println(data);
                                     OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                                     out.write(data);
                                     out.close();
                                     int code = conn.getResponseCode();
                                     if(code!=201) {
                                     switch(code){
                                     case 400:System.out.println("Issues are not sent in proper format");break;
                                     
                                     }
                                     throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());   
                                     }
                                     else {
                                     BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                     String line = "";
//                                 while ((line = reader.readLine())!= null) 
//                                 {
//                                                 //return line;
//                                                 JSONObject json = (JSONObject) parser.parse(line);
//                                                 JSONArray issuearray = (JSONArray) json.get("issues");
//                                                 java.util.Iterator i1 = issuearray.iterator();
//                                                 while(i1.hasNext()) {
//                                                                 JSONObject issue=(JSONObject)i1.next();
//                                                                 String issuekey = (String) issue.get("key");
//                                                                 System.out.println(issuekey);
//                                                                 String[] label = {"label1","label2"}; 
//
//                                                                 //addField(issuekey,label,username,password);
//                                                 }
//
//                                 }
                                     while ((line = reader.readLine())!= null) 
                                     {
                                                     return line;
                                     }
                                     }

                     } 
                     catch (Exception e) {
                                     
                                     return "error";
                     }
                     return "success";
     }


	
	
	
	public static void main(String[] args) 
	{
		SpringApplication.run(JiraApplication.class, args);
	}

}
