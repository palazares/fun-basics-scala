package funprog.com.huffman

case class Fork (left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree {
}
