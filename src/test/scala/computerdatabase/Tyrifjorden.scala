package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Tyrifjorden extends Simulation {

	val httpProtocol = http
		.baseUrl("https://epj.qa.forskrivning.no")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:85.0) Gecko/20100101 Firefox/85.0")

	val headers_1 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://epj.qa.forskrivning.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_3 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_6 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://hid-testidp.azurewebsites.net",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_7 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "null",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_9 = Map(
		"Access-Control-Request-Headers" -> "bugsnag-api-key,bugsnag-payload-version,bugsnag-sent-at,content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_11 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2021-03-10T10:56:59.583Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_12 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2021-03-10T10:56:59.623Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_13 = Map(
		"Access-Control-Request-Headers" -> "authorization",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_14 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "text/plain",
		"Origin" -> "https://client.qa.forskrivning.no",
		"authorization" -> "exk30taclgeuedbSQC3E+PjxuV7kbUscPHKP97ig1EyhaY2u8hOakTuaJPYG4o+NkHW1Gc+Y4jd1YnM9zRXwbg==")

	val headers_15 = Map(
		"Access-Control-Request-Headers" -> "cache-control,content-type,pragma",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_16 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"Pragma" -> "no-cache")

	val headers_21 = Map(
		"Access-Control-Request-Headers" -> "x-requested-with",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_25 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2021-03-10T10:57:04.880Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_28 = Map(
		"Content-Type" -> "text/plain;charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_29 = Map("Accept" -> "application/json, text/plain, */*")

    val uri1 = "https://client.qa.forskrivning.no/version.json"
    val uri3 = "https://www.google.com/complete/search"
    val uri4 = "https://server.qa.forskrivning.no"
    val uri5 = "https://sessions.bugsnag.com"
    val uri6 = "https://helseid-sts.test.nhn.no"
    val uri7 = "https://hid-testidp.azurewebsites.net"

	val scn = scenario("Tyrifjorden")

		.exec(http("request_0")
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

		.exec(session=>{
			println("___RequestVerificationToken 2:")
			println(session("requestVerificationToken2").as[String])
			session})



		.exec(http("request_3")
			.get("/home/ChangingHelseEnvironment?selectedHelseIdEnvironment=6842b79e-43ae-4915-9600-55631de237a7_https%3A%2F%2Fhelseid-sts.test.nhn.no")
			.headers(headers_3))


		.exec(http("request_4")
			.post("/")
			.headers(headers_2)
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
			.get(uri6 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3D${authzId}")
			.headers(headers_1))

/*
		.exec(http("request_6")
			.post(uri7 + "/Account/Login?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dsts%26redirect_uri%3Dhttps%253A%252F%252Fhelseid-sts.test.nhn.no%252Fsignin-testidp-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520identity%252Fpid%26response_mode%3Dform_post%26nonce%3D637509706076113217.ZGIyYWJjNGItNmQzOS00MzNhLWE2Y2ItN2FkM2VkZmMxZGU3YTJjMTM4NmYtMDgzZC00ZDAzLWI4YzctODhjMGUxMTk4NzNk%26state%3DCfDJ8GfBORlsqExGm4aKBlEm0wwwOUaon1w20TyK34JOtlxKlU9dDKGx-450v93yh-JRa9Ul8h6Sy8iXs2WFKnjP06_HvJRmghZNGydGIshRD9dPzWT1b3gyAOjyG50FGOrUVw3PzQZz8eBdiW3SLji6lvKt3HWscdrv0cLTYMmqtTAw1jIOvvYGgZtZv6wwC7ILWfEAizepPJ7qi_6YbW5yE-BXh7A66kwivRQ-_7zKMhZh2DvzaKqykhQ3fLsdSphVwyNcVhyiIp-va249mIWAtA1MNOseulRRn7zUQ6OKEHYcI4dcWx2qKFUbAlFxtzc2HUY7WKqEwgZxeez4R6agZt33bJPM1-6oknYgCeVdEEP5AdOZh9O9boeJwXcrFr8KGXmYdBOrDf_Ibk9QDGnEHAM9yhU9KKqUEd88o4gyP-GZaFvszFJG85fA_zTh9FnYLFHNTvldarHQFzi0KS3UYSBDZ1Zsk8lRVxc-TIezDjpaQOmUVENhs5FAJ3BBbVqQBWYYwEsfwUccxJ9TM5O0m6vm-AIiaxtlCZ8bUhNQoYq2THyb-_l-4VSJyoHRcAaNwo6155-pL_JPQ7zxIro16PhJ7AV9SUZu5NDbk7KBbGLb6ZVe7mmWQVzAvUEaWkRAlw%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D5.6.0.0")
			.headers(headers_6)
			.formParam("Pid", "08128315978")
			.formParam("SecurityLevel", "4")
			.formParam("HprNumber", "")
			.formParam("__RequestVerificationToken", "CfDJ8Nv0QGlyx7VKuDTHATtRtixpyvwW6bHA7gDDjLBp6qxhh02UAOI9AgEmHiGIQp1dKOm9DXpoSyvDZX5E9V7AR7ysdb10Y-ay6GGyfxi1OmDVP5XXFnh5xoejPU-5TvS23qNAOqYHRQKHB94eG0KGRo0")
			.resources(http("request_7")
			.post(uri6 + "/signin-testidp-oidc")
			.headers(headers_7)
			.formParam("code", "LrB1yiDNve-QOFuRblo9g24GtkeslVnJ6J_HigdVe70")
			.formParam("id_token", "eyJhbGciOiJSUzI1NiIsImtpZCI6IndYVFZLVkROVVRiSTlfYjJ5dXZPNkEiLCJ0eXAiOiJKV1QifQ.eyJuYmYiOjE2MTUzNzM4MTcsImV4cCI6MTYxNTM3NDQxNywiaXNzIjoiaHR0cHM6Ly9oaWQtdGVzdGlkcC5henVyZXdlYnNpdGVzLm5ldCIsImF1ZCI6InN0cyIsIm5vbmNlIjoiNjM3NTA5NzA2MDc2MTEzMjE3LlpHSXlZV0pqTkdJdE5tUXpPUzAwTXpOaExXRTJZMkl0TjJGa00yVmtabU14WkdVM1lUSmpNVE00Tm1ZdE1EZ3paQzAwWkRBekxXSTRZemN0T0Roak1HVXhNVGs0TnpOayIsImlhdCI6MTYxNTM3MzgxNywiY19oYXNoIjoiTFlhWmVvc0pzQ2dJX0pUNkFvVkN5dyIsInNfaGFzaCI6IlU5aThISmtBVFdvYkQxTzV5Y1pDSVEiLCJzaWQiOiJEZ0h4elFfMXh4N21BeHNadmdTOWxBIiwic3ViIjoiNjNlZGRjMmUtMjc4Ny00NTZhLTg5NzktZWE0OTBkYjUyZWVmIiwiYXV0aF90aW1lIjoxNjE1MzczODE3LCJpZHAiOiJsb2NhbCIsInBpZCI6IjA4MTI4MzE1OTc4IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCI6IjQiLCJhbXIiOlsicHdkIl19.f0zEtoJCy6OJy5v7wUdYnHOg7W5QzaI_zY1KAZuG69KhzbfVDhiczkkyB3n4IZcZshMtUKtlPctERSIhXnDGhYPPBg_l7CmSAEBgaX2C_-o2qmo-VQ13fH96jIBtwFhJXjqmKUTd7kEhNZbb9P-hBo9RjobJa1c2eF4snL8mlWRuY0Yzs_reyddg7eUKeR2agX8MZXVCOo7zBGF1nES6ddMoVvrCp_6W5CLI0YXCankhVQX38znzDMWx0ZlVl5XZ6FMn28oy8pdEZEacf8G_jgQ945mr5srXAIZCmkD-_v_VyL5_vMWzEvb2BtLckSlRykBmmZMGq5nPaqwrdu4XmA")
			.formParam("scope", "openid profile identity/pid")
			.formParam("state", "CfDJ8GfBORlsqExGm4aKBlEm0wwwOUaon1w20TyK34JOtlxKlU9dDKGx-450v93yh-JRa9Ul8h6Sy8iXs2WFKnjP06_HvJRmghZNGydGIshRD9dPzWT1b3gyAOjyG50FGOrUVw3PzQZz8eBdiW3SLji6lvKt3HWscdrv0cLTYMmqtTAw1jIOvvYGgZtZv6wwC7ILWfEAizepPJ7qi_6YbW5yE-BXh7A66kwivRQ-_7zKMhZh2DvzaKqykhQ3fLsdSphVwyNcVhyiIp-va249mIWAtA1MNOseulRRn7zUQ6OKEHYcI4dcWx2qKFUbAlFxtzc2HUY7WKqEwgZxeez4R6agZt33bJPM1-6oknYgCeVdEEP5AdOZh9O9boeJwXcrFr8KGXmYdBOrDf_Ibk9QDGnEHAM9yhU9KKqUEd88o4gyP-GZaFvszFJG85fA_zTh9FnYLFHNTvldarHQFzi0KS3UYSBDZ1Zsk8lRVxc-TIezDjpaQOmUVENhs5FAJ3BBbVqQBWYYwEsfwUccxJ9TM5O0m6vm-AIiaxtlCZ8bUhNQoYq2THyb-_l-4VSJyoHRcAaNwo6155-pL_JPQ7zxIro16PhJ7AV9SUZu5NDbk7KBbGLb6ZVe7mmWQVzAvUEaWkRAlw")
			.formParam("session_state", "ej0gt49YXebiD19OD-_1H-OPBj6_E3OX_eSBOcih52Y.2muuvN_W92JvmN8X6xOQAA"),
            http("request_8")
			.post("/signin-oidc")
			.headers(headers_7)
			.formParam("code", "06D00AF975D91F9B2E81FD9F4D70DCF73C653CD3334BD8996F130DD6954C17F7")
			.formParam("scope", "openid profile e-helse:sfm.api/sfm.api helseid://scopes/identity/pid helseid://scopes/identity/security_level helseid://scopes/hpr/hpr_number offline_access")
			.formParam("state", "CfDJ8OQMddEVb5xLu4X5MGc56Yx_1JwH5eRcHRQnA3yuKVxjEkM28KEkUKTgoeI8_dbnzJhVSx5QzCZo33kD6zhHAI9TCgUy4SWaWWHOApljnkIC-ML380Eqt_1CJNPWrztOeuQcLHoUaG96S-7hol9vldB8tBxwcD7sH2DOr7NK-XbiSGTmho-akDLHFgWHp7U205Yh5dNeZM_dv5OI0nbnDA6R524Of3qhN_RNEvPJR9aOtKI8wtyVFhk77wZ3-y3418nU9P2OK-mq5Pnu18CfrNAQey0ksZljvPQD5Nqvj288DmqkL8qIv53iO0ynXs-5Gq3S12MFIdsXISkeEZvxPTBmqwnegpuj858j6WmbqScy87CJFknN2hQIzjAX-Oqw4CnopNUBx1AmmAF_Bc5Rg6Ea8GT5nKr5uSRNsOZZ6kW11LFtqVaxYDfPmLVdEbfO-VqtQqCdCST1Ima-D7rLTH6pGDuUK-h6zr10vTRAAWciMgZIx_bAWDHSEmsH_CcFTrbL_gC5LS-3HES_Jbca6YPVV8kT-3zY7m2bwSuOiAR2Js8XuylRT4dv924426btxVFn6qwKQVE-fylNP1VnLauQxX-gNhMpzHwDvEBac3Uo_CxmD3KFt9YDttksq1_k3g")
			.formParam("session_state", "5-vkebBao7MG6gVNMpupUlqI7ef6rM05wkNEc7LPaFg.7832CED9963DBDF303780CB1F98EFB9E"),
            http("request_9")
			.options(uri5 + "/")
			.headers(headers_9),
            http("request_10")
			.options(uri5 + "/")
			.headers(headers_9),
            http("request_11")
			.post(uri5 + "/")
			.headers(headers_11)
			.body(RawFileBody("computerdatabase/tyrifjorden/0011_request.json")),
            http("request_12")
			.post(uri5 + "/")
			.headers(headers_12)
			.body(RawFileBody("computerdatabase/tyrifjorden/0012_request.json")),
            http("request_13")
			.options(uri4 + "/api/Login")
			.headers(headers_13),
            http("request_14")
			.post(uri4 + "/api/Login")
			.headers(headers_14)
			.body(RawFileBody("computerdatabase/tyrifjorden/0014_request.txt")),
            http("request_15")
			.options(uri4 + "/api/users/current")
			.headers(headers_15),
            http("request_16")
			.get(uri4 + "/api/users/current")
			.headers(headers_16)))


		.exec(http("request_17")
			.get("/Patient/LoadTicketAsync?identifier=03021454701&forceNewTicket=false")
			.headers(headers_3)
			.resources(http("request_18")
			.options(uri4 + "/api/patients/9a5cc658-c392-407a-bec7-1b49cf13a6e7")
			.headers(headers_15),
            http("request_19")
			.options(uri4 + "/api/users/onbehalfof/431002790")
			.headers(headers_15),
            http("request_20")
			.get(uri4 + "/api/patients/9a5cc658-c392-407a-bec7-1b49cf13a6e7")
			.headers(headers_16),
            http("request_21")
			.options(uri4 + "/hubs/sfmHub/negotiate?patientTicket=9a5cc658-c392-407a-bec7-1b49cf13a6e7&negotiateVersion=1")
			.headers(headers_21),
            http("request_22")
			.get(uri4 + "/api/users/onbehalfof/431002790")
			.headers(headers_16),
            http("request_23")
			.options(uri4 + "/api/institutions/")
			.headers(headers_15),
            http("request_24")
			.options(uri4 + "/api/catalogues/lib/2020-12-23%2000:51:49")
			.headers(headers_15),
            http("request_25")
			.post(uri5 + "/")
			.headers(headers_25)
			.body(RawFileBody("computerdatabase/tyrifjorden/0025_request.json")),
            http("request_26")
			.options(uri4 + "/api/serverinfo/version")
			.headers(headers_15),
            http("request_27")
			.options(uri4 + "/api/prescriptions/?patientTicket=9a5cc658-c392-407a-bec7-1b49cf13a6e7")
			.headers(headers_15),
            http("request_28")
			.post(uri4 + "/hubs/sfmHub/negotiate?patientTicket=9a5cc658-c392-407a-bec7-1b49cf13a6e7&negotiateVersion=1")
			.headers(headers_28),
            http("request_29")
			.get(uri1 + "?t=1615373824914")
			.headers(headers_29),
            http("request_30")
			.options(uri4 + "/api/patientJournals/result/?patientTicket=9a5cc658-c392-407a-bec7-1b49cf13a6e7")
			.headers(headers_15),
            http("request_31")
			.get(uri4 + "/api/institutions/")
			.headers(headers_16),
            http("request_32")
			.get(uri4 + "/api/serverinfo/version")
			.headers(headers_16),
            http("request_33")
			.get(uri4 + "/api/catalogues/lib/2020-12-23%2000:51:49")
			.headers(headers_16),
            http("request_34")
			.get(uri4 + "/api/patientJournals/result/?patientTicket=9a5cc658-c392-407a-bec7-1b49cf13a6e7")
			.headers(headers_16),
            http("request_35")
			.options(uri4 + "/api/catalogues/localDrugs")
			.headers(headers_15),
            http("request_36")
			.get(uri4 + "/api/catalogues/localDrugs")
			.headers(headers_16),
            http("request_37")
			.get(uri4 + "/api/prescriptions/?patientTicket=9a5cc658-c392-407a-bec7-1b49cf13a6e7")
			.headers(headers_16)))
		.pause(14)
		// log ut
		.exec(http("request_38")
			.post("/Patient/EndSession")
			.headers(headers_2)
			.formParam("submitButton", "logoff")
			.formParam("__RequestVerificationToken", "CfDJ8OQMddEVb5xLu4X5MGc56YyjiW9zqiCmYLeQ_UFJEpQuzHg0dd1tGkvgTAwVELbB7rLp8uloA9aUQMyg2IP6iIKN6NIzY39NlLK_uheQ91mAhibmopSaT30gIl6CaG1OnDnGM8V1Q3KglnlGPjsDwkJ5dwrtKxEtFiowwptpC3Q35QLwlouBZAvS4u302d02CQ")
			.resources(http("request_39")
			.get(uri7 + "/connect/endsession/callback?endSessionId=CfDJ8Nv0QGlyx7VKuDTHATtRtizlJprl4WwHo6Gl_G-H3-wz8-wnapNGGonk4jTh1w0gyj55in0-TyvePTK0u8ISI0x29NN-ypyNSkeRAG2i74wud1zCOCSqPyRlA2qhTVcMwTc8KkgRHyFFlpe1UANTB5Bci8g2KX0Vs2VoC8quwyX72Acm8UPM5BLNAE4RIcGR4XZdAQpNnt4udAUQYjOs3nrLgfaoz2oKkwwMwjJ6e2OFwZoaMYwjvzH3f7wUWF2GLrv2GNJd7Ji5ByRhCWllhVkrYk0D-Pxshv4XCLNhynT2N_WAqieTQkQW-jvk14KOIQ")
			.headers(headers_1),
            http("request_40")
			.get(uri6 + "/signout-callback-testidp-oidc?state=CfDJ8GfBORlsqExGm4aKBlEm0ww1vmNP6gyRQ1dZ5PmQz4eAx0h-r_fMS_GkR_6wDLkTARx8jLLFZj3zgFuVODWdEiMdI-4bvToB0ztHMcrJKY78bjFLV2L7I_wnWZWWiEhmSqp74i8Q7odJbG5t8LK2bawBXShHfmxgpQq0qVsEhTXytxuftOS_E2tFZqllyPH7oLHXtCQagW6m_6_RMdsXhlBDxPIIoVr0_ToU6kPOnTW37YTEq-Hy6OnGCeJSKv1CDOvNOhjmr9O9TB-2iM9WZ_XHyenfvXdv_Nzp127ugHr0JCealUsnmw1paTkzq8-a84WTpUx2-UZ4dzAJYefNULrlbTsf0EflwG2yhh-FR9BC9FcZDjKS__7sBJm5spSj7kKVXcWxwyEIPQgdwVze4QHEV_T04fco4lFibLRq39mjzqHNkwdH4khyZyWj_yXjobgj4kNOCxX4HSIGR1uAyBM3DrlLw9VWkqmI9whZperbv3onSOJ2zmXNTnKKW3XMgpnTUSwmW-QGfPeGtDtPpe6aY3onKrtn-W1rIZoGPMU_J-GDAx9xl6VJuUcK0NMZmr019ofXuvrmrYMtcKQPNZwmHfLb12gkaDfdSz_UVM0PZdjak9ZYjZjYVFPTTdDdR96Ba1JucMz9kgk9irKTBosgyadIDvDM_yJH6oL9Mtf4wm4vAAuYbZHYtW7iN5ppve9YFo5g_mWCOrxZGrD6iiTloVDXQ1FxxXv6a-fyKWswZvAbUjqMzcquEI75JV9gDj6xWsnnMsGHAlTloArO0G8BF4w_yrFFQU5rVdYYn27uzarD7kvOzvHY-Yqv43khcVmC6GH5abPoGa0nmhNou2Vq4XF3oFfNfC7_9pTqjzJ6UNJ684VzcZzPVejhf5_Ztw")
			.headers(headers_1),
            http("request_41")
			.get(uri6 + "/connect/endsession/callback?endSessionId=CfDJ8GfBORlsqExGm4aKBlEm0wymIy60cldtBPdcb6drbyiY5_jTk4OV9WMka1OeZ39XdB5F1NKZ-buQn2X-q7NbIshMoBbgF7vb4co5yYxR8IupEa0WRgdDHal1T7tFOwHWU8Tb5w5o3ICHpVU_G8GnDH8Di3aymhF8SK0DDUPr3TmMulvBp7m0HJaL_DBjtz0eWixk_nev0VGTgrDW3XOlcIM2Vutp9NgEmCTWkeo43viIuGK6kfy2WFeSfqxRvOKwpC9MZvpvuLWLwlBNqKd2ddb5JRiECWTVOhlyoX-30eJDaZPKfs9Ib0KSGaiWoTw5BZv3fAK7scEV-wIDOcrQID-c2BKHVbR3dGngEYBoDVhlqJK0evtHSdEGVUKgIWqS9Q")
			.headers(headers_1),
            http("request_42")
			.get(uri6 + "/js/signout-redirect.js")))

			*/


	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}