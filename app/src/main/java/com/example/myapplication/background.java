package com.example.myapplication;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class background extends AsyncTask <String, Void, String> {
    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String name = voids[0];
        String mail = voids[1];
        String number = voids[2];
        String city = voids[3];
        String event = voids[4];
        String budget = voids[5];
        String connstr = "http://localhost:8080/form.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream op = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(op,"UTF-8"));
            String data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name);
        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return null;
    }
}
