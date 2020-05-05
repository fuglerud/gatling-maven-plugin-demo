package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CMSSimulation extends Simulation{

  val httpProtocol = http
    // .baseUrl("https://app-hn-cms-dev.azurewebsites.net")
   //.baseUrl("http://app-hn-cms-dev-perftest.azurewebsites.net")
   .baseUrl("http://helsenorge-perftest.azureedge.net/")
   //.baseUrl("https://hn-varnish-test.azurewebsites.net")
   //.baseUrl("https://epi-helsenorge-dev.int-hn.nhn.no")
   //.baseUrl("http://hn-varnish-test.azurewebsites.net")

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "TE"->"Trailers",
    "User-Agent"->"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0",
    "Connection"->"Connection: keep-alive",
    "Upgrade-Insecure-Requests"->"1",
  //  "Host" ->"app-hn-cms-dev.azurewebsites.net"
  )

  val headers_1 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "TE"->"Trailers",
    "User-Agent"->"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0",
    "Connection"->"Connection: keep-alive",
    "Upgrade-Insecure-Requests"->"1",
   // "Referer"->"https://app-hn-cms-dev.azurewebsites.net/",
   // "Host" ->"app-hn-cms-dev.azurewebsites.net"
  )

  val headers_2 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "TE"->"Trailers",
    "User-Agent"->"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0",
    "Connection"->"Connection: keep-alive",
    "Upgrade-Insecure-Requests"->"1",
   // "Referer"->"https://app-hn-cms-dev.azurewebsites.net/sykdommer/",
  //  "Host" ->"app-hn-cms-dev.azurewebsites.net"
  )

  val scn = scenario("CMSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/CMS.csv").circular)

    .exec(http(requestName = "baseURL")
      .get("/")
      .headers(headers_0)
      .check(status.is(expected = 200))
      .check(regex("<title>Helsenorge</title>")))

/*

    .exec(http(requestName = "sykdommer")
      .get("/sykdommer/")
      .headers(headers_1)
      .check(status.is(expected = 200))
      .check(regex("<a href=\"/sykdommer/hormoner/\" class=\"bg-neutral50 has-hover-bg\">Hormoner</a>")))

    .exec(http(requestName = "spesifikk_sykdom")
      .get("/sykdommer/${sokeord}/")
      .headers(headers_2)
      .check(status.is(expected = 200))
      .check(regex("${sokeord}")))

    .exec(http(requestName = "subsok")
      .get("/sykdommer/${sokeord}/${subsokeord}/")
      .headers(headers_2)
      .check(status.is(expected = 200))
      .check(regex("${subsokeord}")))


*/
  //setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(rampUsersPerSec(1) to 5 during (30),constantUsersPerSec(5) during(600)))
  //setUp(scn.inject(constantUsersPerSec(60) during(30))).protocols(httpProtocol)
  setUp(scn.inject(rampConcurrentUsers(5) to(200) during(120)).protocols(httpProtocol))
  //setUp(scn.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))).protocols(httpProtocol)

  //setUp(scn.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10)).protocols(httpProtocol))

}
