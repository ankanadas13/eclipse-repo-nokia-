package com.nokia.jiraauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpPost3Application 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(HttpPost3Application.class, args);
		
		String inline = "";
		
		try
		{
			URL url = new URL("https://jiradc.int.net.nokia.com/rest/api/2/issue/bulk");
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST");
			
			conn.connect();
			
			int responsecode = conn.getResponseCode();
			System.out.println("Response code is: " +responsecode);
			
			if(responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
					inline+=sc.nextLine();
				}
				System.out.println("\nJSON Response in String format"); 
				System.out.println(inline);
				sc.close();
			}
			
			JSONParser parse = new JSONParser();
			
			JSONObject jobj = (JSONObject)parse.parse(inline);
			
			JSONArray jsonarr_1 = (JSONArray) jobj.get("issueupdates");
			
			for(int i=0;i<jsonarr_1.size();i++)
			{
				JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
				
				System.out.println("Elements under issueupdates array");
				
				System.out.println("fields, key, summary, issuetype, assignee, "
						+ "priority,labels, description and components are:");
				
				String str_data1 = (String) jsonobj_2.get("fields");
				System.out.println(str_data1);
				String str_data2 = (String) jsonobj_2.get("summary");
				System.out.println(str_data2);
				String str_data1 = (String) jsonobj_2.get("issuetype");
				System.out.println(str_data3);
				String str_data2 = (String) jsonobj_2.get("assignee");
				System.out.println(str_data4);
				String str_data1 = (String) jsonobj_2.get("priority");
				System.out.println(str_data5);
				String str_data2 = (String) jsonobj_2.get("labels");
				System.out.println(str_data6);
				String str_data1 = (String) jsonobj_2.get("description");
				System.out.println(str_data7);
				String str_data2 = (String) jsonobj_2.get("components");
				System.out.println(str_data8);
				System.out.println("\n");
				
						
	}

}
		conn.disconnect();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
}
