import codegen.CodeGenerator

/**
  * 1/ compile project first
  * 2/ config Run Type : Plain
  * 3/ update cutoff limit
  *   File -> Settings... -> Languages and Frameworks -> Scala -> "Worksheet" tab -> "Output cutoff limit, lines" option
  * 4/ for live reload of code
  *   File -> Settings -> Languages & Frameworks -> Scala -> Worksheet (tab) . Unselect "Run worksheet in the compiler process".
  */
val case_class = """
case class Address(
                  id:String,
    number:  Option[String] = None,
    street:  Option[String] = None,
    zip:     Option[String] = None,
    city:    Option[String] = None,
    country: Option[String] = None,
    latitude:Option[Double] = None,
    longitude:Option[Double] = None
)


  """

val repository = CodeGenerator.generate_from_case_class(case_class, true)
