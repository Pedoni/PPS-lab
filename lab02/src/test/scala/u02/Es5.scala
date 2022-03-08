package u02

import org.junit.*
import org.junit.Assert.*

class Es5 {

  def compose[A, B, C](f: B => C, g: A => B): A => C = i => f(g(i))


  @Test def testComposeFunction() =
    val f: Int => String = x => x.toString
    val g: Boolean => Int = x => if (x) 1 else 0
    val fg: Boolean => String = compose(f, g)
    assertTrue(fg(true) == "1")
  
}
