package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class JMeterCert extends Simulation {

	val httpProtocol = http
		.baseUrl("https://epj.qa.forskrivning.no")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:83.0) Gecko/20100101 Firefox/83.0")

	val headers_1 = Map("Origin" -> "https://epj.qa.forskrivning.no")

	val headers_4 = Map("Origin" -> "https://hid-testidp.azurewebsites.net")

	val headers_5 = Map("Origin" -> "null")

    val uri1 = "https://helseid-sts.test.nhn.no"
    val uri3 = "https://hid-testidp.azurewebsites.net/Account/Login"

	val scn = scenario("JMeterCert")
		.exec(http("request_0")
			.get("/"))
		.pause(12)
		.exec(http("request_1")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "")
			.formParam("IsSelectingInstallation", "False")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "CfDJ8OKdjf3R47FHgBEqJUeDXBneXo5-_gEnZBsWvxMkEGpahBhIO9Ym9IbFoBvH8qbmanPl7lGcwXdPvs9WJLs886AcNdmsmKYhSMl67AUZ7YQ82y2FKhq9qVAJmdJ7wSxsJm0we61RybjeW-oFy6Pr6xk"))
		.pause(5)
		.exec(http("request_2")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedEnvironmentId", "ffad58fd-a86f-4122-8541-42a82f719fe8#Glittertind testlegekontor (Fastlege)")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("IsSelectingInstallation", "True")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "CfDJ8OKdjf3R47FHgBEqJUeDXBlJw0HPxvASGJKNry01Ev4logY9kJd1dweZQpX9VFWkad-Xqj-pO3uGnWX_F_A8ejNxBp62ky0kJdYLfL9swoGd_JdMlqBItp54Ik-ULjwnnTDJyIhGSnkhl5szB_RJ32c"))
		.pause(5)
		.exec(http("request_3")
			.get(uri1 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3DmB8K9cRDAyP9gjhS-zRyk90ZIWuAa3prXOAsHuDYluU"))
		.pause(5)
		.exec(http("request_4")
			.post(uri3 + "?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dsts%26redirect_uri%3Dhttps%253A%252F%252Fhelseid-sts.test.nhn.no%252Fsignin-testidp-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520identity%252Fpid%26response_mode%3Dform_post%26nonce%3D637431292543219834.NzhlY2Y5MTQtODlhMS00ZDhlLWI1ODktZGMxY2Y0NWIwNTk3ZTYzM2JjOTctYTAzZC00MWJkLTllZWYtYzNiYWM0ODEyYjU4%26state%3DCfDJ8Ga6H20JSgdNlDQ5aDqJgDl22t-jFoSN0knyjbtHTo667JtGN8FG_ZA1Q1F7oLKsZhwD2EoGcetdxvh3bOxPJNJII4Nf_1IfunZWMPW86nCloTOki4Jb267oYebdKBtGQDvQ6qiRCn0JaW_glneCq0Lxf8gEYkEQGQ7MPRpgtH8nlkuSW9TyGwDaZmnIlO9BKPyCw_0lNBXDYbEgHen09OvQaHaHYUJySThGUiy9SmLc2QaLAMip8s1lQ1ZbeKsFXrlr5SgX-7UknNklAo7SJdxwnaPapDqEbAwVIfRqRuHZ0eKmyXZcqPAFsM5TW8GHckkKKsKLp8BnTOkFJJ-gWCEJZUmkKUyBGoAnRh-WJ7cP3FS00DQiAzhrTF50NVM0nSGGqAdIfwvNbljLmtiVXwhOrJ0Ro17FmbMlNFaUA09JfeUkJbdf6KXZ9UYeds0BqKW6qp5A_aU4-6cEBg4mZG4nP_x-Jr8im1xAqr_FJ1sBl-hgO1Lo4fJau5Q4HFPEvJHlyX49NSrMJG97spazck5DgKFyX8X1odhbxc-Eyg-33z4Wm6xHI_SyYH3kbExxYxWZmEExr_x4OQbeVVSHE38IlUDTa2YAi-BXYANcOKAx%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D5.6.0.0")
			.headers(headers_4)
			.formParam("Pid", "07015900215")
			.formParam("SecurityLevel", "4")
			.formParam("HprNumber", "")
			.formParam("__RequestVerificationToken", "CfDJ8FaTBnOJbilOiDHyjCKwidPB6cmF3P9ZAOv4s8cK3KksIx19ne2iMjt-YYrDdZ87NWRxo8M6MxzFkcty5v2PIyDuMnCydt7drKkEL7SmJgnddiuFOD4zU6hvMdWcdNMFB2RCrP2pk-Q461LOxw12BhA")
			.resources(http("request_5")
			.post(uri1 + "/signin-testidp-oidc")
			.headers(headers_5)
			.formParam("code", "vUcBg0xXHcawDuV_UzP6cp0AGcRcHkZ6S77OR7dre_w")
			.formParam("id_token", "eyJhbGciOiJSUzI1NiIsImtpZCI6IlRnZVBrNS12SFRuTXRQTGI2RC1NUXciLCJ0eXAiOiJKV1QifQ.eyJuYmYiOjE2MDc1MzI0NjAsImV4cCI6MTYwNzUzMzA2MCwiaXNzIjoiaHR0cHM6Ly9oaWQtdGVzdGlkcC5henVyZXdlYnNpdGVzLm5ldCIsImF1ZCI6InN0cyIsIm5vbmNlIjoiNjM3NDMxMjkyNTQzMjE5ODM0Lk56aGxZMlk1TVRRdE9EbGhNUzAwWkRobExXSTFPRGt0WkdNeFkyWTBOV0l3TlRrM1pUWXpNMkpqT1RjdFlUQXpaQzAwTVdKa0xUbGxaV1l0WXpOaVlXTTBPREV5WWpVNCIsImlhdCI6MTYwNzUzMjQ2MCwiY19oYXNoIjoieHg4YURScDBtY1JPYkg5Z2lrVmQ3QSIsInNfaGFzaCI6IkU4blVIUjh4VndmblgwZ09oc1htWFEiLCJzaWQiOiJwcThXZ3BhYktnU0lRaG5tM25sZTN3Iiwic3ViIjoiZjI1ZjVhOWItNzZhMi00OGNjLWI5MTAtMThmZjcxOTMwM2JlIiwiYXV0aF90aW1lIjoxNjA3NTMyNDYwLCJpZHAiOiJsb2NhbCIsInBpZCI6IjA3MDE1OTAwMjE1IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCI6IjQiLCJhbXIiOlsicHdkIl19.DCEdTkn_HOzdqlR5KKyNzEwcOoycC9-ogxPSLkvxWmfv0epTCz9YTWGoeGdiFqaitpkVrgzTg1DFMnOf57oIA93UOveJhbdsuqgHRelqfvNu__0QcYJi3bx_Pl4tmXOGhIeZf2SyJTRKS5bz1vdbA5P3dUQGw6_YtZLiYnba5AhKXasrryrm38zcOfwS-Qo5ITg09Gd8GnqyeyoW_QkCXX4FaNlyP2f1HAoqqYxxymLZz5HzMHXGb0zVad0pLUogYMGCFwZ2WAcbaeCQw130Ve7zd0ko-8A4ElqMzNdYDXVtKncUOcLwJSB4ZrkkSimHBEFlFo_bov0mPSVlaX3rUQ")
			.formParam("scope", "openid profile identity/pid")
			.formParam("state", "CfDJ8Ga6H20JSgdNlDQ5aDqJgDl22t-jFoSN0knyjbtHTo667JtGN8FG_ZA1Q1F7oLKsZhwD2EoGcetdxvh3bOxPJNJII4Nf_1IfunZWMPW86nCloTOki4Jb267oYebdKBtGQDvQ6qiRCn0JaW_glneCq0Lxf8gEYkEQGQ7MPRpgtH8nlkuSW9TyGwDaZmnIlO9BKPyCw_0lNBXDYbEgHen09OvQaHaHYUJySThGUiy9SmLc2QaLAMip8s1lQ1ZbeKsFXrlr5SgX-7UknNklAo7SJdxwnaPapDqEbAwVIfRqRuHZ0eKmyXZcqPAFsM5TW8GHckkKKsKLp8BnTOkFJJ-gWCEJZUmkKUyBGoAnRh-WJ7cP3FS00DQiAzhrTF50NVM0nSGGqAdIfwvNbljLmtiVXwhOrJ0Ro17FmbMlNFaUA09JfeUkJbdf6KXZ9UYeds0BqKW6qp5A_aU4-6cEBg4mZG4nP_x-Jr8im1xAqr_FJ1sBl-hgO1Lo4fJau5Q4HFPEvJHlyX49NSrMJG97spazck5DgKFyX8X1odhbxc-Eyg-33z4Wm6xHI_SyYH3kbExxYxWZmEExr_x4OQbeVVSHE38IlUDTa2YAi-BXYANcOKAx")
			.formParam("session_state", "qIe9A3rDN9A-pbh3gnCSUPLwBs1s1ISwZo4KLljqxY0.tlxT7umMbVtcW3FSCssIrg"),
            http("request_6")
			.post("/signin-oidc")
			.headers(headers_5)
			.formParam("code", "a0vT7rLJCCJOQu_suth43ZavZfH1tptssK5krjJeWq8")
			.formParam("scope", "openid profile helseid://scopes/identity/pid helseid://scopes/identity/security_level helseid://scopes/hpr/hpr_number e-helse/sfm.api/sfm.api offline_access")
			.formParam("state", "CfDJ8OKdjf3R47FHgBEqJUeDXBnMt-_xT1fiLiJLDHR1dXk6p1av1dSyK8_O_rVui-VC-2ittQXo7Wf2LSsZDvuS8ovw6Use0aUyvvLJyNFCeftfZJesePjjVCldCfnWfah3qD0x6AYGTAlmxZatvTtMwaRgSaI1NXbGxP5UlOrpgOAPV5jgYE6h5qdgb8hf03NViEkHhaSe3P--CiBg0t91RHJj-cKdrRrGj1dMjgjxbMsW67I5THYxhFGfVjtruxx5suFKyh8z4ua76HI008JRnOS-N-nFovV8Kny26YYWKjGj_Il-JTmwC0FH6O5WvB9qYk1gaKu2mNcVsNX51eU7oV9TLARRUsBNWZn3JkrvsOvKtJh4HBO_FOj21I-3i5mTow4-Hxb-OZh59zK_ZxJFzwTfHkIZ98OQuQLj6tIzUZCd41LI4qlYdVkCQFfWpM-Lgoh0Q5cO6LBo1OIzZngul0XwFA6SGJpeji2NhDDm2ZWXPsXLJmFFdxkSXTHQI4DoukGN4Rvwlbrt5ntm6WH80gSFP7QF845lgQjKIOIkbVxD5ISju2DOHelizD4cEBRz3Q")
			.formParam("session_state", "EHmlGEa6AFAJyepWEMb0qckldYlkKxmR5423_v6vt9w.mf859ouDnGqSUnypiEqc3w")
			.check(status.is(500))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}