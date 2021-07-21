package com.arrow.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.arrow.accelerators.ActionEngine;

public class DBConnection {

	// Should be defined as jdbc:mysql://host:port/database name
	
	private static String databaseURLAuto = "jdbc:oracle:thin:@//wkclsct1stage-scn.na.wkglobal.com:1521/CT1STAGE";
	static Connection connection=null;
	static String resultValue = "";
	static ResultSet rs;
	static int count=0;
	static String testEnv = "Staging";
	
	static String connectionUrl= databaseURLAuto;
	private static String dbusername = "arrowwebuser";
	private static String dbpassword = "arrowwebuser";
	static ActionEngine eng=new ActionEngine();
	
	public static String executeSQLQuery(String sqlQuery) throws SQLException {
		
		//connection=null;
		rs=null;
		try {
			// Register the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			// Creating connection
			connection = DriverManager.getConnection(connectionUrl, dbusername,dbpassword);

			if (connection != null) {
				System.out.println("Connected to the database...");
			} else {
				System.out.println("Database connection failed to" + eng.environment + "Environment");
			}

			// Creating statement
			Statement stmt = connection.createStatement();

			// Executing queries
			rs = stmt.executeQuery(sqlQuery);

			try {
				if(rs.next()){
					resultValue = rs.getString(1).toString();	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException err) {
				System.out.println("No Records obtained for this specific query");
				err.printStackTrace();
			}
		} catch (SQLException sqlEx) {
			System.out.println("SQL Exception:" + sqlEx.getStackTrace());
		}finally {
			connection.close();
		}
		return resultValue;
	}
	
	public static ArrayList<String> fetchPermKey(String sqlQuery) throws SQLException {
	
		 ArrayList<String> resultList = new ArrayList<String>();
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		 try {
			// Register the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			// Creating connection
			connection = DriverManager.getConnection(connectionUrl, dbusername,dbpassword);

			if (connection != null) {
				System.out.println("Connected to the database...");
			} else {
				System.out.println("Database connection failed to" + eng.environment + "Environment");
			}

			// Creating statement
			Statement stmt = connection.createStatement();

			// Executing queries
			rs = stmt.executeQuery(sqlQuery);
			ResultSetMetaData rsmd = rs.getMetaData(); 
			int columnCount = rsmd.getColumnCount();
			
			try {
                while(rs.next()){
                	int i = 1;
                	   while(i <= columnCount) {
                		   if(i==2){
                			   Timestamp dbSqlTime = rs.getTimestamp(i++);
                			   Date date = new Date(dbSqlTime.getTime());
                			   resultList.add(sdf.format(date));
                		   }else{
                			   resultList.add(rs.getString(i++));   
                		   }
                	       
                	   }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (NullPointerException ex) {
                System.out.println("No Records found for this specific query" +ex.getStackTrace());
            }
			
			
		} catch (SQLException sqlEx) {
			System.out.println("SQL Exception:" + sqlEx.getStackTrace());
		}finally {
			connection.close();
		}
		return resultList;
	}	
	
	public static ArrayList<String> getResult(String sqlQuery) throws Throwable {
		boolean flag = false;
		
		 ArrayList<String> resultList = new ArrayList<String>();
		 
		 try {
			// Register the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			// Creating connection
			connection = DriverManager.getConnection(connectionUrl, dbusername,dbpassword);

			if (connection != null) {
				System.out.println("Connected to the database...");
			} else {
				System.out.println("Database connection failed to" + eng.environment + "Environment");
			}

			// Creating statement
			Statement stmt = connection.createStatement();

			// Executing queries
			rs = stmt.executeQuery(sqlQuery);
			ResultSetMetaData rsmd = rs.getMetaData(); 
			int columnCount = rsmd.getColumnCount();
			
			try {
               while(rs.next()){
               	int i = 1;
               	   while(i <= columnCount) {
               		   resultList.add(rs.getString(i++));
               	   }
               }
               flag = true;
           } catch (SQLException e) {
               e.printStackTrace();
           }
           catch (NullPointerException ex) {
               System.out.println("No Records found for this specific query" +ex.getStackTrace());
           }
			if (flag) {
				System.out.println("Successyfully connected to database and found ID");
				//eng.SuccessReport("Connection to database", "Successyfully connected to database and found ID");
			} else if(!flag){
				eng.failureReport("Connection to database","Could not connect to the database");
			}
			
		} catch (SQLException sqlEx) {
			System.out.println("SQL Exception:" + sqlEx.getStackTrace());
		}finally {
			connection.close();
		}
		return resultList;
	}	
	
	public static int getResultCount(String sqlQuery) throws SQLException {
		
		try {
			// Register the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			// Creating connection
			connection = DriverManager.getConnection(connectionUrl, dbusername,dbpassword);

			if (connection != null) {
				System.out.println("Connected to the database...");
			} else {
				System.out.println("Database connection failed to" + testEnv + "Environment");
			}

			// Creating statement
			Statement stmt = connection.createStatement();

			// Executing queries
			rs = stmt.executeQuery(sqlQuery);
			try {
              while(rs.next()){
            	  count=rs.getInt(1); 
              }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch (NullPointerException ex) {
				System.out.println("No Records found for this specific query" +ex.getStackTrace());
			}
			
			
		} catch (SQLException sqlEx) {
			System.out.println("SQL Exception:" + sqlEx.getStackTrace());
		}finally {
			connection.close();
		}
		return count;
	}
	
}
