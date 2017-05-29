package com.placefinder.server.service;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.placefinder.server.entity.Place;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    private static final String API_KEY =
            "AAAAuSHcFF4:APA91bEkjUpqpWqFh6ITrbmg-eViU3ncNRr_7dUFUErNDIzBbUZ9JMyHbbD7q0RYSKXDKJwLuVLeRKt3GMKhRrxU5Y-mtO1uSXMYM3bNF7C1P_6rB4uuvTfqhhSjnmRs1Us7fglMalZ_";
    private static final GenericUrl url = new GenericUrl("https://fcm.googleapis.com/fcm/send");

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String actionPlaceAdded = "added";
    private static final String actionPlaceDeleted = "deleted";

    public void notifyPlaceAdded(Place place) {

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("key=" + API_KEY);
            headers.setContentType("application/json");

            Map<String, Object> notificationMap = new HashMap<String, Object>();
            notificationMap.put("title", "New place : " + place.getTitle());
            notificationMap.put("body", place.getDescription());

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("action", actionPlaceAdded);
            data.put("id", place.getId());
            data.put("title", place.getTitle());
            data.put("ownerGoogleId", place.getOwnerGoogleId());
            data.put("description", place.getDescription());
            data.put("latitude", place.getLatitude());
            data.put("longitude", place.getLongitude());

            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("to", "/topics/places");
            jsonMap.put("notification", notificationMap);
            jsonMap.put("data", data);


            HttpContent content = new JsonHttpContent(JSON_FACTORY, jsonMap);
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
            HttpRequest request = requestFactory.buildPostRequest(url, content);
            request.setHeaders(headers);
            request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
            HttpResponse response = request.execute();

            int code = response.getStatusCode();

/*            URL url1 = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection httpCon = (HttpURLConnection) url1.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setDoInput(true);
            httpCon.setUseCaches(false);
            httpCon.setRequestProperty( "Content-Type", "application/json" );
            httpCon.setRequestProperty("Authorization", "key=" + API_KEY);
            httpCon.setRequestMethod("POST");
            httpCon.connect();
            byte[] outputBytes = msg.toString().getBytes("UTF-8");
            OutputStream os = httpCon.getOutputStream();
            os.write(outputBytes);
            //OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            //msg.write(osw);
            os.flush();
            os.close();
            InputStream is = httpCon.getInputStream();
            if(is != null){
                String str = is.toString();
            }*/
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void notifyPlaceDeleted(long id){
        try{

            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("key=" + API_KEY);
            headers.setContentType("application/json");

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("action", actionPlaceDeleted);
            data.put("id", id);

            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("to", "/topics/places");
            jsonMap.put("data", data);

            HttpContent content = new JsonHttpContent(JSON_FACTORY, jsonMap);
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
            HttpRequest request = requestFactory.buildPostRequest(url, content);
            request.setHeaders(headers);
            request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
            HttpResponse response = request.execute();

            int code = response.getStatusCode();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
