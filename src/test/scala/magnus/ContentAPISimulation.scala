package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ContentAPISimulation extends Simulation{

  val httpProtocol = http
    .inferHtmlResources()
    .acceptHeader("application/json")

  val headers_0 = Map("Content-Type"->"application/json")

  val scn = scenario("ContentAPISimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .exec(http("request_access_token")
      .post("https://eksternapi-hn-mas-01.int-hn.nhn.no/sts/Token")
      .headers(headers_0)
      .queryParam("grant_type", "client_credentials")
      .queryParam("client_id", "17CE8063-ABDC-4266-A524-DC97E0A305EA")
      .queryParam("client_secret", "UeM3kEffKLVbgOTkMvUIHHXX0riHZ7xekYghu7F5v7KrYNfoNXquOXGck8d1PEAIsoz840q_hBDfB0Z4JjWzueySre-POLZ3dFeooMZZhuywD44x_RXcxI-DP4Xmpb4cTt6s4w")
      .check(regex("access_token\":\"(.*?)\"").saveAs("access_token")))

    .exec(session=>{
      println("access_token:")
      println(session("access_token").as[String])
      session})

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}