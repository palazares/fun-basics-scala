object CountChange {
  def main(args: Array[String]): Unit = {
    println(countChange(6, List(1, 2)))
    println(countChange(7, List(1, 2, 3)))
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    def internalCountChange(money: Int, coins: List[Int], path:List[Int]): Int = {
      if (money < 0 || coins.isEmpty) 0
      else if (money == 0) { println(path.reverse); 1 }
      else internalCountChange(money - coins.head, coins, coins.head :: path ) +
        internalCountChange(money, coins.tail, path)
    }

    if (money < 1 || coins.isEmpty) 0
    else internalCountChange(money, coins, List.empty)
  }
}
