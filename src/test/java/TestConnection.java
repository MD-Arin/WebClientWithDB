import org.junit.*;
import Model.Database;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class TestConnection {
   Database db;
   Connection conn;

    @Before
    public void setup(){
        //db.getConnection();

    }
    @After
    public void tearDown(){
     //   db.closeConnection();
    }
    @Test
    public void testConnection(){
        System.out.println("Testing Database Connections");
        Database baseimplementation = new BaseImplementation();

        Connection expectedResult = db.getConnection();
        Connection result = db.getConnection();
        assertEquals(expectedResult,result);
    }

    public class BaseImplementation extends Database{

    }

}
