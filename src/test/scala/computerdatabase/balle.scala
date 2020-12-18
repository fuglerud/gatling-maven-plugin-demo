package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class balle extends Simulation {

	val httpProtocol = http
		.baseUrl("https://epj.qa.forskrivning.no")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:83.0) Gecko/20100101 Firefox/83.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://epj.qa.forskrivning.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_4 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://hid-testidp.azurewebsites.net",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_5 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "null",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_9 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_10 = Map(
		"Access-Control-Request-Headers" -> "authorization,x-requested-with",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_11 = Map(
		"Access-Control-Request-Headers" -> "access-control-allow-origin,authorization,cache-control,content-type,expires,pragma",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_16 = Map(
		"Access-Control-Request-Headers" -> "bugsnag-api-key,bugsnag-payload-version,bugsnag-sent-at,content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_18 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:31.641Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_19 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:31.673Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_21 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Access-Control-Allow-Origin" -> "*",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Expires" -> "0",
		"Origin" -> "https://client.qa.forskrivning.no",
		"Pragma" -> "no-cache",
		"authorization" -> "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UifQ.eyJuYmYiOjE2MDc5ODU2NzcsImV4cCI6MTYwNzk4OTI3NywiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2UvU0ZNLmFwaSIsImNsaWVudF9pZCI6ImZmYWQ1OGZkLWE4NmYtNDEyMi04NTQxLTQyYTgyZjcxOWZlOCIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvb3JnbnJfcGFyZW50IjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvZXhwIjoxOTI0OTkyMDAwLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9jb21tb25fbmFtZSI6IkdsaXR0ZXJ0aW5kIHRlc3RsZWdla29udG9yIiwiY2xpZW50X2FtciI6InByaXZhdGVfa2V5X2p3dCIsInN1YiI6ImJkUUVud09jc0w1L0d5Yy9kc2k4RVF6NnVqcDVxMi9YcUxpb1d2cGR1RU09IiwiYXV0aF90aW1lIjoxNjA3OTg1Njc3LCJpZHAiOiJ0ZXN0aWRwLW9pZGMiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIjoiNCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvcGlkIjoiMDcwMTU5MDAyMTUiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L2Fzc3VyYW5jZV9sZXZlbCI6ImhpZ2giLCJoZWxzZWlkOi8vY2xhaW1zL2hwci9ocHJfbnVtYmVyIjoiMjIyMjAwMDgyIiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvYW1yIjoicnNhX3ByaXZhdGVfa2V5IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvY2xhaW1zL29yZ25yX3BhcmVudCI6IjEwMDE3Nzk5NCIsInNjb3BlIjpbInByb2ZpbGUiLCJvcGVuaWQiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3BpZCIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiLCJoZWxzZWlkOi8vc2NvcGVzL2hwci9ocHJfbnVtYmVyIiwiZS1oZWxzZS9zZm0uYXBpL3NmbS5hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsiZXh0ZXJuYWwiXX0.a5RUkCzVg-kso7hgE2Xr9lO5cQcJ-NZ_Uj88Tivum3ySO7OMXmV-8CkjK2rDES0gxlP8BEnbbM6it9xxDbZjy58dbkFE3_dui6H6yqWCje4caQt2VB5lVi2L_yZSrngTw38SDoCXDGSG_Nf0dOEMe39cv67voT40kYnHv_LNxQNMefFSMSqP1Vp3EdbmdN-tytYFYXkR3YXFnrnBY8xBqGnzXmNQgPEeELmlUTwxNp1DnRyDbCVutrjtIsF9aytuhGQ71kSqlN-x0P5o1Pvj14v9mxKCu2iKMj39W9OaCflVsn69OcvVvoY1T611vtm6JiNaIyOEiWng2pFR5M5zag")

	val headers_25 = Map(
		"Content-Type" -> "text/plain;charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"X-Requested-With" -> "XMLHttpRequest",
		"authorization" -> "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UifQ.eyJuYmYiOjE2MDc5ODU2NzcsImV4cCI6MTYwNzk4OTI3NywiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2UvU0ZNLmFwaSIsImNsaWVudF9pZCI6ImZmYWQ1OGZkLWE4NmYtNDEyMi04NTQxLTQyYTgyZjcxOWZlOCIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvb3JnbnJfcGFyZW50IjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvZXhwIjoxOTI0OTkyMDAwLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9jb21tb25fbmFtZSI6IkdsaXR0ZXJ0aW5kIHRlc3RsZWdla29udG9yIiwiY2xpZW50X2FtciI6InByaXZhdGVfa2V5X2p3dCIsInN1YiI6ImJkUUVud09jc0w1L0d5Yy9kc2k4RVF6NnVqcDVxMi9YcUxpb1d2cGR1RU09IiwiYXV0aF90aW1lIjoxNjA3OTg1Njc3LCJpZHAiOiJ0ZXN0aWRwLW9pZGMiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIjoiNCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvcGlkIjoiMDcwMTU5MDAyMTUiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L2Fzc3VyYW5jZV9sZXZlbCI6ImhpZ2giLCJoZWxzZWlkOi8vY2xhaW1zL2hwci9ocHJfbnVtYmVyIjoiMjIyMjAwMDgyIiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvYW1yIjoicnNhX3ByaXZhdGVfa2V5IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvY2xhaW1zL29yZ25yX3BhcmVudCI6IjEwMDE3Nzk5NCIsInNjb3BlIjpbInByb2ZpbGUiLCJvcGVuaWQiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3BpZCIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiLCJoZWxzZWlkOi8vc2NvcGVzL2hwci9ocHJfbnVtYmVyIiwiZS1oZWxzZS9zZm0uYXBpL3NmbS5hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsiZXh0ZXJuYWwiXX0.a5RUkCzVg-kso7hgE2Xr9lO5cQcJ-NZ_Uj88Tivum3ySO7OMXmV-8CkjK2rDES0gxlP8BEnbbM6it9xxDbZjy58dbkFE3_dui6H6yqWCje4caQt2VB5lVi2L_yZSrngTw38SDoCXDGSG_Nf0dOEMe39cv67voT40kYnHv_LNxQNMefFSMSqP1Vp3EdbmdN-tytYFYXkR3YXFnrnBY8xBqGnzXmNQgPEeELmlUTwxNp1DnRyDbCVutrjtIsF9aytuhGQ71kSqlN-x0P5o1Pvj14v9mxKCu2iKMj39W9OaCflVsn69OcvVvoY1T611vtm6JiNaIyOEiWng2pFR5M5zag")

	val headers_39 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:36.448Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_45 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:36.245Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_46 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:36.261Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_47 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:36.256Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_48 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:36.249Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_49 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:36.240Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_51 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:39.422Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_52 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:39.426Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_53 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:39.418Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_54 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:39.424Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_55 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:39.432Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_56 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:41:39.428Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

    val uri1 = "https://client.qa.forskrivning.no"
    val uri3 = "https://notify.bugsnag.com"
    val uri4 = "https://server.qa.forskrivning.no"
    val uri5 = "https://sessions.bugsnag.com"
    val uri6 = "https://helseid-sts.test.nhn.no"
    val uri7 = "https://hid-testidp.azurewebsites.net/Account/Login"

	val scn = scenario("balle")


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

		//feiler
		.exec(http("request_2_feiler")
			.post("/")
			.headers(headers_1)
			.formParam("SelectedEnvironmentId", "ffad58fd-a86f-4122-8541-42a82f719fe8#Glittertind testlegekontor (Fastlege)")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("IsSelectingInstallation", "True")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "${requestVerificationToken2}")
			.check(regex("authzId%3D(.*?)&amp").saveAs("authzId"))
		)

	/*
      .exec(session=>{
        println("authzId:")
        println(session("authzId").as[String])
        session})


      // fyll inn fnr
      .exec(http("request_3")
        .get(uri6 + "/Account/ExternalLogin?provider=testidp-oidc&returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3FauthzId%3D${authzId}")
        .headers(headers_0)
        .check(regex("nonce%(.*?)%26").saveAs("nonce"))
        .check(regex("state%(.*?)%26").saveAs("state"))
        .check(regex("RequestVerificationToken\" type=\"hidden\" value=\"(.*?)\"").saveAs("requestVerificationToken3"))
        .check(regex("returnurl=%(.*?)0.0").saveAs("returnurl"))

      )

      .exec(session=>{
        println("returnurl:")
        println(session("returnurl").as[String])
        session})




      // trykk logg inn
      .exec(http("request_4")
        //.post(uri7 + "?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dsts%26redirect_uri%3Dhttps%253A%252F%252Fhelseid-sts.test.nhn.no%252Fsignin-testidp-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520identity%252Fpid%26response_mode%3Dform_post%26nonce%3D${nonce}%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D5.6.0.0")
        .post(uri7 + "?returnurl=%${returnurl}")
        .headers(headers_4)
        .formParam("Pid", "07015900215")
        .formParam("SecurityLevel", "4")
        .formParam("HprNumber", "")
        .formParam("__RequestVerificationToken", "${requestVerificationToken3}")
        .check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
        .check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope"))
        .check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
        .check(regex("name='id_token' value='(.*?)' />").saveAs("id_token"))
        .check(regex("name='state' value='(.*?)' />").saveAs("state"))
        .resources(http("request_5")
        .post(uri6 + "/signin-testidp-oidc")
        .headers(headers_5)
        .formParam("code", "${code}")
        .formParam("id_token", "${id_token}")
        .formParam("scope", "${scope}")
        .formParam("state", "${state}")
        .formParam("session_state", "${session_state}")
          .check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
          .check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
          .check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope"))
          .check(regex("name='state' value='(.*?)' />").saveAs("state")),
              http("request_6")
        .post("/signin-oidc")
        .headers(headers_5)
        .formParam("code", "${code}")
        .formParam("scope", "${scope}")
        .formParam("state", "${state}").disableUrlEncoding
        .formParam("session_state", "${session_state}")))








      /*
      // åpne pasient
      .exec(http("request_7")
        .post("/Patient/LoadClientAsync")
        .headers(headers_1)
        .formParam("OnBehalfOf", "")
        .formParam("ShowAllergies", "true")
        .formParam("SelectedTicket", "54688c89-e7d2-4662-a054-f24daabafa50")
        .formParam("SelectedEnvironment", "Glittertind testlegekontor (Fastlege)")
        .formParam("ApiEndpoint", "https://server.qa.forskrivning.no")
        .formParam("HelseIdClientId", "ffad58fd-a86f-4122-8541-42a82f719fe8")
        .formParam("submitButton", "open")
        .formParam("__RequestVerificationToken", "CfDJ8OKdjf3R47FHgBEqJUeDXBkB1mJ3P2ADVf0xQIG9iIeDdYqf4mCa5EllahfNKxGAnHGVJw4ybwowz2QgbYexRsIiSHitFtVOWA3LsRIaqysRB3WN2VapjifuI2oZ580WigSRSGM0NIa3Cg4PgEJwXiN8RCnRRMYLlVWlUkU8ba-sWgu1SFaHv5EMopt1vdWwnA")
        .formParam("ShowAllergies", "false")
  */

      /*
        .resources(http("request_8")
        .get(uri1 + "/pages/set-context?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379&api_endpoint=https://server.qa.forskrivning.no/&onBehalfOf=&show-cave=True&access_token=eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UifQ.eyJuYmYiOjE2MDc5ODU2NzcsImV4cCI6MTYwNzk4OTI3NywiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2UvU0ZNLmFwaSIsImNsaWVudF9pZCI6ImZmYWQ1OGZkLWE4NmYtNDEyMi04NTQxLTQyYTgyZjcxOWZlOCIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvb3JnbnJfcGFyZW50IjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvZXhwIjoxOTI0OTkyMDAwLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9jb21tb25fbmFtZSI6IkdsaXR0ZXJ0aW5kIHRlc3RsZWdla29udG9yIiwiY2xpZW50X2FtciI6InByaXZhdGVfa2V5X2p3dCIsInN1YiI6ImJkUUVud09jc0w1L0d5Yy9kc2k4RVF6NnVqcDVxMi9YcUxpb1d2cGR1RU09IiwiYXV0aF90aW1lIjoxNjA3OTg1Njc3LCJpZHAiOiJ0ZXN0aWRwLW9pZGMiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIjoiNCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvcGlkIjoiMDcwMTU5MDAyMTUiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L2Fzc3VyYW5jZV9sZXZlbCI6ImhpZ2giLCJoZWxzZWlkOi8vY2xhaW1zL2hwci9ocHJfbnVtYmVyIjoiMjIyMjAwMDgyIiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvYW1yIjoicnNhX3ByaXZhdGVfa2V5IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvY2xhaW1zL29yZ25yX3BhcmVudCI6IjEwMDE3Nzk5NCIsInNjb3BlIjpbInByb2ZpbGUiLCJvcGVuaWQiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3BpZCIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiLCJoZWxzZWlkOi8vc2NvcGVzL2hwci9ocHJfbnVtYmVyIiwiZS1oZWxzZS9zZm0uYXBpL3NmbS5hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsiZXh0ZXJuYWwiXX0.a5RUkCzVg-kso7hgE2Xr9lO5cQcJ-NZ_Uj88Tivum3ySO7OMXmV-8CkjK2rDES0gxlP8BEnbbM6it9xxDbZjy58dbkFE3_dui6H6yqWCje4caQt2VB5lVi2L_yZSrngTw38SDoCXDGSG_Nf0dOEMe39cv67voT40kYnHv_LNxQNMefFSMSqP1Vp3EdbmdN-tytYFYXkR3YXFnrnBY8xBqGnzXmNQgPEeELmlUTwxNp1DnRyDbCVutrjtIsF9aytuhGQ71kSqlN-x0P5o1Pvj14v9mxKCu2iKMj39W9OaCflVsn69OcvVvoY1T611vtm6JiNaIyOEiWng2pFR5M5zag&id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UifQ.eyJuYmYiOjE2MDc5ODU2NzcsImV4cCI6MTYwNzk4NTk3NywiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImZmYWQ1OGZkLWE4NmYtNDEyMi04NTQxLTQyYTgyZjcxOWZlOCIsImlhdCI6MTYwNzk4NTY3NywiYXRfaGFzaCI6Imk0Y2JxVno0ZUh0emZhazMwb185VWciLCJzX2hhc2giOiItREMzTjVrV3NiQlhLSm91dlVqbzFBIiwic2lkIjoiRm1NWGMydkxVMm8tRURQMmloRFRndyIsInN1YiI6ImJkUUVud09jc0w1L0d5Yy9kc2k4RVF6NnVqcDVxMi9YcUxpb1d2cGR1RU09IiwiYXV0aF90aW1lIjoxNjA3OTg1Njc3LCJpZHAiOiJ0ZXN0aWRwLW9pZGMiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIjoiNCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvcGlkIjoiMDcwMTU5MDAyMTUiLCJoZWxzZWlkOi8vY2xhaW1zL2hwci9ocHJfbnVtYmVyIjoiMjIyMjAwMDgyIiwibmFtZSI6IlZJVkkgUFNBIFVMVkVOIiwiZ2l2ZW5fbmFtZSI6IlZJVkkiLCJtaWRkbGVfbmFtZSI6IlBTQSIsImZhbWlseV9uYW1lIjoiVUxWRU4iLCJhbXIiOlsiZXh0ZXJuYWwiXX0.Qz-crvOUOAGtj6fdeC3zE-SIgc8o8pRE-qtJJyI4fT0NTIXjMvid5thQvshBGbdPctInakZDCQYZcnWn5l6B7-bj-T1L_gyiHqBfC32uVUHC1oku3gaZan4i-z7SrTzpAsWpjTeNnwhHACNq-0gtEup_lp6RwLTR7rShCev8r8Rt2PawWIrkWGzm9bHd34EtJYIlpqSfm2OKEFb6AcMe2g98psj-rXDWUtx4x_T3tI4ABsgSLiDBPKOSAogaQxTT9MvKSmOcQ2SR7JVhaKGPVNq6iuHo4JoO24UBcoJ_BpEOPioYwaWYbVag9WdixaBiqjiDN-Od_ffT8HXlXlH-xA")
        .headers(headers_0),
              http("request_9")
        .get(uri1 + "/version.json?t=1607985691683")
        .headers(headers_9),
              http("request_10")
        .options(uri4 + "/hubs/sfmHub/negotiate?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379&negotiateVersion=1")
        .headers(headers_10),
              http("request_11")
        .options(uri4 + "/api/patients/0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
        .headers(headers_11),
              http("request_12")
        .options(uri4 + "/api/catalogues/localDrugs")
        .headers(headers_11),
              http("request_13")
        .options(uri4 + "/api/catalogues/lib")
        .headers(headers_11),
              http("request_14")
        .options(uri4 + "/api/catalogues/caves")
        .headers(headers_11),
              http("request_15")
        .options(uri4 + "/api/catalogues/productConditions")
        .headers(headers_11),
              http("request_16")
        .options(uri5 + "/")
        .headers(headers_16),
              http("request_17")
        .options(uri5 + "/")
        .headers(headers_16),
              http("request_18")
        .post(uri5 + "/")
        .headers(headers_18)
        .body(RawFileBody("computerdatabase/balle/0018_request.json")),
              http("request_19")
        .post(uri5 + "/")
        .headers(headers_19)
        .body(RawFileBody("computerdatabase/balle/0019_request.json")),
              http("request_20")
        .options(uri4 + "/api/catalogues/vaccines")
        .headers(headers_11),
              http("request_21")
        .get(uri4 + "/api/catalogues/lib")
        .headers(headers_21),
              http("request_22")
        .get(uri4 + "/api/catalogues/localDrugs")
        .headers(headers_21),
              http("request_23")
        .get(uri4 + "/api/catalogues/vaccines")
        .headers(headers_21),
              http("request_24")
        .get(uri4 + "/api/catalogues/caves")
        .headers(headers_21),
              http("request_25")
        .post(uri4 + "/hubs/sfmHub/negotiate?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379&negotiateVersion=1")
        .headers(headers_25),
              http("request_26")
        .get(uri4 + "/api/patients/0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
        .headers(headers_21),
              http("request_27")
        .options(uri4 + "/api/users/current")
        .headers(headers_11),
              http("request_28")
        .options(uri4 + "/api/institutions/")
        .headers(headers_11),
              http("request_29")
        .options(uri4 + "/api/patientJournals/result/?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
        .headers(headers_11),
              http("request_30")
        .options(uri4 + "/api/prescriptions/?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
        .headers(headers_11),
              http("request_31")
        .options(uri4 + "/api/serverinfo/version")
        .headers(headers_11),
              http("request_32")
        .options(uri4 + "/api/catalogues/productNodes/Fib")
        .headers(headers_11),
              http("request_33")
        .get(uri4 + "/api/catalogues/productConditions")
        .headers(headers_21),
              http("request_34")
        .options(uri3 + "/")
        .headers(headers_16),
              http("request_35")
        .options(uri3 + "/")
        .headers(headers_16),
              http("request_36")
        .options(uri3 + "/")
        .headers(headers_16),
              http("request_37")
        .options(uri3 + "/")
        .headers(headers_16),
              http("request_38")
        .options(uri3 + "/")
        .headers(headers_16),
              http("request_39")
        .post(uri5 + "/")
        .headers(headers_39)
        .body(RawFileBody("computerdatabase/balle/0039_request.json")),
              http("request_40")
        .get(uri4 + "/api/institutions/")
        .headers(headers_21),
              http("request_41")
        .get(uri4 + "/api/serverinfo/version")
        .headers(headers_21),
              http("request_42")
        .options(uri4 + "/api/catalogues/productNodes/Nib")
        .headers(headers_11),
              http("request_43")
        .get(uri4 + "/api/users/current")
        .headers(headers_21),
              http("request_44")
        .get(uri4 + "/api/patientJournals/result/?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
        .headers(headers_21),
              http("request_45")
        .post(uri3 + "/")
        .headers(headers_45)
        .body(RawFileBody("computerdatabase/balle/0045_request.json")),
              http("request_46")
        .post(uri3 + "/")
        .headers(headers_46)
        .body(RawFileBody("computerdatabase/balle/0046_request.json")),
              http("request_47")
        .post(uri3 + "/")
        .headers(headers_47)
        .body(RawFileBody("computerdatabase/balle/0047_request.json")),
              http("request_48")
        .post(uri3 + "/")
        .headers(headers_48)
        .body(RawFileBody("computerdatabase/balle/0048_request.json")),
              http("request_49")
        .post(uri3 + "/")
        .headers(headers_49)
        .body(RawFileBody("computerdatabase/balle/0049_request.json")),
              http("request_50")
        .get(uri4 + "/api/prescriptions/?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
        .headers(headers_21)
        .check(status.is(500)),
              http("request_51")
        .post(uri3 + "/")
        .headers(headers_51)
        .body(RawFileBody("computerdatabase/balle/0051_request.json")),
              http("request_52")
        .post(uri3 + "/")
        .headers(headers_52)
        .body(RawFileBody("computerdatabase/balle/0052_request.json")),
              http("request_53")
        .post(uri3 + "/")
        .headers(headers_53)
        .body(RawFileBody("computerdatabase/balle/0053_request.json")),
              http("request_54")
        .post(uri3 + "/")
        .headers(headers_54)
        .body(RawFileBody("computerdatabase/balle/0054_request.json")),
              http("request_55")
        .post(uri3 + "/")
        .headers(headers_55)
        .body(RawFileBody("computerdatabase/balle/0055_request.json")),
              http("request_56")
        .post(uri3 + "/")
        .headers(headers_56)
        .body(RawFileBody("computerdatabase/balle/0056_request.json")),
              http("request_57")
        .get(uri4 + "/api/catalogues/productNodes/Nib")
        .headers(headers_21))
        */


  */

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}