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


public class AddProcess extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
    public AddProcess() {
    	
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		Connection con = DBConnection.connect();
		
		String material = request.getParameter("material");
		String quantityStr = request.getParameter("quantity");
		int quantity = Integer.parseInt(quantityStr);
		String unit = request.getParameter("unit");
		String cpuStr = request.getParameter("cpu");
		int cpu = Integer.parseInt(cpuStr);	
		
		int id = AdminGetSet.getId();
		int srNo = 0;
		
		try {
			
			//Finding last value of srNo
			String sqlLast = "SELECT * FROM processmaterial ORDER BY srNo DESC LIMIT 1";
			
			PreparedStatement last = con.prepareStatement(sqlLast); 
			ResultSet rs = last.executeQuery();
			
			if (rs.next()) {
				
				int sr = rs.getInt("srNo");
				srNo = sr + 1;
			}
			
		String sql = "INSERT INTO processmaterial (srNo, name, quantity, unit, costPerUnit, userID) SELECT ?, ?, ?, ?, ?, ? WHERE EXISTS (SELECT id FROM admin WHERE id = ?)";
		
		
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, srNo);
			pstmt.setString(2, material);
			pstmt.setInt(3, quantity);
			pstmt.setString(4, unit);
			pstmt.setInt(5, cpu);
			pstmt.setInt(6, id);
			pstmt.setInt(7, id);
			
			int j = pstmt.executeUpdate();
			
			if (j > 0) {
				String message = "Material Added";

		        out.println("<script>");
		        out.println("alert('" + message + "');");
		        out.println("window.location.href='" + request.getContextPath() + "/addProcess.jsp';");
		        out.println("</script>");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
