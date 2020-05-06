package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PaaskeaftenSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://helsenorge.hn.test.nhn.no:4443")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.contentTypeHeader("application/json")
		.userAgentHeader("PostmanRuntime/7.24.0")
		.disableCaching

	val headers_CitizenTokens = Map("Postman-Token" -> "5fc94e73-3507-47b9-9b06-8960f00742e7")

	val headers_minhelse = Map(
		"MH_AccessToken_test1"->"${ReferenceToken}",
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.5",
		"Connection" -> "keep-alive",
		"Host" -> "minhelse.hn.test.nhn.no",
		"Upgrade-Insecure-Requests" -> "1",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0")

	val headers_LokasjonsLogg = Map("Cookie" -> "MH_AccessToken_test1=${ReferenceToken}")
	val headers_LoggOverBruk = Map("Cookie" -> "MH_AccessToken_test1=${ReferenceToken}")

	val scn = scenario("PaaskeaftenSimulation")

		.feed(csv("magnus/TestdataSmittestopp.csv").random)

		.exec(http("CitizenTokens")
			.post("/api/v1/CitizenTokens")
			.headers(headers_CitizenTokens)
			.body(ElFileBody("magnus/CitizenToken.json"))
			.check(jsonPath("$.ReferenceToken").saveAs("ReferenceToken")))

		.exec(http("VerifyPhone")
			.post("/api/v1/VerifyPhone")
			.check(status.is(expected = 200))
			.body(ElFileBody("magnus/VerifyPhone.json")))

		.exec(http("LokasjonsLogg")
			//.get("https://minhelse.hn.test.nhn.no/proxy/InnsynSmittestopp/api/v1/lokasjonslogg/?debug=1")
			.get("https://minhelse.hn.test.nhn.no/proxy/InnsynSmittestopp/api/v1/lokasjonslogg/?debug=1&date=${date}&timeSlot=${time}")
			.headers(headers_LokasjonsLogg)
			.check(status.is(expected = 200))
			.check(jsonPath("$.foundInSystem").is("true")))

		.exec(http("LoggOverBruk")
			.get("https://minhelse.hn.test.nhn.no/proxy/InnsynSmittestopp/api/v1/loggoverbruk/?debug=1")
			.headers(headers_LoggOverBruk)
			.check(status.is(expected = 200))
			.check(jsonPath("$.foundInSystem").is("true")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
	//setUp(scn.inject(constantUsersPerSec(10) during(500))).protocols(httpProtocol)
	//setUp(scn.inject(rampConcurrentUsers(1) to(10) during(300)).protocols(httpProtocol))
}