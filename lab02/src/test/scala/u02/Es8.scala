package u02

import org.junit.Assert.*
import org.junit.*
import Option.*

enum Option[A]:
  case Some(a: A)
  case None()

object Option:

  def isEmpty[A](opt: Option[A]): Boolean = opt match
    case None() => true
    case _ => false

  def orElse[A, B >: A](opt: Option[A], orElse: B): B = opt match
    case Some(a) => a
    case _ => orElse

  def flatMap[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt match
    case Some(a) => f(a)
    case _ => None()

  def filter[A](opt: Option[A])(f: Option[A] => Boolean): Option[A] = opt match
    case Some(a) => f(opt) match
      case true => Some(a)
      case _ => None()
    case _ => None()

  def map[A](opt: Option[A])(f: Option[A] => Boolean): Option[Boolean] = opt match
    case Some(a) => f(opt) match
      case true => Some(true)
      case _ => Some(false)
    case _ => None()

  def map2[A, B, C](opt1: Option[A])(opt2: Option[B])(f: (Option[A], Option[B]) => Option[C]): Option[C] = (opt1, opt2) match
    case (Some(a), Some(b)) => f(Some(a), Some(b))
    case _ => None()

class Es8 {

  val none: Option[Int] = None()
  val f: Option[Int] => Boolean = x => orElse(x, 0) > 2
  val g: Option[Int] => Boolean = x => orElse(x, 0) > 8
  val h: (Option[Int], Option[Int]) => Option[Boolean] = (x, y) => Option.Some(x == y)

  @Test def testIsEmpty() =
    assertTrue(isEmpty(none))
    assertFalse(isEmpty(Some(2)))

  @Test def testOrElse() =
    assertTrue(orElse(Some(1), 0) == 1)
    assertTrue(orElse(none, 0) == 0)

  @Test def testFlatMap() =
    assertTrue(flatMap(Some(1))(i => Some(i+1)) == Some(2))
    assertTrue(flatMap(Some(1))(i => flatMap(none)(j => Some(i+j))) == none)

  @Test def testFilter() =
    assertTrue(filter(Some(1))(f) == none)
    assertTrue(filter(Some(5))(g) == none)

  @Test def testMap() =
    assertTrue(map(Some(5))(f) == Option.Some(true))
    assertTrue(map(Some(5))(g) == Option.Some(false))

  @Test def testMap2() =
    assertTrue(map2(Some(5))(none)(h) == none)
    assertTrue(map2(Some(5))(Some(2))(h) == Option.Some(false))

}
