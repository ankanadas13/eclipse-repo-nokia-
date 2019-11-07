package com.nokia.jiraconnect;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IssueAppTest {

    private IssueAppTest application;

    @Before
    public void setUp() {
        application = new IssueAppTest();
    }

    @SuppressWarnings("rawtypes")
	@Test
    public void testGetIssue() {
        ResponseEntity issueResponse = application.getIssue("DIGNENOKIAACEDDE");

        assertThat(issueResponse.getStatusCode().value(), is(200));
        assertThat(issueResponse.getBody().toString(), containsString("\"key\":\"DIGNENOKIAACEDDE\""));
    }

	private ResponseEntity<?> getIssue(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}



