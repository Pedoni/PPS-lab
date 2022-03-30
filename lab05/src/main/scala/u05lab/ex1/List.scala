package u05lab.ex1

import u05lab.ex1.List
import scala.annotation.tailrec

enum List[A]:
  case ::(h: A, t: List[A])
  case Nil()
  def ::(h: A): List[A] = List.::(h, this)

  def head: Option[A] = this match
    case h :: t => Some(h)
    case _ => None

  def tail: Option[List[A]] = this match
    case h :: t => Some(t)
    case _ => None

  def append(list: List[A]): List[A] = this match
    case h :: t => h :: t.append(list)
    case _ => list

  def foreach(consumer: A => Unit): Unit = this match
    case h :: t => consumer(h); t.foreach(consumer)
    case _ =>

  def get(pos: Int): Option[A] = this match
    case h :: t if pos == 0 => Some(h)
    case h :: t if pos > 0 => t.get(pos - 1)
    case _ => None

  def filter(predicate: A => Boolean): List[A] = this match
    case h :: t if predicate(h) => h :: t.filter(predicate)
    case _ :: t => t.filter(predicate)
    case _ => Nil()

  def map[B](fun: A => B): List[B] = this match
    case h :: t => fun(h) :: t.map(fun)
    case _ => Nil()

  def flatMap[B](f: A => List[B]): List[B] =
    foldRight[List[B]](Nil())(f(_) append _)

  def foldLeft[B](z: B)(op: (B, A) => B): B = this match
    case h :: t => t.foldLeft(op(z, h))(op)
    case Nil() => z

  def foldRight[B](z: B)(f: (A, B) => B): B = this match
    case h :: t => f(h, t.foldRight(z)(f))
    case _ => z

  def length: Int = foldLeft(0)((l, _) => l + 1)

  def isEmpty: Boolean = this match
    case Nil() => true
    case _ => false

  def reverse(): List[A] = foldLeft[List[A]](Nil())((l, e) => e :: l)

  def zipRight: List[(A, Int)] =
    def _zipRight(counter: Int, l: List[A]): List[(A, Int)] = l match
      case ::(h, t) => (h, counter) :: _zipRight(counter + 1, t)
      case _ => Nil()
    _zipRight(0, this)

  def partition(pred: A => Boolean): (List[A], List[A]) =
    (this.filter(pred), this.filter(a => !pred(a)))

  def takeRight(k: Int): List[A] =
    if k > this.length then throw UnsupportedOperationException()
    @tailrec
    def _takeRight(c: Int, l: List[A]): List[A] = l match
      case ::(h, t) if c > k => _takeRight(c - 1, t)
      case ::(h, t) => l
      case _ => Nil()
    _takeRight(this.length, this)

  def takeLeft(k: Int): List[A] = this.reverse().takeRight(k).reverse()

  def getIndex(pred: A => Boolean, i: Int = 0): Int = if pred(this.get(i).get) then getIndex(pred, i + 1) else i

  def span(pred: A => Boolean): (List[A], List[A]) =
    val i: Int = this.getIndex(pred)
    (this.takeLeft(i), this.takeRight(this.length - i))

  def reduce(f: (A, A) => A): A = this match
    case h :: Nil() => h
    case h :: t => f(h, t.reduce(f))
    case _ => throw UnsupportedOperationException()

  def collect[B](f: PartialFunction[A, B]): List[B] = this.filter(f.isDefinedAt).map(f)

object List:

  def apply[A](elems: A*): List[A] =
    var list: List[A] = Nil()
    for e <- elems.reverse do list = e :: list
    list

  def of[A](elem: A, n: Int): List[A] =
    if n == 0 then Nil() else elem :: of(elem, n - 1)
