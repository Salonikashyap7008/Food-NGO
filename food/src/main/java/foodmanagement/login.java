package foodmanagement;

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


public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public login() {
        super();
       
    }

	
	public void init(ServletConfig config) throws ServletException {
		
	}

	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	     
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/food";
	            String username = "root";
	            String password1 = "Saloni@2002";

	             
	        try (Connection con = DriverManager.getConnection(url, username, password1)) {
	                String email = request.getParameter("email");
	                String password = request.getParameter("password");

	                // Basic input validation (you might want to improve this)
	                if (email != null && password != null) {
	                    // Check if the user is registered
	                    if (isUserRegistered(con, email)) {
	                        // Check the password for the registered user
	                        if (isPasswordCorrect(con, email, password)) {
	                            // User exists and login successful, redirect to dashboard
	                            response.sendRedirect("./user_dashboard.html");
	                        } else {
	                            // Incorrect password, display an alert message   
	                            String errorMessage = "Incorrect password. Please try again.";
	                            response.getWriter().println("<script>alert('" + errorMessage + "'); window.location.href='./Login.html';</script>");
	                        }
	                    } else {
	                        // User not registered, display an alert message
	                        String errorMessage = "User not registered. Please register first.";
	                        response.getWriter().println("<script>alert('" + errorMessage + "'); window.location.href='./Login.html';</script>");
	                    }
	                } else {
	                    response.getWriter().println("Invalid email or password");
	                }
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace(); // Handle the exception more gracefully in a production environment
	            response.getWriter().println("<script>alert('Error during login. Please try again later.'); window.location.href='./Login.html';</script>");
	        }
	    }

	    private boolean isUserRegistered(Connection con, String email) throws SQLException {
	        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM login WHERE email = ?")) {
	            ps.setString(1, email);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }
	        }
	    }

	    private boolean isPasswordCorrect(Connection con, String email, String password) throws SQLException {
	        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM login WHERE email = ? AND password = ?")) {
	            ps.setString(1, email);
	            ps.setString(2, password);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }
	        }
	    }

	    public void destroy() {
	    }
	}