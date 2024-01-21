package com.github.papertrail.server.http;

import com.github.papertrail.driver.MockDriver;
import com.github.papertrail.parser.Statement;
import com.github.papertrail.parser.statement.Select;
import com.github.papertrail.server.http.querybuilder.QueryBuilder;
import com.github.papertrail.server.http.querybuilder.QueryBuilderFactory;
import com.github.papertrail.server.http.responsebuilder.ResponseBuilder;
import com.github.papertrail.server.http.responsebuilder.ResponseBuilderFactory;
import com.github.papertrail.parser.MockParser;
import com.github.papertrail.parser.Transaction;
import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sql")
public class Controller {
    @GetMapping
    public ResponseEntity<String> getMethod(HttpServletRequest request) throws Exception {
        return this.handle(request);
    }

    @PostMapping
    public ResponseEntity<String> postMethod(HttpServletRequest request) throws Exception {
        return this.handle(request);
    }

    private ResponseEntity<String> handle(HttpServletRequest request) throws Exception {
        QueryBuilder queryBuilder = QueryBuilderFactory.build(request.getHeader("Content-Type"));
        ResponseBuilder respBuilder = ResponseBuilderFactory.build(request.getHeader("Accepts"));

        String query = queryBuilder.build(request);
        Transaction tx = new MockParser().parse(query);

        if (Objects.equals(request.getMethod(), "GET")) {
            for (Statement statement : tx.statements) {
                if (!(statement instanceof Select)) {
                    // TODO: use custom exception
                    throw new Exception("GET method allows SELECT queries only");
                }
            }
        }

        List<JsonElement> result = new MockDriver().run(tx);
        String response = respBuilder.build(result);
        return ResponseEntity.ok(response);
    }
}
