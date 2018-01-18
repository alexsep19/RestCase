package rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import rolo.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class RestClient {
    static final String URL = "http://localhost:8080/rest";

    static void sendGet(String postPath, int offset, int length){
        try(CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpGet httpGet = new HttpGet(URL + postPath + "?offset="+String.valueOf(offset)+"&length="+String.valueOf(length));
            httpGet.addHeader("User-Agent", USER_AGENT);

            HttpResponse resp = client.execute(httpGet);
            int respCode = resp.getStatusLine().getStatusCode();
            if (respCode == 200)
                System.out.println("response = " + respCode + resp.getStatusLine().getReasonPhrase());
            else
                System.out.println("response = " + resp.getStatusLine().getStatusCode() +" "+resp.getStatusLine().getReasonPhrase());

            BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
            String line = rd.readLine();
            System.out.println(line);
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue( line, new TypeReference<List<User>>(){});
            System.out.println(users.get(0).getName());
            System.out.println(users.get(0).getPass());
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    static void sendPost(String postPath, int offset, int length){
//        try(CloseableHttpClient client = HttpClientBuilder.create().build()){
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpPost httpPost = new HttpPost(URL + postPath);
//            httpPost.setHeader("User-Agent", USER_AGENT);
            httpPost.setHeader("content-type", "application/json");
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//            urlParameters.add(new BasicNameValuePair("offset", "0"));
//            urlParameters.add(new BasicNameValuePair("length", String.valueOf(length)));
//            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
            StringEntity params = new StringEntity("{\"offset\":0, \"length\":1}");
            httpPost.setEntity(params);

            HttpResponse resp = client.execute(httpPost);
            int respCode = resp.getStatusLine().getStatusCode();
            if (respCode == 200)
                System.out.println("response = " + respCode + resp.getStatusLine().getReasonPhrase());
            else
                System.out.println("response = " + resp.getStatusLine().getStatusCode() +" "+resp.getStatusLine().getReasonPhrase());

			BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
			String line = rd.readLine();
			System.out.println(line);
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue( line, new TypeReference<List<User>>(){});
            System.out.println(users.get(0).getName());
            System.out.println(users.get(0).getPass());
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){

        sendPost("/user", 0, 1);
    }
}
