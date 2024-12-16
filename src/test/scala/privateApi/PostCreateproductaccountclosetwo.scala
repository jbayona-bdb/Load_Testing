package Api_PrivadaPOST

import io.gatling.core.Predef._
import objects.MethodsHttp.post
import utilities.{BuildRequest, EnvironmentValues, RequestParams}

class PostCreateproductaccountclosetwo extends Simulation{

  val env = new EnvironmentValues()
  val params= new RequestParams()

  params.baseUrl="https://api.labdigbdbstgcd.com"
  params.pathUrl="/accounts-management/v2/product/account"
  params.headers=Map(
    "X-CustIdentType" -> "#{tipoid}",
    "X-CustIdentNum" -> "#{documento}",
    "X-IPAddr" -> "192.168.0.1",
    "X-RqUID" -> "a9e738b0-6369-47d9-b563-8dce7bb300fa",
    "X-Name" -> "CuentaDeAhorros",
    "X-Channel" -> "Web",
    "X-DigRequest" -> "123",
    "X-AccessToken" -> "accessToken",
    "X-CustType" -> "ACTIVE",
    "x-api-key" -> "uGTRR0b1R96HW9zyPb8hh3PhmiYufJyb4OS6WO1r",
    "Content-Type" -> "application/json"
  )
  params.method=post
  params.requestName="CreateProductAccount"
  params.statusExpected=201
  params.scenarioName="Crear Cuentas"
  params.templateJson= "data/crearBodyAccount.json"
  params.feederCsv = Array("data/datoscliente.csv")

  val request = new BuildRequest(params)

  setUp(
    request.scn.inject(rampConcurrentUsers(10).to(5).during(5))
  ).protocols(request.httpProtocol)
}