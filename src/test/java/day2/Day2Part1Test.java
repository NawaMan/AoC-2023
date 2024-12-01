package day2;

import static day2.Day2Part1Test.Color.blue;
import static day2.Day2Part1Test.Color.green;
import static day2.Day2Part1Test.Color.red;
import static functionalj.list.FuncList.AllOf;
import static functionalj.tuple.Tuple.tuple2;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

import common.BaseTest;
import functionalj.list.FuncList;
import functionalj.map.FuncMap;
import functionalj.tuple.Tuple;
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
    
    private int calculateSum(FuncList<String> lines) {
        System.out.println("Inputs: ");
        
        for (var line : lines) {
            var parts = line.split(":");
            var gameStr = parts[0].trim();
            var mapStr  = parts[1].trim();
            
            var coinPairs = valueToPair(mapStr);
            var pairs     = processPair(coinPairs);
            
            var valid = validateCount(pairs, maxByColors);
            
            if (valid) {
                System.out.println("    " + gameStr + ": " + valid + " <- " +  pairs + " -- " + mapStr + " -- " + coinPairs);
            }
        }
        
        
        
//        lines.map(indent()).forEach(System.out::println);
//        
//        var map = lines
//                .toMap   (line   -> line.split(":")[0], 
//                        line   -> line.split(":")[1].trim())
//              .mapValue(value  -> valueToPair(value))
//              .mapValue(pairs  -> processPair(pairs));
//        map.entries().forEach(entry -> {
//            println(entry.getKey() + ": " + entry.getValue() + " = " + validateCount(entry.getValue(), maxByColors));
//        });
//        var sum = map
//        .mapValue(value -> validateCount(value, maxByColors))
//        .filterByValue(Boolean.TRUE::equals)
//        .keys()
//        .map(key -> {
//            println(key + ": " + map.get(key));
//            return key;
//        })
//        .map(key -> key.replaceAll("[^0-9]*", ""))
//        .mapToInt(Integer::parseInt)
//        .peek(this::println)
//        .sum();
        
        int sum = 0;
        return sum;
    }
    
    private FuncList<Tuple2<String, Integer>> valueToPair(String value) {
        return AllOf(value.split("[,;]"))
                .map(each -> each.trim().split(" "))
                .map(each -> tuple2(each[1], Integer.parseInt(each[0])));
    }
    
    private FuncMap<Color, Integer> processPair(FuncList<Tuple2<String, Integer>> pairs) {
        return pairs.toMap(pair -> Color.valueOf(pair.first()), pair -> pair.second(), (a, b) -> a + b);
    }
    
    private boolean validateCount(FuncMap<Color, Integer> colors, FuncMap<Color, Integer> maxByColors) {
        return AllOf(Color.values()).allMatch(color -> colors.getOrDefault(color, 0) <= maxByColors.getOrDefault(color, 0));
    }
    
    //== Test ==
    
    @Test
    public void testExample() {
        var lines = readAllLines();
        var sum = calculateSum(lines);
        
        println();
        println(sum);
        
        assertAsString("8", sum);
    }
    
    @Test
    public void testProd() {
        var lines = readAllLines();
        var sum = calculateSum(lines);
        
        println();
        println(sum);
        
//        assertAsString("", ...);
    }
    
}
