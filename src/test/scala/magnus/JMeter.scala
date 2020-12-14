package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class JMeter extends Simulation  {

  val httpProtocol = http
    .baseUrl("https://epj.qa.forskrivning.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")

  val headers_1 = Map(
    "Content-Type" -> "application/json",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
    "Update-Insecure-Requests" -> "1",
    "Set-Fetch-Site" -> "none",
    "Set-Fetch-User" -> "?1",
    "Set-Fetch-Mode" -> "navigate")

  val headers_2 = Map(
    "Content-Type" -> "application/x-www-form-urlencoded",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Update-Insecure-Requests" -> "1",
    "Sec-Fetch-Dest" -> "document",
    "Origin"->"https://epj.qa.forskrivning.no")

  val headers_3 = Map(
    "Content-Type" -> "application/x-www-form-urlencoded",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Update-Insecure-Requests" -> "1",
    "Sec-Fetch-Dest" -> "document",
    "Origin"->"https://epj.qa.forskrivning.no")




  val scn = scenario("JMeterSimulation")

    .exec(http(requestName = "request_1")
      .get(url = "/")
      .headers(headers_1)
      .check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken1")))


  .exec(session=>{
    println("___RequestVerificationToken 1:")
    println(session("requestVerificationToken1").as[String])
    session})

    .exec(http("request_2")
      .post("/")
      .headers(headers_2)
      .formParam("SelectedPortalId", "PatientPortal")
      .formParam("submitButton", "select")
      .formParam("IsSelectingInstallation", "False")
      .formParam("SelectedEnvironmentId", "")
      .formParam("__RequestVerificationToken", "${requestVerificationToken1}")
      .check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken2")))


    .exec(session=>{
      println("___RequestVerificationToken 2:")
      println(session("requestVerificationToken2").as[String])
      session})


    .exec(http("request_3")
      .post("/")
      .headers(headers_3)
      .formParam("SelectedPortalId", "PatientPortal")
      .formParam("submitButton", "select")
      .formParam("IsSelectingInstallation", "True")
      .formParam("SelectedEnvironmentId", "ffad58fd-a86f-4122-8541-42a82f719fe8#Glittertind testlegekontor (Fastlege)")
      .formParam("__RequestVerificationToken", "${requestVerificationToken2}"))









  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
