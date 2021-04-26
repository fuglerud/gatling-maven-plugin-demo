package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class nordtroms extends Simulation {

	val httpProtocol = http
		.baseUrl("https://session.qa.forskrivning.no")
		.inferHtmlResources()
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:85.0) Gecko/20100101 Firefox/85.0")

	val headers_0 = Map(
		"Content-Type" -> "application/json",
		"Origin" -> "https://session.qa.forskrivning.no",
		"authorization" -> "Bearer ${token}")

	val headers_1 = Map("Accept" -> "image/webp,*/*")

	val scn = scenario("nordtroms")

		.exec(http("PatientTicket")
			.post("/api/PatientTicket")
			.headers(headers_0)
			.body(RawFileBody("computerdatabase/nordtroms/0000_request.json")))

		.exec(http("create")
			.post("/api/Session/create")
			.headers(headers_0)
			.body(RawFileBody("computerdatabase/sfmsessiongateway/0002_request.json")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}