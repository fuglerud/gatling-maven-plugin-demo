package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SysVakSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://kubtest.kj.nhn.no/")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("application/json, text/javascript, */*; q=0.01")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0")

  val headers_0 = Map(
    "Connection"->"keep-alive",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map("X-Requested-With" -> "XMLHttpRequest")

  val headers_4 = Map(
    "Content-Type" -> "application/json",
    "Origin" -> "https://qa1.kjernejournal-test.no",
    "X-Kj-CsrfToken" -> "${csrfToken}",
    "X-Requested-With" -> "XMLHttpRequest")

  val headers_5 = Map("X-Requested-With" -> "XMLHttpRequest")

  val headers_6 = Map(
    "X-Kj-CsrfToken" -> "${csrfToken}",
    "Host"->"qa1.kjernejournal-test.no",
    "Connection"->"keep-alive",
    "Content-Length"->"2",
    "Accept"->"*/*",
    "Sec-Fetch-Dest"->"empty",
    "X-Requested-With"->"XMLHttpRequest",
    "User-Agent"->"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36",
    "Content-Type"->"application/json",
    "Origin"->"https://qa1.kjernejournal-test.no",
    "Sec-Fetch-Site"->"same-origin",
    "Sec-Fetch-Mode"->"cors",
    "Referer"->"https://qa1.kjernejournal-test.no/hpp-webapp/kjernejournal.html",
    "Accept-Encoding"->"gzip, deflate, br",
    "Accept-Language"->"en-US,en;q=0.9")

  val headers_7 = Map(
    "Content-Type" -> "application/json",
    "Origin" -> "https://qa1.kjernejournal-test.no",
    "X-Kj-CsrfToken" -> "${csrfToken}",
    "X-Requested-With" -> "XMLHttpRequest")

  val headers_11 = Map("X-Requested-With" -> "XMLHttpRequest")



  val scn = scenario("SysVakSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)


    .exec(http("hpp-webapp")
      .get("https://kubtest.kj.nhn.no/innlogging-webapp/index.html#/Innlogging")
      .headers(headers_0))

    .exec(http("Mockinnlogging")
      .get("/innlogging-webapp/mock/Innlogging/login?nin=05085600143&resource_url=https%3A%2F%2Fkubtest.kj.nhn.no%2Fhpp-webapp")
      .headers(headers_0)
      .check(regex("<input type=\"hidden\" name=\"jwt\" value=\"(.*?)\"").saveAs("jwt")))

    .exec(http("hpp-webapp")
      .get("/hpp-webapp")
      .headers(headers_0)
      .check(jsonPath("$.csrfToken").saveAs("csrfToken")))

    /*.exec(http("Personsok")
      .post("/hpp-webapp/api/personsok/sok")
      .headers(headers_0)
      .body(ElFileBody("magnus/sokPasient_request.json")))*/





  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(constantUsersPerSec(1) during(60))).protocols(httpProtocol)
}