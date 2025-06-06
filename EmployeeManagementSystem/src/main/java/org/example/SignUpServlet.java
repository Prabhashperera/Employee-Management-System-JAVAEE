package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource ds;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            BufferedReader reader = req.getReader();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> userData = mapper.readValue(reader, Map.class);
            System.out.println(userData);
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO userTable VALUES (?,?,?,?)");
            ps.setString(1, userData.get("email"));
            ps.setString(2, userData.get("name"));
            ps.setString(3, userData.get("password"));
            ps.setString(4, userData.get("phone"));
            int i = ps.executeUpdate();
            System.out.println("Insereted " + i + " rows into database");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
