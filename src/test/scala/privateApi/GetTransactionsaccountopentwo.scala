package Api_PrivadaGET

import io.gatling.core.Predef._
import objects.MethodsHttp.get
import utilities.{BuildRequest, EnvironmentValues, RequestParams}

class GetTransactionsaccountopentwo extends Simulation{

  val env = new EnvironmentValues()
  val params= new RequestParams()

  params.baseUrl="https://api-transactions-management-adapter.labdigitalbdbtvsstg.com"
  params.pathUrl="/V1/Enterprise/TransactionsManagement/account/transactions"
  params.queryParams=Map(
    "AccountType" -> "SDA",
    "AccountNumber" -> "0019601533",
    "StartDate" -> "2023-01-25",
    "EndDate" -> "2024-01-25",
    "TransactionCategory" -> "All"
  )
  params.headers=Map(
    "X-accept" -> "application/json",
    "X-RqUID" -> "6ae0fb59-0a91-4737-ada9-cceaed7fc0b9",
    "X-IPAddr" -> "100.20.30.40",
    "X-Channel" -> "APN001",
    "X-CustIdentType" -> "CC",
    "X-IdentSerialNum" -> "080788781",
    "X-Name" -> "PQRS",
    "x-api-key" -> "uGTRR0b1R96HW9zyPb8hh3PhmiYufJyb4OS6WO1r",
    "Content-Type" -> "application/json"
  )
  params.method=get
  params.requestName="gettransactionsaccount"
  params.statusExpected=200
  params.scenarioName="Obtener movimientos por cuenta"

  val request = new BuildRequest(params)

  setUp(
    request.scn.inject(constantUsersPerSec(10).during(5).randomized)
  ).protocols(request.httpProtocol)
}