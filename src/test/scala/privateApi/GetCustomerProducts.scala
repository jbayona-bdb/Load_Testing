package privateApi

import io.gatling.core.Predef._
import utilities.{BuildRequest, EnvironmentValues, RequestParams}
import objects.MethodsHttp._

class GetCustomerProducts extends Simulation{

  val env = new EnvironmentValues () //llamar variables de entorno
  val params = new RequestParams() // objeto de parametros

  params.baseUrl="https://api-clients.labdigitalbdbtvsstg.com" //llamar parametros URL base
  params.pathUrl ="/customer-management-v3-mngr/V3/enterprise/customer/product"   // Endpoint o recurso
  params.headers =Map(                //se definen los headers de Api
    "accept" -> "application/json",
    "X-GovIssueIdentType" -> "CC",
    "X-IdentSerialNum" -> "2921380",
    "X-Channel" -> "Web",
    "X-CompanyId" -> "001",
    "X-IPAddr" -> "127.0.0.1",
    "X-Name" -> "Transversales",
    "X-RqUID" -> "61aa74d6-e313-4964-9e22-49733b1b54d7",
    "x-api-key" -> "uGTRR0b1R96HW9zyPb8hh3PhmiYufJyb4OS6WO1r"
  )

  params.method=get //metodo a utilizar
  params.requestName ="getCustomerProducts" //nombre del request
  params.statusExpected= 200  //respuesta esperada
  params.scenarioName ="Consulta de productos de un cliente" //nombre del escenario




  val request= new BuildRequest(params) //pasar el params creado a la clase build request, para que se firme la petición como la espera la lambda



setUp(
  request.scn.inject(constantUsersPerSec(4).during(1)) // 4 usuarios durante 11 segundos

).protocols(request.httpProtocol) //se hace el llamado



}
