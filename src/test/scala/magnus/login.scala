package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class login extends Simulation {

  val httpProtocol = http
    .baseUrl("https://epj.test1.forskrivning.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0")

  val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map(
    "Origin" -> "https://epj.test1.forskrivning.no",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_4 = Map("Accept" -> "*/*")

  val headers_5 = Map(
    "Origin" -> "https://hid-testidp.azurewebsites.net",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_6 = Map(
    "Origin" -> "null",
    "Upgrade-Insecure-Requests" -> "1")

  val uri1 = "https://helseid-sts.test.nhn.no"
  val uri2 = "https://ajax.aspnetcdn.com/ajax/bootstrap/3.3.7/css/bootstrap.min.css.map"
  val uri4 = "https://hid-testidp.azurewebsites.net/Account/Login"

  val scn = scenario("LoginSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/SFM_Testdata.csv").circular)
    .feed(csv("magnus/SFM_Headere.csv").circular)

    //BaseURL
    .exec(http("request_BaseURL")
      .get("/")
      .headers(headers_0)
      .check(regex("SFM EPJ Emulator").exists)
      .check(status.is(expected = 200))
      .check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("RequestVerificationToken1")))

    .exec(session=>{
      println("RequestVerificationToken1:")
      println(session("RequestVerificationToken1").as[String])
      session})

    //Select
    .exec(http("request_Select")
      .post("/")
      .headers(headers_1)
      .formParam("SelectedPortal", "0")
      .formParam("submitButton", "select")
      .formParam("IsSelectingInstallation", "False")
      .formParam("IsSelectingPatient", "False")
      .formParam("SelectedPortalType", "")
      .formParam("__RequestVerificationToken", "${RequestVerificationToken1}")
      .check(status.is(expected = 200))
      .check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("RequestVerificationToken2")))


    .exec(session=>{
      println("RequestVerificationToken2:")
      println(session("RequestVerificationToken2").as[String])
      session})


    //Fastlege
    .exec(http("request_Fastlege")
      .post("/")
      .headers(headers_1)
      .formParam("SelectedClientId", "${client_id}")
      .formParam("submitButton", "select")
      .formParam("IsSelectingInstallation", "True")
      .formParam("IsSelectingPatient", "True")
      .formParam("SelectedPortalType", "0")
      .formParam("__RequestVerificationToken", "${RequestVerificationToken2}")
      .check(status.is(expected = 200))
      .check(regex("authzId%(.*?)&amp").saveAs("authzId")))

    .exec(session=>{
      println("authzId:")
      println(session("authzId").as[String])
      session})

    //TestIDP
    .exec(http("request_TestIdp")
      .get(uri1 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%${authzId}")
      .headers(headers_0)
      .resources(http("request_TestIdp_resource")
        .get(uri2)
        .headers(headers_4))
      .check(status.is(expected = 200))
      .check(regex("HPR-testregistry").exists)
      .check(regex("nonce%(.*?)%26").saveAs("nonce"))
      .check(regex("state%(.*?)%26").saveAs("state"))
      .check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("RequestVerificationToken3"))
      .check(regex("returnurl=%(.*?)0.0").saveAs("returnurl"))
    )

    .exec(session=>{
      println("nonce:")
      println(session("nonce").as[String])
      session})

    .exec(session=>{
      println("state:")
      println(session("state").as[String])
      session})

    .exec(session=>{
      println("RequestVerificationToken3:")
      println(session("RequestVerificationToken3").as[String])
      session})

    .exec(session=>{
      println("returnurl:")
      println(session("returnurl").as[String])
      session})


    //Logg inn inn med fnr
    .exec(http("request_Logg_inn_med_fnr")
      .post(uri4 + "?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dsts%26redirect_uri%3Dhttps%253A%252F%252Fhelseid-sts.test.nhn.no%252Fsignin-testidp-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520identity%252Fpid%26response_mode%3Dform_post%26nonce%${nonce}%26state%${state}%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D5.3.0.0")
      .headers(headers_5)
      .formParam("Pid", "${pid}")
      .formParam("SecurityLevel", "4")
      .formParam("HprNumber", "")
      .formParam("__RequestVerificationToken", "${RequestVerificationToken3}")
      .check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
      .check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope"))
      .check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
      .check(regex("name='id_token' value='(.*?)' />").saveAs("id_token"))
      .check(regex("name='state' value='(.*?)' />").saveAs("state"))
      .resources(http("request_Logg_inn_med_fnr_resource1")
        .post(uri1 + "/signin-testidp-oidc")
        .headers(headers_6)
        .formParam("code", "${code}")
        .formParam("id_token", "${id_token}")
        .formParam("scope", "${scope}")
        .formParam("state", "${state}")
        .formParam("session_state", "${session_state}")
        .check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
        .check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope"))
        .check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
        .check(regex("name='state' value='(.*?)' />").saveAs("state")),
        http("request_Logg_inn_med_fnr_resource2")
          .post("/signin-oidc")
          .headers(headers_6)
          .formParam("code", "${code}")
          .formParam("scope", "${scope}")
          .formParam("state", "${state}").disableUrlEncoding
          .formParam("session_state", "${session_state}")))



  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}