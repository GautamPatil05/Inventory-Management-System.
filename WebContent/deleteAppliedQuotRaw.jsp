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

	    // Delete the row with the specified id
	    String deleteSql = "DELETE FROM rawquotation WHERE id=?";
	    PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
	    deleteStmt.setInt(1, id);
	    int deleteResult = deleteStmt.executeUpdate();

	    if (deleteResult > 0) {
	    	
	        // Select the remaining rows ordered by srNo
	        String selectSql = "SELECT id FROM rawquotation ORDER BY id";
	        Statement selectStmt = con.createStatement();
	        ResultSet selectResult = selectStmt.executeQuery(selectSql);

	        // Update the srNo values starting from 1
	        int newSrNo = 1;
	        String updateSql = "UPDATE rawquotation SET id=? WHERE id=?";
	        PreparedStatement updateStmt = con.prepareStatement(updateSql);
	        
	        while (selectResult.next()) {
	            int oldSrNo = selectResult.getInt("id");
	            updateStmt.setInt(1, newSrNo);
	            updateStmt.setInt(2, oldSrNo);
	            updateStmt.executeUpdate();
	            newSrNo++;
	        }

	        String message = "Quatation Log Deleted";

	        out.println("<script>");
	        out.println("alert('" + message + "');");
	        out.println("window.location.href='" + request.getContextPath() + "/appliedRawVendor.jsp';");
	        out.println("</script>");
	    }
	%>

		
</body>
</html>