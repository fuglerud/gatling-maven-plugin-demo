package computerdatabase

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class FHISimulation extends Simulation {
  val httpProtocol = http
    .baseUrl("https://minhelse-koronavarsling.int-hn.nhn.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

  val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1",
    "Host" -> "minhelse-koronavarsling.int-hn.nhn.no")
  val scn = scenario("RecordedSimulation")

    .exec(http("request_0")
      .get("/innsynsmittestopp/?pnr=20039409462&tlfnr=90589302")
      .headers(headers_0)
      .check(regex("\"__AnonymousHash__\": \"(.*?)\"").saveAs("AnonymousHash"))
      .check(regex("\"__AuthenticatedHash__\": \"(.*?)\"").saveAs("AuthenticatedHash"))
      .check(regex("\"__TimeStamp__\": \"(.*?)\"").saveAs("TimeStamp"))
      .check(regex("\"__TjenesteType__\": \"(.*?)\"").saveAs("TjenesteType"))
      .check(regex("\"__HendelseLoggType__\": \"(.*?)\"").saveAs("HendelseLoggType")))

    .exec(session=>{
      println("AuthenticatedHash:")
      println(session("AuthenticatedHash").as[String])
      session})

   /* .exec(http("request_0")
      .get("/innsynsmittestopp/?pnr=20039409462&tlfnr=90589302")
      .headers(headers_0)
      .check(regex("\"__AnonymousHash__\": \"(.*?)\"").saveAs("AnonymousHash"))
      .check(regex("\"__AuthenticatedHash__\": \"(.*?)\"").saveAs("AuthenticatedHash"))
      .check(regex("\"__TimeStamp__\": \"(.*?)\"").saveAs("TimeStamp"))
      .check(regex("\"__TjenesteType__\": \"(.*?)\"").saveAs("TjenesteType"))
      .check(regex("\"__HendelseLoggType__\": \"(.*?)\"").saveAs("HendelseLoggType")))*/

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
