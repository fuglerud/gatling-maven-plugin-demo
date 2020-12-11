package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class idportenSimulation extends Simulation{

  val httpProtocol = http
    .baseUrl("https://qa-vl-op.ss2np.fhi.no/connect/authorize?response_type=code&client_id=smittestopp&state=bExENGtLTXhrbzBtX3JBLllmdGJaa25qbG5UWVl0cHo2YnhwRS5tRnhGZC5s&redirect_uri=no.fhi.smittestopp-exposure-notification%3A%2Foauth2redirect&scope=openid+smittestop&code_challenge=yQaWTtcwbH_K6f53l01WTuB9JeyCHqNhI5NbBo2r9zA&code_challenge_method=S256&nonce=bExENGtLTXhrbzBtX3JBLllmdGJaa25qbG5UWVl0cHo2YnhwRS5tRnhGZC5s&prompt=login")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")

  val header_get = Map(
    "Content-Type" -> "application/json",
    "Content-Type" -> "text/html",
    "authorization_mobile" -> "24jRFidazK",
    "User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:48.0) Gecko/20100101 Firefox/48.0",
    "Accept" -> "*/*",
    "Cache-Control"->"no-cache, no-store",
    "Accept-Language" -> "fr,en-US;q=0.7,en;q=",
    "Accept-Encoding" -> "gzip, deflate",
    "Connection" -> "close")

  val scn = scenario("authorizeSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .exec(http("authorize")
      .get("/")
      .check(status.is(200)))

  //setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)


  //setUp(scn.inject(rampUsersPerSec(10) to 208 during (10 minutes),constantUsersPerSec(208) during(5 minutes)).protocols(httpProtocol))
  setUp(scn.inject(rampUsersPerSec(1) to 10 during (2 minutes),constantUsersPerSec(10) during(5 minutes)).protocols(httpProtocol))
  //setUp(scn.inject(rampUsersPerSec(10) to 833 during (10 minutes),constantUsersPerSec(833) during(5 minutes)).protocols(httpProtocol))
  //setUp(scn.inject(rampUsersPerSec(1) to 10 during (10 minutes),constantUsersPerSec(10) during(5 minutes)).protocols(httpProtocol))

  //scn.inject(rampUsersPerSec(10) to 208 during (10 minutes),constantUsersPerSec(208) during(5 minutes))

}
