package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import java.sql.SQLException;
import java.lang.Math;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		//Canvas setup
				double width = 800;
				double height = 800;
				int radius = (int) (height/Math.sqrt(height/Math.sqrt(height)));
				
		//set up stage
				Group root = new Group();
				Canvas canvas = new Canvas(width, height);
				
		//Change color of background to make colors more visible
		        GraphicsContext GC = canvas.getGraphicsContext2D();
		        stage.setScene(new Scene(root)); 
		        Pane flow = new Pane();
		        flow.setStyle("-fx-background-color: darkgrey");
		        
		        Scene scene = new Scene(flow, width, height);
	            flow.getChildren().addAll(canvas);
	            stage.setTitle("Project 4");
	            stage.setScene(scene);
	            stage.show();
	     
		        MyPoint center = new MyPoint(width/2,height/2);
				try {
					
		//Set Driver and String URL
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String url = "jdbc:sqlserver://DESKTOP-F35C5EE\\SQLEXPRESS;database=springSchedule2021;integratedSecurity=true";
					try {
						
		//Connection to Database
						Database DB = new Database(url);
						System.out.println("CONNECTED SUCCESSFULLY!!!!!!!");
						
		//Creating SCHEDULE Table
						String sqlTable, sqlInsert, sqlQuery;
						sqlTable = "CREATE TABLE Schedule("
								+ "courseId CHAR(12) NOT NULL UNIQUE,"
								+ "sectionNumber VARCHAR(8) NOT NULL UNIQUE,"
								+ "courseTitle VARCHAR(64),"
								+ "YEAR INT,"
								+ "semester CHAR(6),"
								+ "instructor VARCHAR(24),"
								+ "department CHAR(16),"
								+ "program VARCHAR(48),"
								+ "PRIMARY KEY(courseId, sectionNumber))";				
						
						String filename = "'C:\\Users\\Dango\\eclipse-workspace\\Project4\\src\\ScheduleSpring2021.txt'";
						String tablename = "Schedule";
						sqlInsert = "Bulk Insert " + tablename + " FROM " + filename + " WITH (FIELDTERMINATOR = '\t', ROWTERMINATOR = '\n', FIRSTROW = 2)";
						Database.Schedule spring2021Schedule = DB.new Schedule(sqlTable,sqlInsert);
		//Updating Schedule
						sqlQuery = "UPDATE Schedule "
								+ "SET instructor = 'Doug Troegar'"
								+ " WHERE courseId =  '10200 CC2' AND sectionNumber = '32119' ";
						
						spring2021Schedule.updateTable(sqlQuery);
						sqlQuery = "UPDATE Schedule "
								+ "SET instructor = 'JESSIE SU'"
								+ " WHERE courseId =  '10200 CC1' AND sectionNumber = '32118' ";
						spring2021Schedule.updateTable(sqlQuery);
		//Creating COURSES Table
						sqlTable = "CREATE TABLE Courses("
								+ "courseId CHAR(12) PRIMARY KEY REFERENCES Schedule(courseId),"
								+ "courseTitle VARCHAR(64),"
								+ "department CHAR(16))";
						
						sqlInsert = "INSERT INTO Courses "
								+ "SELECT courseId, courseTitle, department "
								+ "FROM Schedule";
						Database.Courses springCourses2021 = DB.new Courses(sqlTable,sqlInsert);
		//Updating COURSES
						sqlQuery = "UPDATE Courses "
								+ "SET courseTitle = 'JESSIE SU CLASS'"
								+ "WHERE courseId = '59866 3HJ' AND department = 'Computer Science'";
						springCourses2021.updateTable(sqlQuery);
								
		//Creating STUDENT table
						sqlTable = "CREATE TABLE Student("
								+ "emplId INT PRIMARY KEY, "
								+ "firstName VARCHAR(32)  NOT NULL,"
								+ "lastName VARCHAR(32) NOT NULL,"
								+ "email VARCHAR(64) NOT NULL,"
								+ "gender CHAR CHECK (gender = 'F' or gender = 'M' or gender = 'U'))";
						
						sqlInsert = "INSERT INTO Student VALUES ( 1, 'FirstName-1','LastName-1','Name#1@home.com', 'M');"
								+ "INSERT INTO Student VALUES ( 2, 'FirstName-2','LastName-2','Name#2@home.com', 'F');"
								+ "INSERT INTO Student VALUES ( 3, 'FirstName-3','LastName-3','Name#3@home.com', 'M');"
								+ "INSERT INTO Student VALUES ( 4, 'FirstName-4','LastName-4','Name#4@home.com', 'U');"
								+ "INSERT INTO Student VALUES ( 5, 'FirstName-5','LastName-5','Name#5@home.com', 'F');"
								+ "INSERT INTO Student VALUES ( 6, 'FirstName-6','LastName-6','Name#6@home.com', 'M');"
								+ "INSERT INTO Student VALUES ( 7, 'FirstName-7','LastName-7','Name#7@home.com', 'F');"
								+ "INSERT INTO Student VALUES ( 8, 'FirstName-8','LastName-8','Name#8@home.com', 'M');"
								+ "INSERT INTO Student VALUES ( 9, 'FirstName-9','LastName-9','Name#9@home.com', 'F');"
								+ "INSERT INTO Student VALUES ( 10, 'FirstName-10','LastName-10','Name#10@home.com', 'U');"
								+ "INSERT INTO Student VALUES ( 11, 'FirstName-11','LastName-11','Name#11@home.com', 'U');"
								+ "INSERT INTO Student VALUES ( 12, 'FirstName-12','LastName-12','Name#12@home.com', 'M');"
								+ "INSERT INTO Student VALUES ( 13, 'FirstName-13','LastName-13','Name#13@home.com', 'F');"
								+ "INSERT INTO Student VALUES ( 14, 'FirstName-14','LastName-14','Name#14@home.com', 'M');"
								+ "INSERT INTO Student VALUES ( 15, 'FirstName-15','LastName-15','Name#15@home.com', 'F');"
								+ "INSERT INTO Student VALUES ( 16, 'FirstName-16','LastName-16','Name#16@home.com', 'U');"
								+ "INSERT INTO Student VALUES ( 17, 'FirstName-17','LastName-17','Name#17@home.com', 'F');"
								+ "INSERT INTO Student VALUES ( 18, 'FirstName-18','LastName-18','Name#18@home.com', 'M');"
								+ "INSERT INTO Student VALUES ( 19, 'FirstName-19','LastName-19','Name#19@home.com', 'M');"
								+ "INSERT INTO Student VALUES ( 20, 'FirstName-20','LastName-20','Name#20@home.com', 'F');";
						Database.Student springStudent2021 = DB.new Student(sqlTable,sqlInsert);
		//Updating STUDENT
						sqlQuery = "INSERT INTO Student "
								+ "(emplId,firstName,lastName,email,gender) VALUES "
								+ "('21','JESSIE-21','SU-21','Jessie#21@home.com','F')";
						springStudent2021.updateTable(sqlQuery);
		//Creating CLASSES table
						sqlTable = "CREATE TABLE Classes("
								+ "courseId CHAR(12) REFERENCES Schedule(courseID),"
								+ "emplId INT REFERENCES Student(emplId),"
								+ "sectionNumber VARCHAR(8) REFERENCES Schedule(sectionNumber),"
								+ "year INT,"
								+ "semester CHAR(6),"
								+ "grade CHAR CHECK(grade = 'A' or grade = 'B' or grade = 'C' or grade = 'D' or grade = 'F' or grade = 'W'),"
								+ "PRIMARY KEY (emplId, courseId, sectionNumber))";
						
						sqlInsert =	"INSERT INTO Classes VALUES ('22100 F', 1, '32131', 2021, 'Spring', 'B');"
								+ "INSERT INTO Classes VALUES ('22100 P', 2, '32132', 2021, 'Spring', 'B');"
								+ "INSERT INTO Classes VALUES ('22100 R', 3, '32150', 2021, 'Spring', 'A');"
								+ "INSERT INTO Classes VALUES ('22100 F', 4, '32131', 2021, 'Spring', 'B');"
								+ "INSERT INTO Classes VALUES ('22100 R', 5, '32150', 2021, 'Spring', 'D');"
								+ "INSERT INTO Classes VALUES ('22100 P', 6, '32150', 2021, 'Spring', 'C');"
								+ "INSERT INTO Classes VALUES ('22100 R', 7, '32150', 2021, 'Spring', 'D');"
								+ "INSERT INTO Classes VALUES ('22100 F', 8, '32150', 2021, 'Spring', 'B');"
								+ "INSERT INTO Classes VALUES ('22100 F', 9, '32150', 2021, 'Spring', 'B');"
								+ "INSERT INTO Classes VALUES ('22100 R', 10, '32150', 2021, 'Spring', 'B');"
								+ "INSERT INTO Classes VALUES ('22100 R', 11, '32150', 2021, 'Spring', 'C');"
								+ "INSERT INTO Classes VALUES ('22100 R', 12, '32150', 2021, 'Spring', 'D');"
								+ "INSERT INTO Classes VALUES ('22100 F', 13, '32150', 2021, 'Spring', 'A');"
								+ "INSERT INTO Classes VALUES ('22100 R', 14, '32150', 2021, 'Spring', 'A');"
								+ "INSERT INTO Classes VALUES ('22100 P', 15, '32150', 2021, 'Spring', 'D');"
								+ "INSERT INTO Classes VALUES ('22100 R', 16, '32150', 2021, 'Spring', 'F');"
								+ "INSERT INTO Classes VALUES ('22100 R', 17, '32150', 2021, 'Spring', 'F');"
								+ "INSERT INTO Classes VALUES ('22100 P', 18, '32150', 2021, 'Spring', 'D');"
								+ "INSERT INTO Classes VALUES ('22100 P', 19, '32150', 2021, 'Spring', 'W');"
								+ "INSERT INTO Classes VALUES ('22100 R', 20, '32150', 2021, 'Spring', 'W');";
						Database.Classes springClasses2021 = DB.new Classes(sqlTable, sqlInsert);
		//Updating Classes
						sqlQuery = "UPDATE Classes "
								+ "SET grade = 'A' "
								+ "WHERE emplId = '20' AND courseId = '22100 R'";
						springClasses2021.updateTable(sqlQuery);
		//Creating AGGREGATEGRADE Table
						sqlTable = "CREATE TABLE AggregateGrades("
												+ "grade CHAR PRIMARY KEY,"
												+ "numberStudents INT)";
						
						sqlInsert = "INSERT INTO AggregateGrades "
												+"SELECT	grade, count(grade) "
												+"FROM		Classes "
												+"GROUP BY	grade";
						Database.AggregateGrades springAggregate2021 = DB.new AggregateGrades(sqlTable, sqlInsert);
						
		//Getting number of rows of table
						sqlQuery = "SELECT * FROM AggregateGrades";
						Database.getRow getRowAggregateGrades = DB.new getRow(sqlQuery);
						int rows = getRowAggregateGrades.returnRowNum();
						
		//Creating Pie chart for #of students for _ grade
						HistogramAlphaBet mapAggregateGrades = new HistogramAlphaBet(springAggregate2021.getAggregateGrades(sqlQuery));
						HistogramAlphaBet.MyPieChart piechartAggregateGrades = mapAggregateGrades.new MyPieChart(rows, center, radius , 0, mapAggregateGrades);
						piechartAggregateGrades.draw(GC, center);
					} catch (SQLException e) {
						System.out.println("CANNOT CONNECT TO DATABASE\n");
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					System.out.println("CANNOT FIND DRIVER CLASS\n");
					e1.printStackTrace();
				}
				
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
