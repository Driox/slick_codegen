package codegen

import codegen.slick.{HugeRepository, SmallRepositoryFull, SmallRepositoryLight}
import utils.FileUtils._
import java.io.File

import scala.collection.immutable.{ListMap, TreeMap}
import scala.collection.mutable

object CodeGenerator {


  def generate_from_folder(folder_path:String, to_path:String) = {
    val folder = new File(folder_path)
    folder.listFiles().map(f => generate_from_file(f, to_path))
  }

  def generate_from_file(from:File, to_path:String) = {
    val file_content = from.read()
    val case_class = caseClassToMap(file_content)
    generate_and_persist(to_path, case_class.name, case_class.fields)
  }

  def generate_from_case_class(case_class_content:String, light:Boolean = true):String = {
    val case_class = caseClassToMap(case_class_content)
    generate(case_class.name, case_class.fields, light)
  }

  private[this] case class CaseClassData(name:String, fields:Map[String, String])
  private[this] def caseClassToMap(case_class_string:String):CaseClassData = {
    val one_line = case_class_string
      .replaceAll("\n", "")
      .replaceAll("\r", "")
      .trim()
      .replaceAll("\\s+", " ")

    val case_class_regex = "case class ([a-zA-Z0-9_]*)\\((.*)\\)".r
    one_line match {
      case case_class_regex(name, field_list) => {
        val fields = field_list.split(",").map(_.trim).map{ s =>
          val a_field = s.split(":")
          val c_name = a_field.head.trim
          val c_type = a_field.last.trim
          (c_name, c_type)
        }
        val f_map = ListMap( fields : _* )
        CaseClassData(name, f_map)
      }
    }
  }

  private[this] def generate_and_persist(target_folder:String, name: String, input: Map[String, String], light: Boolean = true): Unit = {
    val result = generate(name, input, light)
    val folder = new File(target_folder + "/" + name.capitalize + ".scala")
    folder.write(result)
  }

  def generate(name: String, input: Map[String, String], light: Boolean = true): String = {
    //TODO if name contains underscore we need to remove them and capitalize the next letter
    // e.g. : admin_projection --> AdminProjection
    generator_builder(name.capitalize, name.toLowerCase, input, light)
  }

  private[this] def generator_builder(name: String, table_name: String, input: Map[String, String], light: Boolean): String = {
    if (input.size <= 22) {
      if (light) {
        SmallRepositoryLight.generate(name, table_name, input)
      } else {
        SmallRepositoryFull.generate(name, table_name, input)
      }
    } else {
      HugeRepository.generate(name, table_name, input)
    }
  }

}
