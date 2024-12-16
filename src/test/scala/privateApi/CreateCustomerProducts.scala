package privateApi

import io.gatling.core.Predef._
import io.gatling.core.scenario._
import objects.MethodsHttp.{get, post}
import utilities.{BuildRequest, EnvironmentValues, RequestParams}

class CreateCustomerProducts extends Simulation{

  val env = new EnvironmentValues () //llamar variables de entorno
  val params = new RequestParams() // objeto de parametros

  params.baseUrl="https://api-clients.labdigitalbdbtvsstg.com" //llamar parametros URL base
  params.pathUrl ="/customer-management-v3-mngr/V3/enterprise/customer"   // Endpoint o recurso
  params.headers =Map(                //se definen los headers de Api
    "accept" -> "application/json",
    "Content-Type" -> "application/json",
    "X-GovIssueIdentType" -> "CC",
    "X-IdentSerialNum" -> "2921380",
    "X-Channel" -> "Web",
    "X-CompanyId" -> "BDB",
    "X-IPAddr" -> "127.0.0.1",
    "X-Name" -> "Transversales",
    "X-RqUID" -> "61aa74d6-e313-4964-9e22-49733b1b54d7",
    "x-api-key" -> "uGTRR0b1R96HW9zyPb8hh3PhmiYufJyb4OS6WO1r"
  )

  params.method= post //metodo a utilizar
  params.requestName ="Create Customer Products" //nombre del request
  params.statusExpected= 200  //respuesta esperada
  params.scenarioName ="Crear un cliente en CRM" //nombre del escenario
  params.templateJson ="data/crearBodyCustomer.json" //llama al body de la Api
  val request = new BuildRequest(params)

  setUp(
    request.scn.inject(constantUsersPerSec(4).during(11)) // 4 usuarios durante 11 segundos

  ).protocols(request.httpProtocol) //se hace el llamado

}
