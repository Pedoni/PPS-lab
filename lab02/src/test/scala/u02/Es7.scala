package u02

import org.junit.Assert.assertTrue
import org.junit.Test
import Shape.*

enum Shape:
  case Rectangle(base: Double, height: Double)
  case Circle(radius: Double)
  case Square(side: Double)

object Shape:

  def perimeter(s: Shape): Double = s match
    case Rectangle(base, height) => 2*(base+height)
    case Circle(radius) => 2*radius*Math.PI
    case Square(side) => side*4

  def area(s: Shape): Double = s match
    case Rectangle(base, height) => base*height
    case Circle(radius) => (radius*radius)*Math.PI
    case Square(side) => side*side

class Es7 {

  @Test def testShapePerimeter() =
    assertTrue(perimeter(Rectangle(4,3))==14 && perimeter(Circle(5))==10*Math.PI && perimeter(Square(6))==24)

  @Test def testShapeArea() =
    assertTrue(area(Rectangle(4,3))==12 && area(Circle(5))==25*Math.PI && area(Square(6))==36)
  
}
