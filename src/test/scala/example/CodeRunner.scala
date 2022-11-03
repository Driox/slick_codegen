package test.example

import codegen.CodeGenerator
import org.scalatest._
import flatspec._
import matchers._

import test.helper.ScalatestHelper

class CodeRunnerTest extends AnyFlatSpec with should.Matchers with ScalatestHelper {

  "The code generator " should "print a light result" in {
    val case_class =
      """
case class Broker(
   id:              String                 = StringUtils.generateUuid(),
   created_at:      OffsetDateTime         = TimeUtils.now(),
   updated_at:      OffsetDateTime         = TimeUtils.now(),
   deleted_at:      Option[OffsetDateTime] = None,
   registration_number: String,
   tag:             Option[String]         = None,
   custom:          Option[JsObject]       = None
)
      """
    val repository = CodeGenerator.generate_from_case_class(case_class, true)
    println(repository)
  }

}
