package codegen.slick

object HugeRepository extends Common {

  def generate(name: String, table_name: String, input: Map[String, String]): String = {
    s"""
package models

import models.dao._
import play.api.libs.json.JsObject
import org.joda.time.{DateTimeZone, DateTime}
import utils.DateUtils._
import utils.StringUtils

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object ${name}Repository extends {
  val profile = slick.driver.PostgresDriver
} with ${name}Repository

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait ${name}Repository {


  import models.dao.ParticeepDrivers.db_driver._
  import driver.api._
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._

  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  type ${name}RowList = HCons[String, HCons[DateTime, HCons[Option[DateTime], HCons[Option[String], HCons[String, HCons[Option[Boolean], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[DateTime], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[Boolean], HCons[Option[Boolean], HCons[Option[String], HCons[Option[String], HCons[Option[DateTime], HCons[Option[String], HCons[Option[String], HCons[Option[StringSet], HCons[Option[StringSet], HCons[Option[Long], HCons[Option[String], HCons[Option[JsObject], HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
  object ${name} {
    def apply(hList: ${name}RowList): ${name} = new ${name}(${
      input.keys.toList.zipWithIndex.map {
        case (elem, index) =>
          val tail = List.fill(index)("tail.").mkString("")
          "hlist." + tail + "head"
      }.mkString(", ")
    })
    def unapply(row: ${name}) = Some(${input.keys.map(c => s"row.$c").mkString(" :: ")} :: HNil)
  }

  ${generate_GR(name, input)}

  /** Table description of table user_search_data. Objects of this class serve as prototypes for rows in queries. */
  class ${name}Table(_tableTag: Tag) extends Table[${name}](_tableTag, "${table_name}") ${generate_table_helper(input)} {

    def * = (${input.keys.mkString(" :: ")} :: HNIL <> (${name}.apply, ${name}.unapply)
    ${generateColumn(input)}
  }
  /** Collection-like TableQuery object for table ${name}s */
  lazy val ${name}s = new TableQuery(tag => new ${name}Table(tag))
}

"""
  }

    private[this] def generate_GR(name: String, input: Map[String, String]): String = {
    s"""/** GetResult implicit for fetching ${name} objects using plain SQL queries */
  implicit def GetResult${name}(implicit ${generate_GR_header(input)}): GR[${name}] = GR {
    prs =>
      import prs._
      ${name}.apply(${
      input.map {
        case (c_name, c_type) => {
          if (c_type.startsWith("Option[")) {
            s"<<${c_type.replaceFirst("Option", "?")}"
          } else {
            s"<<[${c_type}]"
          }
        }
      }.mkString(" :: ") + " :: HNIL"
    })
  }"""
  }

}
