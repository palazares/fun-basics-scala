package funprog.com

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CubeCalculatorTest extends FunSuite{
  test("CubeCalculator.cube should return cube value") {

    assert(CubeCalculator.cube(3) == 27)
  }
}