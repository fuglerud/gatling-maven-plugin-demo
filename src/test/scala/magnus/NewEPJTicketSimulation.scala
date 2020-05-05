package magnus

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class NewEPJTicketSimulation extends Simulation{

  val httpProtocol = http
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .doNotTrackHeader("1")

  val headers_0 = Map(
    "Content-Type"->"application/json;charset=UTF-8",
    "Authorization"->"Bearer ${access_token}")


  val scn = scenario("NewEPJTicketSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/01_EPJTicketTestData.csv").circular)
    .feed(csv("magnus/02_EPJTicketTestData.csv").circular)

    .exec(http("request_access_token")
      .post("https://helseid-sts.test.nhn.no/connect/token")
      .header("Authorization", "${authorizationHeader}")
      .formParam("grant_type", "${grant_type}")
      .formParam("client_id", "${client_id}")
      .formParam("client_secret", "${client_secret}")
      .formParam("audience", "${audience}")
      .check(regex("access_token\":\"(.*?)\"").saveAs("access_token")))

    .exec(session=>{
      println("access_token:")
      println(session("access_token").as[String])
      session})

    .exec(http("request_epj_ticket")
     //.post("https://api.qa2.kjernejournal-test.no:8000/v1/helseindikator/")
      .post("https://api.st1.kjernejournal-test.no:8000/v1/helseindikator/")
      //https://api.st1.kjernejournal-test.no:8000/v1/helseindikator/
      //https://api.st2.kjernejournal-test.no:8000/v1/helseindikator/
      //https://api.st3.kjernejournal-test.no:8000/v1/helseindikator/
      .header("Authorization", "${authorizationHeader}")
      .headers(headers_0)
      .body(ElFileBody("magnus/epj_ticket_request.json"))
      .check(regex("ticket\" : \"(.*?)\"").saveAs("epj_ticket")))

    .exec(session=>{
      println("epj_ticket:")
      println(session("epj_ticket").as[String])
      session})

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}