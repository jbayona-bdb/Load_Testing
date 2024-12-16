package apiDemo

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CreateGame extends Simulation {

  val httpConfig = http.baseUrl("https://videogamedb.uk/api") //crear un objeto en escala, el cual se iguala a la libreria hhtp con el metodo base.Url
    .acceptHeader("application/json") //header ocultos que aceptan las apis
    .contentTypeHeader("application/json")

  val scenarioCreateGame = scenario("crear video Juego") //se crea el escenario con el comando scenario
    .exec(                                                                   //comando de ejecución
      http("Solicitud de token")                                                      //descripción del servicio a usar
        .post("/authenticate") //recurso del Api
        .body(StringBody("{\n \"password\": \"admin\", \n \"username\": \"admin\"\n}"))
        .check(jsonPath("$.token").saveAs("token"))
        .check(status.is(200))                                               //Status esperado segun la respuesta del Api o Test Case
    )
    .exec(                                                                   //comando de ejecución
      http("Crear el juego")                                                      //descripción del servicio a usar
        .post("/videogame") //recurso del Api
        .header("Autorizathion","Bearer #{token}")
        .body(StringBody("{\n \"password\": \"admin\", \n \"username\": \"admin\"\n}"))
        .check(status.is(200))
    )

  setUp(
    scenarioCreateGame.inject(rampUsers(10).during(5)).protocols(httpConfig)
  )

}
