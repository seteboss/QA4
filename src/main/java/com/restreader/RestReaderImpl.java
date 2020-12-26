package com.restreader;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class RestReaderImpl implements RestReader {

    private final String encoding = "utf8";

    @SneakyThrows
    private HttpURLConnection getSettingConnection(String urlPath) {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        return connection;
    }

    @Override
    public Optional<String> getJsonString(String urlPath) {
        try {
            HttpURLConnection connection = getSettingConnection(urlPath);

            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), encoding));

                StringBuilder answer = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    answer.append(line);
                }

                reader.close();

                return Optional.of(answer.toString());
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
