package br.com.encoder.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import android.util.Log;

public class WS {
	public final String[] get(String url) {

        String[] result = new String[2];
        HttpGet httpget = new HttpGet(url);
        HttpResponse response;

        try {

            response = WSHttpClientSingleton.getHttpClientInstace().execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result[0] = String.valueOf(response.getStatusLine().getStatusCode());
                InputStream instream = entity.getContent();
                result[1] = toString(instream);
                instream.close();

                //Log.i("get", "Result from post JsonPost : " + result[0] + " : " + result[1]);
            }
        } catch (Exception e) {
            Log.e("NGVL", "Falha ao acessar Web service", e);
            result[0] = "0";
            result[1] = "Falha na rede!";
                      
        }
        return result;
       }


   /*public final String[] post(String url, String json) {
        String[] result = new String[2];
        try {

            HttpPost httpPost = new HttpPost(new URI(url));
            httpPost.setHeader("content-type", "application/json");
            //StringEntity sEntity = new StringEntity(json);
            httpPost.setEntity(new StringEntity(json, "UTF-8"));

            HttpResponse response;
            response = WSHttpClientSingleton.getHttpClientInstace().execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result[0] = String.valueOf(response.getStatusLine().getStatusCode());
                InputStream instream = entity.getContent();
                result[1] = toString(instream);
                instream.close();

                Log.d("post", "Result from post JsonPost : " + result[0] + " : " + result[1]);
            }

        } catch (Exception e) {
            Log.e("NGVL", "Falha ao acessar Web service", e);
            result[0] = "0";
            result[1] = "Falha na rede!";
           
            
        }
        return result;
       }

       private String toString(InputStream is) throws IOException {

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
       }*/
	
	public final String[] post(String url, List<NameValuePair> valores) {
        String[] result = new String[2];
        try {

            HttpPost httpPost = new HttpPost(new URI(url));
            httpPost.setEntity(new UrlEncodedFormEntity(valores));

            HttpResponse response;
            response = WSHttpClientSingleton.getHttpClientInstace().execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result[0] = String.valueOf(response.getStatusLine().getStatusCode());
                InputStream instream = entity.getContent();
                result[1] = toString(instream);
                instream.close();

                Log.d("post", "Result from post JsonPost : " + result[0] + " : " + result[1]);
            }

        } catch (Exception e) {
            Log.e("NGVL", "Falha ao acessar Web service", e);
            result[0] = "0";
            result[1] = "Falha na rede!";
           
            
        }
        return result;
       }

       private String toString(InputStream is) throws IOException {

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
       }
}
