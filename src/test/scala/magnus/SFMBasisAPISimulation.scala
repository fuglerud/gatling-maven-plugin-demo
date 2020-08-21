package magnus

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import org.json4s.jackson.Json

class SFMBasisAPISimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://base-fhir.staging.sfm.cloud")
    //.baseUrl("https://base-fhir.qa.forskrivning.no")
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

    .feed(csv("magnus/SFMBasisAPI.csv").circular)
    .feed(csv("magnus/TestdataSFMBasis.csv").circular)
/*

    .exec(http("practitioner")
      .get("/Practitioner/${rekvirent}")
      .check(status.is(200))
      .check(jsonPath("$..id").is("${rekvirent}")))

    .exec(http("organization")
      .get("/Organization?name=${organizationname}")
      .check(status.is(200))
      .check(jsonPath("$..resource.id").is("${organizationid}")))
*/


    .exec(http("getMedication")
      .post("/Patient/$getMedication")
      .body(ElFileBody("magnus/0000_request.json"))
      .check(status.is(200))
      .check(jsonPath("$..name").is("medication"))
      .check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").is("OK"))
      //.check(bodyString.saveAs( "RESPONSE_DATA" ))
    )

/*
    .exec(session=>{
      println("RESPONSE_DATA:")
      println(session("RESPONSE_DATA").as[String])
      session})



    .exec(http("sendMedication")
      .post("/Patient/$sendMedication")
     // .body(ElFileBody("${RESPONSE_DATA}"))
      .body(StringBody("""${RESPONSE_DATA}"""))
      .check(status.is(200)))

*/

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}