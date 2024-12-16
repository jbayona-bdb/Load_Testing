package utilities

import java.nio.charset.StandardCharsets

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.http.request.builder.HttpRequestBuilder

class BuildRequest(requestParams: RequestParams) {

  val env = new EnvironmentValues()
  val requestSigner = new RequestSignerAWS(requestParams)

  if(env.getenv("USE_LAMBDA_URL") == "true"){
    requestParams.baseUrl = requestSigner.url
    requestParams.pathUrl = ""
    requestParams.headers = Map()
    requestParams.method = requestSigner.method
    requestParams.templateJson = requestSigner.body
    requestParams.queryParams = Map()
  }

  def httpProtocol: HttpProtocolBuilder = http
    .baseUrl(requestParams.baseUrl)

  def httpRequest: HttpRequestBuilder = {
    var requestBuild = http(requestParams.requestName)
      .httpRequest(requestParams.method, requestParams.pathUrl)
      .check(bodyString.saveAs("responseBody"))
      .queryParamMap(requestParams.queryParams)
      .check(status.is(requestParams.statusExpected))
      .headers(requestParams.headers)

    if(requestParams.templateJson != ""){
      if(env.getenv("USE_LAMBDA_URL") == "true") {
        requestBuild = requestBuild.body(StringBody(s"""${requestParams.templateJson}"""))
        requestBuild = requestBuild.sign{(request, session) =>
          val bodyJson = new String(request.getBody.getBytes, StandardCharsets.UTF_8)
          val headerSigned = requestSigner.headers(bodyJson)
          for ((k, v) <- headerSigned) request.getHeaders.set(k, v)
          request
        }
      } else {
        requestBuild = requestBuild.body(ElFileBody(requestParams.templateJson)).asJson
      }
    }
    requestBuild
  }

  def scn: ScenarioBuilder = {
    var scenarioRequest = scenario(requestParams.scenarioName)
    for ( i <- requestParams.feederCsv.indices) {
      scenarioRequest = scenarioRequest.feed(csv(requestParams.feederCsv(i)).queue)
    }
    scenarioRequest = scenarioRequest.feed(Iterator.continually{
      requestParams.feederRandom
    })
    scenarioRequest = scenarioRequest.exec(httpRequest)
    .exec{ session => println(session("responseBody").as[String]); session}
    scenarioRequest
  }
}
