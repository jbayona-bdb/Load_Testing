package apiDemo

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class GetGameWithFeeder extends Simulation{

  val httpconfig= http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  val archivo= csv("data/datosJuego.csv").circular

  val escenario1 = scenario("Obtener un jueco el ID del juego")
    .feed(archivo)
    .exec(
      http("obtener juego con ID #{gameid}")
        .get("/videogame/#{gameid}")
        .check(status.is(200))
        .check(bodyString.saveAs("response"))
    )
    .exec{
      session => println(session("response").as[String]); session

    }

setUp(
  escenario1.inject(atOnceUsers(23)).protocols(httpconfig)

)


}
