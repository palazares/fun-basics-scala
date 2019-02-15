package funprog.com.objsets

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
}
