package com.increff.employee;

import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeJdbcApi {
    private static final Logger logger = Logger.getLogger(EmployeeJdbcApi.class);
    private Connection con;


    public EmployeeJdbcApi() throws Exception {
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

    public void inserting() throws SQLException{
        Statement stmt = con.createStatement();
        for (int i = 0; i < 3; i++) {
            int id = i;
            int age = 30 + i;
            String name = "name " + i;
            String query = "insert into employee values(" + id + ", '" + name + "', " + age + ")";
            logger.info(query);
            stmt.executeUpdate(query);
        }
        stmt.close();
    }

    public void deleting() throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from employee");
        List<Integer> idList = new ArrayList<Integer>();
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }
        for (int i = 0; i < idList.size(); i++) {
            stmt.executeUpdate("delete from employee where id=" + idList.get(i));
        }
        stmt.close();
    }
}


