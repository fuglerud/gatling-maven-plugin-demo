package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://epj.qa.forskrivning.no")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:85.0) Gecko/20100101 Firefox/85.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://epj.qa.forskrivning.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_4 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_9 = Map("Accept" -> "image/webp,*/*")

	val headers_10 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://hid-testidp.azurewebsites.net",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_11 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "null",
		"Host" -> "epj.qa.forskrivning.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_14 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_18 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_20 = Map(
		"Access-Control-Request-Headers" -> "authorization",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_21 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "text/plain",
		"Origin" -> "https://client.qa.forskrivning.no",
		"authorization" -> "1UQNACyW0wRAJbRoGMJ87WLUmWtFh3StXYYFbbqL2i6f0B/Ncpw+yT/M0idtiWpqRF5aXiQMo9Ux2eK0OFim0w==")

	val headers_22 = Map(
		"Access-Control-Request-Headers" -> "cache-control,content-type,pragma",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_23 = Map(
		"Access-Control-Request-Headers" -> "bugsnag-api-key,bugsnag-payload-version,bugsnag-sent-at,content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_25 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"Pragma" -> "no-cache")

	val headers_26 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2021-02-11T09:53:45.421Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_27 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2021-02-11T09:53:45.505Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

    val uri1 = "https://client.qa.forskrivning.no"
    val uri3 = "https://server.qa.forskrivning.no/api"
    val uri4 = "https://sessions.bugsnag.com"
    val uri5 = "https://helseid-sts.test.nhn.no"
    val uri6 = "https://hid-testidp.azurewebsites.net"

	val scn = scenario("RecordedSimulation")

		.exec(flushCookieJar)
		.exec(flushHttpCache)

		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken1")))

		.exec(session=>{
			println("requestVerificationToken1:")
			println(session("requestVerificationToken1").as[String])
			session})

		.exec(http("request_1")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "")
			.formParam("IsSelectingInstallation", "False")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken1}")
			.check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken2")))

		.exec(session=>{
			println("requestVerificationToken2:")
			println(session("requestVerificationToken2").as[String])
			session})

		.exec(http("ChangingHelseEnvironment")
			.get("/home/ChangingHelseEnvironment?selectedHelseIdEnvironment=6842b79e-43ae-4915-9600-55631de237a7_https%3A%2F%2Fhelseid-sts.test.nhn.no")
			.headers(headers_2))

		.exec(http("request_3")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedHelseIdEnvironment", "6842b79e-43ae-4915-9600-55631de237a7_https://helseid-sts.test.nhn.no")
			.formParam("SelectedEnvironmentId", "ffad58fd-a86f-4122-8541-42a82f719fe8#Glittertind testlegekontor (Fastlege)")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("IsSelectingInstallation", "True")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken2}")
			.check(regex("authzId%3D(.*?)&amp").saveAs("authzId")))

		.exec(session=>{
			println("authzId:")
			println(session("authzId").as[String])
			session})


		.exec(http("ExternalLogin")
			.get(uri5 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3D${authzId}")
			.headers(headers_0)
			.check(regex("nonce%(.*?)%26").saveAs("nonce"))
			.check(regex("state%(.*?)%26").saveAs("state"))
			.check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("requestVerificationToken3"))
			.check(regex("returnurl=%(.*?)0.0").saveAs("returnurl")))

		.exec(session=>{
			println("nonce:")
			println(session("nonce").as[String])
			session})

		.exec(http("Account/Login")
			.post(uri6 + "/Account/Login?returnurl=%${returnurl}")
			.headers(headers_10)
			.formParam("Pid", "07015900215")
			.formParam("SecurityLevel", "4")
			.formParam("HprNumber", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken3}")
			.check(regex("name='state' value='(.*?)' />").saveAs("state"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
			.check(regex("name='id_token' value='(.*?)' />").saveAs("id_token"))
			.resources(http("request_11")
			.post(uri5 + "/signin-testidp-oidc")
			.headers(headers_11)
				.formParam("code", "${code}")
				.formParam("id_token", "${id_token}")
				.formParam("scope", "${scope}")
				.formParam("state", "${state}")
				.formParam("session_state", "${session_state}"),
            http("signin-oidc")
			.post("/signin-oidc")
			.headers(headers_11)
			.formParam("code", "${code}")
			.formParam("scope", "${scope}")
			.formParam("state", "${state}")
			.formParam("session_state", "${session_state}"),
            http("nyBaseCall")
			.get(uri1 + "/")
			.headers(headers_0),
            http("request_14")
			.get(uri1 + "/styles.b83ff6942713f512fabb.css")
			.headers(headers_14),
            http("request_15")
			.get(uri1 + "/runtime.b5587e90f093839891f2.js"),
            http("request_16")
			.get(uri1 + "/polyfills.9ff8a9e3ca313829efd3.js"),
            http("request_17")
			.get(uri1 + "/main.4b4221ebc07a824ca7ae.js"),
            http("request_18")
			.get(uri1 + "/assets/i18n/no.json")
			.headers(headers_18),
            http("request_19")
			.get(uri1 + "/SourceSansPro-Regular.ttf.f963ed837d6e84c7f143.woff2")
			.headers(headers_4),
            http("request_20")
			.options(uri3 + "/Login")
			.headers(headers_20),
            http("request_21")
			.post(uri3 + "/Login")
			.headers(headers_21)
			.body(RawFileBody("computerdatabase/recordedsimulation/0021_request.txt")),
            http("request_22")
			.options(uri3 + "/users/current")
			.headers(headers_22),
            http("request_23")
			.options(uri4 + "/")
			.headers(headers_23),
            http("request_24")
			.options(uri4 + "/")
			.headers(headers_23),
            http("request_25")
			.get(uri3 + "/users/current")
			.headers(headers_25),
            http("request_26")
			.post(uri4 + "/")
			.headers(headers_26)
			.body(RawFileBody("computerdatabase/recordedsimulation/0026_request.json")),
            http("request_27")
			.post(uri4 + "/")
			.headers(headers_27)
			.body(RawFileBody("computerdatabase/recordedsimulation/0027_request.json"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}