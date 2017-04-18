package model


import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}


//class MovieSpec extends PlaySpec {
//
//  "A Movie" must {
//    "have a title and rating must match" in {
//      val movie = new Movie("lethal weapon")
//      val rating = new Rating("Internet Movie Database", "8.9")
//      movie.ratings.add(rating)
//
//      movie.title mustBe "lethal weapon"
//      movie.getImdbRating mustBe "8.9"
//    }
//  }

class MovieSpec extends FlatSpec with Matchers with MockitoSugar {

  "mockito test" should "be easy" in {

    val mockedList = mock[java.util.ArrayList[Rating]]

    val movie = new Movie("test")1
    movie.ratings = mockedList

    movie.ratings.add(new Rating("imdb", "5.0"))

    mockedList.clear()
    org.mockito.Mockito.verify(mockedList, org.mockito.Mockito.times(1)).add(new Rating("imdb", "5.0"))
    org.mockito.Mockito.verify(mockedList, org.mockito.Mockito.times(1)).clear()
  }



}
