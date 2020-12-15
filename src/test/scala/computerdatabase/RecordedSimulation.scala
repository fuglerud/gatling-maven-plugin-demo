package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://server.qa.forskrivning.no")
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

	val headers_4 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_5 = Map(
		"Access-Control-Request-Headers" -> "authorization,x-requested-with",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_6 = Map(
		"Access-Control-Request-Headers" -> "access-control-allow-origin,authorization,cache-control,content-type,expires,pragma",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_11 = Map(
		"Access-Control-Request-Headers" -> "bugsnag-api-key,bugsnag-payload-version,bugsnag-sent-at,content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_14 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Access-Control-Allow-Origin" -> "*",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Expires" -> "0",
		"Origin" -> "https://client.qa.forskrivning.no",
		"Pragma" -> "no-cache",
		"authorization" -> "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UifQ.eyJuYmYiOjE2MDc5ODM0MzgsImV4cCI6MTYwNzk4NzAzOCwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2UvU0ZNLmFwaSIsImNsaWVudF9pZCI6ImZmYWQ1OGZkLWE4NmYtNDEyMi04NTQxLTQyYTgyZjcxOWZlOCIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvb3JnbnJfcGFyZW50IjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvZXhwIjoxOTI0OTkyMDAwLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9jb21tb25fbmFtZSI6IkdsaXR0ZXJ0aW5kIHRlc3RsZWdla29udG9yIiwiY2xpZW50X2FtciI6InByaXZhdGVfa2V5X2p3dCIsInN1YiI6ImJkUUVud09jc0w1L0d5Yy9kc2k4RVF6NnVqcDVxMi9YcUxpb1d2cGR1RU09IiwiYXV0aF90aW1lIjoxNjA3OTgzNDM3LCJpZHAiOiJ0ZXN0aWRwLW9pZGMiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIjoiNCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvcGlkIjoiMDcwMTU5MDAyMTUiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L2Fzc3VyYW5jZV9sZXZlbCI6ImhpZ2giLCJoZWxzZWlkOi8vY2xhaW1zL2hwci9ocHJfbnVtYmVyIjoiMjIyMjAwMDgyIiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvYW1yIjoicnNhX3ByaXZhdGVfa2V5IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvY2xhaW1zL29yZ25yX3BhcmVudCI6IjEwMDE3Nzk5NCIsInNjb3BlIjpbInByb2ZpbGUiLCJvcGVuaWQiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3BpZCIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiLCJoZWxzZWlkOi8vc2NvcGVzL2hwci9ocHJfbnVtYmVyIiwiZS1oZWxzZS9zZm0uYXBpL3NmbS5hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsiZXh0ZXJuYWwiXX0.ieXBJkDhRiCPsDJe1BDpxYg58Nj9q6Fs2Q9Cce5DhQuH3_eeasOehmwXlyd1Eyd2lda7sxxeAY3HC4TP1uD01rH7icEYtcwOOUxmLakfCbydVrNAvShgPO6TkL3vrBAFEz7exqwKIyxpn8-BDMqwubuP6gJyqOfB-SW09Z3zqdAnfuNWpy-KQOv9ssz9OOVDHm1iZIHPbmaC0goVvlOXlFjjcvuDuG7dfYuDeIYXrHKgApdQfYFlLwrDF9eFzwa4WDTT9KFG3TovQMWoZD0tGPmv6Ms_P653Gzn4NU78QyeSjiP5E4tvz93Yr-ZWy8xm04tPZMXdv85dHCFr0WMhAA")

	val headers_16 = Map(
		"Content-Type" -> "text/plain;charset=UTF-8",
		"Origin" -> "https://client.qa.forskrivning.no",
		"X-Requested-With" -> "XMLHttpRequest",
		"authorization" -> "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UifQ.eyJuYmYiOjE2MDc5ODM0MzgsImV4cCI6MTYwNzk4NzAzOCwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2UvU0ZNLmFwaSIsImNsaWVudF9pZCI6ImZmYWQ1OGZkLWE4NmYtNDEyMi04NTQxLTQyYTgyZjcxOWZlOCIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvb3JnbnJfcGFyZW50IjoiMTAwMTc3OTk0IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvZXhwIjoxOTI0OTkyMDAwLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9jb21tb25fbmFtZSI6IkdsaXR0ZXJ0aW5kIHRlc3RsZWdla29udG9yIiwiY2xpZW50X2FtciI6InByaXZhdGVfa2V5X2p3dCIsInN1YiI6ImJkUUVud09jc0w1L0d5Yy9kc2k4RVF6NnVqcDVxMi9YcUxpb1d2cGR1RU09IiwiYXV0aF90aW1lIjoxNjA3OTgzNDM3LCJpZHAiOiJ0ZXN0aWRwLW9pZGMiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIjoiNCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvcGlkIjoiMDcwMTU5MDAyMTUiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L2Fzc3VyYW5jZV9sZXZlbCI6ImhpZ2giLCJoZWxzZWlkOi8vY2xhaW1zL2hwci9ocHJfbnVtYmVyIjoiMjIyMjAwMDgyIiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvYW1yIjoicnNhX3ByaXZhdGVfa2V5IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvY2xhaW1zL29yZ25yX3BhcmVudCI6IjEwMDE3Nzk5NCIsInNjb3BlIjpbInByb2ZpbGUiLCJvcGVuaWQiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3BpZCIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiLCJoZWxzZWlkOi8vc2NvcGVzL2hwci9ocHJfbnVtYmVyIiwiZS1oZWxzZS9zZm0uYXBpL3NmbS5hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsiZXh0ZXJuYWwiXX0.ieXBJkDhRiCPsDJe1BDpxYg58Nj9q6Fs2Q9Cce5DhQuH3_eeasOehmwXlyd1Eyd2lda7sxxeAY3HC4TP1uD01rH7icEYtcwOOUxmLakfCbydVrNAvShgPO6TkL3vrBAFEz7exqwKIyxpn8-BDMqwubuP6gJyqOfB-SW09Z3zqdAnfuNWpy-KQOv9ssz9OOVDHm1iZIHPbmaC0goVvlOXlFjjcvuDuG7dfYuDeIYXrHKgApdQfYFlLwrDF9eFzwa4WDTT9KFG3TovQMWoZD0tGPmv6Ms_P653Gzn4NU78QyeSjiP5E4tvz93Yr-ZWy8xm04tPZMXdv85dHCFr0WMhAA")

	val headers_17 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:47.149Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_20 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:47.183Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_33 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "1",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:47.713Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_34 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:47.595Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_35 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:47.504Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_36 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:47.602Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_37 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:47.607Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_38 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:47.598Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_52 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:58.189Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_53 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:58.184Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_54 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:58.194Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_55 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:58.190Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_56 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:58.187Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

	val headers_58 = Map(
		"Bugsnag-Api-Key" -> "45336333a84a9023c27a3938800a27e6",
		"Bugsnag-Payload-Version" -> "4",
		"Bugsnag-Sent-At" -> "2020-12-14T22:08:58.180Z",
		"Content-Type" -> "application/json",
		"Origin" -> "https://client.qa.forskrivning.no")

    val uri1 = "https://client.qa.forskrivning.no/version.json"
    val uri2 = "https://epj.qa.forskrivning.no"
    val uri3 = "https://notify.bugsnag.com"
    val uri5 = "https://sessions.bugsnag.com"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get(uri2 + "/")
			.headers(headers_0))

		.exec(http("request_1")
			.post(uri2 + "/")
			.headers(headers_1)
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "")
			.formParam("IsSelectingInstallation", "False")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "CfDJ8OKdjf3R47FHgBEqJUeDXBm_bBWF2wPs3tl8OEwnAmB0sTzto_yiBuomyAGPmPm8UjEw050X1peL12yKLH6usrvqnpf2S_VP2VeZPOunZAi7mt-gjCewBEfhRa-0hW5Lu6Qcdz5uXKkx36LzgFld_XtP_hzm8gKvHtkBFa3vfiMVGiF8hPQgf9FVgsc0-QeshA"))

		.exec(http("request_2")
			.post(uri2 + "/")
			.headers(headers_1)
			.formParam("SelectedEnvironmentId", "ffad58fd-a86f-4122-8541-42a82f719fe8#Glittertind testlegekontor (Fastlege)")
			.formParam("submitButton", "select")
			.formParam("SelectedPortalId", "PatientPortal")
			.formParam("IsSelectingInstallation", "True")
			.formParam("SelectedEnvironmentId", "")
			.formParam("__RequestVerificationToken", "CfDJ8OKdjf3R47FHgBEqJUeDXBkERbgUGFKD6FBbG-V1MdIA31o-eFl0gtDymOrFnFMcOFK_CkRFzyIVQf3ycBhzFpAkuaWWplJGsddz630JRCg5OcTlpqMuy9YGOCGqGlYe-7ptcCBLnWJvm8YpiWW0hZThADWFTx-V6DqyyeHCw8K9GepCTYG0FBBdxUNSXsO5FA"))

		.exec(http("request_3")
			.post(uri2 + "/Patient/LoadClientAsync")
			.headers(headers_1)
			.formParam("OnBehalfOf", "")
			.formParam("ShowAllergies", "true")
			.formParam("SelectedTicket", "54688c89-e7d2-4662-a054-f24daabafa50")
			.formParam("SelectedEnvironment", "Glittertind testlegekontor (Fastlege)")
			.formParam("ApiEndpoint", "https://server.qa.forskrivning.no")
			.formParam("HelseIdClientId", "ffad58fd-a86f-4122-8541-42a82f719fe8")
			.formParam("submitButton", "open")
			.formParam("__RequestVerificationToken", "CfDJ8OKdjf3R47FHgBEqJUeDXBkMpAZa-NFOD4VzoXRm1spyAtZ71BIXd8SqqENONpFBbDd4EQ7bXKoCj5VP9hIkUICd2fCJpWrxnlh4MWW1vf-6W-XkNrQ-P-lgaskwFVhxn7RJH-tNwa3wBwK3krOHJRnBFV3MBPJ07beGjvTRef5pA8jKcHhbQ8f8mO2jg4h08A")
			.formParam("ShowAllergies", "false"))

		.exec(http("request_4")
			.get(uri1 + "?t=1607983727193")
			.headers(headers_4))

		.exec(http("request_5")
			.options("/hubs/sfmHub/negotiate?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379&negotiateVersion=1")
			.headers(headers_5))

		.exec(http("request_6")
			.options("/api/patients/0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
			.headers(headers_6))

		.exec(http("request_7")
			.options("/api/catalogues/localDrugs")
			.headers(headers_6))

		.exec(http("request_8")
			.options("/api/catalogues/lib")
			.headers(headers_6))

		.exec(http("request_9")
			.options("/api/catalogues/caves")
			.headers(headers_6))

		.exec(http("request_10")
			.options("/api/catalogues/vaccines")
			.headers(headers_6))




		.exec(http("request_11")
			.options(uri5 + "/")
			.headers(headers_11))
		.exec(http("request_12")
			.options(uri5 + "/")
			.headers(headers_11))
		.exec(http("request_13")
			.options("/api/catalogues/productConditions")
			.headers(headers_6))
		.exec(http("request_14")
			.get("/api/catalogues/vaccines")
			.headers(headers_14))
		.exec(http("request_15")
			.get("/api/catalogues/localDrugs")
			.headers(headers_14))
		.exec(http("request_16")
			.post("/hubs/sfmHub/negotiate?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379&negotiateVersion=1")
			.headers(headers_16))
		.exec(http("request_17")
			.post(uri5 + "/")
			.headers(headers_17)
			.body(RawFileBody("computerdatabase/recordedsimulation/0017_request.json")))
		.exec(http("request_18")
			.get("/api/catalogues/lib")
			.headers(headers_14))
		.exec(http("request_19")
			.get("/api/catalogues/caves")
			.headers(headers_14))
		.exec(http("request_20")
			.post(uri5 + "/")
			.headers(headers_20)
			.body(RawFileBody("computerdatabase/recordedsimulation/0020_request.json")))
		.exec(http("request_21")
			.get("/api/catalogues/productConditions")
			.headers(headers_14))
		.exec(http("request_22")
			.get("/api/patients/0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
			.headers(headers_14))
		.exec(http("request_23")
			.options("/api/institutions/")
			.headers(headers_6))
		.exec(http("request_24")
			.options("/api/users/current")
			.headers(headers_6))
		.exec(http("request_25")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_26")
			.options("/api/serverinfo/version")
			.headers(headers_6))
		.exec(http("request_27")
			.options("/api/patientJournals/result/?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
			.headers(headers_6))
		.exec(http("request_28")
			.options("/api/prescriptions/?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
			.headers(headers_6))
		.exec(http("request_29")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_30")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_31")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_32")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_33")
			.post(uri5 + "/")
			.headers(headers_33)
			.body(RawFileBody("computerdatabase/recordedsimulation/0033_request.json")))
		.exec(http("request_34")
			.post(uri3 + "/")
			.headers(headers_34)
			.body(RawFileBody("computerdatabase/recordedsimulation/0034_request.json")))
		.exec(http("request_35")
			.post(uri3 + "/")
			.headers(headers_35)
			.body(RawFileBody("computerdatabase/recordedsimulation/0035_request.json")))
		.exec(http("request_36")
			.post(uri3 + "/")
			.headers(headers_36)
			.body(RawFileBody("computerdatabase/recordedsimulation/0036_request.json")))
		.exec(http("request_37")
			.post(uri3 + "/")
			.headers(headers_37)
			.body(RawFileBody("computerdatabase/recordedsimulation/0037_request.json")))
		.exec(http("request_38")
			.post(uri3 + "/")
			.headers(headers_38)
			.body(RawFileBody("computerdatabase/recordedsimulation/0038_request.json")))
		.exec(http("request_39")
			.options("/api/catalogues/productNodes/Fib")
			.headers(headers_6))
		.exec(http("request_40")
			.get("/api/institutions/")
			.headers(headers_14))
		.exec(http("request_41")
			.get("/api/serverinfo/version")
			.headers(headers_14))
		.exec(http("request_42")
			.get("/api/users/current")
			.headers(headers_14))
		.exec(http("request_43")
			.get("/api/patientJournals/result/?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
			.headers(headers_14))
		.exec(http("request_44")
			.options("/api/catalogues/productNodes/Nib")
			.headers(headers_6))
		.exec(http("request_45")
			.get("/api/prescriptions/?patientTicket=0cc89d01-873a-4ebb-8696-1a9a7a0ea379")
			.headers(headers_14)
			.check(status.is(500)))
		.exec(http("request_46")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_47")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_48")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_49")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_50")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_51")
			.options(uri3 + "/")
			.headers(headers_11))
		.exec(http("request_52")
			.post(uri3 + "/")
			.headers(headers_52)
			.body(RawFileBody("computerdatabase/recordedsimulation/0052_request.json")))
		.exec(http("request_53")
			.post(uri3 + "/")
			.headers(headers_53)
			.body(RawFileBody("computerdatabase/recordedsimulation/0053_request.json")))
		.exec(http("request_54")
			.post(uri3 + "/")
			.headers(headers_54)
			.body(RawFileBody("computerdatabase/recordedsimulation/0054_request.json")))
		.exec(http("request_55")
			.post(uri3 + "/")
			.headers(headers_55)
			.body(RawFileBody("computerdatabase/recordedsimulation/0055_request.json")))
		.exec(http("request_56")
			.post(uri3 + "/")
			.headers(headers_56)
			.body(RawFileBody("computerdatabase/recordedsimulation/0056_request.json")))
		.exec(http("request_57")
			.get("/api/catalogues/productNodes/Nib")
			.headers(headers_14))
		.exec(http("request_58")
			.post(uri3 + "/")
			.headers(headers_58)
			.body(RawFileBody("computerdatabase/recordedsimulation/0058_request.json")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}