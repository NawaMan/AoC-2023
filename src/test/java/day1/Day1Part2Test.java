package day1;

import static functionalj.function.Func.itself;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import common.AocCommon;
import common.Testable;
import functionalj.list.FuncList;
import functionalj.map.FuncMap;
import nullablej.nullable.Nullable;

public class Day1Part2Test implements AocCommon, Testable {
    
    final String challenge = this.getClass().getSimpleName().replaceFirst("Test$", "");
    
    final FuncList<String> numbers = FuncList.of(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    );
    
    private final AtomicInteger   numberCounter = new AtomicInteger(1);
    final FuncMap<String, String> numberToInts
            = numbers
            .toMap(itself(), __ -> numberCounter.getAndIncrement())
            .mapValue(String::valueOf)
            .toImmutableMap();
    
    final String numberText = numbers.join("|");
    
    final String firstDigit = "^.*?([0-9]|" + numberText + ").*$";
    final String lastDigit  = "^.*([0-9]|" + numberText + ").*?$";
    
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
        var dig = text.replaceFirst(pattern, "$1");
        var num = Nullable.of(numberToInts.get(dig)).orElse(dig);
        return num;
    }
    
    @Test
    public void testDemo() {
        var lines = readAllLines(test, challenge);
        var sum   = lines.mapToInt(this::numOf).sum();
        assertAsString("281", sum);
    }
    
    @Test
    public void testProd() {
        var lines = readAllLines(prod, challenge);
        var sum   = lines.mapToInt(this::numOf).sum();
        assertAsString("55413", sum);
    }
    
}
