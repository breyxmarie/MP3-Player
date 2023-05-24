/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author miaag
 */
public class Database {
     Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;
    
    String user = "root";
    String pass = "admin";
    private String schema = "musicquery";
    private String url = "jdbc:mysql://localhost:3306/" +schema;
    
    
    public Database(){
        getConnection();
        myConn = getconns();
    } 
    
    public Connection getconns(){
        return myConn;
    }
    
    public void getConnections()
    {
        try {
        // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicquery?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, pass);
            System.out.println("Success");
        }catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}  
    }       
    
    
    public void getConnection(){
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
            this.myConn= DriverManager.getConnection(url, user, pass);
        } catch(SQLException se) {
            try {
                System.out.println("database not found. \ncreating database...");
               myConn = DriverManager.getConnection("jdbc:mysql://localhost/?user=" +user +"&password=" +pass);
            //   myConn= DriverManager.getConnection(url, user, pass);
                myStmt=myConn.createStatement();
                myStmt.executeUpdate("CREATE DATABASE " +schema +";");
                Class.forName("com.mysql.cj.jdbc.Driver");
                myConn = DriverManager.getConnection(url, user, pass);
                myStmt=myConn.createStatement();
                myStmt.executeUpdate("CREATE TABLE IF NOT EXISTS music_list(MusicId VARCHAR(3) NOT NULL, FileID VARCHAR(30) NOT NULL, PicFIle VARCHAR(100) NOT NULL, Lyrics VARCHAR(30) NOT NULL, Genre VARCHAR(30) NOT NULL, Years VARCHAR(25) NOT NULL, Titles VARCHAR(30) NOT NULL,Artists VARCHAR(30) NOT NULL, PRIMARY KEY (MusicId));");
                myStmt.executeUpdate("INSERT INTO music_list VALUES('01','music/Sun and Moon.wav','src/image/anees.gif','lyrics/sunandmoon.txt','R&B','2022', 'Sun and Moon', 'anees' );");
                myStmt.executeUpdate("INSERT INTO music_list VALUES('02','music/daylight.wav','src/image/taylor swift.gif','lyrics/daylight.txt',\n" +
"'Romance','2019', 'Daylight','Taylor Swift');");                
                myStmt.executeUpdate("INSERT INTO music_list VALUES('03','music/ligoy.wav','src/image/nadinelustre.gif','lyrics/paligoyligoy.txt','Pop','2014', 'Paligoy - Ligoy', 'Nadine Lustre');");  
                myStmt.executeUpdate("INSERT INTO music_list VALUES('04','music/angel baby.wav','src/image/troyesivan.gif','lyrics/angelbaby.txt','Electropop','2021', 'Angel Baby', 'Troye Sivan');");  
                myStmt.executeUpdate("INSERT INTO music_list VALUES('05','music/running.wav','src/image/katebush.gif','lyrics/runningupthathill.txt','New Wave','1985', 'Running Up that Hill', 'Kate Bush');");  
                
                myStmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex) { 
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
	} catch (ClassNotFoundException ex) { 
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
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
    
     public ArrayList<musics> ShowRec()
    {
        ArrayList<musics> musiclists=new ArrayList<musics>();
      //  getConnection();
        try{
            myStmt=myConn.createStatement();	
            String query = "SELECT * FROM music_list";
	    ResultSet rs = myStmt.executeQuery(query);

	    while(rs.next())
            {
          
                musiclists.add(new musics(rs.getString("MusicId"),rs.getString("FileID"), rs.getString("PicFIle"),rs.getString("Lyrics"),rs.getString("Genre"),rs.getString("Years"),rs.getString("Titles"),rs.getString("Artists")));
             //   System.out.println("data in");
            }
            rs.close();
	    myStmt.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
        return musiclists;
    }
     
     public int musiccount()
    {
      //  ArrayList<musics> musiclists=new ArrayList<musics>();
        int count = 0;
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String query = "SELECT * FROM music_list";
	    ResultSet rs = myStmt.executeQuery(query);

	    while(rs.next())
            {
              //  musiclists.add(new musics(rs.getString("MusicId"),rs.getString("FileID"), rs.getString("PicFIle"),rs.getString("Lyrics"),rs.getString("Genre"),rs.getString("Years")));
             //   System.out.println("data in");
                count++;
            }
            rs.close();
	    myStmt.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
        System.out.println(count);
        return count;
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
