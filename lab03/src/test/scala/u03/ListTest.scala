package u03

import org.junit.*
import org.junit.Assert.*
import Lists.*
import u03.Lists.List.*
import Optionals.*
import Optionals.Option.*

class ListTest:
  import List.*

  val l: List[Int] = Cons(10, Cons(20, Cons(30, Nil())))

  @Test def testSum() =
    assertEquals(0, sum(Nil()))
    assertEquals(60, sum(l))

  @Test def testMap() =
    assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), map(l)(_ + 1))
    assertEquals(Cons("10", Cons("20", Cons("30", Nil()))), map(l)(_ + ""))

  @Test def testMap2() =
    assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), map2(l)(_ + 1))
    assertEquals(Cons("10", Cons("20", Cons("30", Nil()))), map2(l)(_ + ""))

  @Test def testFilter() =
    assertEquals(Cons(20, Cons(30, Nil())), filter(l)(_ >= 20))
    assertEquals(Cons(10, Cons(30, Nil())), filter(l)(_ != 20))

  @Test def testFilter2() =
    assertEquals(Cons(20, Cons(30, Nil())), filter2(l)(_ >= 20))
    assertEquals(Cons(10, Cons(30, Nil())), filter2(l)(_ != 20))

  @Test def testDrop() =
    assertEquals(drop(l, 1), Cons(20, Cons(30, Nil())))
    assertEquals(drop(l, 2), Cons(30, Nil()))
    assertEquals(drop(l, 5), Nil())

  @Test def testAppend() =
    val tail = Cons(40, Nil())
    assertEquals(append(l, append(tail, tail)), Cons(10, Cons(20, Cons(30, Cons(40, Cons(40, Nil ()))))))

  @Test def testFlatMap() =
    assertEquals(flatMap(l)(v => Cons(v + 1, Nil())), Cons(11, Cons(21, Cons(31, Nil()))))
    assertEquals(flatMap(l)(v => Cons(v + 1, Cons(v + 2, Nil()))), Cons(11, Cons(12, Cons(21, Cons(22, Cons(31, Cons(32, Nil())))))))

  @Test def testMax() =
    assertEquals(max(Cons(10, Cons(25, Cons(20, Nil())))), Some(25))
    assertEquals(max(Cons(10, Cons(25, Cons(20, Cons(30, Nil()))))), Some(30))
    assertEquals(max(Nil()), None())

  @Test def testGetCourses() =
    val pl: List[Person] = Cons(Person.Teacher("Mirko Viroli", "OOP"), Cons(Person.Student("Emanuele Lamagna", 1998), Cons(Person.Teacher("Vittorio Ghini", "SISOP"), Nil())))
    assertEquals(Cons("OOP", Cons("SISOP", Nil())), getCourses(pl))

  @Test def testFoldLeft() =
    assertEquals(60, foldLeft(l)(0)(_ + _))
    assertEquals(61, foldLeft(l)(1)(_ + _))
    assertEquals(-60, foldLeft(l)(0)(_ - _))

  @Test def testFoldRight() =
    assertEquals(60, foldRight(l)(0)(_ + _))
    assertEquals(61, foldRight(l)(1)(_ + _))
    assertEquals(20, foldRight(l)(0)(_ - _))
