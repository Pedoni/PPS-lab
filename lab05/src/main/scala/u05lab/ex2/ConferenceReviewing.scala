package u05lab.ex2

import scala.collection.immutable.ListMap

enum Question:
  case Relevance
  case Significance
  case Confidence
  case Final

trait ConferenceReviewing:
  var list: List[(Int, Map[Question, Int])]
  def loadReview(article: Int, scores: Map[Question, Int]): Unit
  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit
  def orderedScores(article: Int, question: Question): List[Int]
  def averageScore(article: Int): Double
  def acceptedArticles(): Set[Int]
  def sortedAcceptedArticles(): List[(Int, Double)]
  def averageWeightedFinalScoreMap(): Map[Int, Double]

class ConferenceReviewingImpl extends ConferenceReviewing:

  import Question.*

  override var list: List[(Int, Map[Question, Int])] = List.empty

  override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
    this.list = this.list :+ (article, scores)

  override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
    this.list = this.list :+ (article, Map(Relevance -> relevance, Significance -> significance, Confidence -> confidence, Final -> fin))
      
  override def orderedScores(article: Int, question: Question): List[Int] =
    this.list.collect({ case i if i._1 == article => i._2(question)}).sorted

  override def averageScore(article: Int): Double =
    val l = this.orderedScores(article, Final)
    l.sum.toDouble / l.length.toDouble

  override def acceptedArticles(): Set[Int] =
    this.list.filter(i => i._2(Relevance) >= 8 && this.averageScore(i._1) > 5).distinct.map(i => i._1).toSet

  override def sortedAcceptedArticles(): List[(Int, Double)] =
    this.acceptedArticles().toList.map(a => (a, this.averageScore(a)))

  override def averageWeightedFinalScoreMap(): Map[Int, Double] =
    this.list.map(i => (i._1, i._2(Confidence).toDouble * i._2(Final).toDouble / 10)).groupMap(_._1)(_._2).view.mapValues(vs => vs.sum / vs.size).toMap

  def getList: List[(Int, Map[Question, Int])] = this.list
