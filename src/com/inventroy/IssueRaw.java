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


public class IssueRaw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public IssueRaw() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("material");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String issuerName = request.getParameter("issuerName");
	    String date = request.getParameter("date");
		String time = request.getParameter("time");
		
		int id = AdminGetSet.getId();
		int oldQty = 0;
		int newQty;
		int srNo = 0;
		Connection con = DBConnection.connect();
		
		try {
			
			//Finding last value of srNo
			String sqlLast = "SELECT * FROM issueraw ORDER BY srNo DESC LIMIT 1";
			
			PreparedStatement last = con.prepareStatement(sqlLast); 
			ResultSet rst = last.executeQuery();
			
			if (rst.next()) {
				
				int sr = rst.getInt("srNo");
				srNo = sr + 1;
			}
			
			// Selecting old Qty from raw material
			String sql3 = "SELECT quantity FROM rawmaterial WHERE name=?";
			
			PreparedStatement pstmt3 = con.prepareStatement(sql3);
			pstmt3.setString(1, name);
			
			ResultSet rs = pstmt3.executeQuery();
			
			while (rs.next()) {
				
				oldQty = rs.getInt("quantity");
				
			}
			
			if(quantity <= oldQty) {
			
			// Subtracting from raw material
			newQty = oldQty - quantity;
			
			
			String sql2 = "UPDATE rawmaterial SET quantity=? where name=?";
			
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, newQty);
			pstmt2.setString(2, name);
			pstmt2.executeUpdate();
				
				
			// Updating in Log
			String sql = "INSERT INTO issueraw(userID, srNo, name, quantity, issuer, date, time)  value(?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, srNo);
			pstmt.setString(3, name);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, issuerName);
			pstmt.setString(6, date);
			pstmt.setString(7, time);
			
			int i = pstmt.executeUpdate();
			
			if(i > 0) {
				
				 String message = "Material Issued and Log Updated";

			        out.println("<script>");
			        out.println("alert('" + message + "');");
			        out.println("window.location.href='" + request.getContextPath() + "/issueRaw.jsp';");
			        out.println("</script>");
			}
			else {
				
				 String message = "Something went wrong please contact to the developer";

			        out.println("<script>");
			        out.println("alert('" + message + "');");
			        out.println("window.location.href='" + request.getContextPath() + "/issueRaw.jsp';");
			        out.println("</script>");
			}
			
			}
			else {
				
				 String message = "Insufficient Quantity of Material to Isuue";

			        out.println("<script>");
			        out.println("alert('" + message + "');");
			        out.println("window.location.href='" + request.getContextPath() + "/issueRaw.jsp';");
			        out.println("</script>");
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
