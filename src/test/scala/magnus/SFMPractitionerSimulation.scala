package magnus

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SFMPractitionerSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://base-fhir.staging.sfm.cloud")
		.inferHtmlResources()
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.authorizationHeader("Bearer ${token}")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
		.disableCaching

	val scn = scenario("RekvirentSimulation")

		.exec(flushCookieJar)
		.exec(flushHttpCache)

		.feed(csv("magnus/SFMPractitioner.csv").circular)

		.exec(http("request_practitioner")
			.get("/api/v1/Practitioner/${rekvirent}")
			.check(status.is(200))
			.check(jsonPath("$..id").is("${rekvirent}")))


		.exec(http("request_organization")
			.get("/api/v1/Organization?name=${organizationname}")
			.check(status.is(200))
			.check(jsonPath("$..id").is("${organizationid}")))




	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}