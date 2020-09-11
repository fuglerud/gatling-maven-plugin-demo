package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class tokenService extends Simulation  {

  val httpProtocol = http
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .doNotTrackHeader("1")
    .disableCaching


  val scn = scenario("TokenService")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/01_EPJTicketTestData.csv").circular)
    .feed(csv("magnus/02_EPJTicketTestData.csv").circular)

    .feed(csv("magnus/SFMBasisAPI_QA.csv").circular)
    .feed(csv("magnus/TestdataSFMBasis.csv").circular)


      .exec(http("request_access_token")
        .post("https://helseid-sts.test.nhn.no/connect/token")
        .header("Authorization", "Basic Y2ZhOWM4ZmItOGRkNi00ZDdjLWIxNDItNzg2YzNkNzc0ZjY0OiUyQjRTbmdMcHV6Um5zbkk3SjVoQ0xHTVlwcUFTVUJRdjJjUGRrZU01JTJCYUIwZHhSSjlCU3diajJlaSUyRlNCdyUyRkF6VWZkSG81VENWVVpJSW1xcEtOY0RRSUElM0QlM0Q=")
        .formParam("grant_type", "client_credentials")
        .formParam("client_id", "cfa9c8fb-8dd6-4d7c-b142-786c3d774f64")
        .formParam("client_secret", "%2B4SngLpuzRnsnI7J5hCLGMYpqASUBQv2cPdkeM5%2BaB0dxRJ9BSwbj2ei%2FSBw%2FAzUfdHo5TCVUZIImqpKNcDQIA%3D%3D")
        .formParam("audience", "e-helse/sfm.api/sfm.api")
        .check(regex("access_token\":\"(.*?)\"").saveAs("access_token")))

    .exec(session=>{
      println("access_token:")
      println(session("access_token").as[String])
      session})
/*
    .exec(http("getMedication")
      .post("https://base-fhir.qa.forskrivning.no/Patient/$getMedication")
      .header("Authorization","Bearer ${access_token}")
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .header("Content-Type", "application/json")
      .body(ElFileBody("magnus/0000_request.json"))
      .check(status.is(200))
      .check(jsonPath("$..name").is("medication"))
      .check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"RFHentetTidspunkt\")].valueDateTime").saveAs("RFHentetTidspunkt"))
      .check(jsonPath("$..[?(@.use==\"official\")].value").saveAs("official")))
*/

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(constantUsersPerSec(rate=1) during(3600))).protocols(httpProtocol)
}