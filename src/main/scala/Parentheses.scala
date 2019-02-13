object Parentheses {
  def main(args: Array[String]): Unit = {
    println(isBalancedParentheses("(sdf)".toList))
    println(isBalancedParentheses("(sdf)(asdf)".toList))
    println(isBalancedParentheses("(s(asdf)sadf)".toList))
    println(isBalancedParentheses("(sdf))(".toList))
    println(isBalancedParentheses(":-)".toList))
    println(isBalancedParentheses("(if (zero? x) max (/ 1 x))".toList))
    println(isBalancedParentheses("(09-".toList))
    println(isBalancedParentheses("sadf(".toList))
    println(isBalancedParentheses("sadf)(---".toList))
  }

  def isBalancedParentheses(str: List[Char]) = {
    def charValue(c: Char) = if ( c == '(') 1 else if (c == ')') -1 else 0
    def internalBalance(parenthesesNum: Int, s: List[Char]): Boolean =
      if ( parenthesesNum < 0 || parenthesesNum != 0 && s.length == 0) false
      else if (parenthesesNum == 0 && s.length == 0) true
      else internalBalance(parenthesesNum + charValue(s.head), s.tail)
    internalBalance(0, str)
  }
}
