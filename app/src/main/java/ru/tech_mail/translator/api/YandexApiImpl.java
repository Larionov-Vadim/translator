package ru.tech_mail.translator.api;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mid on 04.03.15.
 */
//TODO вынести контсанты в реусурсную часть
public class YandexApiImpl {
    private static final String apiKey="trnsl.1.1.20150303T162336Z.1bf9bdd3996bcd6c.00688c6f9cbf5169250b7731d6fabc63b6e92585";
    private static final String urlString="https://translate.yandex.net/api/v1.5/tr.json/translate";
    public String translate(String text,String from,String to) {
        URL url;
        URLConnection conn;
        BufferedReader rd;
        String line;
        String responseString = "";
        String requestString="";
        String result="";
        try {
            requestString+=urlString+"?key="+apiKey+"&text="+text+"&lang="+from+"-"+to;
            url = new URL(requestString);
            conn = url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            //conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                responseString += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "connection error";
        }

        try {
            JSONObject responseJSON = new JSONObject(responseString);
            switch (responseJSON.getInt("code")){
                case 200:{
                    result= responseJSON.getJSONArray("text").get(0).toString();///TODO а если яндекс таки вернет массив???
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            result= "service down";
        }
        return result;
    }

}
