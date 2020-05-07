package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ClosedModelCMS extends Simulation{

  val httpProtocol = http
   // .baseUrl("http://helsenorge-perftest.azureedge.net")
    .baseUrl("https://helsenorge-perftest.azureedge.net")
    .disableCaching

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "TE"->"Trailers",
    "User-Agent"->"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0",
    "Connection"->"Connection: keep-alive",
    "Upgrade-Insecure-Requests"->"1")

  val scn = scenario("ClosedModelCMS")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .during(600) {

    exec(http(requestName = "Sykdommer")
      .get("/sykdommer")
      .headers(headers_0)
      .check(status.is(expected = 200))
      .check(regex("Angst")))

        .pause(3)
    }

  setUp(scn. inject(rampUsers(500) during(30)).protocols(httpProtocol))
}
