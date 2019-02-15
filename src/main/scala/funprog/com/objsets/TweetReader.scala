package funprog.com.objsets

import spray.json._
import DefaultJsonProtocol._

object TweetReader {
  object ParseTweets {

    object ApiProtocol extends DefaultJsonProtocol {
      implicit def listReader[T : JsonReader]: JsonReader[List[T]] = {
        case JsArray(elements) => elements.map(_.convertTo[T]).toList
        case x => deserializationError("Expected List as JsArray, but got " + x)
      }
    }

    def getTweets(user: String, json: String): List[Tweet] =
      for (map <- json.parseJson.convertTo[List[Map[String, JsValue]]]) yield {
        val text = map("text")
        val retweets = map("retweet_count")
        new Tweet(user, text.toString, retweets.toString.toDouble.toInt)
      }

    def getTweetData(user: String, json: String): List[Tweet] = {
      // is list
      val l = json.parseJson.convertTo[List[Map[String, JsValue]]]
      for (map <- l) yield {
        val text = map("text")
        val retweets = map("retweets")
        new Tweet(user, text.toString, retweets.toString.toDouble.toInt)
      }
    }
  }

  def toTweetSet(l: List[Tweet]): TweetSet = {
    l.foldLeft(new Empty: TweetSet)(_.incl(_))
  }

  def unparseToData(tws: List[Tweet]): String = {
    val buf = new StringBuffer
    for (tw <- tws) {
      val json = "{ \"user\": \"" + tw.user + "\", \"text\": \"" +
                                    tw.text.replaceAll(""""""", "\\\\\\\"") + "\", \"retweets\": " +
                                    tw.retweets + ".0 }"
      buf.append(json + ",\n")
    }
    buf.toString
  }

  val sites = List("gizmodo", "TechCrunch", "engadget", "amazondeals", "CNET", "gadgetlab", "mashable")
  
  private val gizmodoTweets = TweetReader.ParseTweets.getTweetData("gizmodo", TweetData.gizmodo)
  private val techCrunchTweets = TweetReader.ParseTweets.getTweetData("TechCrunch", TweetData.TechCrunch)
  private val engadgetTweets = TweetReader.ParseTweets.getTweetData("engadget", TweetData.engadget)
  private val amazondealsTweets = TweetReader.ParseTweets.getTweetData("amazondeals", TweetData.amazondeals)
  private val cnetTweets = TweetReader.ParseTweets.getTweetData("CNET", TweetData.CNET)
  private val gadgetlabTweets = TweetReader.ParseTweets.getTweetData("gadgetlab", TweetData.gadgetlab)
  private val mashableTweets = TweetReader.ParseTweets.getTweetData("mashable", TweetData.mashable)
  
  private val sources = List(gizmodoTweets, techCrunchTweets, engadgetTweets, amazondealsTweets, cnetTweets, gadgetlabTweets, mashableTweets)

  val tweetMap: Map[String, List[Tweet]] =
    Map() ++ Seq(sites.head -> gizmodoTweets,
                 sites(1) -> techCrunchTweets,
                 sites(2) -> engadgetTweets,
                 sites(3) -> amazondealsTweets,
                 sites(4) -> cnetTweets,
                 sites(5) -> gadgetlabTweets,
                 sites(6) -> mashableTweets)

  val tweetSets: List[TweetSet] = sources.map(tweets => toTweetSet(tweets))
  
  private val siteTweetSetMap: Map[String, TweetSet] =
    Map() ++ (sites zip tweetSets)

  private def unionOfAllTweetSets(curSets: List[TweetSet], acc: TweetSet): TweetSet =
    if (curSets.isEmpty) acc
    else unionOfAllTweetSets(curSets.tail, acc.union(curSets.head))

  val allTweets: TweetSet = unionOfAllTweetSets(tweetSets, new Empty)
}