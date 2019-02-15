package funprog.com

object Pascal {
  def main(args: Array[String]): Unit = {
    printPascal(10)
  }

  def pascal (column: Int, row: Int): Int = {
    require(column >= 0)
    require(row >= 0)
    if (column == 0 || row == 0 || column == row) 1 else pascal(column - 1, row - 1) + pascal (column, row - 1)
  }

  def printPascal(n: Int): Unit = for (i <- 0 to n) { for (j <- 0 to i) print(pascal(j, i).toString + ' '); println() }
}
