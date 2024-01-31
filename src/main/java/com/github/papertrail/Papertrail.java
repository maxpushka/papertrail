package com.github.papertrail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.sql.*;

public class Papertrail implements PapertrailConnection {
    URI uri;

    public Papertrail(URI uri) {
        this.uri = uri;
    }

    @Override
    public JSONArray query(String query) {
        try (Connection connection = DriverManager.getConnection(uri.toString());
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            JSONArray jsonArray = new JSONArray();

            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                int columns = resultSet.getMetaData().getColumnCount();

                for (int i = 1; i <= columns; ++i) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Object value = resultSet.getObject(i);
                    jsonObject.put(columnName, value.toString());
                }
                jsonArray.put(jsonObject);
            }

            return jsonArray;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int exec(String query) {
        return 0;
    }
}
