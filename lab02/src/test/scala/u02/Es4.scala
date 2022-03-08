package u02

import org.junit.*
import org.junit.Assert.*

class Es4 {

  val sortedVal: (Double, Double, Double) => Boolean = (x, y, z) => x<=y && y<=z

  def sortedDef(x: Double, y: Double, z: Double): Boolean = x<=y && y<=z

  val sortedValC: Double => Double => Double => Boolean = x => y => z => x<=y && y<=z

  def sortedDefC(x: Double)(y: Double)(z: Double): Boolean = x<=y && y<=z
  

  @Test def testSorted() =
    assertTrue(sortedVal(5,6,7))
    assertFalse(sortedVal(5,7,6))
    assertTrue(sortedDef(5,6,7))
    assertFalse(sortedDef(7,6,5))

  @Test def testSortedWithCurrying() =
    assertTrue(sortedValC(5)(6)(7))
    assertFalse(sortedValC(5)(7)(6))
    assertTrue(sortedDefC(5)(6)(7))
    assertFalse(sortedDefC(5)(7)(6))

}
