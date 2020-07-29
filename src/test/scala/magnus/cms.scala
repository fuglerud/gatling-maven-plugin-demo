package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class cms extends Simulation  {

  val httpProtocol = http
    .baseUrl("https://eksternapi-hn-mas-01.int-hn.nhn.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")

  val headers_token = Map(
    "Content-Type" -> "application/json")

  val headers_API = Map(
    "Content-Type" -> "application/xml",
    "Authorization" -> "Bearer ${bearer_token}")

  val scn = scenario("ContentAPI")

    .exec(http(requestName = "sts-token")
      .post(url = "/sts/Token")
      .headers(headers_token)
      .body(ElFileBody("magnus/sts-token.json"))
      .check(regex("\"access_token\":\"(.*?)\"").saveAs("bearer_token")))

    .exec {session =>
          println(session("bearer_token").as[String])
          session}

    .exec(http("Sykdomsinfo")
      .get("/contentapi/v1/Sykdomsinfo")
      .headers(headers_API)
      .queryParam("Sykdomtilstand","35489007")
      .queryParam("Maalgruppe","133936004")
      .check(status.is(expected = 200)))


  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(constantUsersPerSec(rate=1) during(3600))).protocols(httpProtocol)
}