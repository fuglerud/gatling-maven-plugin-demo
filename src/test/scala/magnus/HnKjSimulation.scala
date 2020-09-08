package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class HnKjSimulation extends Simulation{

  val httpProtocol = http
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("application/json, text/javascript, */*; q=0.01")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0")
    .disableCaching

  val hn = Map(
    "Accept"->"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Sec-Fetch-Mode"->"navigate",
    "Sec-Fetch-Site"->"none",
    "Upgrade-Insecure-Requests"->"1")


  val scn = scenario("HnKjSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/HnTestdata.csv").random)

    .exec(http("login")
      .get("https://tjenester.hn.test.nhn.no/?pnr=${pnr}")
      .headers(hn)
      .check(status.is(expected = 200))
      .check(regex("\"__Authorized__\": (.*?),").saveAs("Authorized"))
      .check(regex("\"__AnonymousHash__\": \"(.*?)\"").saveAs("AnonymousHash"))
      .check(regex("\"__AuthenticatedHash__\": \"(.*?)\"").saveAs("AuthenticatedHash"))
      .check(regex("\"__TimeStamp__\": \"(.*?)\"").saveAs("TimeStamp"))
      .check(regex("\"__TjenesteType__\": \"(.*?)\"").saveAs("TjenesteType"))
      .check(regex("\"__HendelseLoggType__\": \"(.*?)\"").saveAs("HendelseLoggType")))


    .exec(http(requestName = "kj_donokort")
      .get("https://tjenester.hn.test.nhn.no/donorkort")
      .headers(hn)
      .check(status.is(expected = 200)))

  setUp(scn.inject(constantUsersPerSec(1) during(10))).protocols(httpProtocol)

}