package day3;

import static functionalj.function.Func.itself;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.junit.Test;

import common.BaseTest;
import functionalj.list.FuncList;


public class Day3Part2Test extends BaseTest {
    
    record Position(int x, int y) {};
    
    static class Number {
        final Position position;
        final int      number;
        final int      length;
        
        public Number(Position position, int number, int length) {
            this.position = position;
            this.number   = number;
            this.length   = length;
        }
        int number() {
            return number;
        }
        public boolean isAdjacent(Position symbolPosition) {
            int top    = position.y;
            int bottom = position.y;
            var topTooFar    = top    < (symbolPosition.y - 1);
            var bottomTooFar = bottom > (symbolPosition.y + 1);
            if (topTooFar || bottomTooFar) {
                return false;
            }
            
            int tail = position.x + length - 1;
            int head = position.x;
            var tailTooFar = tail < (symbolPosition.x - 1);
            var headTooFar = head > (symbolPosition.x + 1);
            if (tailTooFar || headTooFar) {
                return false;
            }
            return true;
        }
        @Override
        public String toString() {
            return "Number [position=" + position + ", number=" + number + ", length=" + length + "]";
        }
    }
    
    
    int calulate(FuncList<String> lines) {
        var symbols = findSymbols(lines);
        var numbers = findNumbers(lines);
        
        symbols.forEach(this::println);
        return symbols
            .mapToInt(symbol -> {
                var nums = numbers.filter(number -> number.isAdjacent(symbol)).cache();
                return (nums.size() != 2)
                        ? 0
                        : nums.mapToInt(Number::number).reduce((a, b) -> a*b).getAsInt();
            })
            .sum();
    }
    
    FuncList<Position> findSymbols(FuncList<String> lines) {
        var symbols = lines.mapWithIndex((index, line) -> {
            var symbolY = index;
            
            var pattern = Pattern.compile("[^\\.0-9]");
            var matcher = pattern.matcher(line);
            var current = 0;
            var symbolPositions = new ArrayList<Position>();
            while (matcher.find(current)) {
                var symbolX = matcher.start();
                symbolPositions.add(new Position(symbolX, symbolY));
                
                current = matcher.end();
            }
            return FuncList.from(symbolPositions);
        }).flatMap(itself());
        return symbols;
    }
    
    FuncList<Number> findNumbers(FuncList<String> lines) {
        var numbers = lines.mapWithIndex((index, line) -> {
            var numberY = index;
            
            var pattern = Pattern.compile("[0-9]+");
            var matcher = pattern.matcher(line);
            var current = 0;
            var numberPositions = new ArrayList<Number>();
            while (matcher.find(current)) {
                var numberX = matcher.start();
                var number  = matcher.group();
                numberPositions.add(new Number(new Position(numberX, numberY), Integer.parseInt(number), number.length()));
                
                current = matcher.end();
            }
            return FuncList.from(numberPositions);
        }).flatMap(itself());
        return numbers;
    }
    
    //== Test ==
    
    @Test
    public void testExample() {
        var lines = readAllLines();
        var result = calulate(lines);
        println(result);
        assertAsString("467835", result);
    }
    
    @Test
    public void testProd() {
        var lines = readAllLines();
        var result = calulate(lines);
        println(result);
        assertAsString("87287096", result);
    }
    
}
