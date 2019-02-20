package funprog.com.objsets

class Empty extends TweetSet {

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = this

  def contains(tweet: Tweet): Boolean = false

  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)

  def remove(tweet: Tweet): TweetSet = this

  def foreach(f: Tweet => Unit): Unit = ()

  def union(that: TweetSet): TweetSet = that

  def filter(p: Tweet => Boolean): TweetSet = this

  def isEmpty: Boolean = true

  def descendingByRetweet: TweetList = Nil

  def mostRetweeted: Tweet = ???
}
