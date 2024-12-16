package Api_PrivadaGET

import io.gatling.core.Predef._
import objects.MethodsHttp.get
import utilities.{BuildRequest, EnvironmentValues, RequestParams}

class GetTransactionsaccountopenthree extends Simulation{

  val env = new EnvironmentValues()
  val params= new RequestParams()

  params.baseUrl="https://api-transactions-management-adapter.labdigitalbdbtvsstg.com"
  params.pathUrl="/V1/Enterprise/TransactionsManagement/account/transactions"
  params.queryParams=Map(
    "AccountType" -> "DDA",
    "AccountNumber" -> "0019601582",
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
    "X-IdentSerialNum" -> "080788785",
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
    request.scn.inject(atOnceUsers(10)
  ).protocols(request.httpProtocol))
}
