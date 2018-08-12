package test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.rwrs.utils.XMLUtils;

/**
 * SimpleMethodClient class Acts as a simple web service client to enable
 * loading of ODM 1.3 data into Rave
 **/
public class PostClientDataFromLocal {
	/* Modify the username, password, hostname and odmXMLFileName accordingly */
	final static String username = "jChen";
	final static String password = "Everest1";
	// final static String hostname = "everest2.mdsol.com";
	final static String hostname = "innovate.mdsol.com";

	final static String odmXMLFileName = "/Users/wilson/rwrsData/subjectInfo/test.xml";

	/* Main method to connect, read the data file and upload via the web service */

	public static void main(String[] args) throws Exception {
		String request = "https://" + hostname + "/Ravewebservices/WebService.aspx?PostODMClinicalData";
		System.out.println(request);

		HttpClient client = new HttpClient(); // Apache's Http client
		Credentials credentials = new UsernamePasswordCredentials(username, password);

		client.getState().setCredentials(AuthScope.ANY, credentials);
		client.getState().setProxyCredentials(AuthScope.ANY, credentials); // may not be necessary

		client.getParams().setAuthenticationPreemptive(true);// send authentication details in the header

		PostMethod post = new PostMethod(request); // uses HTTP POST
		post.setDoAuthentication(true); // must authenticate
		post.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);

		//File f = new File(odmXMLFileName);
		RequestEntity entity = new StringRequestEntity(XMLUtils.xmlToString(XMLUtils.createXML(101020)), "text/xml",null);
		post.setRequestEntity(entity);

		try {
			int statusCode = client.executeMethod(post); // send POST request with data
			if (statusCode != HttpStatus.SC_OK) { // did it work ?
				System.err.println("Method failed: " + post.getStatusLine());
			}

			// Process the response from Rave Web Services
			InputStream rstream = post.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line); // just display the response
			}
			br.close();

		} finally {
			post.releaseConnection(); // remember to free up the connection
		}
	}

}
