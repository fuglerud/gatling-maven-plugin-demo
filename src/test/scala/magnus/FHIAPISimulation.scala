package magnus


import io.gatling.core.Predef._
import io.gatling.http.Predef._


class FHISimulation extends Simulation{

  val httpProtocol = http
    //.baseUrl("https://minhelse-koronavarsling.int-hn.nhn.no")
    //.baseUrl("https://tjenester-hn-mas-01.int-hn.nhn.no")
    //.baseUrl("https://minhelse-koronavarsling.hn.test.nhn.no")
    .baseUrl("https://tjenester.hn.test.nhn.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Upgrade-Insecure-Requests"->"1")

  val headers_1 = Map(
    "content-type" -> "application/json",
    "hnanonymoushash" -> "4gbBS0gzP_F7Fa9Bg_dEzYqig0QMnYaP52gDbfFDEIE1",
    "hnauthenticatedhash" -> "lVV3WANbaTHTR5hkXYkwXKLLgEaCX0_75jOym_nqemA1",
    "hntimestamp" -> "2020-06-19T06:08:16.9874867Z",
    "hntjeneste" -> "78",
    "x-hn-hendelselogg" -> "InnsynSmittestopp")


  val scn = scenario("FhiSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/Fhi.csv").circular)

    .exec(http("Base")
      .get("https://minhelse.hn.test.nhn.no/innsynsmittestopp/?pnr=${pnr}&tlfnr=${tlfnr}")
      .headers(headers_0))


    .exec(http("Innsynsmittestopp")
      .get("/innsynsmittestopp/")
      .headers(headers_0))


    .exec(http("Lokasjonslogg")
      .get("/proxy/InnsynSmittestopp/api/v1/Lokasjonslogg?date=${date}&timeSlot=${timeSlot}")
      .check(jsonPath("$..longitude").ofType[Any].saveAs("ref1"))
      .headers(headers_0))

  /*  .exec(session=>{
      println("Korrelasjonsparameter Lokasjonslogg:")
      println(session("ref1").as[String])
      session})
    */

    .exec(http("Varsling")
      .get("/proxy/InnsynSmittestopp/api/v1/varsling")
      .check(jsonPath("$..hendelse").ofType[Any].saveAs("ref2"))
      .headers(headers_0))
/*
    .exec(session=>{
      println("Korrelasjonsparameter Varsling:")
      println(session("ref2").as[String])
      session})
    */

    .exec(http("LoggOverBruk")
      .get("/proxy/InnsynSmittestopp/api/v1/LoggOverBruk")
      .check(jsonPath("$..navn").ofType[Any].saveAs("ref3"))
      .headers(headers_0))
/*
    .exec(session=>{
      println("Korrelasjonsparameter LoggOverBruk:")
      println(session("ref3").as[String])
      session})
      */



  //setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(rampUsersPerSec(1) to 5 during (30),constantUsersPerSec(5) during(60))).protocols(httpProtocol)
  setUp(scn.inject(constantUsersPerSec(1) during(120))).protocols(httpProtocol)
  //setUp(scn.inject(rampConcurrentUsers(5) to(200) during(120))).protocols(httpProtocol)
  //setUp(scn.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))).protocols(httpProtocol)
  //setUp(scn.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10))).protocols(httpProtocol)
}