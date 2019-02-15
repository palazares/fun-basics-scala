package funprog.com

object Rationals {

  def main(args: Array[String]) {
    val x = new Rational(1, 2)
    print(x)
  }

  class Rational(x: Int, y: Int){
    require(y > 0, "denominator must be positive")

    def this(x: Int) = this(x , 1)

    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd (b, a % b)
    private val g = gcd (x, y)

    val numer: Int = x / g
    val denom: Int = y / g

    def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
    def neg: Rational = new Rational(-numer, denom)
    def sub(that: Rational): Rational = add(that.neg)
    def mult(that: Rational) = new Rational(numer * that.numer, denom * that.denom)
    def div(that: Rational) = new Rational(numer * that.denom, denom * that.numer)
    def less(that: Rational): Boolean = numer * that.denom < that.numer * denom
    def max(that: Rational): Rational = if(this.less(that)) that else this

    override def toString: String = numer + "/" + denom
  }

}
