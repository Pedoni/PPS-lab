import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CircularListTest {

    private CircularList list;

    @BeforeEach
    void beforeEach(){
        list = new CircularListImpl();
    }

    @Test
    void testListIsEmpty(){
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddOneValue(){
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    void testAddMultipleValues(){
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    void testNextOnEmptyList(){
        assertEquals(this.list.next(), Optional.empty());
    }

    @Test
    void testFirstNextOnFilledList(){
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(this.list.next(), Optional.of(1));
    }

    @Test
    void testLastNextOnFilledList(){
        list.add(1);
        list.add(2);
        list.add(3);
        this.list.next();
        this.list.next();
        assertEquals(this.list.next(), Optional.of(3));
    }

    @Test
    void testFirstAgainNextOnFilledList(){
        list.add(1);
        list.add(2);
        list.add(3);
        this.list.next();
        this.list.next();
        this.list.next();
        assertEquals(this.list.next(), Optional.of(1));
    }

    @Test
    void testResetList(){
        list.add(1);
        list.add(2);
        list.add(3);
        this.list.next();
        this.list.next();
        list.reset();
        assertEquals(this.list.next(), Optional.of(2));
    }

    @Test
    void testPreviousOnEmptyList(){
        assertEquals(this.list.previous(), Optional.empty());
    }

    @Test
    void testFirstPreviousOnFilledList(){
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(this.list.previous(), Optional.of(2));
    }

    @Test
    void testLastPreviousOnFilledList(){
        list.add(1);
        list.add(2);
        list.add(3);
        this.list.previous();
        assertEquals(this.list.previous(), Optional.of(1));
    }

    @Test
    void testMoveCombined(){
        list.add(1);
        list.add(2);
        list.add(3);
        this.list.previous();
        this.list.previous();
        this.list.next();
        this.list.next();
        this.list.next();
        assertEquals(this.list.previous(), Optional.of(3));
    }

    @Test
    void testEvenStrategy(){
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(this.list.next(new StrategyFactoryImpl().createEvenStrategy()), Optional.of(2));
    }

    @Test
    void testEvenStrategyFail(){
        list.add(1);
        list.add(5);
        list.add(3);
        assertEquals(this.list.next(new StrategyFactoryImpl().createEvenStrategy()), Optional.empty());
    }

    @Test
    void testMultipleOfStrategy(){
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(2);
        assertEquals(this.list.next(new StrategyFactoryImpl().createMultipleOfStrategy(2)), Optional.of(4));
    }

    @Test
    void testMultipleOfStrategyFail(){
        list.add(3);
        list.add(5);
        list.add(8);
        assertEquals(this.list.next(new StrategyFactoryImpl().createMultipleOfStrategy(7)), Optional.empty());
    }
    @Test
    void testEqualsStrategy(){
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(5);
        list.add(2);
        assertEquals(this.list.next(new StrategyFactoryImpl().createEqualsStrategy(2)), Optional.of(2));
    }

    @Test
    void testEqualsStrategyFail(){
        list.add(3);
        list.add(5);
        list.add(8);
        assertEquals(this.list.next(new StrategyFactoryImpl().createEqualsStrategy(7)), Optional.empty());
    }

}
