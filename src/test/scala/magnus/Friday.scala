package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Friday extends Simulation {

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

	val headers_3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://epj.qa.forskrivning.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_4 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_7 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://hid-testidp.azurewebsites.net",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_8 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "null",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_12 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_16 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_17 = Map(
		"Access-Control-Request-Headers" -> "authorization",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_18 = Map(
		"Access-Control-Request-Headers" -> "bugsnag-api-key,bugsnag-payload-version,bugsnag-sent-at,content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_19 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "text/plain",
		"Origin" -> "https://client.qa.forskrivning.no",
		"authorization" -> "yPtO4eyRXE5JfndyQ4THC9LiU8FhpL+scK72oZHIWKlVUTLXZAfXcVb5RQgTdM0rRQBeROHChNlsf9LowEkrGA==")

	val headers_21 = Map(
		"Access-Control-Request-Headers" -> "cache-control,content-type,pragma",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_22 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-18T08:09:53.357Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_23 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-18T08:09:53.477Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_24 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"Pragma" -> "no-cache")

	val headers_25 = Map(
		"Access-Control-Request-Headers" -> "x-requested-with",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_28 = Map(
		"Content-Type" -> "text/plain;charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_32 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-18T08:09:54.057Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_43 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

    val uri1 = "https://client.qa.forskrivning.no"
    val uri3 = "https://www.google.com/complete/search"
    val uri4 = "https://server.qa.forskrivning.no"
    val uri5 = "https://sessions.bugsnag.com"
    val uri6 = "https://helseid-sts.test.nhn.no"
    val uri7 = "https://hid-testidp.azurewebsites.net/Account/Login"

	val scn = scenario("Friday")
		.exec(http("request_0")
			.get(uri3 + "?client=firefox&q=epj")
			.resources(http("request_1")
			.get("/")
			.headers(headers_1),
            http("request_2")
			.get("/js/site.js?v=86-8P34PLFhGx0tEe5UP1RatwOQXj1EINGmWeyiptHE")))
		.pause(1)
		.exec(http("request_3")
			.post("/")
			.headers(headers_3)
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "")
			.formParam("IsSelectingInstallation", "False")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "CfDJ8EIQDhsJrOxOqIiXGzMJBCFtZGN6IzYQpeY5XPWHhbY-NkiDWTUksL7F7x5p2O9QkRQcL1UFcI08E2dz5rutZ_NRFFFrF2q7lXlMVNxguVQ9nSdmCcstjTfUJYdOvbDiQpmiApU8xNfzG5VXJDw2-ZU"))
		.pause(2)
		.exec(http("request_4")
			.get("/home/ChangingHelseEnvironment?selectedHelseIdEnvironment=6842b79e-43ae-4915-9600-55631de237a7_https%3A%2F%2Fhelseid-sts.test.nhn.no")
			.headers(headers_4))
		.pause(4)
		.exec(http("request_5")
			.post("/")
			.headers(headers_3)
			.formParam("SelectedHelseIdEnvironment", "6842b79e-43ae-4915-9600-55631de237a7_https://helseid-sts.test.nhn.no")
			.formParam("SelectedEnvironmentId", "ffad58fd-a86f-4122-8541-42a82f719fe8#Glittertind testlegekontor (Fastlege)")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("IsSelectingInstallation", "True")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "CfDJ8EIQDhsJrOxOqIiXGzMJBCFiRFRyWRxVFR8wy4iwJ4seKKgSNgvg5pSLl_sG4qBmgzTnFgPdE-9lbyXHoVmMZQmqOpKbxCwG4GFHX8Qi_PxchP_picHHvv7rUe_qK00uoPVM_Er--jUgkQw3NJEZYyE"))
		.pause(1)
		.exec(http("request_6")
			.get(uri6 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3D0D9CA7FC14431409E3B0F7C7C93C2AD01D5EE9AA4019C9E0CFF174FA6C77E0D2")
			.headers(headers_1))
		.pause(3)
		.exec(http("request_7")
			.post(uri7 + "?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dsts%26redirect_uri%3Dhttps%253A%252F%252Fhelseid-sts.test.nhn.no%252Fsignin-testidp-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520identity%252Fpid%26response_mode%3Dform_post%26nonce%3D637438757789068937.NjdmYmNhMGEtZGEyYS00MWJlLTgyMjUtNGU5YjBiZmYwMGI1NzgxOWQwODktNzE3NC00MmFkLTkyYjItOGI2NGUyMmFkNTQy%26state%3DCfDJ8Ga6H20JSgdNlDQ5aDqJgDnTZpJk_yw11VA44VUWduFOJpSF-kuL5jfaWDLafV8vjAJlmI7AaOYLMDswRl540j9ngDj_VX7BXa2WauJLWXWU-QVgS3-eeYNRULZcfAaZ5YPJbNuhyOfV1_yaEx5QrLkhHMeAY4CTqLt5rTYhjRq4pyUyRtdguxW4X0yccf0acQtM7QuF9dcKwUn_4yzn_gO-7fCVJDo-Se_WkXVym4FFcLwheBclYu4SzSfr62pDEJ50E6S1F21HeQP6UXaRgmxeJSSVqXcau7FjV3mdt7xbJ1iB-S3DO1qYioH7D178faQkCVAC1IhdlSPxorNcXODRvovlwoFgNEyKHOu5BNiCUYiwlDvmU66M9FReJq9zg-mlXJr2i0BpY_ngBV5lc_sZW-tgFK5CDZO8NdW6dBm8kkS0q476gRnAtrTB0nyGH_oTzzChWu9eQZAkr40cyBvB8Yvk8mCznyKZ5tuRXSLzEVDzT8j-OgvgFaozcfAwNm_lCBbhhksojnbuCIRsrhv9e-NdZVfYSLOBdQddbnnGxFD_8V-uacomB32bpHJ7fRWtlCJOQ9ufTERyahVUjk4mxfs5ccdN_rd3mUe4F33wncH34TDdmLYa3esnfGT5Ww%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D5.6.0.0")
			.headers(headers_7)
			.formParam("Pid", "07015900215")
			.formParam("SecurityLevel", "4")
			.formParam("HprNumber", "")
			.formParam("__RequestVerificationToken", "CfDJ8FaTBnOJbilOiDHyjCKwidP6xmUYIFECy2K3gT2scXtTeeGmbs4belqryirW_ZPdkPaFejnyix1NMbh3BYktDDbGhUKCCe192HGqnjvFebEkv64ZxBzkqhlSKeO6VdoW_W7kLZHdFtzU4Co7bjRALPo")
			.resources(http("request_8")
			.post(uri6 + "/signin-testidp-oidc")
			.headers(headers_8)
			.formParam("code", "GK-y6i_fxG5CVmZHia4XsRPWcstIcxxTp44Pxq1mxdo")
			.formParam("id_token", "eyJhbGciOiJSUzI1NiIsImtpZCI6IlRnZVBrNS12SFRuTXRQTGI2RC1NUXciLCJ0eXAiOiJKV1QifQ.eyJuYmYiOjE2MDgyNzg5ODMsImV4cCI6MTYwODI3OTU4MywiaXNzIjoiaHR0cHM6Ly9oaWQtdGVzdGlkcC5henVyZXdlYnNpdGVzLm5ldCIsImF1ZCI6InN0cyIsIm5vbmNlIjoiNjM3NDM4NzU3Nzg5MDY4OTM3Lk5qZG1ZbU5oTUdFdFpHRXlZUzAwTVdKbExUZ3lNalV0TkdVNVlqQmlabVl3TUdJMU56Z3hPV1F3T0RrdE56RTNOQzAwTW1Ga0xUa3lZakl0T0dJMk5HVXlNbUZrTlRReSIsImlhdCI6MTYwODI3ODk4MywiY19oYXNoIjoiWXZobnpiakFZSUtaNVVfQVZ0ZnFfQSIsInNfaGFzaCI6IjVia3F3RUhCR2VJcWVxNUpRWHRrOXciLCJzaWQiOiJ1S0lJN19hOW5uTGVHQXZsWmZCQVN3Iiwic3ViIjoiNDUzODgzYjgtY2M0Yy00Mjg5LWE3ZmUtOTc0ZmY3OTE5MWQ0IiwiYXV0aF90aW1lIjoxNjA4Mjc4OTgzLCJpZHAiOiJsb2NhbCIsInBpZCI6IjA3MDE1OTAwMjE1IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCI6IjQiLCJhbXIiOlsicHdkIl19.q-U9_TWI2RpDnXeF70PV2Rd4i36LowJJts1ssFCGtw_AhhDM6yZ7bdb8TYLsxWwvnWo2DEwTADMp9LvIwWy7GXhwqVolg_OIbrZ3EIGqAJR2APA6vDW8oofrfjt8uLptLeQ7bVqi6iTetki4ielyDnjMzI-IrR21KLa0BAdcUbRJ5eAvgqX21Nn6V7AFcBiFuyXQY7QIcP_NH-dS8WPE7WAfmJw967GBPnKsLxSBJtBu8objQ5JMAaVr5rYUGP5aKzin5JSITysHAFYsrMjxEeCh92a4nblMxNLzVKZ9zBOmg_4O8xeieEuyypAaECFznbsVnA_YDNOGyRJDVeg85g")
			.formParam("scope", "openid profile identity/pid")
			.formParam("state", "CfDJ8Ga6H20JSgdNlDQ5aDqJgDnTZpJk_yw11VA44VUWduFOJpSF-kuL5jfaWDLafV8vjAJlmI7AaOYLMDswRl540j9ngDj_VX7BXa2WauJLWXWU-QVgS3-eeYNRULZcfAaZ5YPJbNuhyOfV1_yaEx5QrLkhHMeAY4CTqLt5rTYhjRq4pyUyRtdguxW4X0yccf0acQtM7QuF9dcKwUn_4yzn_gO-7fCVJDo-Se_WkXVym4FFcLwheBclYu4SzSfr62pDEJ50E6S1F21HeQP6UXaRgmxeJSSVqXcau7FjV3mdt7xbJ1iB-S3DO1qYioH7D178faQkCVAC1IhdlSPxorNcXODRvovlwoFgNEyKHOu5BNiCUYiwlDvmU66M9FReJq9zg-mlXJr2i0BpY_ngBV5lc_sZW-tgFK5CDZO8NdW6dBm8kkS0q476gRnAtrTB0nyGH_oTzzChWu9eQZAkr40cyBvB8Yvk8mCznyKZ5tuRXSLzEVDzT8j-OgvgFaozcfAwNm_lCBbhhksojnbuCIRsrhv9e-NdZVfYSLOBdQddbnnGxFD_8V-uacomB32bpHJ7fRWtlCJOQ9ufTERyahVUjk4mxfs5ccdN_rd3mUe4F33wncH34TDdmLYa3esnfGT5Ww")
			.formParam("session_state", "Iv9mtmT9v-grGlcuo_p1NEqn3U5_tg07jSja4jh1iU0.KEuv_v3yS1f_S7trVZJWTg"),
            http("request_9")
			.post("/signin-oidc")
			.headers(headers_8)
			.formParam("code", "31A9497014E4C22803FC56BD26D3137832DA9D0AD0FE13FFBDD03DCBABDA18B4")
			.formParam("scope", "openid profile e-helse/sfm.api/sfm.api helseid://scopes/identity/pid helseid://scopes/identity/security_level helseid://scopes/hpr/hpr_number offline_access")
			.formParam("state", "CfDJ8EIQDhsJrOxOqIiXGzMJBCH8t0NlL-dZmPscSSTOvB-_22fD4hDZj6bqs497tDjYx99rsRtz6FM7C03H0z1pEVvH2DKwfdQmwMcgKHC35h5YN3KPw_ptgW3fg88FTEjrFomNPWhmkBZaqZFVTDYNJhavH1D03RrflRI6DBjY5P3oH5EbLTksBuAMeWjTtz9SXIaChTbswhaHLwyoHpxLNl7SK7WVqdJz7resWQQ8-QBTynkzRmqq5ifeVOZe61Fht0m8-61X1_ICy5nkgahZvQMPHp3fMevfDH39NQwp-THEpTSIrroOF46DB3yYSRpoqnw04pw550tDfZA3cg-XsqkAoMMYRpslRHPiemrDDxHQT7hEcX5FbPllYMO2lnMmLlcJy0_nDLaldLtio2YOqrQYwezfKYMfnAMLBvs7imTQdF8f82odAvcRc05PUfktcNarc00vNhMwNmDGnPNK8JjR9tOol8g9OKP1n9ixn3ESTTKs61ZowuulsXz7fVPh5Shks7kbS39f5NYj87O44ST1jfzZEZL2eVQm8RyrLo5xlPlWWrFtcXJqK5ICr7PaR0gpR8BRw-OseDBVpNHDVRBbwCiFpE52PBXiGQxUX2Gw9eK2tguBD_vDK2VQX0iHRw")
			.formParam("session_state", "w8NwFcliGajl7izgCk1Xf8op5E8LWIvpCLh5H4VBe90.82E715B8F7E6074F703ADB7CCF35A25A")))
		.pause(7)
		.exec(http("request_10")
			.post("/Patient/LoadClientAsync")
			.headers(headers_3)
			.formParam("OnBehalfOf", "")
			.formParam("ShowAllergies", "true")
			.formParam("SelectedFnr", "03021454701")
			.formParam("SelectedEnvironment", "Glittertind testlegekontor (Fastlege)")
			.formParam("ApiUrl", "")
			.formParam("HelseIdClientId", "ffad58fd-a86f-4122-8541-42a82f719fe8")
			.formParam("submitButton", "open")
			.formParam("__RequestVerificationToken", "CfDJ8EIQDhsJrOxOqIiXGzMJBCF86-B9N-B_bfAlh8RP2ZTE5_rsw41YrGs6vXD84F8BSL5webtqw05-_deP49NsWoKZbQO-scR4F5-LrQwk2ehD-BfiLTckRe2W70t9T7IHZZu-7yhblecP6X9Y6jXiIG7UtGb84c5W_YJiKexafKCFE71Shx2ljiL8ZW2h3hMi3Q")
			.formParam("ShowAllergies", "false")
			.resources(http("request_11")
			.get(uri1 + "/")
			.headers(headers_1),
            http("request_12")
			.get(uri1 + "/styles.aad74f74f18b76bf1b4f.css")
			.headers(headers_12),
            http("request_13")
			.get(uri1 + "/main-es2018.0b63300c65bdd1191419.js"),
            http("request_14")
			.get(uri1 + "/runtime-es2018.121c1274a11ea7a4e8c8.js"),
            http("request_15")
			.get(uri1 + "/polyfills-es2018.7474306e653c1078eb05.js"),
            http("request_16")
			.get(uri1 + "/assets/i18n/no.json")
			.headers(headers_16),
            http("request_17")
			.options(uri4 + "/api/Login")
			.headers(headers_17),
            http("request_18")
			.options(uri5 + "/")
			.headers(headers_18),
            http("request_19")
			.post(uri4 + "/api/Login")
			.headers(headers_19)
			.body(RawFileBody("computerdatabase/friday/0019_request.txt")),
            http("request_20")
			.options(uri5 + "/")
			.headers(headers_18),
            http("request_21")
			.options(uri4 + "/api/patients/81037feb-1472-4b08-a851-1aa2b6cbf213")
			.headers(headers_21),
            http("request_22")
			.post(uri5 + "/")
			.headers(headers_22)
			.body(RawFileBody("computerdatabase/friday/0022_request.json")),
            http("request_23")
			.post(uri5 + "/")
			.headers(headers_23)
			.body(RawFileBody("computerdatabase/friday/0023_request.json")),
            http("request_24")
			.get(uri4 + "/api/patients/81037feb-1472-4b08-a851-1aa2b6cbf213")
			.headers(headers_24),
            http("request_25")
			.options(uri4 + "/hubs/sfmHub/negotiate?patientTicket=81037feb-1472-4b08-a851-1aa2b6cbf213&negotiateVersion=1")
			.headers(headers_25),
            http("request_26")
			.get(uri1 + "/version.json?t=1608278994098")
			.headers(headers_16),
            http("request_27")
			.options(uri4 + "/api/patientJournals/result/?patientTicket=81037feb-1472-4b08-a851-1aa2b6cbf213")
			.headers(headers_21),
            http("request_28")
			.post(uri4 + "/hubs/sfmHub/negotiate?patientTicket=81037feb-1472-4b08-a851-1aa2b6cbf213&negotiateVersion=1")
			.headers(headers_28),
            http("request_29")
			.options(uri4 + "/api/catalogues/lib/2020-12-10%2011:05:48")
			.headers(headers_21),
            http("request_30")
			.options(uri4 + "/api/institutions/")
			.headers(headers_21),
            http("request_31")
			.options(uri4 + "/api/users/current")
			.headers(headers_21),
            http("request_32")
			.post(uri5 + "/")
			.headers(headers_32)
			.body(RawFileBody("computerdatabase/friday/0032_request.json")),
            http("request_33")
			.options(uri4 + "/api/serverinfo/version")
			.headers(headers_21),
            http("request_34")
			.options(uri4 + "/api/prescriptions/?patientTicket=81037feb-1472-4b08-a851-1aa2b6cbf213")
			.headers(headers_21),
            http("request_35")
			.get(uri4 + "/api/catalogues/lib/2020-12-10%2011:05:48")
			.headers(headers_24),
            http("request_36")
			.get(uri4 + "/api/serverinfo/version")
			.headers(headers_24),
            http("request_37")
			.get(uri4 + "/api/patientJournals/result/?patientTicket=81037feb-1472-4b08-a851-1aa2b6cbf213")
			.headers(headers_24),
            http("request_38")
			.get(uri4 + "/api/institutions/")
			.headers(headers_24),
            http("request_39")
			.get(uri4 + "/api/users/current")
			.headers(headers_24),
            http("request_40")
			.options(uri4 + "/api/catalogues/localDrugs")
			.headers(headers_21),
            http("request_41")
			.get(uri4 + "/api/catalogues/localDrugs")
			.headers(headers_24),
            http("request_42")
			.get(uri4 + "/api/prescriptions/?patientTicket=81037feb-1472-4b08-a851-1aa2b6cbf213")
			.headers(headers_24),
            http("request_43")
			.get(uri1 + "/SourceSansPro-Semibold.ttf.acbf737b5bfddd31d0f6.woff2")
			.headers(headers_43)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}