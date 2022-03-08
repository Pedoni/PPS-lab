package u02

import org.junit.Assert.*
import org.junit.Test

class Es6 {

  def fib(x: Int): Int = x match
    case 0 | 1 => x
    case x => fib(x-1) + fib(x-2)

  
  @Test def testFibonacci() =
    assertEquals(fib(7), 13)
  
}
