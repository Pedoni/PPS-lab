import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CircularListTest {

    private CircularList list;

    @BeforeEach
    void beforeEach(){
        list = new CircularListImpl();
    }

    private void fillList(final List<Integer> initializer) {
        initializer.forEach((i) -> this.list.add(i));
    }

    @Test
    void testListIsEmpty(){
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddOneValue(){
        this.list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    void testAddMultipleValues(){
        this.fillList(List.of(1,2,3));
        assertEquals(3, list.size());
    }

    @Test
    void testNextOnEmptyList(){
        assertEquals(this.list.next(), Optional.empty());
    }

    @Test
    void testFirstNextOnFilledList(){
        this.fillList(List.of(1,2,3));
        assertEquals(this.list.next(), Optional.of(1));
    }

    @Test
    void testLastNextOnFilledList(){
        this.fillList(List.of(1,2,3));
        this.list.next();
        this.list.next();
        assertEquals(this.list.next(), Optional.of(3));
    }

    @Test
    void testFirstAgainNextOnFilledList(){
        this.fillList(List.of(1,2,3));
        this.list.next();
        this.list.next();
        this.list.next();
        assertEquals(this.list.next(), Optional.of(1));
    }

    @Test
    void testResetList(){
        this.fillList(List.of(1,2,3));
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
        this.fillList(List.of(1,2,3));
        assertEquals(this.list.previous(), Optional.of(2));
    }

    @Test
    void testLastPreviousOnFilledList(){
        this.fillList(List.of(1,2,3));
        this.list.previous();
        assertEquals(this.list.previous(), Optional.of(1));
    }

    @Test
    void testMoveCombined(){
        this.fillList(List.of(1,2,3));
        this.list.previous();
        this.list.previous();
        this.list.next();
        this.list.next();
        this.list.next();
        assertEquals(this.list.previous(), Optional.of(3));
    }

    @Test
    void testEvenStrategy(){
        this.fillList(List.of(1,2,3));
        assertEquals(this.list.next(new StrategyFactoryImpl().createEvenStrategy()), Optional.of(2));
    }

    @Test
    void testEvenStrategyFail(){
        this.fillList(List.of(1,5,3));
        assertEquals(this.list.next(new StrategyFactoryImpl().createEvenStrategy()), Optional.empty());
    }

    @Test
    void testMultipleOfStrategy(){
        this.fillList(List.of(3,4,5,2));
        assertEquals(this.list.next(new StrategyFactoryImpl().createMultipleOfStrategy(2)), Optional.of(4));
    }

    @Test
    void testMultipleOfStrategyFail(){
        this.fillList(List.of(3,5,8));
        assertEquals(this.list.next(new StrategyFactoryImpl().createMultipleOfStrategy(7)), Optional.empty());
    }
    @Test
    void testEqualsStrategy(){
        this.fillList(List.of(3,4,2,5,2));
        assertEquals(this.list.next(new StrategyFactoryImpl().createEqualsStrategy(2)), Optional.of(2));
    }

    @Test
    void testEqualsStrategyFail(){
        this.fillList(List.of(3,5,8));
        assertEquals(this.list.next(new StrategyFactoryImpl().createEqualsStrategy(7)), Optional.empty());
    }

}
