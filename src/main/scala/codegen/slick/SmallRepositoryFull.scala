package codegen.slick

object SmallRepositoryFull extends Common {

  def generate(name: String, table_name: String, input: Map[String, String]): String = {
    s"""
package models

import java.time.ZonedDateTime

import models.dao._
import play.api.db.slick.HasDatabaseConfig
import models.dao.EnhancedPostgresDriver
import play.api.libs.json.JsObject

import org.joda.time.{DateTimeZone, DateTime}
import utils.DateUtils._
import play.api.libs.json.JsObject
import utils.StringUtils
import models.${name}
import slick.model.ForeignKeyAction

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object ${name}Repository extends {
  val profile = slick.driver.PostgresDriver
} with ${name}Repository

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait ${name}Repository {
  import models.dao._
  import EnhancedPostgresDriver.api._

  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** GetResult implicit for fetching ${name} objects using plain SQL queries */

  ${generate_GR(name, input)}

  /** Table description of table ${table_name}. Objects of this class serve as prototypes for rows in queries. */
  class ${name}Table(_tableTag: Tag) extends Table[${name}](_tableTag, "${table_name}") ${generate_table_helper(input)} {
    def * = ( ${input.keys.mkString(", ")} ) <> ((${name}.apply _).tupled, ${name}.unapply)

    ${generate_optional_projection(name, input)}

    ${generateColumn(input)}
  }
  /** Collection-like TableQuery object for table financialProducts */
  lazy val ${lowerCaseFirstLetter(name)}s = new TableQuery(tag => new ${name}Table(tag))
}


/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
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

  private[this] def generate_optional_projection(name: String, input: Map[String, String]): String = {
    val type_list = input.map {
      case (c_name, c_type) if (c_type.startsWith("Option[")) => c_name
      case (c_name, c_type)                                   => s"Rep.Some($c_name)"
    }.mkString(",\n")

    val tupled_list = input.zipWithIndex.map {
      case ((_, c_type), i) if (c_type.startsWith("Option[")) => s"_${i+1}"
      case ((_, _), i)                                        => s"_${i+1}.get"
    }.mkString(",\n")

    s"""
  /** Maps whole row to an option. Useful for outer joins. */
    def ? = (
      $type_list
    ).shaped.<>({ r =>
      import r._; _1.map(_ => (${name}.apply _).tupled((
        $tupled_list
      )))
    }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))
   """.stripMargin
  }

  private[this] def generate_GR(name: String, input: Map[String, String]): String = {
    s"""implicit def GetResult${name}(implicit ${generate_GR_header(input)}): GR[${name}] = GR {
    prs =>
      import prs._
      (${name}.apply _).tupled((
        ${
      input.map {
        case (c_name, c_type) => {
          if (c_type.startsWith("Option[")) {
            s"<<${c_type.replaceFirst("Option", "?")}"
          } else {
            s"<<[${c_type}]"
          }
        }
      }.mkString(",\n")
    }
      ))
  }"""
  }

}
