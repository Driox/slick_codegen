package test

import scala.collection.immutable.ListMap

object GeneratorTest {
    val caseclass =
      """
  case class User(
                 id:                String,
                 created_at:        DateTime,
                 deleted_at:        Option[DateTime],
                 created_by:        Option[String],
                 email:             String,
                 gender:            Option[String],
                 first_name:        Option[String],
                 last_name:         Option[String],
                 avatar_url:        Option[String],
                 birthday:          Option[DateTime],
                 birth_place:       Option[String],
                 birth_country:      Option[String],
                 phone:             Option[String],
                 nationality:       Option[String],
                 bio:               Option[String],
                 sector:            Option[String],
                 investor_type:     Option[String],
                 linkedin_url:      Option[String],
                 does_pay_taxes:    Option[Boolean],
                 has_been_claimed:  Option[Boolean],
                 city:              Option[String],
                 wallet_id:         Option[String],
                 wallet_updated_at: Option[DateTime],
                 wallet_type:       Option[String],
                 status:            Option[String],
                 roles:             Option[String],
                 investor_score:    Option[Long],
                 targeting_roles:   Option[String],
                 tag:               Option[String],
                 custom:            Option[JsObject]
  )

"""

    val input: Map[String, String] = ListMap(
      "id" -> "String",
      "email" -> "String",
      "password" -> "String",
      "created_at" -> "DateTime",
      "deleted_at" -> "Option[DateTime]",
      "tag" -> "Option[String]",
      "custom" -> "Option[JsObject]"
    )

    val input_large = ListMap(
      "id" -> "String",
      "created_at" -> "DateTime",
      "deleted_at" -> "Option[DateTime]",
      "created_by" -> "Option[String]",
      "email" -> "String",
      "gender" -> "Option[String]",
      "first_name" -> "Option[String]",
      "last_name" -> "Option[String]",
      "avatar_url" -> "Option[String]",
      "birthday" -> "Option[DateTime]",
      "birth_place" -> "Option[String]",
      "birth_country" -> "Option[String]",
      "phone" -> "Option[String]",
      "nationality" -> "Option[String]",
      "bio" -> "Option[String]",
      "sector" -> "Option[String]",
      "investor_type" -> "Option[String]",
      "linkedin_url" -> "Option[String]",
      "does_pay_taxes" -> "Option[Boolean]",
      "has_been_claimed" -> "Option[Boolean]",
      "city" -> "Option[String]",
      "wallet_id" -> "Option[String]",
      "wallet_updated_at" -> "Option[DateTime]",
      "wallet_type" -> "Option[String]",
      "status" -> "Option[String]",
      "roles" -> "Option[String]",
      "investor_score" -> "Option[Long]",
      "targeting_roles" -> "Option[String]",
      "tag" -> "Option[String]",
      "custom" -> "Option[JsObject]"
    )

    def caseClassToMap(case_class_string: String): Map[String, String] = {
      val one_line = case_class_string
        .replaceAll("\n", "")
        .replaceAll("\r", "")
        .trim()
        .replaceAll("\\s+", " ")

      val case_class_regex = "case class ([a-zA-Z0-9_]*)\\((.*)\\)".r
      one_line match {
        case case_class_regex(name, fields) => {
          fields.split(",").map(_.trim).map { s =>
            val a_field = s.split(":")
            val c_name = a_field.head.trim
            val c_type = a_field.last.trim
            (c_name, c_type)
          }.toMap
        }
      }
    }

    caseClassToMap(caseclass)

    //CodeGenerator.generate("user", input_large)

    //CodeGenerator.generator_builder("User", "user", input, false)
}
