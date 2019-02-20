package funprog.com

object Fibonacci {
  def main(args: Array[String]) {
    for (i <- 1 to 10) println(i + " -> " + fibonacci(i))
    println("++++++++++++++++++++")
    for (i <- 1 to 10) println(i + " -> " + fibonacciCycle(i))
  }

  def fibonacci(n: Int) : Int = fibonacciInternal(2, n, 1, 0)

  def fibonacciInternal(i: Int, n: Int, prev: Int, prevPrev: Int) : Int = n match {
    case 0 => 0
    case 1 => 1
    case x => if(i == x) prev + prevPrev else fibonacciInternal(i + 1, n, prev + prevPrev, prev)
  }

  def fibonacciCycle(n: Int) : Int = n match {
    case 0 => 0
    case 1 => 1
    case x =>
      var prev = 1
      var prevPrev = 0
      for(_ <- 2 to x) {
        val swap = prev
        prev = prev + prevPrev
        prevPrev = swap
      }
      prev
  }
}
