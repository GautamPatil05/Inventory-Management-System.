package com.inventroy;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DbConnection.DBConnection;

public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
    public Login() {
    	
        super();
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hi");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		Connection con = DBConnection.connect();
		
		try {
		String sql = "SELECT * FROM admin WHERE uname=? AND password=?";
		
			PreparedStatement userCheck = con.prepareStatement(sql);
			userCheck.setString(1, uname);
			userCheck.setString(2, password);
			
			ResultSet rs = userCheck.executeQuery();
			
			if(rs.next()) 
			{	AdminGetSet.setName(rs.getString("name"));
				int id = rs.getInt("id");
				AdminGetSet.setUname(uname);
				AdminGetSet.setUname(uname);
				AdminGetSet.setId(id);
				
				response.sendRedirect("Dashboard.jsp");
			}
			else 
			{
				String message = "Wrong UserID or Password";

		        out.println("<script>");
		        out.println("alert('" + message + "');");
		        out.println("window.location.href='" + request.getContextPath() + "/login.jsp';");
		        out.println("</script>");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
