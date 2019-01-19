package test.codegen

import codegen.CodeGenerator
import org.scalatest._

import scala.collection.immutable.ListMap
import test.helper.ScalatestHelper

class LargeCodeGeneratorTest extends FlatSpec with Matchers with ScalatestHelper {

  "The basic generator" should "generate code from large Map" in {
    val name = "user"
    val input = ListMap(
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
    val result = CodeGenerator.generate(name, input, false)

    result should equal (generate_large_code) (after being whiteSpaceNormalised)
  }

  private[this] val generate_large_code =
    """
package models

import models.dao._
import play.api.libs.json.JsObject
import org.joda.time.{DateTimeZone, DateTime}
import utils.DateUtils._
import utils.StringUtils

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object UserRepository extends {
  val profile = slick.driver.PostgresDriver
} with UserRepository

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait UserRepository {

  import models.dao.ParticeepDrivers.db_driver._
  import driver.api._
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._

  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  type UserRowList = HCons[String, HCons[DateTime, HCons[Option[DateTime], HCons[Option[String], HCons[String, HCons[Option[Boolean], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[DateTime], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[String], HCons[Option[Boolean], HCons[Option[Boolean], HCons[Option[String], HCons[Option[String], HCons[Option[DateTime], HCons[Option[String], HCons[Option[String], HCons[Option[StringSet], HCons[Option[StringSet], HCons[Option[Long], HCons[Option[String], HCons[Option[JsObject], HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
  object User {
    def apply(hList: UserRowList): User = new User(hlist.head, hlist.tail.head, hlist.tail.tail.head, hlist.tail.tail.tail.head, hlist.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head, hlist.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head)
    def unapply(row: User) = Some(row.wallet_id :: row.tag :: row.deleted_at :: row.custom :: row.last_name :: row.wallet_type :: row.birthday :: row.id :: row.has_been_claimed :: row.created_at :: row.city :: row.targeting_roles :: row.status :: row.sector :: row.bio :: row.investor_type :: row.birth_place :: row.does_pay_taxes :: row.phone :: row.first_name :: row.avatar_url :: row.investor_score :: row.roles :: row.email :: row.gender :: row.created_by :: row.linkedin_url :: row.wallet_updated_at :: row.nationality :: row.birth_country :: HNil)
  }

  /** GetResult implicit for fetching User objects using plain SQL queries */
  implicit def GetResultUser(implicit e0: GR[Option[Boolean]], e1: GR[Option[String]], e2: GR[DateTime], e3: GR[Option[DateTime]], e4: GR[Option[Long]], e5: GR[Option[JsObject]], e6: GR[String]): GR[User] = GR {
    prs =>
      import prs._
      User.apply(<<[String] :: <<[DateTime] :: <<?[DateTime] :: <<?[String] :: <<[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[DateTime] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[Boolean] :: <<?[Boolean] :: <<?[String] :: <<?[String] :: <<?[DateTime] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[Long] :: <<?[String] :: <<?[String] :: <<?[JsObject] :: HNIL)
  }

  /** Table description of table user_search_data. Objects of this class serve as prototypes for rows in queries. */
  class UserTable(_tableTag: Tag) extends Table[User](_tableTag, "user") with TableHelper with TableHelperContext with TableHelperDeletable {

    def * = (id :: created_at :: deleted_at :: created_by :: email :: gender :: first_name :: last_name :: avatar_url :: birthday :: birth_place :: birth_country :: phone :: nationality :: bio :: sector :: investor_type :: linkedin_url :: does_pay_taxes :: has_been_claimed :: city :: wallet_id :: wallet_updated_at :: wallet_type :: status :: roles :: investor_score :: targeting_roles :: tag :: custom :: HNIL <> (User.apply, User.unapply)
    val id = column[String]("id", O.PrimaryKey, O.Length(36, varying = true)
    val created_at = column[DateTime]("created_at")
    val deleted_at = column[Option[DateTime]]("deleted_at")
    val created_by = column[Option[String]]("created_by")
    val email = column[String]("email")
    val gender = column[Option[String]]("gender")
    val first_name = column[Option[String]]("first_name")
    val last_name = column[Option[String]]("last_name")
    val avatar_url = column[Option[String]]("avatar_url")
    val birthday = column[Option[DateTime]]("birthday")
    val birth_place = column[Option[String]]("birth_place")
    val birth_country = column[Option[String]]("birth_country")
    val phone = column[Option[String]]("phone")
    val nationality = column[Option[String]]("nationality")
    val bio = column[Option[String]]("bio")
    val sector = column[Option[String]]("sector")
    val investor_type = column[Option[String]]("investor_type")
    val linkedin_url = column[Option[String]]("linkedin_url")
    val does_pay_taxes = column[Option[Boolean]]("does_pay_taxes")
    val has_been_claimed = column[Option[Boolean]]("has_been_claimed")
    val city = column[Option[String]]("city")
    val wallet_id = column[Option[String]]("wallet_id")
    val wallet_updated_at = column[Option[DateTime]]("wallet_updated_at")
    val wallet_type = column[Option[String]]("wallet_type")
    val status = column[Option[String]]("status")
    val roles = column[Option[String]]("roles")
    val investor_score = column[Option[Long]]("investor_score")
    val targeting_roles = column[Option[String]]("targeting_roles")
    val tag = column[Option[String]]("tag")
    val custom = column[Option[JsObject]]("custom")
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new UserTable(tag))
}
"""

}
