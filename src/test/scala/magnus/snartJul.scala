package magnus

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class snartJul extends Simulation {

	val httpProtocol = http
		.baseUrl("https://epj.qa.forskrivning.no")
		.inferHtmlResources()
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

	val headers_10 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_14 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_15 = Map(
		"Access-Control-Request-Headers" -> "authorization",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_16 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_17 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "text/plain",
		"Origin" -> "https://client.qa.forskrivning.no",
		"authorization" -> "Zi+lXMNihRiBvBbXrzBBiCDaoVn3YqVFxudzaNaj09aTwuXw71Ja7sK+vqFAA81Nt6NxiIqtMnmIe5nmOY3tuA==")

	val headers_18 = Map(
		"Access-Control-Request-Headers" -> "bugsnag-api-key,bugsnag-payload-version,bugsnag-sent-at,content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_19 = Map(
		"Access-Control-Request-Headers" -> "cache-control,content-type,pragma",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_21 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-21T20:07:33.442Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_22 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-21T20:07:33.512Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_23 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"Pragma" -> "no-cache")

	val headers_24 = Map(
		"Access-Control-Request-Headers" -> "x-requested-with",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_28 = Map(
		"Content-Type" -> "text/plain;charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_37 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-21T20:07:33.929Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

    val uri1 = "https://client.qa.forskrivning.no"
    val uri3 = "https://server.qa.forskrivning.no"
    val uri4 = "https://sessions.bugsnag.com"
    val uri5 = "https://helseid-sts.test.nhn.no"
    val uri6 = "https://hid-testidp.azurewebsites.net/Account/Login"

	val scn = scenario("snartJul")

		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken1")))

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


		.exec(http("request_2")
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

		.exec(http("request_4")
			.get(uri5 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3D${authzId}")
			.headers(headers_0)
			.check(regex("nonce%(.*?)%26").saveAs("nonce"))
			.check(regex("state%(.*?)%26").saveAs("state"))
			.check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("requestVerificationToken3"))
			.check(regex("returnurl=%(.*?)0.0").saveAs("returnurl")))

		.exec(http("request_5")
			.post(uri6 + "?returnurl=%${returnurl}")
			.headers(headers_5)
			.formParam("Pid", "07015900215")
			.formParam("SecurityLevel", "4")
			.formParam("HprNumber", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken3}")
			//.check(regex("name='state' value='(.*?)' />").saveAs("state"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope"))
			.check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
			.check(regex("name='id_token' value='(.*?)' />").saveAs("id_token")))


		.exec(session=>{
			println("session_state:")
			println(session("session_state").as[String])
			session})

			.exec(http("request_6")
			.post(uri5 + "/signin-testidp-oidc")
			.headers(headers_6)
			.formParam("code", "${code}")
			.formParam("id_token", "${id_token}")
			.formParam("scope", "${scope}")
			.formParam("state", "${state}")
			.formParam("session_state", "${session_state}"))

		.exec(http("request_7")
			.post("/signin-oidc")
			.headers(headers_6)
			.formParam("code", "3B8932B2BA42D9E72884E646C8F9BDE65AA90394B480574445468AB76FF22A62")
			.formParam("scope", "openid profile e-helse/sfm.api/sfm.api helseid://scopes/identity/pid helseid://scopes/identity/security_level helseid://scopes/hpr/hpr_number offline_access")
			.formParam("state", "CfDJ8EIQDhsJrOxOqIiXGzMJBCE1a1149ClmbR5w-l2xVa6rYP2qCltwXf6tw_nP_P0Gv0aoV7rR5g6EpmgrcjqFu8V3ZymBwDPOOcO5p5SYbOdJaY78XHCfxs0YfKwTSjiD1Ye8H3JYkkAaeiwepW9xhiYFlSm86ggp_tYs1BI-lS3gcfGtX2MrQNnl0bgjp14GkJTpcTBpr-frF4LBqV9mcZ-C0cwy2eN_x9FgClpwaXRU_suLkYLzsfePDZnavc9SE-LfxKXxV5MIcOluSfy96c7b85wfLPOvWiikugmf9Ae3uvPV8UMd1fl32pu3pTt0QbP__11yy21pTYXduG61oTZ8rrHzVE8n9jkLVGfxIwtn6Ov81nvgt3b2ulXNWJcZVj2OXVVgP67plCnm5N9Qr0tb8CX5U3rmfHtmoZC3iN6qPYsKiIJ5omWv2G5Nhxbq8R0VFFsX44n3BShTVOR5nC8WMtaq87D9gRidHZtqWuI9ksiMqq1NFpe5S7zIecCjujLh9b29AJmMyi0xv5Ct0N1OoeTYlDv2CfhhVC4t5qm9yevBsnpOojpxNrU_Bw-JHsVLkPykiwclDVONV6nEy9RWzvwISaLnaEurfOchCvom-T4_-8gbWuFGQCEDoprNIQ")
			.formParam("session_state", "eSr4i6hQkTve6NJeTr6MeTlR_V7fti9IkPNpzkwmK3Q.8B72C75A300AED11A0B244DD6230ECC2"))

		/*
		.exec(http("request_8")
			.post("/Patient/LoadClientAsync")
			.headers(headers_1)
			.formParam("OnBehalfOf", "")
			.formParam("ShowAllergies", "true")
			.formParam("SelectedFnr", "03021454701")
			.formParam("SelectedEnvironment", "Glittertind testlegekontor (Fastlege)")
			.formParam("ApiUrl", "")
			.formParam("HelseIdClientId", "ffad58fd-a86f-4122-8541-42a82f719fe8")
			.formParam("submitButton", "open")
			.formParam("__RequestVerificationToken", "CfDJ8EIQDhsJrOxOqIiXGzMJBCFCHmYhU395m_BpU9BBeR4zBhM2IOqxmh-XxFdzYsqpram5fez6pHh9oqLZuulCNlTCV9DB78unZO9w6k7_WDxhckmH2_HVRmNidLZ0rmVq2ufbz1CGy2QEU8BKQ35NODfeJmFRt1PzjME5zHKVvITa4epb17PljLp1wmjLeYC_Sw")
			.formParam("ShowAllergies", "false")
			.resources(http("request_9")
			.get(uri1 + "/")
			.headers(headers_0),
            http("request_10")
			.get(uri1 + "/styles.aad74f74f18b76bf1b4f.css")
			.headers(headers_10),
            http("request_11")
			.get(uri1 + "/runtime-es2018.121c1274a11ea7a4e8c8.js"),
            http("request_12")
			.get(uri1 + "/polyfills-es2018.7474306e653c1078eb05.js"),
            http("request_13")
			.get(uri1 + "/main-es2018.0b63300c65bdd1191419.js"),
            http("request_14")
			.get(uri1 + "/assets/i18n/no.json")
			.headers(headers_14),
            http("request_15")
			.options(uri3 + "/api/Login")
			.headers(headers_15),
            http("request_16")
			.get(uri1 + "/SourceSansPro-Regular.ttf.f963ed837d6e84c7f143.woff2")
			.headers(headers_16),
            http("request_17")
			.post(uri3 + "/api/Login")
			.headers(headers_17)
			.body(RawFileBody("magnus/snartjul/0017_request.txt")),
            http("request_18")
			.options(uri4 + "/")
			.headers(headers_18),
            http("request_19")
			.options(uri3 + "/api/patients/e8fa3461-fcff-4391-ab7c-1aa9d46f5120")
			.headers(headers_19),
            http("request_20")
			.options(uri4 + "/")
			.headers(headers_18),
            http("request_21")
			.post(uri4 + "/")
			.headers(headers_21)
			.body(RawFileBody("magnus/snartjul/0021_request.json")),
            http("request_22")
			.post(uri4 + "/")
			.headers(headers_22)
			.body(RawFileBody("magnus/snartjul/0022_request.json")),
            http("request_23")
			.get(uri3 + "/api/patients/e8fa3461-fcff-4391-ab7c-1aa9d46f5120")
			.headers(headers_23),
            http("request_24")
			.options(uri3 + "/hubs/sfmHub/negotiate?patientTicket=e8fa3461-fcff-4391-ab7c-1aa9d46f5120&negotiateVersion=1")
			.headers(headers_24),
            http("request_25")
			.options(uri3 + "/api/users/current")
			.headers(headers_19),
            http("request_26")
			.get(uri1 + "/version.json?t=1608581253970")
			.headers(headers_14),
            http("request_27")
			.options(uri3 + "/api/patientJournals/result/?patientTicket=e8fa3461-fcff-4391-ab7c-1aa9d46f5120")
			.headers(headers_19),
            http("request_28")
			.post(uri3 + "/hubs/sfmHub/negotiate?patientTicket=e8fa3461-fcff-4391-ab7c-1aa9d46f5120&negotiateVersion=1")
			.headers(headers_28),
            http("request_29")
			.get(uri1 + "/assets/i18n/sfm-library/no.json")
			.headers(headers_14),
            http("request_30")
			.options(uri3 + "/api/serverinfo/version")
			.headers(headers_19),
            http("request_31")
			.options(uri3 + "/api/institutions/")
			.headers(headers_19),
            http("request_32")
			.options(uri3 + "/api/catalogues/lib/2020-12-10%2011:05:48")
			.headers(headers_19),
            http("request_33")
			.get(uri1 + "/SourceSansPro-Bold.ttf.2f5f78b01bf8ea38446d.woff2")
			.headers(headers_16),
            http("request_34")
			.get(uri3 + "/api/serverinfo/version")
			.headers(headers_23),
            http("request_35")
			.options(uri3 + "/api/prescriptions/?patientTicket=e8fa3461-fcff-4391-ab7c-1aa9d46f5120")
			.headers(headers_19),
            http("request_36")
			.get(uri3 + "/api/users/current")
			.headers(headers_23),
            http("request_37")
			.post(uri4 + "/")
			.headers(headers_37)
			.body(RawFileBody("magnus/snartjul/0037_request.json")),
            http("request_38")
			.get(uri3 + "/api/institutions/")
			.headers(headers_23),
            http("request_39")
			.get(uri3 + "/api/patientJournals/result/?patientTicket=e8fa3461-fcff-4391-ab7c-1aa9d46f5120")
			.headers(headers_23),
            http("request_40")
			.get(uri3 + "/api/catalogues/lib/2020-12-10%2011:05:48")
			.headers(headers_23),
            http("request_41")
			.options(uri3 + "/api/catalogues/localDrugs")
			.headers(headers_19),
            http("request_42")
			.get(uri3 + "/api/catalogues/localDrugs")
			.headers(headers_23),
            http("request_43")
			.get(uri3 + "/api/prescriptions/?patientTicket=e8fa3461-fcff-4391-ab7c-1aa9d46f5120")
			.headers(headers_23),
            http("request_44")
			.get(uri1 + "/SourceSansPro-Semibold.ttf.acbf737b5bfddd31d0f6.woff2")
			.headers(headers_16)))
			*/


	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}