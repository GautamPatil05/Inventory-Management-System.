package com.inventroy;

public class AdminGetSet {

	public static String uname;
	public static String password;
	public static int id;
	public static String name;
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		AdminGetSet.name = name;
	}
	public static int rawTotal;
	public static int processTotal;
	public static int total;
	public static float rawPer;
	public static float processPer;
	
	public static float getRawPer() {
		return rawPer;
	}
	public static void setRawPer(float rawPer) {
		AdminGetSet.rawPer = rawPer;
	}
	public static float getProcessPer() {
		return processPer;
	}
	public static void setProcessPer(float processPer) {
		AdminGetSet.processPer = processPer;
	}
	public static int getTotal() {
		return total;
	}
	public static void setTotal(int total) {
		AdminGetSet.total = total;
	}
	public static int getRawTotal() {
		return rawTotal;
	}
	public static void setRawTotal(int rawTotal) {
		AdminGetSet.rawTotal = rawTotal;
	}
	public static int getProcessTotal() {
		return processTotal;
	}
	public static void setProcessTotal(int processTotal) {
		AdminGetSet.processTotal = processTotal;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		AdminGetSet.id = id;
	}
	public static String getUname() {
		return uname;
	}
	public static void setUname(String uname) {
		AdminGetSet.uname = uname;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		AdminGetSet.password = password;
	}
	
}
