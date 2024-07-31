package foodmanagement;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NGO extends HttpServlet {
    private static final String URL = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Saloni@2002";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<NGO> ngos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                try (PreparedStatement ps = con.prepareStatement("SELECT id, name, location, distance FROM NGO")) {
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            NGO ngo = new NGO();
                            ngo.setId(rs.getString("id"));
                            ngo.setName(rs.getString("name"));
                            ngo.setLocation(rs.getString("location"));
                            ngo.setDistance(rs.getString("distance"));
                            ngos.add(ngo);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception more gracefully in a production environment
        }
    }

	private void setDistance(String string) {
		// TODO Auto-generated method stub
		
	}

	private void setLocation(String string) {
		// TODO Auto-generated method stub
		
	}

	private void setName(String string) {
		// TODO Auto-generated method stub
		
	}

	private void setId(String string) {
		// TODO Auto-generated method stub
		
	}
}