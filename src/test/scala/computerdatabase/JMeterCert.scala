package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class JMeterCert extends Simulation {

	val httpProtocol = http
		.baseUrl("https://epj.qa.forskrivning.no")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:84.0) Gecko/20100101 Firefox/84.0")

	val headers_1 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://epj.qa.forskrivning.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_4 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "null",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_5 = Map(
		"Access-Control-Request-Headers" -> "authorization",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_6 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "text/plain",
		"Origin" -> "https://client.qa.forskrivning.no",
		"authorization" -> "3OaWIwwZZq12D2kqZ2AWcBZODLNnCVqEaCctiMANe4psZ2Rd6P8esFr21BCoN4sH586fy4o4fzLKFijDEeQFNQ==")

	val headers_7 = Map(
		"Access-Control-Request-Headers" -> "bugsnag-api-key,bugsnag-payload-version,bugsnag-sent-at,content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_9 = Map(
		"Access-Control-Request-Headers" -> "cache-control,content-type,pragma",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_10 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"Pragma" -> "no-cache")

	val headers_11 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2021-02-10T19:24:05.381Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_12 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2021-02-10T19:24:05.336Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_13 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_15 = Map(
		"Access-Control-Request-Headers" -> "cache-control,content-type,pragma",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_17 = Map(
		"Access-Control-Request-Headers" -> "x-requested-with",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_21 = Map(
		"Content-Type" -> "text/plain;charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_27 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_29 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2021-02-10T19:25:10.382Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

    val uri1 = "https://client.qa.forskrivning.no/version.json"

    val uri4 = "https://server.qa.forskrivning.no"
    val uri5 = "https://sessions.bugsnag.com"

	val scn = scenario("JMeterCert")


		.exec(http("request_1")
			.get("/")
			.headers(headers_1)
			.check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken1")))

		.exec(session=>{
			println("___RequestVerificationToken 1:")
			println(session("requestVerificationToken1").as[String])
			session})

		.exec(http("request_2")
			.post("/")
			.headers(headers_2)
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "")
			.formParam("IsSelectingInstallation", "False")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken1}")
			.check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken2")))

		.exec(http("request_3")
			.post("/")
			.headers(headers_2)
			.formParam("SelectedHelseIdEnvironment", "046045e6-538d-4607-8000-3cc74887658b_https://helseid-int-sts.test.nhn.no")
			.formParam("SelectedEnvironmentId", "ffad58fd-a86f-4122-8541-42a82f719fe8#Glittertind testlegekontor (Fastlege)")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("IsSelectingInstallation", "True")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken2}")
			.resources(http("request_4")
			.post("/signin-oidc")
			.headers(headers_4)
			.formParam("code", "EE463B5B750ED44BDAAFEEDEC4A3990C53DEF9F5F8EE84D7B2DFB605AD89766A")
			.formParam("scope", "openid profile e-helse/sfm.api/sfm.api helseid://scopes/identity/pid helseid://scopes/identity/security_level helseid://scopes/hpr/hpr_number offline_access")
			.formParam("state", "CfDJ8DnOQbtIxW5HnCTA54D95ZqtLKNFakmrL8XZI_ogduDMk4aj2M2n0RBn_WPJldf7LLJPKULdSwYnyC6NFo9PsWUsFCd6M2mjKXxq6xstVQc3WZ-5Dhkl9621qRNj9uTmA8wcmZgQFLYlw6txk6jliy9Xk7QXBoJ4RxUbZo49BR7qSp7A9epQn7PAGhRt0wxkEd7ivOmyOTUoeUxikFXk3Shv18-9HReZ1FUuaTTX5xslaRYwIFZQ8kzRJ_U9Rcp8DsWONRktS9V-pA9iIGx3zuBH7P50MmVUHJ4CciX7mjyPKAm3Jj8w6tGy34X58JNm6ke0qb1EdCanAPt5NpO2NZ1Ag-mVWxUidwOrpFGJ6JaQORCabLtQOozdXlerL3pTFYjNvekm8-Q1Hl_GUpx1q3R7LMbJdv53nvjUzyEaKPs-cnd6vqIdti-iyzDFXbR46GdTm6aRorVyLDyjj5OCpCtAlhR22Q1a9og5Dhxl9a2CxsT-jImj4kzZxomUvXE_JB2YKbc11HWLv_f19NN2GSar5KbwksQ1udBszHW3ZhCA87ahntqib6NnZhq9KMzSFhg8JKicnL4yIpz3nwqlZm8oZRpZewe26GseFK0NvJCrOEJmKknOO6X93ziYfYBnh1TY1RKm7RHP4NsoZ8GxSx8")
			.formParam("session_state", "BKZ9yZ_GV5UqJ2z9xnedGfZlTzr0DPTp39_TM_JOk1s.202270D826CCCD56911A479CDB85505A"),
            http("request_5")
			.options(uri4 + "/api/Login")
			.headers(headers_5),
            http("request_6")
			.post(uri4 + "/api/Login")
			.headers(headers_6)
			.body(RawFileBody("computerdatabase/jmetercert/0006_request.txt")),
            http("request_7")
			.options(uri5 + "/")
			.headers(headers_7),
            http("request_8")
			.options(uri5 + "/")
			.headers(headers_7),
            http("request_9")
			.options(uri4 + "/api/users/current")
			.headers(headers_9),
            http("request_10")
			.get(uri4 + "/api/users/current")
			.headers(headers_10),
            http("request_11")
			.post(uri5 + "/")
			.headers(headers_11)
			.body(RawFileBody("computerdatabase/jmetercert/0011_request.json")),
            http("request_12")
			.post(uri5 + "/")
			.headers(headers_12)
			.body(RawFileBody("computerdatabase/jmetercert/0012_request.json"))))

		.exec(http("request_13")
			.get("/Patient/LoadTicketAsync?identifier=03021454701&forceNewTicket=false")
			.headers(headers_13)
			.resources(http("request_14")
			.options(uri4 + "/api/patients/7918cf4d-977f-439a-aecc-1b117f26f9db")
			.headers(headers_9),
            http("request_15")
			.options(uri4 + "/api/clientlogging/error")
			.headers(headers_15),
            http("request_16")
			.get(uri4 + "/api/patients/7918cf4d-977f-439a-aecc-1b117f26f9db")
			.headers(headers_10),
            http("request_17")
			.options(uri4 + "/hubs/sfmHub/negotiate?patientTicket=7918cf4d-977f-439a-aecc-1b117f26f9db&negotiateVersion=1")
			.headers(headers_17),
            http("request_18")
			.post(uri4 + "/api/clientlogging/error")
			.headers(headers_10)
			.body(RawFileBody("computerdatabase/jmetercert/0018_request.json")),
            http("request_19")
			.options(uri4 + "/api/patientJournals/result/?patientTicket=7918cf4d-977f-439a-aecc-1b117f26f9db")
			.headers(headers_9),
            http("request_20")
			.options(uri4 + "/api/institutions/")
			.headers(headers_9),
            http("request_21")
			.post(uri4 + "/hubs/sfmHub/negotiate?patientTicket=7918cf4d-977f-439a-aecc-1b117f26f9db&negotiateVersion=1")
			.headers(headers_21),
            http("request_22")
			.options(uri5 + "/")
			.headers(headers_7),
            http("request_23")
			.options(uri4 + "/api/catalogues/lib/2020-12-23%2000:51:49")
			.headers(headers_9),
            http("request_24")
			.options(uri4 + "/api/serverinfo/version")
			.headers(headers_9),
            http("request_25")
			.options(uri4 + "/api/prescriptions/?patientTicket=7918cf4d-977f-439a-aecc-1b117f26f9db")
			.headers(headers_9),
            http("request_26")
			.get(uri4 + "/api/institutions/")
			.headers(headers_10),
            http("request_27")
			.get(uri1 + "?t=1612985110458")
			.headers(headers_27),
            http("request_28")
			.get(uri4 + "/api/catalogues/lib/2020-12-23%2000:51:49")
			.headers(headers_10),
            http("request_29")
			.post(uri5 + "/")
			.headers(headers_29)
			.body(RawFileBody("computerdatabase/jmetercert/0029_request.json")),
            http("request_30")
			.get(uri4 + "/api/patientJournals/result/?patientTicket=7918cf4d-977f-439a-aecc-1b117f26f9db")
			.headers(headers_10),
            http("request_31")
			.get(uri4 + "/api/serverinfo/version")
			.headers(headers_10),
            http("request_32")
			.options(uri4 + "/api/catalogues/localDrugs")
			.headers(headers_9),
            http("request_33")
			.get(uri4 + "/api/catalogues/localDrugs")
			.headers(headers_10),
            http("request_34")
			.get(uri4 + "/api/prescriptions/?patientTicket=7918cf4d-977f-439a-aecc-1b117f26f9db")
			.headers(headers_10)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}