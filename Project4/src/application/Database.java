package application;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

public class Database {
	String url;

	Connection connection;
	ResultSet RS;
	
	Map<Character, Integer> grades = new HashMap<Character, Integer>();
	
	Database(String url) throws SQLException{
		this.url = url;
		this.connection = getConnection(url);
	}
	
	public Connection getConnection(String url) throws SQLException{
		Connection connection = null;
		Properties connectionProperties = new Properties();
		try {
			connection = DriverManager.getConnection(url, connectionProperties);
			Statement stmt = connection.createStatement();
			String sql = "IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'Classes') DROP TABLE Classes\n"
					+ "IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'Student') DROP TABLE Student\n"
					+ "IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'Courses') DROP TABLE Courses\n"
					+ "IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'GradeAggregate') DROP TABLE GradeAggregate\n"
					+ "IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'AggregateGrades') DROP TABLE AggregateGrades\n"
					+ "IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'Schedule') DROP TABLE Schedule";
			stmt.executeUpdate(sql);
			System.out.println("Connected and Dropped tables if exists\n");
		}
		catch(SQLException e){
			System.out.println("There was an error connecting\n");
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	class Schedule {
		String sqlTableSchedule;
		String sqlInsertSchedule;
		
		Schedule(String sqlTable, String sqlInsert){
			this.sqlTableSchedule = sqlTable;
			try {
				PreparedStatement psTableSchedule = connection.prepareStatement(sqlTableSchedule);
				psTableSchedule.executeUpdate();
				System.out.println("SCHEDULE TABLE CREATED SUCCESSFULLY\n");
			} catch (SQLException e) {
				System.out.println("SCHEDULE TABLE CREATED UNSUCCESSFULLY\n");
				e.printStackTrace();
			}
			
			this.sqlInsertSchedule = sqlInsert;
			try {
				PreparedStatement psInsertSchedule = connection.prepareStatement(sqlInsertSchedule);
				psInsertSchedule.executeUpdate();
				System.out.println("SUCCESSFULLY POPULATED SCHEDULE TABLE WITH TXT FILE\n");
			} catch (SQLException e) {
				System.out.println("UNSUCCESSFULLY POPULATED SCHEDULE TABLE WITH TXT FILE\n");
				e.printStackTrace();
			}
		}
		void updateTable(String sqlQuery) throws SQLException{
			String updateSqlQuery = sqlQuery;
			PreparedStatement psUpdatetable = connection.prepareStatement(updateSqlQuery);
			try {
				psUpdatetable.execute();
				System.out.println("SUCCESSFULLY UPDATED\n");
			}catch(SQLException e){
				System.out.println("UNSUCCESSFULLY UPDATED");
				e.printStackTrace();
			}
		}
		
	}
	
	class Student{
		String sqlTableStudent;
		String sqlInsertStudent;
		Student(String sqlTable, String sqlInsert){
			this.sqlTableStudent = sqlTable;
			try {
				PreparedStatement psTableStudent = connection.prepareStatement(sqlTableStudent);
				psTableStudent.executeUpdate();
				System.out.println("STUDENTS TABLE CREATED SUCCESSFULLY");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			this.sqlInsertStudent = sqlInsert;
			try {
				PreparedStatement psInsertStudent = connection.prepareStatement(sqlInsertStudent);
				psInsertStudent.executeUpdate();
				System.out.println("SUCCESSFULLY POPULATED STUDENTS TABLE\n");
			} catch (SQLException e) {
				System.out.println("UNSUCCESSFULLY POPULATED STUDENTS TABLE\n");
				e.printStackTrace();
			}
		}
		
		void updateTable(String sqlQuery) throws SQLException{
			String updateSqlQuery = sqlQuery;
			PreparedStatement psUpdatetable = connection.prepareStatement(updateSqlQuery);
			try {
				psUpdatetable.execute();
				System.out.println("SUCCESSFULLY UPDATED\n");
			}catch(SQLException e){
				System.out.println("UNSUCCESSFULLY UPDATED");
				e.printStackTrace();
			}
		}
		
	}
	class Courses{
		String sqlTableCourses;
		String sqlInsertCourses;
		
		Courses(String sqlTable, String sqlInsert){
			this.sqlTableCourses = sqlTable;
			try {
				PreparedStatement psTableCourses = connection.prepareStatement(sqlTableCourses);
				psTableCourses.executeUpdate();
				System.out.println("COURSES TABLE CREATED SUCCESSFULLY");
			} catch(SQLException e) {
				System.out.println("COURSES TABLE CREATED UNNSUCCESSFULLY");
				e.printStackTrace();
			}
			
			this.sqlInsertCourses = sqlInsert;
			try {
				PreparedStatement psInsertCourses = connection.prepareStatement(sqlInsertCourses);
				psInsertCourses.executeUpdate();
				System.out.println("SUCCESSFULLY POPULATED COURSES TABLE\n");
			}catch(SQLException e) {
				System.out.println("UNSUCCESSFULLY POPULATED COURSES TABLE\n");
				e.printStackTrace();
			}
		}
		void updateTable(String sqlQuery) throws SQLException{
			String updateSqlQuery = sqlQuery;
			PreparedStatement psUpdatetable = connection.prepareStatement(updateSqlQuery);
			try {
				psUpdatetable.execute();
				System.out.println("SUCCESSFULLY UPDATED\n");
			}catch(SQLException e){
				System.out.println("UNSUCCESSFULLY UPDATED");
				e.printStackTrace();
			}
		}
	}
	class Classes{
		String sqlTableClasses;
		String sqlInsertClasses;
		Classes(String sqlTable, String sqlInsert){
			this.sqlTableClasses = sqlTable;
			try {
				PreparedStatement psTableClasses = connection.prepareStatement(sqlTableClasses);
				psTableClasses.executeUpdate();
				System.out.println("CLASSES TABLE CREATED SUCCESSFULLY");
			} catch(SQLException e) {
				System.out.println("CLASSES TABLE CREATED UNSUCCESSFULLY");
				e.printStackTrace();
			}
			
			this.sqlInsertClasses = sqlInsert;
			try {
				PreparedStatement psInsertClasses = connection.prepareStatement(sqlInsertClasses);
				psInsertClasses.executeUpdate();
				System.out.println("SUCCESSFULLY POPULATED COURSES TABLE\n");
			}catch(SQLException e) {
				System.out.println("UNSUCCESSFULLY POPULATED COURSES TABLE\n");
				e.printStackTrace();
			}
			
		}
		void updateTable(String sqlQuery) throws SQLException{
			String updateSqlQuery = sqlQuery;
			PreparedStatement psUpdatetable = connection.prepareStatement(updateSqlQuery);
			try {
				psUpdatetable.execute();
				System.out.println("SUCCESSFULLY UPDATED\n");
			}catch(SQLException e){
				System.out.println("UNSUCCESSFULLY UPDATED");
				e.printStackTrace();
			}
		}
		
	}
	
	
	class AggregateGrades{
		String sqlTableAggregateGrades;
		String sqlInsertAggregateGrades;
		
		AggregateGrades(String sqlTable, String sqlInsert) throws SQLException{
			this.sqlTableAggregateGrades = sqlTable;
			try {
				PreparedStatement psTableAggregateGrades = connection.prepareStatement(sqlTableAggregateGrades);
				psTableAggregateGrades.executeUpdate();
				System.out.println("AGGREGATEGRADES TABLE CREATED SUCCESSFULLY");
			}
			catch(SQLException e){
				System.out.println("AGGREGATEGRADES TABLE CREATED UNSUCCESSFULLY");
				e.printStackTrace();
			}
			
			this.sqlInsertAggregateGrades = sqlInsert;
			try {
				PreparedStatement psInsertAggregateGrades = connection.prepareStatement(sqlInsertAggregateGrades);
				psInsertAggregateGrades.executeUpdate();
				System.out.println("SUCCESSFULLY POPULATED AGGREGATEGRADES TABLE\n");
			}catch(SQLException e) {
				System.out.println("UNSUCCESSFULLY POPULATED AGGREGATEGRADES TABLE\n");
				e.printStackTrace();
			}
			
		}
		
		public Map<Character,Integer> getAggregateGrades(String sqlQuery) throws SQLException{
			Map<Character, Integer> mapAggregateGrades = new HashMap<Character,Integer>();
			
			String sqlQueryAggregateGrades = sqlQuery;
			PreparedStatement psQueryAggregateGrades = connection.prepareStatement(sqlQueryAggregateGrades);
			try {
				ResultSet RS = psQueryAggregateGrades.executeQuery();
				while(RS.next()){
						mapAggregateGrades.put(RS.getString("grade").charAt(0), RS.getInt("numberStudents"));
				}
				System.out.println("SUCCESSFULLY RS QUERY\n");
			}
			catch(SQLException e){
				System.out.println("UNSUCCESSFULLY RS QUERY\n");
				e.printStackTrace();
			}
			
			return mapAggregateGrades;	
		}
	}
	class getRow{
		String sqlgetNumRow;
		int rowCount = 0;
		getRow(String sqlQuery){
			this.sqlgetNumRow = sqlQuery;
			try {
				PreparedStatement psgetRow = connection.prepareStatement(sqlgetNumRow);
				ResultSet rs = psgetRow.executeQuery();
				while(rs.next()) {
				    rowCount++; 
				}
				System.out.println("SUCCESSFULLY COUNTED ROWS" + " \t" + "Row Count: " + rowCount + "\n");
			}catch(SQLException e) {
				System.out.println("UNSUCCESSFULLY COUNTED ROWS\n");
				e.printStackTrace();
			}
		}
		public int returnRowNum() {
			System.out.println("SUCCESSFULLY RETURN NUMBER OF ROWS IN TABLE");
			return rowCount;}
		
	}
}

