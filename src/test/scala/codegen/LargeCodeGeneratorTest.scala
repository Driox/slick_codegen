package test.codegen

import codegen.CodeGenerator
import org.scalatest._
import flatspec._
import matchers._

import scala.collection.immutable.ListMap
import test.helper.ScalatestHelper

class LargeCodeGeneratorTest extends AnyFlatSpec with should.Matchers with ScalatestHelper {

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
trait UserDao {
  self: HasDatabaseConfig[EnhancedPostgresDriver] =>

  import driver.api._
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  import slick.jdbc.{GetResult => GR}

  private[this] type UserRowList = String :: DateTime :: Option[DateTime] :: Option[String] :: String :: Option[String] :: Option[String] :: Option[String] :: Option[String] :: Option[DateTime] :: Option[String] :: Option[String] :: Option[String] :: Option[String] :: Option[String] :: Option[String] :: Option[String] :: Option[String] :: Option[Boolean] :: Option[Boolean] :: Option[String] :: Option[String] :: Option[DateTime] :: Option[String] :: Option[String] :: Option[String] :: Option[Long] :: Option[String] :: Option[String] :: Option[JsObject] :: HNil

  object User {
    def apply(hList: UserRowList): User = hList match {
      case id :: created_at :: deleted_at :: created_by :: email :: gender :: first_name :: last_name :: avatar_url :: birthday :: birth_place :: birth_country :: phone :: nationality :: bio :: sector :: investor_type :: linkedin_url :: does_pay_taxes :: has_been_claimed :: city :: wallet_id :: wallet_updated_at :: wallet_type :: status :: roles :: investor_score :: targeting_roles :: tag :: custom :: HNil =>
        User(id, created_at, deleted_at, created_by, email, gender, first_name, last_name, avatar_url, birthday, birth_place, birth_country, phone, nationality, bio, sector, investor_type, linkedin_url, does_pay_taxes, has_been_claimed, city, wallet_id, wallet_updated_at, wallet_type, status, roles, investor_score, targeting_roles, tag, custom)
    }

    def unapply(row: User): Option[UserRowList] = Some(
      row.wallet_id :: row.tag :: row.deleted_at :: row.custom :: row.last_name :: row.wallet_type :: row.birthday :: row.id :: row.has_been_claimed :: row.created_at :: row.city :: row.targeting_roles :: row.status :: row.sector :: row.bio :: row.investor_type :: row.birth_place :: row.does_pay_taxes :: row.phone :: row.first_name :: row.avatar_url :: row.investor_score :: row.roles :: row.email :: row.gender :: row.created_by :: row.linkedin_url :: row.wallet_updated_at :: row.nationality :: row.birth_country :: HNil
    )
  }

  /** GetResult implicit for fetching User objects using plain SQL queries */
  implicit def GetResultUser(implicit e0: GR[Option[Boolean]], e1: GR[Option[String]], e2: GR[DateTime], e3: GR[Option[DateTime]], e4: GR[Option[Long]], e5: GR[Option[JsObject]], e6: GR[String]): GR[User] = GR {
    prs =>
      import prs._
      User.apply(<<[String] :: <<[DateTime] :: <<?[DateTime] :: <<?[String] :: <<[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[DateTime] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[Boolean] :: <<?[Boolean] :: <<?[String] :: <<?[String] :: <<?[DateTime] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[Long] :: <<?[String] :: <<?[String] :: <<?[JsObject] :: HNil)
  }

  /** Table description of table user_search_data. Objects of this class serve as prototypes for rows in queries. */
  class UserTable(_tableTag: Tag) extends Table[User](_tableTag, "user") with TableHelper with TableHelperContext with TableHelperDeletable {

    val id = column[String]("id", O.PrimaryKey, O.Length(36, varying = true))
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

    def * = (
      id :: created_at :: deleted_at :: created_by :: email :: gender :: first_name :: last_name :: avatar_url :: birthday :: birth_place :: birth_country :: phone :: nationality :: bio :: sector :: investor_type :: linkedin_url :: does_pay_taxes :: has_been_claimed :: city :: wallet_id :: wallet_updated_at :: wallet_type :: status :: roles :: investor_score :: targeting_roles :: tag :: custom :: HNil
    ) <> (User.apply, User.unapply)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new UserTable(tag))

  /** This is usefull for typesafe dynamic sorting */
  implicit val columns: Map[String, UserTable => Rep[_]] = Map(
     "id" -> { t => t.id },
 "created_at" -> { t => t.created_at },
 "deleted_at" -> { t => t.deleted_at },
 "created_by" -> { t => t.created_by },
 "email" -> { t => t.email },
 "gender" -> { t => t.gender },
 "first_name" -> { t => t.first_name },
 "last_name" -> { t => t.last_name },
 "avatar_url" -> { t => t.avatar_url },
 "birthday" -> { t => t.birthday },
 "birth_place" -> { t => t.birth_place },
 "birth_country" -> { t => t.birth_country },
 "phone" -> { t => t.phone },
 "nationality" -> { t => t.nationality },
 "bio" -> { t => t.bio },
 "sector" -> { t => t.sector },
 "investor_type" -> { t => t.investor_type },
 "linkedin_url" -> { t => t.linkedin_url },
 "does_pay_taxes" -> { t => t.does_pay_taxes },
 "has_been_claimed" -> { t => t.has_been_claimed },
 "city" -> { t => t.city },
 "wallet_id" -> { t => t.wallet_id },
 "wallet_updated_at" -> { t => t.wallet_updated_at },
 "wallet_type" -> { t => t.wallet_type },
 "status" -> { t => t.status },
 "roles" -> { t => t.roles },
 "investor_score" -> { t => t.investor_score },
 "targeting_roles" -> { t => t.targeting_roles },
 "tag" -> { t => t.tag },
 "custom" -> { t => t.custom }
  )
}
"""

}
