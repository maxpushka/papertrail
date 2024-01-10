package com.github.papertrail.connection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpConnectionType implements ConnectionType {

    private static void postRequest(String url, String data, String contentType) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", contentType);
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            read(connection);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getRequest(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");

            read(connection);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read(HttpURLConnection connection) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }
    }
}
