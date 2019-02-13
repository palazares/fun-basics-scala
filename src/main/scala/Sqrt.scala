object Sqrt {
  def main(args: Array[String]) {
    println(sqrt(2))
    println(sqrt(3))
    println(sqrt(4))
    println(sqrt(5))
    println(sqrt(6))
    println(sqrt(9))
    println(sqrt(16))
    println(sqrt(25))
  }

  def sqrt(x: Double): Double = {
    def abs(d: Double) : Double =
      if(d > 0) d else -d

    def isGoodEnough(guess: Double): Boolean =
      abs(guess * guess - x) / x < 0.0001

    def improve(guess: Double): Double =
      (guess + x / guess) / 2

    def sqrtIter(guess: Double): Double =
      if(isGoodEnough(guess)) guess
      else  sqrtIter(improve(guess))

    sqrtIter(1)
  }
}