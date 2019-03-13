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

trait ${name}Dao extends TableMapping {
  self: HasDatabaseConfig[EnhancedPostgresDriver] =>

  import profile.api._

  class ${name}Table(_tableTag: Tag) extends Table[${name}](_tableTag, "${table_name}") with TableHelper {

    ${generateColumn(input)}

    def * = (${input.keys.mkString(", ")}) <> (${name}.tupled, ${name}.unapply _)
  }

}

@Singleton
class ${name}Repository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[EnhancedPostgresDriver]
    with CrudRepository[${name}, EnhancedPostgresDriver]
    with EntityWithTableLifecycle[EnhancedPostgresDriver]
    with ${name}Dao {

  import profile.api._

  val tables = TableQuery[${name}Table]
  type TableType = ${name}Table

}

"""
  }
}
