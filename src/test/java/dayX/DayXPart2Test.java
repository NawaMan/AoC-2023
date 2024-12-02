package dayX;

import org.junit.Ignore;
import org.junit.Test;

import common.BaseTest;
import functionalj.list.FuncList;

@Ignore
public class DayXPart2Test extends BaseTest {
    
    int calulate(FuncList<String> lines) {
        return 0;
    }
    
    //== Test ==
    
    @Test
    public void testExample() {
        var lines = readAllLines();
        var result = calulate(lines);
        println(result);
        assertAsString("", result);
    }
    
    @Test
    public void testProd() {
        var lines = readAllLines();
        var result = calulate(lines);
        println(result);
        assertAsString("", result);
    }
    
}
