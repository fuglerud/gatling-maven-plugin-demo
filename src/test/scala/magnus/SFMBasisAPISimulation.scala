package magnus

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SFMBasisAPISimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://base-fhir.staging.sfm.cloud")
		.inferHtmlResources()
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.authorizationHeader("Bearer ${token}")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
		.disableCaching

	val scn = scenario("SFMBasisAPISimulation")

		.exec(flushCookieJar)
		.exec(flushHttpCache)

		.feed(csv("magnus/SFMBasisAPI.csv").circular)

		.exec(http("request_practitioner")
			.get("/api/v1/Practitioner/${rekvirent}")
			.check(status.is(200))
			.check(jsonPath("$..id").is("${rekvirent}")))

		.exec(http("request_organization")
			.get("/api/v1/Organization?name=${organizationname}")
			.check(status.is(200))
			.check(jsonPath("$..resource.id").is("${organizationid}")))

		.exec(http("request_getMedication")
			.post("/api/v1/Patient/$getMedication")
			.body(ElFileBody("computerdatabase/recordedsimulation/0000_request.json"))
			.check(status.is(200)))


	/*
	val selectedProfile = System.getProperty("selectedProfile") match {
		case "profile1" => scn.inject(atOnceUsers(1))
		case "profile2" => scn.inject(rampUsersPerSec(1) to 5 during (30),constantUsersPerSec(5) during(600))
		case "profile3" => scn.inject(constantUsersPerSec(500) during(60))
		case "profile4" => scn.inject(rampConcurrentUsers(5) to(200) during(120))
		case "profile5" => scn.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))
		case "profile6" => scn.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10))}

	setUp(selectedProfile).protocols(httpProtocol)
	*/

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}