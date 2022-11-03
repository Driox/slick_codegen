package codegen.slick

object HugeRepository extends Common {

  def generate(name: String, table_name: String, input: Map[String, String]): String = {
    s"""
package models

import models.dao._
import play.api.libs.json.JsObject
import play.api.db.slick.HasDatabaseConfig
import java.time.OffsetDateTime
import models.dao.EnhancedPostgresDriver

// AUTO-GENERATED Slick data model
/**
 * Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.)
 * Usage :
 *
 * @Singleton
 * class SubscriptionProjectionRepository @Inject() (
 *   protected val dbConfigProvider: DatabaseConfigProvider
 * )(implicit ec: ExecutionContext)
 *     extends HasDatabaseConfigProvider[EnhancedPostgresDriver]
 *     with SubscriptionProjectionDao {
 *
 *   import profile.api._
 *
 *   type TableType = SubscriptionProjectionTable
 *   val tables = TableQuery[SubscriptionProjectionTable]
 *
 *   // ...
 * }
 */
trait ${name}Dao {
  self: HasDatabaseConfig[EnhancedPostgresDriver] =>

  import driver.api._
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  import slick.jdbc.{GetResult => GR}

  ${generate_RowList(name, input)}

  object ${name}Companion {
    def apply(hList: ${name}RowList): ${name} = hList match {
      case ${hlist_name(input)} =>
        new ${name}(${input.keys.mkString(", ")})
    }

    def unapply(row: ${name}): Option[${name}RowList] = Some(
      ${input.keys.toList.map(c => s"row.$c").mkString(" :: ")} :: HNil
    )
  }

  ${generate_GR(name, input)}

  /** Table description of table user_search_data. Objects of this class serve as prototypes for rows in queries. */
  class ${name}Table(_tableTag: Tag) extends Table[${name}](_tableTag, "${table_name}") ${generate_table_helper(input)} {

    ${generateColumn(input)}

    def * = (
      ${hlist_name(input)}
    ) <> (${name}Companion.apply, ${name}Companion.unapply)
  }
  /** Collection-like TableQuery object for table ${name}s */
  lazy val ${name}s = new TableQuery(tag => new ${name}Table(tag))

  /** This is usefull for typesafe dynamic sorting */
  implicit val columns: Map[String, ${name}Table => Rep[_]] = Map(
    ${input.map{ case (name, _) => s""" "$name" -> { t => t.$name }"""}.mkString(", \n")}
  )
}

"""
  }

  private[this] def hlist_name(input: Map[String, String]):String = input.keys.mkString(" :: ") + " :: HNil"
  private[this] def hlist_type(input: Map[String, String]):String = input.map(_._2).mkString(" :: ") + " :: HNil"

  private[this] def generate_RowList(name: String, input: Map[String, String]): String = {
    s"private[this] type ${name}RowList = ${hlist_type(input)}"
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
      }.mkString(" :: ") + " :: HNil"
    })
  }"""
  }

}
