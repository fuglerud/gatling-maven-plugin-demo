package magnus

//import config.Environment.smittestopp_base_url
import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.concurrent.duration.DurationInt


class SmitteStopp_ALL extends Simulation {


  val httpProtocol = http
    .baseUrl("https://qa-be-op.ss2np.fhi.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")

  val header_get = Map(
    "Content-Type" -> "application/json",
    "Content-Type" -> "text/html",
    //"authorization_mobile" -> "24jRFidazK",
    "authorization_mobile" -> "5e7VejU56Ea9CJ2XKCU5Wn8BxFmEzQPB",
    "User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:48.0) Gecko/20100101 Firefox/48.0",
    "Accept" -> "*/*",
    "Cache-Control"->"no-cache, no-store",
    "Accept-Language" -> "fr,en-US;q=0.7,en;q=",
    "Accept-Encoding" -> "gzip, deflate",
    "Connection" -> "close"
  )

  val header_post = Map(
    "Accept" -> "application/json; text/plain; application/zip",
    //"Authorization_Mobile" -> "24jRFidazK",
    "Authorization_Mobile" -> "5e7VejU56Ea9CJ2XKCU5Wn8BxFmEzQPB",
    "Manufacturer" -> "Apple",
    "OSVersion" -> "14.2",
    "OS" -> "IOS",
    "Host" -> "test-be.ss2np.fhi.no",
    // "Host" -> "qa-be-op.ss2np.fhi.no",
    "Authorization" -> "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjU1MkUwMjJDRTU4M0I4ODRDNTUyQzZDNUYyNEZFQTFBODc0Rjk0MkMiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJWUzRDTE9XRHVJVEZVc2JGOGtfcUdvZFBsQ3cifQ.eyJuYmYiOjE2MDYzMTUxMDYsImV4cCI6MTYwNjMxODcwNiwiaXNzIjoiaHR0cHM6Ly9kZXYtc21pdHRlc3RvcHAtdmVyaWZpY2F0aW9uLmF6dXJld2Vic2l0ZXMubmV0IiwiYXVkIjoiaHR0cHM6Ly9kZXYtc21pdHRlc3RvcHAtdmVyaWZpY2F0aW9uLmF6dXJld2Vic2l0ZXMubmV0L3Jlc291cmNlcyIsImNsaWVudF9pZCI6InNtaXR0ZXN0b3BwIiwic3ViIjoiM2IyM2IzZjAtNWE5Zi00NDdiLWIwN2QtM2IwMmZiN2U4ZjRhIiwiYXV0aF90aW1lIjoxNjA2MzE1MTA1LCJpZHAiOiJpZHBvcnRlbiIsImNvdmlkMTlfc3RhdHVzIjoicG9zaXRpdiIsImNvdmlkMTlfYmxva2VyZXQiOiJmYWxzZSIsImNvdmlkMTlfc21pdHRlX3N0YXJ0IjoiMjAyMC0xMS0xMSIsImp0aSI6IkExRjMzNkQ2RkNDMzJFRkJERjAyMDE2MUE2MDA3MkU4Iiwic2lkIjoiQjM0MTAwODM3MjY3NDdDMkVBNjRCNTM3OTgxOTBGRTkiLCJpYXQiOjE2MDYzMTUxMDYsInNjb3BlIjpbIm9wZW5pZCIsInNtaXR0ZXN0b3AiXSwiYW1yIjpbImV4dGVybmFsIl19.dqIs9Bec70AryerFmkLDUFEBVQ3Qyy58U94fJZUyKwMdsHhFMuUrF7wOGX0DSPKgk1yYJxqGSOlqfhJiUzgqSUuWbhtA6NUAOUHemP1bsYT4DuboH5cPJBDPRZU_BwuVK7Zt2jsZE1lhU-xe8_EfaMkiulWEiLLf06O-c-UShfanzQ_nEnN6B9bRGO7nkl_iF3A4i33QY8WBSsRVcdvcoLHqnqg7fWiZlMzshGljZ7-kZi7Y0bC5-2ys6a97KQusw0gSUfe1p4aJJ1yGp2MnE3OTm0NJXizFWNVdUAyR-Fh3EpYpP7hkgcS2ZUX5q4f19lNNFu7qn7kIQwW1e9p6rg"
  )

  /*val getConfiguration: HttpRequestBuilder = http("GetConfiguration").get("https://qa-be-op.ss2np.fhi.no/api/v3/diagnostickeys/exposureconfiguration")
    .headers(header_get)
    .check(status is 200)*/

 // PROD
  val getConfiguration: HttpRequestBuilder = http("GetConfiguration").get("https://be-op.ss2.fhi.no/api/v3/diagnostickeys/exposureconfiguration")
    .headers(header_get)
    .check(status is 200)

  /*val getKeys: HttpRequestBuilder = http("GetKeys_").get("https://qa-be-op.ss2np.fhi.no/api/v3/diagnostickeys/2020-12-07_1_no.zip")
    .headers(header_get)
    .check(status is 200)*/

  //PROD
  val getKeys: HttpRequestBuilder = http("GetKeys_").get("https://be-op.ss2.fhi.no/api/v3/diagnostickeys/2020-12-07_2_no.zip")
    .headers(header_get)
    .check(status is 200)


  /*val postLogs: HttpRequestBuilder = http("PostLogs").post("https://qa-be-op.ss2np.fhi.no/api/v3/logging/logMessages")
    .headers(header_post)
    .check(status is 200)*/


  //val feeder: BatchableFeederBuilder[String]#F = csv("data/smittestopp/keys.csv").circular

  val SCN_SmitteStopp: ScenarioBuilder = scenario("SmitteStopp")
    //.feed(feeder)

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .exec(getConfiguration)
    //.exec(diagnostickeys.body(ElFileBody("bodies/smittestopp/diagnostickeys.json")))
    .exec(getKeys)
    //.exec(postLogs.body(ElFileBody("magnus/logMessages.json")))



  //setUp(SCN_SmitteStopp.inject(atOnceUsers(1))).protocols(httpProtocol)

  //setUp(SCN_SmitteStopp.inject(rampUsersPerSec(10) to 208 during (10 minutes),constantUsersPerSec(208) during(5 minutes)).protocols(httpProtocol))
  setUp(SCN_SmitteStopp.inject(rampUsersPerSec(10) to 417 during (10 minutes),constantUsersPerSec(417) during(5 minutes)).protocols(httpProtocol))
  //setUp(SCN_SmitteStopp.inject(rampUsersPerSec(10) to 833 during (10 minutes),constantUsersPerSec(833) during(5 minutes)).protocols(httpProtocol))
  //setUp(SCN_SmitteStopp.inject(rampUsersPerSec(10) to 1111 during (10 minutes),constantUsersPerSec(1111) during(5 minutes)).protocols(httpProtocol))

  //scn.inject(rampUsersPerSec(10) to 208 during (10 minutes),constantUsersPerSec(208) during(5 minutes))


}
