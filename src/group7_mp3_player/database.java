/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group7_mp3_player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author miaag
 */
public class database {
    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;

    String user = "admin";
    String pass = "admin";

    public void getConnection()
    {
        try {
        // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, pass);
        }catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}  
    }      
    
    public void createTable()
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String qry="CREATE TABLE STUDREC(STNUM INT, NAME VARCHAR(32), PROGRAM VARCHAR(50), PRIMARY KEY(STNUM))";		
            myStmt.executeUpdate(qry);
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
    }
    
  /*  public void AddRow(Student student)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String qry="INSERT INTO STUDREC VALUES(" + student.getStnum()+ ",'" + student.getName()+ "','" + student.getProgram()+"')";
            myStmt.executeUpdate(qry);
            myStmt.close();
            JOptionPane.showMessageDialog(null,"Added successfully!");
	}
	catch(SQLException se)
	{
            String msg="Cannot add. Student" + student.getStnum() + " already exists!";
            JOptionPane.showMessageDialog(null,msg);		
	}
    }

    public void EditRow(Student student, int oldStnum)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String qry="UPDATE STUDREC SET STNUM = " + student.getStnum()+ ", NAME = '" + student.getName()+ "' , PROGRAM = '" + student.getProgram()+ "' WHERE STNUM = " + oldStnum;
            myStmt.executeUpdate(qry);
            myStmt.close();
            JOptionPane.showMessageDialog(null,"Updated successfully!");
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
    }
    
    public void DeleteRow(int oldStnum)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String qry="DELETE FROM STUDREC WHERE STNUM = " + oldStnum;
            myStmt.executeUpdate(qry);
            myStmt.close();
            JOptionPane.showMessageDialog(null,"Deleted successfully!");
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
    }
    
    public ArrayList<Student> ShowRec()
    {
        ArrayList<Student> students=new ArrayList<Student>();
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String query = "SELECT * FROM STUDREC";
	    ResultSet rs = myStmt.executeQuery(query);

	    while(rs.next())
            {
                students.add(new Student(rs.getInt("stnum"),rs.getString("name"),rs.getString("program")));
            }
            rs.close();
	    myStmt.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
        return students;
    } */
}
