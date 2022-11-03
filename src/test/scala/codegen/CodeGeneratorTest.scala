package test.codegen

import codegen.CodeGenerator
import org.scalatest._
import flatspec._
import matchers._

import scala.collection.immutable.ListMap
import test.helper.ScalatestHelper

class CodeGeneratorTest extends AnyFlatSpec with should.Matchers with ScalatestHelper {

  "The case class generator " should "generate code from case class mono line" in {
      val case_class_content =
        s"""
case class User( id: String, email: String, password: String, created_at: DateTime, deleted_at: Option[DateTime], tag: Option[String], custom: Option[JsObject] )
       """
      val result = CodeGenerator.generate_from_case_class(case_class_content, true)
      result should equal(generate_small_light_code)(after being whiteSpaceNormalised)
    }

  "The case class generator " should "generate code from case class multi line" in {
      val case_class_content =
        s"""
case class User( id: String,
email: String,
  password: String,
created_at: DateTime,
    deleted_at: Option[DateTime],
tag: Option[String],
custom: Option[JsObject]
)
       """
      val result = CodeGenerator.generate_from_case_class(case_class_content, false)
      result should equal(generate_small_heavy_code)(after being whiteSpaceNormalised)
  }

  "The basic generator" should "generate heavy code from small Map" in {
      val name = "user"
      val input: Map[String, String] = ListMap(
        "id" -> "String",
        "email" -> "String",
        "password" -> "String",
        "created_at" -> "DateTime",
        "deleted_at" -> "Option[DateTime]",
        "tag" -> "Option[String]",
        "custom" -> "Option[JsObject]"
      )
      val result = CodeGenerator.generate(name, input, false)
      result should equal(generate_small_heavy_code)(after being whiteSpaceNormalised)
  }

  "The basic generator" should "generate light code from small Map" in {
    val name = "user"
    val input:Map[String, String] = ListMap(
      "id" -> "String",
      "email" -> "String",
      "password" -> "String",
      "created_at" -> "DateTime",
      "deleted_at" -> "Option[DateTime]",
      "tag" -> "Option[String]",
      "custom" -> "Option[JsObject]"
    )
    val result = CodeGenerator.generate(name, input, true)

    result should equal (generate_small_light_code) (after being whiteSpaceNormalised)
  }


  private[this] val generate_small_heavy_code =
    """
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
import models.User
import slick.model.ForeignKeyAction

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object UserDao extends {
  val profile = slick.driver.PostgresDriver
} with UserDao

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait UserDao {
  import models.dao._
  import EnhancedPostgresDriver.api._

  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** GetResult implicit for fetching User objects using plain SQL queries */


  implicit def GetResultUser(implicit e0: GR[Option[String]], e1: GR[DateTime], e2: GR[Option[DateTime]], e3: GR[Option[JsObject]], e4: GR[String]): GR[User] = GR {
    prs =>
      import prs._
      (User.apply _).tupled((
        <<[String],
        <<[String],
        <<[String],
        <<[DateTime],
        <<?[DateTime],
        <<?[String],
        <<?[JsObject]
      ))
  }

  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class UserTable(_tableTag: Tag) extends Table[User](_tableTag, "user") with TableHelper with TableHelperDeletable {
    def * = ( id, email, password, created_at, deleted_at, tag, custom ) <> ((User.apply _).tupled, User.unapply)


  /** Maps whole row to an option. Useful for outer joins. */
    def ? = (
      Rep.Some(id),
      Rep.Some(email),
      Rep.Some(password),
      Rep.Some(created_at),
      deleted_at,
      tag,
      custom
    ).shaped.<>({ r =>
      import r._; _1.map(_ => (User.apply _).tupled((
        _5,
        _4.get,
        _6,
        _3.get,
        _2.get,
        _7,
        _1.get
      )))
    }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))


    val id = column[String]("id", O.PrimaryKey, O.Length(36, varying = true))
    val email = column[String]("email")
    val password = column[String]("password")
    val created_at = column[DateTime]("created_at")
    val deleted_at = column[Option[DateTime]]("deleted_at")
    val tag = column[Option[String]]("tag")
    val custom = column[Option[JsObject]]("custom")
  }
  /** Collection-like TableQuery object for table financialProducts */
  lazy val users = new TableQuery(tag => new UserTable(tag))
}

"""

  private[this] val generate_small_light_code = """
package models

import java.time.ZonedDateTime

import models.dao._
import play.api.db.slick.HasDatabaseConfig
import models.dao.EnhancedPostgresDriver
import play.api.libs.json.JsObject

trait UserDao extends TableMapping {
  self: HasDatabaseConfig[EnhancedPostgresDriver] =>

  import profile.api._

  class UserTable(_tableTag: Tag) extends Table[User](_tableTag, "user") with TableHelper {

    val id = column[String]("id", O.PrimaryKey, O.Length(36, varying = true))
    val email = column[String]("email")
    val password = column[String]("password")
    val created_at = column[DateTime]("created_at")
    val deleted_at = column[Option[DateTime]]("deleted_at")
    val tag = column[Option[String]]("tag")
    val custom = column[Option[JsObject]]("custom")

    def * = (id, email, password, created_at, deleted_at, tag, custom) <> (User.tupled, User.unapply _)
  }

}


@Singleton
class UserRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[EnhancedPostgresDriver]
    with CrudRepository[User, EnhancedPostgresDriver]
    with EntityWithTableLifecycle[EnhancedPostgresDriver]
    with UserDao {

  import profile.api._

  val tables = TableQuery[UserTable]
  type TableType = UserTable

}

"""
}
