package u05lab.ex1

import org.junit.*
import org.junit.Assert.*
import u05lab.ex2.*
import Question.*
import scala.collection.immutable.List

class ConferenceReviewingTest:

  @Before
  var cr: ConferenceReviewingImpl = new ConferenceReviewingImpl

  @Test
  def testLoadReview1(): Unit =
    cr.loadReview(1, Map(Relevance -> 4, Significance -> 7, Confidence -> 6, Final -> 7))
    assertEquals(cr.getList, List((1, Map(Relevance -> 4, Significance -> 7, Confidence -> 6, Final -> 7))))

  @Test
  def testLoadReview2(): Unit =
    cr.loadReview(1, 4, 7, 6, 7)
    assertEquals(cr.getList, List((1, Map(Relevance -> 4, Significance -> 7, Confidence -> 6, Final -> 7))))

  @Test
  def testOrderedScores(): Unit =
    cr = new ConferenceReviewingImpl
    cr.loadReview(1, 4, 7, 6, 7)
    cr.loadReview(1, 8, 3, 9, 5)
    cr.loadReview(2, 6, 5, 8, 6)
    cr.loadReview(2, 4, 7, 6, 7)
    cr.loadReview(3, 8, 3, 9, 5)
    cr.loadReview(3, 6, 5, 8, 6)
    cr.orderedScores(2, Relevance)
    assertEquals(List(4, 6), cr.orderedScores(2, Relevance))

  @Test
  def testAverageScore(): Unit =
    cr = new ConferenceReviewingImpl
    cr.loadReview(1, 4, 7, 6, 7)
    cr.loadReview(1, 8, 3, 9, 5)
    cr.loadReview(1, 6, 5, 8, 6)
    assertTrue(cr.averageScore(1) == 6.0)

  @Test
  def testAcceptedArticles(): Unit =
    cr = new ConferenceReviewingImpl
    cr.loadReview(1, 4, 7, 6, 7)
    cr.loadReview(1, 8, 3, 9, 5)
    cr.loadReview(2, 6, 5, 8, 6)
    cr.loadReview(2, 4, 7, 6, 7)
    cr.loadReview(3, 8, 3, 9, 6)
    cr.loadReview(3, 6, 5, 8, 7)
    assertEquals(cr.acceptedArticles(), Set(1, 3))

  @Test
  def testSortedAcceptedArticles(): Unit =
    cr = new ConferenceReviewingImpl
    cr.loadReview(1, 4, 7, 6, 7)
    cr.loadReview(1, 9, 3, 9, 5)
    cr.loadReview(2, 6, 5, 8, 6)
    cr.loadReview(2, 4, 7, 6, 7)
    cr.loadReview(3, 8, 3, 9, 6)
    cr.loadReview(3, 7, 5, 8, 7)
    assertEquals(cr.sortedAcceptedArticles(), List((1, 6.0), (3, 6.5)))

  @Test
  def testLast(): Unit =
    cr = new ConferenceReviewingImpl
    cr.loadReview(1, 4, 7, 6, 7)
    cr.loadReview(1, 9, 3, 9, 5)
    cr.loadReview(2, 6, 5, 8, 6)
    cr.loadReview(2, 4, 7, 6, 7)
    cr.loadReview(3, 8, 3, 9, 6)
    cr.loadReview(3, 7, 5, 8, 7)
    assertEquals(cr.averageWeightedFinalScoreMap(), Map((1, 4.35), (2, 4.5), (3, 5.5)))