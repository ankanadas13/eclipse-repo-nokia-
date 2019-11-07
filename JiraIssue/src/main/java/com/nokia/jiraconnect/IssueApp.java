package com.nokia.jiraconnect;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class IssueApp {

    
    private static final String jiraBaseURL = "https://jiradc.int.net.nokia.com";
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

    public IssueApp() {
        restTemplate = new RestTemplate();
        
         }

	
    public ResponseEntity<String> createIssue(String key, String summary, String description, String issueType) {
        String createIssueJSON = createCreateIssueJSON(key, summary, description, issueType);

        String url = jiraBaseURL + "issue";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<String>(createIssueJSON, httpHeaders);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

    }

    String createCreateIssueJSON(String key, String summary, String description, String issueType) {
        String createIssueJSON = "{\"fields\":{\"project\":{\"key\":\"$KEY\"},\"summary\":\"$SUMMARY\",\"description\":\"$DESCRIPTION\",\"issuetype\": {\"name\": \"$ISSUETYPE\"}}}";

        createIssueJSON = createIssueJSON.replace("DIGNENOKIAACEDDE", key);
        createIssueJSON = createIssueJSON.replace("rest api", summary);
        createIssueJSON = createIssueJSON.replace("Creating of an issue using project keys and issue type names using the REST API", description);
        return createIssueJSON.replace("Task", issueType);
    }

}

