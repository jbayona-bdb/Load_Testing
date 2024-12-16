package privateApi

import io.gatling.core.Predef._
import utilities.{BuildRequest, EnvironmentValues, RequestParams}
import objects.MethodsHttp._

class DeleteRedisAgregacion extends Simulation {

  val env = new EnvironmentValues() //llamar variables de entorno
  val params = new RequestParams() // objeto de parametros

  params.baseUrl = "https://api-redis.labdigbdbstgredis.com" //llamar parametros URL base
  params.pathUrl = "/V4/Utilities/redis-cache/ods/#{CustIdentType}:#{CustIdentNum}:Ods" // Endpoint o recurso
  params.headers = Map( //se definen los headers de Api
    "accept" -> "application/json",
    "X-Name" -> "Digitalizacion",
    "X-RqUID" -> "06a0a2cc-8224-4513-8e22-6ab76184ae7f",
    "x-api-key" -> "uGTRR0b1R96HW9zyPb8hh3PhmiYufJyb4OS6WO1r"
  )
  params.method = delete //metodo a utilizar
  params.requestName = "deleteRedis" //nombre del request
  params.statusExpected = 200 //respuesta esperada
  params.scenarioName = "Borra un cliente en cache" //nombre del escenario
  params.feederCsv = Array("data/DataRedis.csv")


  val request = new BuildRequest(params) //pasar el params creado a la clase build request, para que se firme la petición como la espera la lambda

  setUp(
    request.scn.inject(atOnceUsers(1))
  ).protocols(request.httpProtocol)

}