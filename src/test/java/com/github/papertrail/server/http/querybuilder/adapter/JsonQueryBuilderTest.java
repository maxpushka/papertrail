package com.github.papertrail.server.http.querybuilder.adapter;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonQueryBuilderTest {
    @Test
    public void testBuildQueryWithProducts() throws IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getReader()).thenReturn(
                new BufferedReader(
                        new StringReader("{\"q\": \"SELECT * from products;\"}")));

        JsonQueryBuilder queryBuilder = new JsonQueryBuilder();

        String query = queryBuilder.build(request);

        assertEquals("SELECT * from products;", query);
    }

    @Test
    public void testBuildQueryWithProductsWhere() throws IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getReader()).thenReturn(
                new BufferedReader(
                        new StringReader("{\"q\": \"SELECT * from products WHERE name = ?;\", \"p\": [\"Salt\"]}")));

        JsonQueryBuilder queryBuilder = new JsonQueryBuilder();

        String query = queryBuilder.build(request);

        assertEquals("SELECT * from products WHERE name = 'Salt';", query);
    }

    @Test
    public void testBuildQueryWithProductsName() throws IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getReader()).thenReturn(
                new BufferedReader(
                        new StringReader("{\"q\": \"INSERT INTO products {name: :name};\", \"p\": {\"name\": \"Paprika\"}}")));

        JsonQueryBuilder queryBuilder = new JsonQueryBuilder();

        String query = queryBuilder.build(request);

        assertEquals("INSERT INTO products {name: 'Paprika'};", query);
    }
}
