package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CMSimulation extends Simulation{

  val httpProtocol = http
    .baseUrl("https://helsenorge-perftest.azurefd.net")
    .disableCaching

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "TE"->"Trailers",
    "User-Agent"->"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0",
    "Connection"->"Connection: keep-alive",
    "Upgrade-Insecure-Requests"->"1")

  val scn = scenario("CMSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/CMS.csv").circular)

    .exec(http(requestName = "Sykdommer")
      .get("/sykdommer")
      .headers(headers_0)
      .check(status.is(expected = 200))
      .check(regex("Angst")))

    .exec(http(requestName = "Astma-og-allergi")
      .get("/sykdommer/astma-og-allergi/")
      .headers(headers_0)
      .check(status.is(expected = 200))
      .check(regex("<title>Astma og allergi</title>")))

    .exec(http(requestName = "Allergisjokk")
      .get("/sykdommer/astma-og-allergi/allergisjokk/")
      .headers(headers_0)
      .check(status.is(expected = 200))
      .check(regex("<title>Allergisk sjokk</title>")))



  val selectedProfile = System.getProperty("selectedProfile") match {
    case "profile1" => scn.inject(atOnceUsers(1))
    case "profile2" => scn.inject(rampUsersPerSec(1) to 5 during (30),constantUsersPerSec(5) during(600))
    case "profile3" => scn.inject(constantUsersPerSec(500) during(60))
    case "profile4" => scn.inject(rampConcurrentUsers(5) to(200) during(120))
    case "profile5" => scn.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))
    case "profile6" => scn.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10))
  }

  setUp(selectedProfile).protocols(httpProtocol)

}
