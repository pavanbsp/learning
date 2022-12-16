package com.increff.employee;

import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;
import com.increff.employee.EmployeeApi;
import org.junit.Test;

public class EmployeeTest {
    @Test
    public void sayHello() throws Exception {
        EmployeeApi api = new EmployeeApi();
        //api.deleting("id", "14");
        //api.inserting(14, "sujatha", 70);
        ResultSet rs = api.selecting();
        int i = 0;
        while (rs.next()) {
            i++;
        }
        assertEquals(6, i);
    }
}