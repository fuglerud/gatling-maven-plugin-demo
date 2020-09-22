package magnus

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import org.json4s.jackson.Json

class SFMBasisAPISimulation extends Simulation {

  val httpProtocol = http
    //.baseUrl("https://base-fhir.staging.sfm.cloud")
    .baseUrl("https://base-fhir.qa.forskrivning.no")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .header("Content-Type", "application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .authorizationHeader("Bearer ${token}")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
    .disableCaching

  val scn = scenario("SFMBasisAPISimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/SFMBasisAPI_QA.csv").circular)
    .feed(csv("magnus/TestdataSFMBasis.csv").circular)


/*  .exec(http("practitioner")
      .get("/Practitioner/${rekvirent}")
      .check(status.is(200))
      .check(jsonPath("$..id").is("${rekvirent}")))

    .exec(http("organization")
      .get("/Organization?name=${organizationname}")
      .check(status.is(200))
      .check(jsonPath("$..resource.id").is("${organizationid}")))*/

    .exec(http("getMedication")
      .post("/Patient/$getMedication")
      .body(ElFileBody("magnus/0000_request.json"))
      .check(status.is(200))
      .check(jsonPath("$..name").is("medication"))
      .check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.use==\"official\")].value").saveAs("official")))

   /* .exec(session=>{
      println("official:")
      println(session("official").as[String])
      session})*/

   /* .exec(http("sendMedication")
      .post("/Patient/$sendMedication")
      .body(ElFileBody("magnus/SendMedication_request.json"))
      .check(status.is(200)))*/

  /* val selectedProfile = System.getProperty("selectedProfile") match {
   case "profile1" => scn.inject(atOnceUsers(1))
   case "profile2" => scn.inject(rampUsersPerSec(1) to 150 during (15 minutes),constantUsersPerSec(150) during(48 hours))
   case "profile3" => scn.inject(constantUsersPerSec(4) during(30))
   case "profile4" => scn.inject(rampConcurrentUsers(5) to(200) during(120))
   case "profile5" => scn.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))
   case "profile6" => scn.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10))
   case "profile7" => scn.inject(rampUsersPerSec(1) to 15 during (3 minutes),constantUsersPerSec(15) during(5 minutes))
   case "profile8" => scn.inject(constantUsersPerSec(1) during(1481))
 }*/


  setUp(scn.inject(atOnceUsers(2))).protocols(httpProtocol)
  //setUp(scn.inject(rampUsersPerSec(1) to 5 during (30),constantUsersPerSec(5) during(60))).protocols(httpProtocol)
  //setUp(scn.inject(constantUsersPerSec(4) during(30))).protocols(httpProtocol)
  //setUp(scn.inject(rampConcurrentUsers(5) to(200) during(120))).protocols(httpProtocol)
  //setUp(scn.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))).protocols(httpProtocol)
  //setUp(scn.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10))).protocols(httpProtocol)


}