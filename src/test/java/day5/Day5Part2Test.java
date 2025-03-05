package day5;

import org.junit.Ignore;
import org.junit.Test;

import common.BaseTest;
import functionalj.list.FuncList;

@Ignore
public class Day5Part2Test extends BaseTest {
    
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
    
    @Ignore
    @Test
    public void testProd() {
        var lines = readAllLines();
        var result = calulate(lines);
        println(result);
        assertAsString("", result);
    }
    
}
