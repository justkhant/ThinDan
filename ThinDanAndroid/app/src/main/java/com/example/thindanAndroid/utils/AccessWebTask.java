package com.example.thindanAndroid.utils;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AccessWebTask extends AsyncTask<URL, String, JSONObject> {
    String method;
    String jsonInputString;

    public AccessWebTask(String method) {
        this.method = method;
        this.jsonInputString = "";
    }

    public AccessWebTask(String method, String jsonInputString) {
        this.method = method;
        this.jsonInputString = jsonInputString;
    }
    /*
    This method is called in background when this object's "execute" method is invoked.
    The arguments passed to "execute" are passed to this method.
     */
    protected JSONObject doInBackground(URL... urls) {
        try {
            // get the first URL from the array
            URL url = urls[0];
            // create connection and send HTTP request
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method); // send HTTP request

            if (method == "GET") {
                conn.connect();

                // read the first line of data that is returned
                Scanner in = new Scanner(url.openStream());
                String msg = in.nextLine();
                // use Android JSON library to parse JSON
                JSONObject jo = new JSONObject(msg);
                in.close();
                // pass the JSON object to the foreground that called this method
                return jo;

            } else {
                conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                String msg = "{}";
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    msg = response.toString();
                }
                // use Android JSON library to parse JSON
                JSONObject jo = new JSONObject(msg);
                // pass the JSON object to the foreground that called this method
                return jo;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject(); // should empty JSONObject upon encountering an exception
        }
    }
}
