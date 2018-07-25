package no.sysco.middleware;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 100tsa
 */
public class DateUtilsTest {
    
    public DateUtilsTest() {
    }

    /**
     * Test of convertDateFormat method, of class DateUtils.
     */
    @Test
    public void testConvertDateFormat() {
        String date = "16/03/1983";
        String expResult = "1983-03-16";
        String result = DateUtils.convertDateFormat(date);
        assertEquals(expResult, result);
    }
    /**
     * Test of convertDateFormat method, of class DateUtils.
     */
    @Test
    public void testConvertDateFormat2() {
        String date = "16.05.1988";
        String expResult = "1988-05-16";
        String result = DateUtils.convertDateFormat(date);
        assertEquals(expResult, result);
    }
    /**
     * Test of convertDateFormat method, of class DateUtils.
     */
    @Test
    public void testConvertDateFormat3() {
        String date = "21-05-1989";
        String expResult = "1989-05-21";
        String result = DateUtils.convertDateFormat(date);
        assertEquals(expResult, result);
    }
    /**
     * Test of convertDateFormat method, of class DateUtils.
     */
    @Test
    public void testConvertDateFormat4() {
        String date = "18/04/1985";
        String expResult = "1985-04-18";
        String result = DateUtils.convertDateFormat(date);
        assertEquals(expResult, result);
    }
    
}
