package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ContentAPISimulation extends Simulation{

  val httpProtocol = http
    .inferHtmlResources()
    .acceptHeader("application/json")

  val headers_0 = Map(
    "Content-Type"->"application/json")

  val headers_1 = Map(
    "Content-Type" -> "application/xml",
    "Authorization" -> "Bearer ${access_token}")

  val scn = scenario("ContentAPISimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/ContentAPI.csv").circular)

    .exec(http("request_access_token")
      .post("https://eksternapi-hn-mas-01.int-hn.nhn.no/sts/Token")
      .headers(headers_0)
        .body(ElFileBody("magnus/ContentAPITestData.json"))
      .check(regex("access_token\":\"(.*?)\"").saveAs("access_token")))

    .exec(http("Sykdomsinfo")
      .get("https://eksternapi-hn-mas-01.int-hn.nhn.no/contentapi/v1/Sykdomsinfo")
      .headers(headers_1)
      .queryParam("Sykdomtilstand","${sykdomtilstand}")
      .queryParam("Maalgruppe","${maalgruppe}")
      .check(status.is(expected = 200)))


  val selectedProfile = System.getProperty("selectedProfile") match {
    case "profile1" => scn.inject(atOnceUsers(1))
    case "profile2" => scn.inject(rampUsersPerSec(1) to 5 during (30),constantUsersPerSec(5) during(600))
    case "profile3" => scn.inject(constantUsersPerSec(500) during(60))
    case "profile4" => scn.inject(rampConcurrentUsers(5) to(200) during(120))
    case "profile5" => scn.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))
    case "profile6" => scn.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10))

  }

  setUp(selectedProfile)
  }

