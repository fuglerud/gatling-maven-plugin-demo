package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://epj.test1.forskrivning.no")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Origin" -> "https://epj.test1.forskrivning.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_3 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_8 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_9 = Map("Accept" -> "*/*")

	val headers_13 = Map("Accept" -> "image/webp,*/*")

	val headers_14 = Map(
		"Origin" -> "https://hid-testidp.azurewebsites.net",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_15 = Map(
		"Origin" -> "null",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "https://helseid-sts.test.nhn.no"
    val uri3 = "https://hid-testidp.azurewebsites.net"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0))
		.pause(7)
		.exec(http("request_1")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "")
			.formParam("IsSelectingInstallation", "False")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "CfDJ8F2JoDoX_OpAubjy6y8Rpw0mBC6c5vU-BrwBykC3qfCAtBRykBjVQ05iNjxx98Mae0K7jhK_VOY-AAHSmFphinibPnA6br3Vqm4XBI_nPct7aP1QY3ZeXgJRKnuPY95FsEPSN9uMR8EQZ6sgwFO2Yvk"))
		.pause(2)
		.exec(http("request_2")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedEnvironmentId", "b2ef1091-55ee-4608-a399-3220c05d738a#Snøhetta PLO institusjon (Plo)")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("IsSelectingInstallation", "True")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "CfDJ8F2JoDoX_OpAubjy6y8Rpw2cwBMrO1SWeG3Vidf59d61JmOiENXHB990dIAf1Ht9lRByH6TRIuqpMrpq7xCTOc4AWxQXzgLJA1UpmIorT4E4aYlXbrif4NRTMjN412tzfiMzyIhDENGVJtcCa7NhwQ8")
			.resources(http("request_3")
			.get(uri1 + "/lib/open-sans/open-sans/open-sans-v13-latin-600.woff2")
			.headers(headers_3),
            http("request_4")
			.get(uri1 + "/lib/open-sans/open-sans/open-sans-v13-latin-700.woff2")
			.headers(headers_3),
            http("request_5")
			.get(uri1 + "/lib/open-sans/open-sans/open-sans-v13-latin-regular.woff2")
			.headers(headers_3),
            http("request_6")
			.get(uri1 + "/lib/font-awesome/fonts/fontawesome-webfont.woff2?v=4.7.0")
			.headers(headers_3)))
		.pause(1)
		.exec(http("request_7")
			.get(uri1 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3D5XXqnALu3DbkuRGbRk8qR6ISPl7Ra_kgrDOEAQvH8ZI")
			.headers(headers_0)
			.resources(http("request_8")
			.get(uri3 + "/lib/bootstrap/dist/css/bootstrap.min.css")
			.headers(headers_8),
            http("request_9")
			.get(uri3 + "/lib/jquery/dist/jquery.min.js")
			.headers(headers_9),
            http("request_10")
			.get(uri3 + "/lib/bootstrap/dist/js/bootstrap.min.js")
			.headers(headers_9),
            http("request_11")
			.get(uri3 + "/lib/jquery-validation/dist/jquery.validate.min.js")
			.headers(headers_9),
            http("request_12")
			.get(uri3 + "/lib/jquery-validation-unobtrusive/jquery.validate.unobtrusive.min.js")
			.headers(headers_9),
            http("request_13")
			.get(uri3 + "/favicon.ico")
			.headers(headers_13)))
		.pause(18)
		.exec(http("request_14")
			.post(uri3 + "/Account/Login?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dsts%26redirect_uri%3Dhttps%253A%252F%252Fhelseid-sts.test.nhn.no%252Fsignin-testidp-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520identity%252Fpid%26response_mode%3Dform_post%26nonce%3D637334362865701888.YWE3ZmI5MDktNGQ1Ny00ZTYxLWE5ZmEtMDA0ZDQ4MDBjMGU2NjYyMTMzNDAtOGM3Yy00ZjUwLWI0MWItNzhiM2RkOTcwZDUz%26state%3DCfDJ8LtSO6YKVp5IlPIVOrjgMFP3-oHJ-1arg6HFv5t40OYh0Gz661Wxej7ytlHug8Plthb0_ZoYZndmv3J5Wbf9WDsWBBlgrVtUtnVjigryYvq4yoRI2As-P6PhCt_Ez4iVJWSFdqZT3rfO0DAu_VfD3aFtpjKJDtDWAvd_ozsPefru76Majuh_AA1KODZ8EIYQbBtkH2S634EpMVDnA4Rs217Xd3D2_5vehtRZMtXhgOQJi5P2ImIteNt2Uh4oLlOhfM-sIz0IsfUsnxJLze5VOXSXyvt9LIXTc-udWIJj0AtXbFAtSlh8caMCDEj9QXTwnOmf2cfAVQD8EHXgzl1YkNWJb8TZekaii2G8sbRIibA7Br-nVpxTKMoh6lRk9yp2nFqTsnRZokjSOdaTgNKekuZSVsN6YdAwv3habLUZ28w9riM1iu4_93Vj6765vSyrDbF4sZiVW0KKGQ_tNXUEG8RHmwJayY5ojxd24dzbk2zv28XYlPssgX6jQmYFkZS7Bg0gT1LddOXvQC4q0lduzVgNHrO1BhqOgNf4MjgAau2KChy1UQrLpxa4COjShq6Bc88WCGNdMG3CQFIHnZsBycvFRtLaZHxyVmlPuCtrlWdc%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D5.6.0.0")
			.headers(headers_14)
			.formParam("Pid", "07015900215")
			.formParam("SecurityLevel", "4")
			.formParam("HprNumber", "")
			.formParam("__RequestVerificationToken", "CfDJ8KWqZL0kb0BNnN456npmnMLOT_zQ17tGGlqBcCDXdKz09rKvqL87gvOM3Y1It9cMsTl7a8Xu76w19_4ycU8rq8McfWiWGjjVuljuxXNzNVE_Dj3Ub7_7_evQ5klI-ntDFCp6i_9ym8bbIDzM8XhtbEQ")
			.resources(http("request_15")
			.post(uri1 + "/signin-testidp-oidc")
			.headers(headers_15)
			.formParam("code", "jGGJNcENDHAa0u_vPHWZ9FB3QO94ytOBQgqpXPwi5p4")
			.formParam("id_token", "eyJhbGciOiJSUzI1NiIsImtpZCI6IlRnZVBrNS12SFRuTXRQTGI2RC1NUXciLCJ0eXAiOiJKV1QifQ.eyJuYmYiOjE1OTc4Mzk1MTAsImV4cCI6MTU5Nzg0MDExMCwiaXNzIjoiaHR0cHM6Ly9oaWQtdGVzdGlkcC5henVyZXdlYnNpdGVzLm5ldCIsImF1ZCI6InN0cyIsIm5vbmNlIjoiNjM3MzM0MzYyODY1NzAxODg4LllXRTNabUk1TURrdE5HUTFOeTAwWlRZeExXRTVabUV0TURBMFpEUTRNREJqTUdVMk5qWXlNVE16TkRBdE9HTTNZeTAwWmpVd0xXSTBNV0l0TnpoaU0yUmtPVGN3WkRVeiIsImlhdCI6MTU5NzgzOTUxMCwiY19oYXNoIjoiTG5ORjZZTXNuNGI1cG10enpjMXhVZyIsInNfaGFzaCI6ImtrRDRRZlluRUNjeWt1WDVvRk96ZEEiLCJzaWQiOiI4bFhLcGtkNzhTU3lUSkczOGFvWm5BIiwic3ViIjoiZjE4MDg2ZWMtYjQ3Ni00MThhLWJkNGMtZWZiYmU1OGM4MTQ2IiwiYXV0aF90aW1lIjoxNTk3ODM5NTA5LCJpZHAiOiJsb2NhbCIsInBpZCI6IjA3MDE1OTAwMjE1IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCI6IjQiLCJhbXIiOlsicHdkIl19.FKKEU8LAO0MursS3XZ8hq6KPmREd7A1XK7N13trg1lyhYrSeMcGKpsglRM-hM9NAKvGD9z_iT8rrJNOGu5ITiB11n3uicAk-0lmlOJ7Inz0OFOJgLpfxtc1RVENisFTPPbxHmLfnUHQONGbM0xs-7Yia3GN8BMbgAtOzs8YqPSoLL_Q7gQZMiWxxwQ_zc2IrEyGmHzsajz98qap2PHu8xsSeGv5bB4NPhWusc1i3EGUvSKhN09ENo3De8JVln0jyvAAZ3iJXSy54wYZMlUgxfciqUrqAVxOs-dgD7kk90P5evgstPYklHXrT02qypqBx78CjESLZlreVO_recDUdyg")
			.formParam("scope", "openid profile identity/pid")
			.formParam("state", "CfDJ8LtSO6YKVp5IlPIVOrjgMFP3-oHJ-1arg6HFv5t40OYh0Gz661Wxej7ytlHug8Plthb0_ZoYZndmv3J5Wbf9WDsWBBlgrVtUtnVjigryYvq4yoRI2As-P6PhCt_Ez4iVJWSFdqZT3rfO0DAu_VfD3aFtpjKJDtDWAvd_ozsPefru76Majuh_AA1KODZ8EIYQbBtkH2S634EpMVDnA4Rs217Xd3D2_5vehtRZMtXhgOQJi5P2ImIteNt2Uh4oLlOhfM-sIz0IsfUsnxJLze5VOXSXyvt9LIXTc-udWIJj0AtXbFAtSlh8caMCDEj9QXTwnOmf2cfAVQD8EHXgzl1YkNWJb8TZekaii2G8sbRIibA7Br-nVpxTKMoh6lRk9yp2nFqTsnRZokjSOdaTgNKekuZSVsN6YdAwv3habLUZ28w9riM1iu4_93Vj6765vSyrDbF4sZiVW0KKGQ_tNXUEG8RHmwJayY5ojxd24dzbk2zv28XYlPssgX6jQmYFkZS7Bg0gT1LddOXvQC4q0lduzVgNHrO1BhqOgNf4MjgAau2KChy1UQrLpxa4COjShq6Bc88WCGNdMG3CQFIHnZsBycvFRtLaZHxyVmlPuCtrlWdc")
			.formParam("session_state", "Kzk_DTc9rL3eYyZkkBo8QgNE2R4HVL5d_2_jVYlfWhU.1K244UFtDUqc_7_RJVH3nA"),
            http("request_16")
			.post("/signin-oidc")
			.headers(headers_15)
			.formParam("code", "aF0WnZSUXtiq6DZ-RKaDyBn4MEusVYCYdV1HtVNdstI")
			.formParam("scope", "openid profile helseid://scopes/identity/pid helseid://scopes/identity/security_level helseid://scopes/hpr/hpr_number e-helse/sfm.api/sfm.api")
			.formParam("state", "CfDJ8F2JoDoX_OpAubjy6y8Rpw3MK31qhtfcjSyXWnVYPT98CrDicN_I0zOVo-MP-wvFNh5o8zlgZn1oOoXBfUWtQZEsTHrA3O5UkCcv2oMZ6IMALHYtRmLVQjB2fe_j3VS_JmGU_53xPq34-VrHXNBdjFoWaHvL_0-O64JqPY5S9g6XHMTiqbdhYs1xCogW4xJ2od6XGRNDokMINVX9pJQPk3B3XvV8tsI-OGFNBldy6fufBmGOru_pW3ZNJ3LQBSVktV_PG_nt3OZxF1AoImamY96kPd-Iwqnnbhkonm_FoawrRIfVzB8zzHHeHeUj0xlUz7AE-hbZuawFAA-FSWBs6rrw1xIMGpX-qn3JlyDpZW7CbiyVAbN2BJ0yxS5H4esx7m4sRNtyC2rEr2VV9qjwsOFeebVAptZ9bU29ECKW2I9NIge1gLCWWJHudbtVAATyROMWZ69zGdPa-d3iXNX9BGrKyhs0F9vqWgrB7PI_uGz5KtngabXLJZt3-fyn9fxUsDnDHSXFgYUMttv4mLt9HvL8VpgH410A_fgVh-x2WUmnL1RImXoC28xx9f_0Yp0m1w")
			.formParam("session_state", "LGSh7DLrXnLeKUdMHYeLTAeGJz_TabvhbN0xZcXR1bI.t6wFZyd5WtURli7Rk8CzvQ")))
		.pause(5)
		.exec(http("request_17")
			.post("/Patient/LoadClientAsync")
			.headers(headers_1)
			.formParam("OnBehalfOf", "")
			.formParam("ShowAllergies", "true")
			.formParam("SelectedTicket", "54688c89-e7d2-4662-a054-f24daabafa50")
			.formParam("SelectedEnvironment", "Snøhetta PLO institusjon (Plo)")
			.formParam("ApiEndpoint", "https://server.test1.forskrivning.no")
			.formParam("HelseIdClientId", "b2ef1091-55ee-4608-a399-3220c05d738a")
			.formParam("submitButton", "open")
			.formParam("__RequestVerificationToken", "CfDJ8F2JoDoX_OpAubjy6y8Rpw2pcPELUJaJzu4fZvsYNlaSR8_oPTbx-bPs1lbH5wZrVGbrCvzWBtmOT5l0y7GoRi-thZRUuQlM0zyaKhYVeKmN5Z_utrm4rJkJglzh4yFjE_G1cVZ6b9T9vk10PdrDYADECscSx01bD3JCcgw6zTrFv3nja8k_u74PRor9mcsTVg")
			.formParam("ShowAllergies", "false"))
		.pause(2)
		.exec(http("request_18")
			.get("/Patient?SelectedEnvironment=Sn%C3%B8hetta%20PLO%20institusjon%20(Plo)&HelseIdClientId=b2ef1091-55ee-4608-a399-3220c05d738a&ShowAllergies=True")
			.headers(headers_0))
		.pause(12)
		.exec(http("request_19")
			.post("/Patient/LoadClientAsync")
			.headers(headers_1)
			.formParam("OnBehalfOf", "")
			.formParam("ShowAllergies", "true")
			.formParam("SelectedTicket", "54688c89-e7d2-4662-a054-f24daabafa50")
			.formParam("SelectedEnvironment", "Snøhetta PLO institusjon (Plo)")
			.formParam("ApiEndpoint", "https://server.test1.forskrivning.no")
			.formParam("HelseIdClientId", "b2ef1091-55ee-4608-a399-3220c05d738a")
			.formParam("submitButton", "open")
			.formParam("__RequestVerificationToken", "CfDJ8F2JoDoX_OpAubjy6y8Rpw3zUf1G5URTQwmAezSYLJyxTGBfAMuo_HlHAY2P7dli9w-JSRUJ6yakM6Ix4znel3Ls32JCxQQfo5EE3nxrzdhLUULZXzwM0qRqQcqQ8dpP0iVeIDcDrMZBkjhn9RwT0lnAQLexDOmSDSx2Zrs-o7PhEISA0kajcAhY7sgh0tB9OA")
			.formParam("ShowAllergies", "false"))
		.pause(2)
		.exec(http("request_20")
			.get("/Patient?SelectedEnvironment=Sn%C3%B8hetta%20PLO%20institusjon%20(Plo)&HelseIdClientId=b2ef1091-55ee-4608-a399-3220c05d738a&ShowAllergies=True")
			.headers(headers_0))
		.pause(2)
		.exec(http("request_21")
			.post("/Patient/LoadClientAsync")
			.headers(headers_1)
			.formParam("OnBehalfOf", "")
			.formParam("ShowAllergies", "true")
			.formParam("SelectedTicket", "54688c89-e7d2-4662-a054-f24daabafa50")
			.formParam("SelectedEnvironment", "Snøhetta PLO institusjon (Plo)")
			.formParam("ApiEndpoint", "https://server.test1.forskrivning.no")
			.formParam("HelseIdClientId", "b2ef1091-55ee-4608-a399-3220c05d738a")
			.formParam("submitButton", "open")
			.formParam("__RequestVerificationToken", "CfDJ8F2JoDoX_OpAubjy6y8Rpw1mdfuSlZUjPGnQjRrqtxVmiZ9NeeOCbIXYQa1fDA5Gpr4vcw3wRYN11awNL01B0J1ANeeY3PZwmlrAYppdUFuF52TUykSu_G3417pa5MW9QUBq0iyVEcff_0teyxvFbK24mwTOqmjEmMfGLDNFjitzPF5JdQ6hM3PS-mbujQ-gIQ")
			.formParam("ShowAllergies", "false"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}