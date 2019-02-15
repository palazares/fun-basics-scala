package funprog.com

object Product {
  def main(args: Array[String]) {
    println(product(x => x)(2, 5))
    println(factorial(5))
    println(series((a, b) => a + b, 0)(x => x * x)(2, 5))
    println(series((a, b) => a * b, 1)(x => x * x)(2, 5))
    println(series((a, b) => a * b, 1)(x => x)(2, 5))
  }

  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1 else f(a) * product(f)(a + 1, b)

  def factorial(n: Int): Int =
    product(x => x)(2, n)

  def series(agr: (Int, Int) => Int, seed: Int)(f: Int => Int)(a: Int, b:Int): Int =
    if (a > b) seed else agr(f(a), series(agr, seed)(f)(a + 1, b))
}
