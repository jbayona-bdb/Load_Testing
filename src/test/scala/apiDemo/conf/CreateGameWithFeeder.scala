package apiDemo

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CreateGameWithFeeder extends Simulation {

  val httpConfig = http.baseUrl("https://videogamedb.uk/api") //se define el protocolo
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val archivo= csv("data/crearJuego.csv").circular

  val scenario1 = scenario("crear juego con difente ID")
    .feed(archivo)
    .exec(
      http("Solicitar token")
      .post("/authenticate")
      .body(StringBody("{\n \"password\": \"admin\", \n \"username\": \"admin\"\n}"))
        .check(jsonPath("$.token").saveAs("token"))
        .check(status.is(200))
    )
    .exec( //bloque de ejecución

      http("crear juego #{name}")
        .post("/videogame")
        .header("Authorization","Bearer #{token}")
        .body(ElFileBody("data/body.json"))
        .check(status.is(200))
        .check(bodyString.saveAs("response")) //imprimir respuesta en pantalla
    )

    .exec{
      session => println(session("response").as[String]); session

    }


  setUp(
    scenario1.inject(rampUsers(10).during(5)).protocols(httpConfig)
  )

}
