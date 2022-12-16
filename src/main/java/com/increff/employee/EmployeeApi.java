package com.increff.employee;

import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class EmployeeApi {
    private static final Logger logger = Logger.getLogger(EmployeeApi.class);
    private Connection con;
    public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException {
    }

    public EmployeeApi() throws Exception {
        Properties props = new Properties();
        InputStream inStream = new FileInputStream("employee.properties");
        props.load(inStream);

        Class.forName(props.getProperty("jdbc.driver"));
        con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"),
                props.getProperty("jdbc.password"));
    }

    public ResultSet selecting() throws SQLException{
        logger.warn("selecting employee");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from employee");
//        while(rs.next()) {
//            logger.info(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
//        }
        return rs;
    }

    public void inserting(int id, String name, int age) throws SQLException{
        Statement stmt=con.createStatement();
        stmt.executeUpdate("insert into employee values(" + id + ", " + '"' +  name + '"' + ", " + age + ")");
        stmt.close();
    }

    public void deleting(String row, String val) throws SQLException{
        Statement stmt=con.createStatement();
        stmt.executeUpdate("delete from employee where " + row + " = " + val);
        stmt.close();
    }
}


