package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PreparedStatement ps = ds.getConnection().prepareStatement("SELECT * FROM userTable");
            ResultSet rs = ps.executeQuery();
            List<Map<String, String>> userList = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("name", rs.getString("name"));
                user.put("email", rs.getString("email"));
                user.put("password", rs.getString("password"));
                user.put("phone", rs.getString("phone"));
                userList.add(user);
            }

            System.out.println("User List " + userList);

            resp.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), userList);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
