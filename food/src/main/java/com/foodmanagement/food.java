

// registration done:

package com.foodmanagement;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


public class food extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public food() {
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/food";
	            String username = "root";
	            String password = "Saloni@2002";

	            try (Connection con = DriverManager.getConnection(url, username, password)) {
	                String name = request.getParameter("name");
	                String email = request.getParameter("email");
	                String passwordParam = request.getParameter("password");

	                // Basic input validation (you might want to improve this)
	                if (name != null && email != null && passwordParam != null) {
	                    // Check if the user already exists
	                    if (isUserExists(con, email)) {
	                        out.println("<html><body><script>alert('User already registered!');window.location.href='Login.html';</script></body></html>");
	                        return;
	                    }

	                    // Insert the new user
	                    try (PreparedStatement ps = con.prepareStatement(
	                            "INSERT INTO signup (name, email, password) VALUES (?, ?, ?)")) {
	                        ps.setString(1, name);
	                        ps.setString(2, email);
	                        ps.setString(3, passwordParam);

	                        int rowsAffected = ps.executeUpdate();

	                        if (rowsAffected > 0) {
	                            // Registration successful
	                            out.println("<html><body><script>alert('Registration successful!');window.location.href='Login.html';</script></body></html>");
	                        } else {
	                            // Registration failed
	                            out.println("<html><body><script>alert('Registration failed. Please try again.');window.location.href='Index.html';</script></body></html>");
	                        }
	                    }
	                }
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace(); // Log the exception for debugging purposes
	            out.println("<html><body><script>alert('Registration failed. Please try again later.');window.location.href='Index.html';</script></body></html>");
	        }
	    }

	    private boolean isUserExists(Connection con, String email) throws SQLException {
	        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM signup WHERE email = ?")) {
	            ps.setString(1, email);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }
	        }
	    }

	    public void destroy() {
	        // TODO Auto-generated method stub
	    }
	}