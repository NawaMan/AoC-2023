package day1;

import org.junit.Test;

import common.BaseTest;
import functionalj.list.FuncList;

public class Day1Part1Test extends BaseTest {
    
    final String firstDigit = "^[^0-9]*([0-9]).*$";
    final String lastDigit  = "^.*([0-9])[^0-9]*$";
    
    int calculate(FuncList<String> lines) {
        return lines
                .map     (this::extractDigits)
                .mapToInt(Integer::parseInt)
                .sum();
    }
    
    String extractDigits(String text) {
        var first = select(text, firstDigit);
        var last  = select(text, lastDigit);
        return first + last;
    }
    
    String select(String text, String pattern) {
        return text.replaceFirst(pattern, "$1");
    }
    
    //== Test ==
    
    @Test
    public void testExample() {
        var lines  = readAllLines();
        var result = calculate(lines);
        assertAsString("142", result);
    }
    
    @Test
    public void testProd() {
        var lines  = readAllLines();
        var result = calculate(lines);
        assertAsString("55712", result);
    }
    
}
