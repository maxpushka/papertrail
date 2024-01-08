package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

import java.net.URI;
import java.sql.*;

public class Papertrail implements PapertrailConnection {
    URI uri;

    public Papertrail(URI uri) {
        this.uri = uri;
    }

    @Override
    public JsonArray query(String query) {
        try (Connection connection = DriverManager.getConnection(uri.toString());
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            JsonArray jsonArray = new JsonArray();

            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                int columns = resultSet.getMetaData().getColumnCount();

                for (int i = 1; i <= columns; ++i) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Object value = resultSet.getObject(i);
                    jsonObject.addProperty(columnName, value.toString());
                }
                jsonArray.add(jsonObject);
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