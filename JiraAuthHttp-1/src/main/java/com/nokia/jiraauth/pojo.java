package com.nokia.jiraauth;

public class pojo 
{
public String jiraurl;
public String projkey;

public pojo(String jiraurl, String projkey)
{
    this.jiraurl = jiraurl;
    this.projkey = projkey;
}

public String geturl()
{
	return jiraurl;
}

public void seturl(String jiraurl)
{
	this.jiraurl=jiraurl;
}

public String getkey()
{
	return projkey;
}

public void setkey(String projkey)
{
	this.projkey=projkey;
}
}
