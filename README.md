# bbog-apis-of-load-testing

**Comandos:**

- Para ejecutar los test: ./ja
- Para instalar dependencias: ./mvnw install
- Para limpiar el proyecto: ./mvnw clean

**NOTA:** se ejecutara el test que este especificado en el [pom.xml](https://github.com/bancodebogota/bbog-pse-load-testing/blob/master/pom.xml) en la seccion del plugin gatling-maven-plugin - simulationClass

**Escenarios de ejecucion:**

```
setUp(buildRequest.scn.inject(
    nothingFor(4.seconds), // 1
    atOnceUsers(10), // 2
    rampUsers(10).during(5.seconds), // 3
    constantUsersPerSec(50).during(6.seconds) // 4
    constantUsersPerSec(20).during(10.seconds).randomized, // 5
    rampUsersPerSec(0).to(8).during(60.seconds) // 6
    rampUsersPerSec(10).to(20).during(10.minutes).randomized, // 7
    heavisideUsers(1000).during(20.seconds) // 8
  ).protocols(buildRequest.httpProtocol)
)

setUp(buildRequest.scn.inject(
    constantConcurrentUsers(10).during(10.seconds), // 1
    rampConcurrentUsers(10).to(50).during(10.seconds) // 2
  ).protocols(buildRequest.httpProtocol)
)
  
setUp(buildRequest.scn.inject(atOnceUsers(100)).protocols(buildRequest.httpProtocol))
```
