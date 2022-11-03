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
case class SubscriptionView(
  id:                   String                      = StringUtils.generateUuid(),
  created_at:           OffsetDateTime              = TimeUtils.now(),
  updated_at:           OffsetDateTime              = TimeUtils.now(),
  deleted_at:           Option[OffsetDateTime]      = None,
  broker_owner_id:      String,
  broker_owner_name:    String,
  broker_user_id:       BrokerUserId,
  broker_user_email:    String,
  product_id:           String,
  product_name:         String,
  user_email:           String,
  user_first_name:      String,
  user_last_name:       String,
  user_phone:           String,
  target_account_id:    Option[String]              = None,
  status:               SubscriptionStatus          = SubscriptionStatus.ONGOING,
  payment_status:       SubscriptionPaymentStatus   = SubscriptionPaymentStatus.PENDING,
  signature_status:     SubscriptionSignatureStatus = SubscriptionSignatureStatus.PENDING,
  selected_quote_id:    Option[String]              = None,
  last_step:            Option[Int]                 = None,
  start_at:             Option[OffsetDateTime]      = None,
  end_at:               Option[OffsetDateTime]      = None,
  amount_ht:            Option[Amount]              = None,
  owner_fees:           Option[Amount]              = None,
  broker_fees:          Option[Amount]              = None,
  taxes:                Option[Amount]              = None,
  booking_fees_ht:      Option[Amount]              = None,
  booking_fees_taxes:   Option[Amount]              = None,
  total_ht:             Option[Amount]              = None,
  total_ttc:            Option[Amount]              = None,
  frequency:            Option[Frequency]           = None,
  currency:             Option[Currency]            = None,
  contract_signed:      Option[URL]                 = None,
  contract_number:      Option[String]              = None,
  data:                 Option[Data]                = None,
  pricer_external_data: Option[JsObject]            = None,
  tag:                  Option[String]              = None,
  custom:               Option[JsObject]            = None
)
  """

val repository = CodeGenerator.generate_from_case_class(case_class, true)
