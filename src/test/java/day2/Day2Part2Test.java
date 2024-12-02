package day2;

import static functionalj.lens.Access.theList;
import static functionalj.lens.Access.theString;
import static functionalj.list.FuncList.AllOf;
import static functionalj.tuple.Tuple.tuple2;
import static java.lang.Integer.parseInt;

import org.junit.Test;

import common.BaseTest;
import functionalj.list.FuncList;
import functionalj.tuple.Tuple2;

public class Day2Part2Test extends BaseTest {
    
    static enum Color {
        red,
        green,
        blue;
    }
    
    int calculate(FuncList<String> lines) {
        return lines
              .map     (theString.split(" *: *"))
              .toMap   (theList.at(0).asString(), theList.at(1).asString())
              .mapValue(this::valueToPairs)
              .mapValue(this::calculatePower)
              .values  ()
              .mapToInt(Integer::intValue)
              .sum();
    }
    
    FuncList<Tuple2<String, Integer>> valueToPairs(String value) {
        return AllOf(value.split(" *[,;] *"))
                .map(each -> each.trim().split(" "))
                .map(each -> tuple2(each[1], parseInt(each[0])));
    }
    
    private Integer calculatePower(FuncList<Tuple2<String, Integer>> pairs) {
        return pairs.toMap(
                    pair -> pair.first(), 
                    pair -> pair.second(), 
                    Math::max).values()
                .reduce((a, b) -> a*b).get();
    }
    
    //== Test ==
    
    @Test
    public void testExample() {
        var lines = readAllLines();
        lines.forEach(this::println);
        assertAsString("2286", calculate(lines));
    }
    
    @Test
    public void testProd() {
        var lines = readAllLines();
        lines.forEach(this::println);
        assertAsString("87984", calculate(lines));
    }
    
}
