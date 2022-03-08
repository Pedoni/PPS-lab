package u02

import org.junit.Assert.*
import org.junit.Test

class Es6 {

  def fib(x: Int): Int = x match
    case 0 | 1 => x
    case x => fib(x-1) + fib(x-2)

  def fibTail(x: Int): Int =
    @annotation.tailrec
    def tail(x: Int, prev: Int, curr: Int): Int = (x<=0) match
      case true => curr
      case _ => tail(x - 1, prev + curr, prev)
    tail(x, prev = 1, curr = 0)

  
  @Test def testFibonacci() =
    assertEquals(fib(7), 13)

  @Test def testFibonacciTail() =
    assertEquals(fib(7), 13)
  
}
