package com.inventroy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DbConnection.DBConnection;


public class AddExistProcess extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
    public AddExistProcess() {
    	
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = DBConnection.connect();
		PrintWriter out = response.getWriter();
		
		String material = request.getParameter("material");
		String qtyStry = request.getParameter("quantity");
		int quantity = Integer.parseInt(qtyStry);
		
		int oldQty = 0;
		int newQty;
		
		
		try {
		String sql = "SELECT * FROM processmaterial WHERE name=?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, material);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			oldQty = rs.getInt("quantity");
		}
		
		if(oldQty >= 0) {
			
		newQty = oldQty + quantity;
		
		String updateQuantity = "UPDATE processmaterial SET quantity=? WHERE name=?";
		
		PreparedStatement pstmt2 = con.prepareStatement(updateQuantity);
		pstmt2.setInt(1, newQty);
		pstmt2.setString(2, material);
		
		int i = pstmt2.executeUpdate();
		
		 if (i > 0) {
		        String message = "Material Updated";

		        out.println("<script>");
		        out.println("alert('" + message + "');");
		        out.println("window.location.href='" + request.getContextPath() + "/addExistProcess.jsp';");
		        out.println("</script>");
		    } else {
		        String message = "Something went wrong please contact to the developer";

		        out.println("<script>");
		        out.println("alert('" + message + "');");
		        out.println("window.location.href='" + request.getContextPath() + "/addExistProcess.jsp';");
		        out.println("</script>");
		    }
	}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
