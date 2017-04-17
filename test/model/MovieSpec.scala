package model


import org.scalatestplus.play._

class MovieSpec extends PlaySpec {

  "A Movie" must {
    "have a title" in {
      val movie = new Movie("lethal weapon")
      val rating = new Rating("Internet Movie Database", "8.9")
      movie.ratings.add(rating)

      movie.title mustBe "lethal weapon"
      movie.getImdbRating mustBe "8.9"
    }
  }

}
