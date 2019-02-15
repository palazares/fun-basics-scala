package funprog.com.sets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SetTest extends FunSuite {
  val negative: Sets.Set = x => x < 0
  val positive: Sets.Set = x => x > 0
  val zero: Sets.Set = x => x == 0
  val hundred: Sets.Set = x => x > 0 && x < 101
  val odds: Sets.Set = x => math.abs(x % 2) == 1
  val evens: Sets.Set = x => x % 2 == 0


  test("Set.contains") {
    assert(Sets.contains(negative, -1))
    assert(!Sets.contains(negative, 1))
    assert(!Sets.contains(negative, 0))

    assert(!Sets.contains(positive, -1))
    assert(!Sets.contains(positive, 0))
    assert(Sets.contains(positive, 1))

    assert(Sets.contains(zero, 0))
    assert(!Sets.contains(zero, -1))
    assert(!Sets.contains(zero, 1))

    assert(!Sets.contains(hundred, 0))
    assert(!Sets.contains(hundred, -1))
    assert(!Sets.contains(hundred, -50))
    assert(Sets.contains(hundred, 1))
    assert(Sets.contains(hundred, 50))
    assert(Sets.contains(hundred, 100))

    assert(Sets.contains(odds, 1))
    assert(Sets.contains(odds, 17))
    assert(Sets.contains(odds, 101))
    assert(Sets.contains(odds, -1))
    assert(Sets.contains(odds, -17))
    assert(Sets.contains(odds, -101))
    assert(!Sets.contains(odds, 0))
    assert(!Sets.contains(odds, 16))
    assert(!Sets.contains(odds, 100))
    assert(!Sets.contains(odds, -16))
    assert(!Sets.contains(odds, -100))

    assert(!Sets.contains(evens, 1))
    assert(!Sets.contains(evens, 17))
    assert(!Sets.contains(evens, 101))
    assert(!Sets.contains(evens, -1))
    assert(!Sets.contains(evens, -17))
    assert(!Sets.contains(evens, -101))
    assert(Sets.contains(evens, 0))
    assert(Sets.contains(evens, 16))
    assert(Sets.contains(evens, 100))
    assert(Sets.contains(evens, -16))
    assert(Sets.contains(evens, -100))
  }
}
