package Maven;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class AppTest  {
    
    public AppTest( String testName )
    {
      
    }
    @Test
    public static void appTest()
    {
        System.out.println("Smoke test of App test class");
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
    }
    public void testApp()
    {
        assertTrue(true);
    }
}
