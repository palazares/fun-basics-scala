package funprog.com.sort

object MergeSort {
  def main(args: Array[String]) {
    println(mergeSort((x: Int, y: Int) => x - y)(List(0,5,-6,8,34,10,-3)))
}

  def mergeSort[T](compare: (T, T) => Int)(list: List[T]) : List[T] = {
    val mergeSortInternal: List[T] => List[T] = mergeSort(compare)
    val n = list.length / 2
    if(n == 0) list
    else {
      def merge(fst: List[T], snd: List[T]) : List[T] = (fst, snd) match {
        case (Nil, s) => s
        case (f, Nil) => f
        case (f::f1, s::s1) =>
          if (compare(f, s) < 0) f :: merge (f1, snd)
          else s :: merge (fst, s1)
      }
      val (x, y) = list.splitAt(n)
      merge(mergeSortInternal(x), mergeSortInternal(y))
    }
  }
}
