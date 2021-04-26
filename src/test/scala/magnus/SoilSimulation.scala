package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SoilSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://epj.qa.forskrivning.no")
		//.inferHtmlResources()
		.inferHtmlResources(BlackList(""".*\.ico"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:84.0) Gecko/20100101 Firefox/84.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://epj.qa.forskrivning.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_5 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://hid-testidp.azurewebsites.net",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_6 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "null",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_9 = Map(
		"Access-Control-Request-Headers" -> "authorization",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_10 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "text/plain",
		"Origin" -> "https://client.qa.forskrivning.no",
		"authorization" -> "ULmyc6BWg6Cf3sJqqXoU7nsGs77h6dVmBnLjbu/H/y2AvDLIuIy8vvqJxmeJ6gu1Tw4TAaFAHLSNAmWaqTlJYw==")

	val headers_11 = Map(
		"Access-Control-Request-Headers" -> "cache-control,content-type,pragma",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_12 = Map(
		"Access-Control-Request-Headers" -> "bugsnag-api-key,bugsnag-payload-version,bugsnag-sent-at,content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_14 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-16T18:02:34.725Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_15 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-16T18:02:34.763Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_16 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"Pragma" -> "no-cache")

	val headers_17 = Map(
		"Access-Control-Request-Headers" -> "x-requested-with",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_22 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_26 = Map(
		"Content-Type" -> "text/plain;charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_27 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-16T18:02:35.219Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

    val uri1 = "https://client.qa.forskrivning.no/version.json"
    val uri3 = "https://server.qa.forskrivning.no"
    val uri4 = "https://sessions.bugsnag.com"
    val uri5 = "https://helseid-sts.test.nhn.no"
	  //val uri5 = "https://helseid-sts.qa.nhn.no/"
    val uri6 = "https://hid-testidp.azurewebsites.net/Account/Login"

	val scn = scenario("SoilSimulation")




		.exec(http("request_1")
			.get("/")
			.headers(headers_0)
			.check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken1")))

		.exec(http("request_2")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "")
			.formParam("IsSelectingInstallation", "False")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken1}")
			.check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken2")))

		.exec(http("request_3")
			.get("/home/ChangingHelseEnvironment?selectedHelseIdEnvironment=6842b79e-43ae-4915-9600-55631de237a7_https%3A%2F%2Fhelseid-sts.test.nhn.no")
			.headers(headers_2))

		.exec(http("request_4")
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

		.exec(http("request_5")
			.get(uri5 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3D${authzId}")
			.headers(headers_0)
			.check(regex("nonce%(.*?)%26").saveAs("nonce"))
			.check(regex("state%(.*?)%26").saveAs("state"))
			.check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("requestVerificationToken3"))
			.check(regex("returnurl=%(.*?)0.0").saveAs("returnurl")))

		.exec(session=>{
			println("returnurl:")
			println(session("returnurl").as[String])
			session})


		.exec(http("request_6")
			//.post(uri6 + "?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dsts%26redirect_uri%3Dhttps%253A%252F%252Fhelseid-sts.test.nhn.no%252Fsignin-testidp-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520identity%252Fpid%26response_mode%3Dform_post%26nonce%3D${nonce}%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D5.6.0.0")
			.post(uri6 + "?returnurl=%${returnurl}")
			.headers(headers_5)
			.formParam("Pid", "07015900215")
			.formParam("SecurityLevel", "4")
			.formParam("HprNumber", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken3}")
			.check(regex("name='state' value='(.*?)' />").saveAs("state"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
			.check(regex("name='id_token' value='(.*?)' />").saveAs("id_token"))
			.resources(http("request_signin-testidp-oidc")
			.post(uri5 + "/signin-testidp-oidc")
			.headers(headers_6)
			.formParam("code", "${code}")
			.formParam("id_token", "${id_token}")
			.formParam("scope", "${scope}")
			.formParam("state", "${state}")
			.formParam("session_state", "${session_state}")
				.check(regex("name='state' value='(.*?)' />").saveAs("state2"))
				.check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state2"))
				.check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope2"))
				.check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code2"))))
		.exec(http("request_8")
			.post("/signin-oidc")
			.headers(headers_6)
			.formParam("code", "${code2}")
			.formParam("scope", "${scope2}")
			.formParam("state", "${state2}")
			.formParam("session_state", "${session_state2}")
			.check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("requestVerificationToken4")))


		.exec(session=>{
			println("requestVerificationToken4:")
			println(session("requestVerificationToken4").as[String])
			session})


		.exec(http("request_LoadClientAsync")
			.post("/Patient/LoadClientAsync")
			.headers(headers_1)
			.formParam("OnBehalfOf", "1010039")
			.formParam("ShowAllergies", "true")
			.formParam("SelectedFnr", "01020566023")
			.formParam("SelectedEnvironment", "Glittertind testlegekontor (Fastlege)")
			.formParam("ApiUrl", "")
			.formParam("HelseIdClientId", "ffad58fd-a86f-4122-8541-42a82f719fe8")
			.formParam("submitButton", "open")
			.formParam("__RequestVerificationToken", "${requestVerificationToken4}")
			.formParam("ShowAllergies", "false")
			//.check(regex("access_token=(.*?)&amp;").saveAs("access_token"))
			//.check(regex("patientTicket=(.*?)&").saveAs("Patient_Ticket"))
		)

	/*	.exec(http("request_10")
			.get("https://client.qa.forskrivning.no/")
			.headers(headers_1)
		)*/


/*
			.resources(http("request_10")
			.options(uri3 + "/api/Login")
			.headers(headers_9),
            http("request_11")
			.post(uri3 + "/api/Login")
			.headers(headers_10)
			.body(RawFileBody("magnus/soilsimulation/0010_request.txt")),
            http("request_12")
			.options(uri3 + "/api/patients/7957608c-2c32-4707-a14d-1a9f7bb3dd26")
			.headers(headers_11),
            http("request_13")
			.options(uri4 + "/")
			.headers(headers_12),
            http("request_14")
			.options(uri4 + "/")
			.headers(headers_12),
            http("request_15")
			.post(uri4 + "/")
			.headers(headers_14)
			.body(RawFileBody("magnus/soilsimulation/0014_request.json")),
            http("request_16")
			.post(uri4 + "/")
			.headers(headers_15)
			.body(RawFileBody("magnus/soilsimulation/0015_request.json")),
            http("request_17")
			.get(uri3 + "/api/patients/7957608c-2c32-4707-a14d-1a9f7bb3dd26")
			.headers(headers_16),
            http("request_18")
			.options(uri3 + "/hubs/sfmHub/negotiate?patientTicket=7957608c-2c32-4707-a14d-1a9f7bb3dd26&negotiateVersion=1")
			.headers(headers_17),
            http("request_19")
			.options(uri3 + "/api/users/current")
			.headers(headers_11),
            http("request_20")
			.options(uri3 + "/api/patientJournals/result/?patientTicket=7957608c-2c32-4707-a14d-1a9f7bb3dd26")
			.headers(headers_11),
            http("request_21")
			.options(uri3 + "/api/users/onbehalfof/1010039")
			.headers(headers_11),
            http("request_22")
			.options(uri3 + "/api/institutions/")
			.headers(headers_11),
            http("request_23")
			.get(uri1 + "?t=1608141755255")
			.headers(headers_22),
            http("request_24")
			.options(uri3 + "/api/serverinfo/version")
			.headers(headers_11),
            http("request_25")
			.options(uri3 + "/api/prescriptions/?patientTicket=7957608c-2c32-4707-a14d-1a9f7bb3dd26")
			.headers(headers_11),
            http("request_26")
			.options(uri3 + "/api/catalogues/lib/2020-12-10%2011:05:48")
			.headers(headers_11),
            http("request_27")
			.post(uri3 + "/hubs/sfmHub/negotiate?patientTicket=7957608c-2c32-4707-a14d-1a9f7bb3dd26&negotiateVersion=1")
			.headers(headers_26),
            http("request_28")
			.post(uri4 + "/")
			.headers(headers_27)
			.body(RawFileBody("magnus/soilsimulation/0027_request.json")),
            http("request_29")
			.get(uri3 + "/api/patientJournals/result/?patientTicket=7957608c-2c32-4707-a14d-1a9f7bb3dd26")
			.headers(headers_16),
            http("request_30")
			.get(uri3 + "/api/serverinfo/version")
			.headers(headers_16),
            http("request_31")
			.get(uri3 + "/api/users/current")
			.headers(headers_16),
            http("request_32")
			.get(uri3 + "/api/users/onbehalfof/1010039")
			.headers(headers_16),
            http("request_33")
			.get(uri3 + "/api/institutions/")
			.headers(headers_16),
            http("request_34")
			.get(uri3 + "/api/catalogues/lib/2020-12-10%2011:05:48")
			.headers(headers_16),
            http("request_35")
			.options(uri3 + "/api/catalogues/localDrugs")
			.headers(headers_11),
            http("request_36")
			.get(uri3 + "/api/catalogues/localDrugs")
			.headers(headers_16),
            http("request_37")
			.get(uri3 + "/api/prescriptions/?patientTicket=7957608c-2c32-4707-a14d-1a9f7bb3dd26")
			.headers(headers_16)))



*/






	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}