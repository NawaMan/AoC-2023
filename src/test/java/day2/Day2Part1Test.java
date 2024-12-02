package day2;

import static day2.Day2Part1Test.Color.blue;
import static day2.Day2Part1Test.Color.green;
import static day2.Day2Part1Test.Color.red;
import static functionalj.lens.Access.theList;
import static functionalj.lens.Access.theString;
import static functionalj.list.FuncList.AllOf;
import static functionalj.tuple.Tuple.tuple2;
import static java.lang.Boolean.TRUE;
import static java.lang.Integer.parseInt;

import org.junit.Test;

import common.BaseTest;
import functionalj.list.FuncList;
import functionalj.map.FuncMap;
import functionalj.tuple.Tuple2;

public class Day2Part1Test extends BaseTest {
    
    static enum Color {
        red,
        green,
        blue;
    }
    
    FuncMap<Color, Integer> maxByColors = FuncMap.of(
                red,   12,
                green, 13,
                blue,  14
            );
    
    int calculateSum(FuncList<String> lines) {
        var sum = lines
              .map          (theString.split(" *: *"))
              .toMap        (theList.at(0).asString(), 
                             theList.at(1).asString())
              .mapValue     (this::valueToPairs)
              .mapValue     (pairs -> pairs.allMatch(this::validatePair))
              .filterByValue(TRUE::equals)
              .keys         ()
              .map          (theString.replaceAll("Game ", ""))
              .mapToInt     (Integer::parseInt)
              .sum();
        
        return sum;
    }
    
    FuncList<Tuple2<String, Integer>> valueToPairs(String value) {
        return AllOf(value.split(" *[,;] *"))
                .map(each -> each.trim().split(" "))
                .map(each -> tuple2(each[1], parseInt(each[0])));
    }
    
    boolean validatePair(Tuple2<String, Integer> pair) {
        var color = Color.valueOf(pair.first());
        var count = pair.second();
        var max   = maxByColors.getOrDefault(color, 0);
        return count <= max;
    }
    
    //== Test ==
    
    @Test
    public void testExample() {
        var lines = readAllLines();
        var sum = calculateSum(lines);
        assertAsString("8", sum);
    }
    
    @Test
    public void testProd() {
        var lines = readAllLines();
        var sum = calculateSum(lines);
        assertAsString("2486", sum);
    }
    
}
