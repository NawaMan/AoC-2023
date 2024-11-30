package day1;

import org.junit.Test;

import common.AocCommon;
import common.Testable;

public class Day1Part1Test implements AocCommon, Testable {
    
    final String firstDigit = "^[^0-9]*([0-9]).*$";
    final String lastDigit  = "^.*([0-9])[^0-9]*$";
    
    int numOf(String text) {
        var digits = digitsOf(text);
        return Integer.parseInt(digits);
    }
    
    String digitsOf(String text) {
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
        var lines = readAllLines(example, challengeName());
        var sum   = lines.mapToInt(this::numOf).sum();
        assertAsString("142", sum);
    }
    
    @Test
    public void testProd() {
        var lines = readAllLines(prod, challengeName());
        var sum   = lines.mapToInt(this::numOf).sum();
        assertAsString("55712", sum);
    }
    
}
