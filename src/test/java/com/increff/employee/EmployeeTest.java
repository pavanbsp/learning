package com.increff.employee;

import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;

import org.junit.Test;

public class EmployeeTest {
    @Test
    public void sayHello() throws Exception {
        EmployeeJdbcApi api = new EmployeeJdbcApi();
        api.deleting();
        api.inserting();
        ResultSet rs = api.selecting();
        int i = 0;
        while (rs.next()) {
            i++;
        }
        assertEquals(6, i);
    }
}