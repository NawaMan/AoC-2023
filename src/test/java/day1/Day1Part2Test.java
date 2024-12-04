package day1;

import static functionalj.function.Func.itself;
import static java.lang.Integer.parseInt;
import static nullablej.nullable.Nullable.nullable;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import common.BaseTest;
import functionalj.list.FuncList;
import functionalj.map.FuncMap;

public class Day1Part2Test extends BaseTest {
    
    final FuncList<String> numbers = FuncList.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    
    private final AtomicInteger   numberCounter = new AtomicInteger(1);
    final FuncMap<String, String> numberToInts
            = numbers
            .toMap(itself(), __ -> Integer.toString(numberCounter.getAndIncrement()))
            .toImmutableMap();
    
    final String numberText = numbers.join("|");
    
    final String firstDigit = "^.*?([0-9]|" + numberText + ").*$";
    final String lastDigit  = "^.*([0-9]|" + numberText + ").*?$";
    
    int calculate(FuncList<String> lines) {
        return lines
                .mapToInt(this::extractDigits)
                .sum();
    }
    
    int extractDigits(String text) {
        var first = select(text, firstDigit);
        var last  = select(text, lastDigit);
        return parseInt(first + last);
    }
    
    String select(String text, String pattern) {
        var digit = text.replaceFirst(pattern, "$1");
        return nullable(numberToInts.get(digit))
                .orElse(digit);
    }
    
    //== Test ==
    
    @Test
    public void testExample() {
        var lines = readAllLines();
        var sum   = calculate(lines);
        assertAsString("281", sum);
    }
    
    @Test
    public void testProd() {
        var lines = readAllLines();
        var sum   = calculate(lines);
        assertAsString("55413", sum);
    }
    
}
