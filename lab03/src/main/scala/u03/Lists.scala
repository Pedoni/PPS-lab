package u03

import Optionals.*
import Optionals.Option.*
import Person.*

import scala.annotation.tailrec

object Lists extends App:

  enum List[E]:
    case Cons(head: E, tail: List[E])
    case Nil()

  object List:

    def sum(l: List[Int]): Int = l match
      case Cons(h, t) => h + sum(t)
      case _ => 0

    def map[A, B](l: List[A])(mapper: A => B): List[B] = l match
      case Cons(h, t) => Cons(mapper(h), map(t)(mapper))
      case Nil() => Nil()

    def map2[A, B](l: List[A])(mapper: A => B): List[B] =
      flatMap(l)(f => Cons(mapper(f), Nil()))

    def filter[A](l: List[A])(pred: A => Boolean): List[A] = l match
      case Cons(h, t) if pred(h) => Cons(h, filter(t)(pred))
      case Cons(_, t) => filter(t)(pred)
      case Nil() => Nil()

    def filter2[A](l: List[A])(pred: A => Boolean): List[A] =
      flatMap(l)(v => v match
        case _ if pred(v) => Cons(v, Nil())
        case _ => Nil()
      )

    @tailrec
    def drop[A](l: List[A], n: Int): List[A] = l match
      case Cons(_, t) if n==1 => t
      case Cons(_, t) => drop(t, n-1)
      case _ => Nil()

    def append[A](left: List[A], right: List[A]): List[A] = left match
      case Cons(h, t) => Cons(h, append(t, right))
      case _ => right

    def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] = l match
      case Cons(h, t) => append(f(h), flatMap(t)(f))
      case _ => Nil()

    def max(l: List[Int]): Option[Int] = l match
      case Cons(h, t) if h > orElse(max(t), 0) => Some(h)
      case Cons(_, t) => max(t)
      case _ => None()

    def getCourses(l: List[Person]): List[String] = l match
      case Cons(h, t1) => h match
        case Teacher(_, t2) => Cons(t2, getCourses(t1))
        case _ => getCourses(t1)
      case _ => Nil()

    def foldLeft(l: List[Int])(init: Int)(op: (Int, Int) => Int): Int = l match
      case Cons(h, t) => op(init, op(h, foldLeft(t)(0)(op)))
      case _ => 0

    def foldRight(l: List[Int])(init: Int)(op: (Int, Int) => Int): Int = l match
      case Cons(h, t) => op(op(h, foldRight(t)(0)(op)), init)
      case _ => 0
