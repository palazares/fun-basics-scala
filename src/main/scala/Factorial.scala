object Factorial {
  def main(args: Array[String]) {
    println(factorial(2))
    println(factorial(3))
    println(factorial(4))
    println(factorial(5))
    println(factorial(6))
    println(factorial(7))
  }

  def factorial(n: Double) : Double = {
    def loop(acc: Double, n: Double): Double =
      if (n == 0) acc else loop(acc * n, n - 1)

    loop(1, n)
  }
}
