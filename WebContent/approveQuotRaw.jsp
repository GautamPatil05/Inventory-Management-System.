<%@page import="com.DbConnection.DBConnection"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Raw Material</title>
</head>
<body>
		
		<%
		
	    Connection con = DBConnection.connect();

	    String idStr = request.getParameter("id");
	    int id = Integer.parseInt(idStr);        
	    
		int qid = 0;
		int quantity = 0;
		int addQuant = 0;
		int newQuant = 0;
		
	    // Selecting qid
	    String sql4 = "SELECT * FROM rawquotation WHERE id=?";
	    
	    PreparedStatement getQid = con.prepareStatement(sql4);
	    getQid.setInt(1, id);
	   // getQid.setString(2,"raw");
	    
	    ResultSet rs = getQid.executeQuery();
	    
	    while(rs.next()) {
	    	
	    	qid = rs.getInt("qid");
	    	addQuant = rs.getInt("quantity");
	    }
	    
	    // Delete the row with the specified id 
	    String deleteSql = "UPDATE rawquotation SET status=? WHERE id=?";
	    
	    PreparedStatement set = con.prepareStatement(deleteSql);
	    set.setString(1,"Approved");
	    set.setInt(2, id);
	    
	    int i = set.executeUpdate();

	    if (i > 0) {
	    	
	    	// Selecting Old value of quantity
	    	String sql3 = "SELECT quantity FROM rawmaterial where srNo=?";
	    	
	    	PreparedStatement quant = con.prepareStatement(sql3);
	    	quant.setInt(1, qid);
	    	
	    	ResultSet rs2 = quant.executeQuery();
	  	    
	  	    while(rs2.next()) {
	  	    	
	  	    	quantity = rs2.getInt("quantity");
	  	    }
	    	
	  	    // Setting up updated quantity
	  	    newQuant = quantity + addQuant;
	  	    
	    	String sql2 = "UPDATE rawmaterial SET quantity=? WHERE srNo=?";
	    	PreparedStatement upd = con.prepareStatement(sql2);
	    	upd.setInt(1, newQuant);
	    	upd.setInt(2, qid);
	    	
			upd.executeUpdate();

	        String message = "Quotation Approved";

	        out.println("<script>");
	        out.println("alert('" + message + "');");
	        out.println("window.location.href='" + request.getContextPath() + "/reqQuotationRaw.jsp';");
	        out.println("</script>");
	       
	    	}
	    
	    else {
	    	
	    	String message = "Something went Wrong";

	        out.println("<script>");
	        out.println("alert('" + message + "');");
	        out.println("window.location.href='" + request.getContextPath() + "/reqQuotationRaw.jsp';");
	        out.println("</script>");
	    	
	    }
	%>

		
</body>
</html>