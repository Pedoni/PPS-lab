package u02

import org.junit.*
import org.junit.Assert.*

class Es3 {

  val parityVal: Int => String = n => (n%2) match
    case 0 => "even"
    case _ => "odd"

  def parityDef(n: Int): String = (n%2) match
    case 0 => "even"
    case _ => "odd"

  val empty: String => Boolean = _ == ""

  def stringsNegate(pred: String => Boolean): String => Boolean = !pred(_)

  def genericNegate[A](pred: A => Boolean): A => Boolean = !pred(_)

  val notEmpty = genericNegate(empty)
  
  
  @Test def testEven() =
    assertTrue(parityVal(2) == "even")
    assertTrue(parityDef(2) == "even")

  @Test def testOdd() =
    assertTrue(parityVal(5) == "odd")
    assertTrue(parityDef(5) == "odd")

  @Test def testNotEmpty() =
    assertTrue(notEmpty("hello"))
    assertFalse(notEmpty(""))
  
}
