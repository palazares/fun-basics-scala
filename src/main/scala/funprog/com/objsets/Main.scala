package funprog.com.objsets

object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  val allTweets: TweetSet = TweetReader.allTweets

  lazy val googleTweets: TweetSet = allTweets.filter(tw => google.exists(key => tw.text.contains(key)))
  lazy val appleTweets: TweetSet = allTweets.filter(tw => apple.exists(key => tw.text.contains(key)))

  /**
    * A list of all tweets mentioning a keyword from either apple or google,
    * sorted by the number of retweets.
    */
  lazy val trending: TweetList = googleTweets.union(appleTweets).descendingByRetweet
}

object Main extends App {
  // Print the trending tweets
  GoogleVsApple.trending foreach println
}
