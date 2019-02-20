package funprog.com.objsets

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    val newAcc = if (p(elem)) acc.incl(elem) else acc
    left.filterAcc(p, acc).union(right.filterAcc(p, acc)).union(newAcc)
  }

  def filter(p: Tweet => Boolean): TweetSet =
    if (p(elem)) left.filter(p).union(right.filter(p)).incl(elem)
    else left.filter(p).union(right.filter(p))

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }

  def union(that: TweetSet): TweetSet =
    if(that.isEmpty) this
    else left.union(right.union(that.incl(elem)))

  def isEmpty: Boolean = false

  def mostRetweeted: Tweet = mostInternal((a,b) => if (a.retweets > b.retweets) a else b)

  private def mostInternal(compare: (Tweet, Tweet) => Tweet): Tweet = {
    if (right.isEmpty && left.isEmpty) elem
    else if (right.isEmpty) compare(elem, left.mostRetweeted)
    else if (left.isEmpty) compare(elem, right.mostRetweeted)
    else compare(elem, compare(left.mostRetweeted, right.mostRetweeted))
  }

  def descendingByRetweet: TweetList = {
    val toRemove = mostRetweeted
    new Cons(toRemove, remove(toRemove).descendingByRetweet)
  }
}
