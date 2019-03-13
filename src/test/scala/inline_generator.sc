import codegen.CodeGenerator

/**
  * 1/ compile project first
  * 2/ config Run Type : Plain
  * 3/ update cutoff limit
  *   File -> Settings... -> Languages and Frameworks -> Scala -> "Worksheet" tab -> "Output cutoff limit, lines" option
  */
val case_class = """
case class Broker(
  id:                  String = StringUtils.generateUuid(),
  registration_number: String
)
  """

val repository = CodeGenerator.generate_from_case_class(case_class, true)
