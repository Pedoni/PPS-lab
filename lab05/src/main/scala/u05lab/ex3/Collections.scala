package u05lab.ex3

import java.util.concurrent.TimeUnit
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.FiniteDuration

object PerformanceUtils:
  case class MeasurementResults[T](result: T, duration: FiniteDuration) extends Ordered[MeasurementResults[_]]:
    override def compare(that: MeasurementResults[_]): Int = duration.toNanos.compareTo(that.duration.toNanos)

  def measure[T](msg: String)(expr: => T): MeasurementResults[T] =
    val startTime = System.nanoTime()
    val res = expr
    val duration = FiniteDuration(System.nanoTime() - startTime, TimeUnit.NANOSECONDS)
    if (msg.nonEmpty) println(duration.toNanos + " nanos -- " + msg/*+ duration.toMillis + "ms"*/)
    MeasurementResults(res, duration)

  def measure[T](expr: => T): MeasurementResults[T] = measure("")(expr)

@main def checkPerformance: Unit =

  import scala.collection.*

  val normalList = List.range(1, 10000000)
  println("1 done")
  val bufferList = ListBuffer.range(1, 10000000)
  println("2 done")
  val lazyList: LazyList[Int] = LazyList.range(1, 10000000)
  println("3 done")
  val vector: Vector[Int] = Vector.range(1, 10000000)
  println("4 done")
  val arrayBuffer: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer.range(1, 10000000)
  println("5 done")
  val array: Array[Int] = Array.range(1, 10000000)
  println("6 done")
  val set: Set[Int] = Set.range(1, 10000000)
  println("7 done")
  val mutableSet: mutable.Set[Int] = mutable.Set.range(1, 10000000)
  println("8 done")
  val map: Map[Int, Int] = Map(1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4, 5 -> 5)
  println("9 done")
  val mutableMap: mutable.Map[Int, Int] = mutable.Map(1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4, 5 -> 5)
  println("10 done")

  import PerformanceUtils.*

  println("1° MEASURE: GET THE LAST ELEMENT")

  measure("normal list last")(normalList.last)
  measure("buffer list last")(bufferList.last)
/*
  println("\n2° MEASURE: ADD AN ELEMENT")
  measure("buffer list add")(normalList :+ 0)
  measure("buffer list add")(bufferList += 0)
*/

  //assert(measure("lst last")(lst.last) > measure("buffer list last")(bufferList.last))
  //assert(measure("lst last")(normalList.) > measure("vec last")(vec.last))
