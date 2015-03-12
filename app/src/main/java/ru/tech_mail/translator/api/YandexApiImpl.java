package ru.tech_mail.translator.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.CharBuffer;


/**
 * Created by mid on 04.03.15.
 */
// TODO вынести контсанты в ресурсную часть
public class YandexApiImpl {
    private static final String apiKey = "trnsl.1.1.20150303T162336Z.1bf9bdd3996bcd6c.00688c6f9cbf5169250b7731d6fabc63b6e92585";
    private static final String urlString = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    public String translate(String text,String from,String to) {
        //зачем блок объявления переменных?//действительно, зачем я написал это, убрал все ненужное.
        String responseString;
        String result;

        try {
            StringBuilder requestStringBuiledr=new StringBuilder(urlString);
            requestStringBuiledr.append("?key=").append(apiKey).append("&text=").append(URLEncoder.encode(text,"UTF-8")).append("&lang=").append(from).append("-").append(to);
            String requestString=requestStringBuiledr.toString();
            URL url = new URL(requestString);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setRequestProperty("method","GET");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("accept-charset", "UTF-8");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            CharBuffer buf = CharBuffer.allocate(1);
            while ((rd.read(buf)) >=0) {
                sb.append(buf.flip());
            }
            responseString = sb.toString();
            rd.close();
        } catch (Exception e) {
            Log.e("connection",e.toString());
            return "Connection error";
        }

        try {
            JSONObject responseJSON = new JSONObject(responseString);
            switch (responseJSON.getInt("code")) {
                case 200:{ //необычный синтаксис //чем он необычен, как бы сделали вы?
                    result= responseJSON.getJSONArray("text").get(0).toString(); // TODO а если яндекс таки вернет массив???
                    break;
                }
                default:{//TODO по идее можно запихнуть сообщения об ошибках в ресуры и брать оттуда по коду ответа, но моя жизнь слишком коротка
                    result = responseJSON.getString("message").toString();//если ошибка, формат ответа code;message всегда
                    break;
                }
            }
        } catch (JSONException e) {
            //e.printStackTrace(); // не на всех версиях выплюен в LogCat используейте Log.e(...
            Log.e("json",e.toString());//так лучше
            result= "Service down";
        }
        return result;
    }

}
