package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SFMSessionGateway extends Simulation {

	val httpProtocol = http
		.baseUrl("https://session.qa.forskrivning.no")
		.inferHtmlResources()
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.authorizationHeader("Bearer ${token}")
		.contentTypeHeader("application/json")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:85.0) Gecko/20100101 Firefox/85.0")

	val headers_0 = Map("Origin" -> "https://session.qa.forskrivning.no")

	val headers_1 = Map("Authorization" -> "${Code}",
		"Content-type" ->"application/json")

	val headers_2 = Map("__Host-sfm_session" -> "${Cookie}",
		"Content-type" ->"application/json")

	val scn = scenario("SFMSessionGateway")

		.feed(csv("computerdatabase/tokens.csv").random)
		.feed(csv("computerdatabase/pid.csv").random)

		.exec(http("Create")
			.post("/api/Session/create")
			.headers(headers_0)
			.body(ElFileBody("computerdatabase/sfmsessiongateway/create_nonce.json"))
			.check(jsonPath("$..id").saveAs("Id"))
			.check(jsonPath("$..code").saveAs("Code"))
			.check(jsonPath("$..clientAddress").saveAs("ClientAddress"))
			.check(jsonPath("$..apiAddress").saveAs("ApiAddress")))

		.exec(http("PatientTicket")
			.post("/api/PatientTicket")
			.headers(headers_0)
			.body(ElFileBody("computerdatabase/sfmsessiongateway/0000_request.json"))
			.check(jsonPath("$..*").saveAs("PatientTicket")))


		// the nonce is there to make sure that
		// both the session create post and and the login post
		// originated from the same caller


		.exec(http("Login")
			.post("https://server.qa.forskrivning.no/api/Login")
			.headers(headers_1)
			.body(StringBody("""t7+pB0i9UFCt0e4hLPrRjO+YAIVJzuPLCWEmd1yAdjLBNES1lEsL7XLNMo4oJis1ZXeT7ZbqYWPYdlN65LXWAA=="""))
			.check(headerRegex("Set-Cookie", "(?s).*__Host-sfm_session=([^;]*).*").saveAs("Cookie")))

		.exec(http("Prescriptions")
			.get("https://server.qa.forskrivning.no/api/Prescriptions")
			.headers(headers_2)
			.queryParam("PatientTicket","${PatientTicket}"))

		.exec(http("Patients_getQueue")
			.get("https://server.qa.forskrivning.no/api/patients/${PatientTicket}/getQueue.xml")
			.headers(headers_2))

		.exec(http("Patients_getQueueIds")
			.get("https://server.qa.forskrivning.no/api/patients/${PatientTicket}/getQueueIds")
			.headers(headers_2))

		.exec(http("Patients_signAndSend")
			.put("https://server.qa.forskrivning.no/api/patients/${PatientTicket}/signAndSend")
			.headers(headers_2)
			.body(ElFileBody("computerdatabase/sfmsessiongateway/0003_request.json")))




		.exec(http("LogOff")
			.post("/api/session/end")
			.headers(headers_0))


	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}