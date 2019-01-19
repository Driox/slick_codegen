package test.helper

import org.scalactic._

trait ScalatestHelper {
  val whiteSpaceNormalised: Uniformity[String] =
  new AbstractStringUniformity {
    /**Returns the string with all consecutive white spaces reduced to a single space.*/
    def normalized(s: String): String = s.replaceAll("(?s)\\s+", " ").trim
    override def toString: String = "whiteSpaceNormalised"
  }
}
