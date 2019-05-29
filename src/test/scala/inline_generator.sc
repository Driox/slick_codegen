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
case class SubscriptionProjection(
  id:              String                                = StringUtils.generateUuid(),
  created_at:      ZonedDateTime                         = TimeUtils.now(),
  updated_at:      ZonedDateTime                         = TimeUtils.now(),
  deleted_at:      Option[ZonedDateTime]                 = None,
  insurer_name:    String,
  insurer_id:      String,
  product_name:    String,
  product_id:      String,
  user_id:         String,
  user_first_name: String,
  user_last_name:  String,
  user_email:      String,
  broker_id:       Option[String]                        = None,
  status:          SubscriptionStatus.SubscriptionStatus = SubscriptionStatus.ONGOING,
  data:            Option[Data]                          = None,
  amount_ht:       Option[Amount]                        = None,
  owner_fees:      Option[Amount]                        = None,
  broker_fees:     Option[Amount]                        = None,
  taxes:           Option[Amount]                        = None,
  total_ht:        Option[Amount]                        = None,
  total_ttc:       Option[Amount]                        = None,
  currency:        Option[Currency]                      = None,
  contract_signed: Option[URL]                           = None,
  tag:             Option[String]                        = None,
  custom:          Option[JsObject]                      = None
)
  """

val repository = CodeGenerator.generate_from_case_class(case_class, true)
