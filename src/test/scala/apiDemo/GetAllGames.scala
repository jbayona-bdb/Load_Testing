package apiDemo

import io.gatling.core.Predef._ //librerias de gatling
import io.gatling.http.Predef._

class GetAllGames extends Simulation { //herencia de clase de gatling

  val httpConfig = http.baseUrl("https://videogamedb.uk/api") //crear un objeto en escala, el cual se iguala a la libreria hhtp con el metodo base.Url
    .acceptHeader("application/json") //header ocultos que aceptan las apis

  val scenario1 = scenario("Test obtener todos los juegos de base de datos") //se crea el escenario con el comando scenario
    .exec(                                                                   //comando de ejecución
      http("GET games")                                                      //descripción del servicio a usar
        .get("/videogame")                                                   //recurso del Api
        .check(status.is(200))                                               //Status esperado segun la respuesta del Api o Test Case
    )                                                                        //cerrar bloque de ejecución

  setUp(                                                                     //define de que forma se va hacer la simulación
    scenario1.inject(atOnceUsers(15)).protocols(httpConfig)                  //se define como se van a inyectar los usuaarios y configuración va a tomar
  )

}
