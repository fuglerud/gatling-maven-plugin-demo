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
			.get("/")
			.check(regex("""<input name="__RequestVerificationToken" type="hidden" value="([^"]*)" />""").saveAs("requestVerificationToken1")))

		.exec(session=>{
			println("___RequestVerificationToken 1:")
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
			println("___RequestVerificationToken 2:")
			println(session("requestVerificationToken2").as[String])
			session})

		.exec(http("request_2")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedEnvironmentId", "ffad58fd-a86f-4122-8541-42a82f719fe8#Glittertind testlegekontor (Fastlege)")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("IsSelectingInstallation", "True")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken2}"))


		.exec(http("request_3")
			.get(uri1 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3DZF1T-PA-PR5l7Yj2bG6qhyia4t9E0F87ax1eFtoMTfU")
			.check(regex("HPR-testregistry").exists)
			.check(regex("nonce%(.*?)%26").saveAs("nonce"))
			.check(regex("state%(.*?)%26").saveAs("state"))
			.check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("RequestVerificationToken3"))
			.check(regex("returnurl=%(.*?)0.0").saveAs("returnurl"))
		)

		.exec(session=>{
			println("nonce:")
			println(session("nonce").as[String])
			session})

		.exec(session=>{
			println("state:")
			println(session("state").as[String])
			session})

		.exec(session=>{
			println("RequestVerificationToken3:")
			println(session("RequestVerificationToken3").as[String])
			session})

		.exec(session=>{
			println("returnurl:")
			println(session("returnurl").as[String])
			session})



      .exec(http("request_4")
        .post(uri3 + "?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dsts%26redirect_uri%3Dhttps%253A%252F%252Fhelseid-sts.test.nhn.no%252Fsignin-testidp-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520identity%252Fpid%26response_mode%3Dform_post%26nonce%3D${nonce}%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D5.6.0.0")
        .headers(headers_4)
        .formParam("Pid", "07015900215")
        .formParam("SecurityLevel", "4")
        .formParam("HprNumber", "")
        .formParam("__RequestVerificationToken", "${RequestVerificationToken3}")
				.check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
				.check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
				.check(regex("name='id_token' value='(.*?)' />").saveAs("id_token"))
				//

        .resources(http("request_5")
        .post(uri1 + "/signin-testidp-oidc")
        .headers(headers_5)
        .formParam("code", "${code}")
        .formParam("id_token", "${id_token}")
        .formParam("scope", "openid profile identity/pid")
        .formParam("state", "${state}")
        .formParam("session_state", "${session_state}"),

              http("request_6")
        .post("/signin-oidc")
        .headers(headers_5)
        .formParam("code", "a0vT7rLJCCJOQu_suth43ZavZfH1tptssK5krjJeWq8")
        .formParam("scope", "openid profile helseid://scopes/identity/pid helseid://scopes/identity/security_level helseid://scopes/hpr/hpr_number e-helse/sfm.api/sfm.api offline_access")
        .formParam("state", "${state}")
        .formParam("session_state", "EHmlGEa6AFAJyepWEMb0qckldYlkKxmR5423_v6vt9w.mf859ouDnGqSUnypiEqc3w")
        .check(status.is(200))))








	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}