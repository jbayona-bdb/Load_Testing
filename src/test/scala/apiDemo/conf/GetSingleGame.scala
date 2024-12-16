package apiDemo

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetSingleGame extends Simulation {

  val httpconfig= http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  val scenario1 = scenario("consultar juego x ID")
    .exec(
      http("Tra juego x ID")
        .get("/videogame/5")                                                   //recurso del Api
        .check(status.is(200))
    )

  setUp(                                                                     //define de que forma se va hacer la simulación
    scenario1.inject(constantUsersPerSec(15).during(5)).protocols(httpconfig)                  //se define como se van a inyectar los usuaarios y configuración va a tomar
  )

}
