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

  val headers_4 = Map("Accept" -> "application/json, text/plain, */*")

  val headers_5 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Origin" -> "https://epj.qa.forskrivning.no",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_6 = Map(
    "Access-Control-Request-Headers" -> "authorization,x-requested-with",
    "Access-Control-Request-Method" -> "POST",
    "Origin" -> "https://client.qa.forskrivning.no")

  val headers_7 = Map(
    "Access-Control-Request-Headers" -> "access-control-allow-origin,authorization,cache-control,content-type,expires,pragma",
    "Access-Control-Request-Method" -> "GET",
    "Origin" -> "https://client.qa.forskrivning.no")

  val uri1 = "https://client.qa.forskrivning.no/version.json"
  val uri2 = "https://epj.qa.forskrivning.no"
  val uri3 = "https://notify.bugsnag.com"
  val uri5 = "https://sessions.bugsnag.com"

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

    .exec(http("LoadClientAsync")
      .post(uri2 + "/Patient/LoadClientAsync")
      .headers(headers_5)
      .formParam("OnBehalfOf", "")
      .formParam("ShowAllergies", "true")
      .formParam("SelectedTicket", "54688c89-e7d2-4662-a054-f24daabafa50")
      .formParam("SelectedEnvironment", "Glittertind testlegekontor (Fastlege)")
      .formParam("ApiEndpoint", "https://server.qa.forskrivning.no")
      .formParam("HelseIdClientId", "ffad58fd-a86f-4122-8541-42a82f719fe8")
      .formParam("submitButton", "open")
      .formParam("__RequestVerificationToken", "${requestVerificationToken2}")
      .formParam("ShowAllergies", "false"))







  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
