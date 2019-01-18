package codegen.slick

object SmallRepositoryLight extends Common {

  def generate(name: String, table_name: String, input: Map[String, String]): String = {
    s"""
package models

import java.time.ZonedDateTime

import models.dao._
import play.api.db.slick.HasDatabaseConfig
import models.dao.EnhancedPostgresDriver
import play.api.libs.json.JsObject

trait ${name}Component extends TableMapping {
  self: HasDatabaseConfig[EnhancedPostgresDriver] =>

  import profile.api._

  class ${name}Table(tag: Tag) extends Table[${name}](tag, "${table_name}") with TableHelper {

    ${generateColumn(input)}

    def * = (${input.keys.mkString(", ")}) <> (${name}.tupled, ${name}.unapply _)
  }

}
"""
  }
}
