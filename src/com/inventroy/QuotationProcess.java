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


public class QuotationProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QuotationProcess() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int qid = Integer.parseInt(request.getParameter("qid"));
		String material = request.getParameter("material");
		int quantity = Integer.parseInt(request.getParameter("quantity")); 
		String unit = request.getParameter("unit");
		int cpu = Integer.parseInt(request.getParameter("cpu")); 
		String vName = request.getParameter("vName");
		String status = request.getParameter("status");
		
		PrintWriter out = response.getWriter();
		Connection con = DBConnection.connect();
		
		int srNo= 0;
		
		try {
			
			String sqlLast = "SELECT * FROM processquotation ORDER BY id DESC LIMIT 1";
			
			PreparedStatement last = con.prepareStatement(sqlLast); 
			ResultSet rs = last.executeQuery();
			
			if (rs.next()) {
				
				int sr = rs.getInt("id");
				srNo = sr + 1;
			}

			
			String sql = "INSERT INTO processquotation(id, qid, name, quantity, unit, costPerUnit, vname, status)"
					+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, srNo);
			pstmt.setInt(2, qid);
			pstmt.setString(3, material);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, unit);
			pstmt.setInt(6, cpu);
			pstmt.setString(7, vName);
			pstmt.setString(8, status);
			
			int i = pstmt.executeUpdate();
			
			if (i > 0) {
				
				String message = "Quotation Submited";
		        
		        out.println("<script>");
		        out.println("alert('" + message + "');");
		        out.println("window.location.href='" + request.getContextPath() + "/quotationProcess.jsp?id="+qid+"';");
		        out.println("</script>");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	
	}

}
