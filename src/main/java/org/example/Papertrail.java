package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Papertrail implements PapertrailConnection {
    URI uri;

    public Papertrail(URI uri) {
        this.uri = uri;
    }

    @Override
    public List<Gson> query(String query) {
        try (Connection connection = DriverManager.getConnection(uri.toString());
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Gson> gsonList = new ArrayList<>();

            while (resultSet.next()) {
                Gson gsonObject;
                int columns = resultSet.getMetaData().getColumnCount();

                JsonObject jsonObject = new JsonObject();

                for (int i = 1; i <= columns; ++i) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Object value = resultSet.getObject(i);
                    jsonObject.addProperty(columnName, value.toString());
                }

                gsonObject = new Gson().fromJson(jsonObject, Gson.class);
                gsonList.add(gsonObject);
            }

            return gsonList;

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
