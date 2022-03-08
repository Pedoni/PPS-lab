package u02

import org.junit.*
import org.junit.Assert.*

class Es4 {

  val sortedVal: (Double, Double, Double) => Boolean = (x, y, z) => x<=y && y<=z

  def sortedDef(x: Double, y: Double, z: Double): Boolean = x<=y && y<=z

  val sortedValC: Double => Double => Double => Boolean = x => y => z => x<=y && y<=z

  def sortedDefC(x: Double)(y: Double)(z: Double): Boolean = x<=y && y<=z
  

  @Test def testSorted() =
    assertTrue(sortedVal(5,6,7) && !sortedVal(5,7,6) && sortedDef(5,6,7) && !sortedDef(7,6,5))

  @Test def testSortedWithCurrying() =
    assertTrue(sortedValC(5)(6)(7) && !sortedValC(5)(7)(6) && sortedDefC(5)(6)(7) && !sortedDefC(5)(7)(6))

}
