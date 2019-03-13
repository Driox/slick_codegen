package codegen.slick

trait Common {

  def lowerCaseFirstLetter(s: String): String = Character.toLowerCase(s.charAt(0)) + s.substring(1)

  def generateColumn(input: Map[String, String]): String = {
    input.map {
      case (c_name, c_type) => {
        s"""val $c_name = column[$c_type]("$c_name"${additionnal_options(c_name)})"""
      }
    }.mkString("\n")
  }

  private[this] def additionnal_options(name: String): String = {
    name match {
      case "id" => ", O.PrimaryKey, O.Length(36, varying = true)"
      case _    => ""
    }
  }

  def generate_GR_header(input: Map[String, String]): String = {
    input.values
      .toSet.toList
      .map((t: String) => s"GR[$t]")
      .zipWithIndex
      .map { case (t: String, i: Int) => s"e$i: $t" }
      .mkString(", ")
  }

  def generate_table_helper(input: Map[String, String]): String = {
    val ctx_helper = if (input.keys.toList.contains("created_by")) " with TableHelperContext" else ""
    val delete_helper = if (input.keys.toList.contains("deleted_at")) " with TableHelperDeletable" else ""

    s"with TableHelper$ctx_helper$delete_helper"
  }
}
