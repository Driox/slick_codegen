package test.example

import codegen.CodeGenerator
import org.scalatest._
import test.helper.ScalatestHelper

class CodeGeneratorTest extends FlatSpec with Matchers with ScalatestHelper {

  "The code generator " should "print a light result" in {
    val case_class =
      """
case class Broker(
   id:              String                = StringUtils.generateUuid(),
   created_at:      ZonedDateTime         = TimeUtils.now(),
   updated_at:      ZonedDateTime         = TimeUtils.now(),
   deleted_at:      Option[ZonedDateTime] = None,
   registration_number: String,
   tag:             Option[String]        = None,
   custom:          Option[JsObject]      = None
)
      """
    val repository = CodeGenerator.generate_from_case_class(case_class, true)
    println(repository)
  }

}
