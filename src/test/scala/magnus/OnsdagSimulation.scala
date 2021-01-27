package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class OnsdagSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://kubtest.kj.nhn.no")
		.inferHtmlResources()
		.acceptHeader("application/json, text/javascript, */*; q=0.01")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:84.0) Gecko/20100101 Firefox/84.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_4 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "https://kubtest.kj.nhn.no",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_7 = Map(
		"Content-Type" -> "application/json",
		"Origin" -> "https://kubtest.kj.nhn.no",
		"X-Kj-CsrfToken" -> "4eab7763-b127-4267-9273-bc9eab00ef60",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_15 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_23 = Map("Accept" -> "image/webp,*/*")



	val scn = scenario("OnsdagSimulation")
		.exec(http("request_0")
			.get("/innlogging-webapp/index.html")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/innlogging-webapp/mock/Innlogging/users")
			.headers(headers_1),
            http("request_2")
			.get("/innlogging-webapp/api/driftsmelding/nyeDriftsmeldinger")
			.headers(headers_1)))
		.pause(5)
		.exec(http("request_3")
			.get("/innlogging-webapp/mock/Innlogging/login?nin=05085600143&resource_url=https%3A%2F%2Fkubtest.kj.nhn.no%2Fhpp-webapp")
			.headers(headers_0)
			.resources(http("request_4")
			.post("/hpp-webapp")
			.headers(headers_4)
			.formParam("jwt", "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzaWtrZXJoZXQua2plcm5lam91cm5hbC5ubyIsImtqX2ZuciI6IjA1MDg1NjAwMTQzIiwia2pfYnJ1a2VyaWQiOiJCREY4RDIwMi1ENDI5LTQxMjYtOTU4RC05N0ZDQkQ5NzQwMkQiLCJoZWxzZWlkLmF0X2hhc2giOiJvOXhhYTREUVVhT3N3X2RCNjhTR193IiwiaGVsc2VpZC5qdGkiOiJFM0NBNTlBMEM2NUE1Q0IyOUU1MTVGNkI2NzREQTAwRiIsImF1ZCI6Imh0dHBzOi8va3VidGVzdC5rai5uaG4ubm8vaHBwLXdlYmFwcCIsImV4cCI6MTYxMTE0MzEyOCwiaWF0IjoxNjExMTQyNTI4LCJzY29wZSI6WyJhdXRvcmlzYXNqb24iXSwia2pfYnJ1a2VyZ3J1cHBlciI6WyJCUlVLRVJTVE9UVEUiLCJIRUxTRVBFUlNPTkVMTCIsIlNBS1NCRUhBTkRMRVIiLCJWSVJLU09NSEVUU0FOU1ZBUkxJRyIsIlNFTlRSQUxBRE1JTklTVFJBVE9SIiwiQlJVS0VSQURNSU5JU1RSQVRPUiIsIkxPR0dBTkFMWVNFRk9SVkFMVEVSIiwiRFJJRlRTUEVSU09ORUxMIiwiS0xJTklTS0ZPUlZBTFRFUiIsIk5PS0tFTEFETUlOSVNUUkFUT1IiLCJWRVJJRklLQVNKT05TUEVSU09ORUxMIl0sIm5iZiI6MTYxMTE0MjUyOCwianRpIjoiNGZlNTlkOTgtMDdkYS00OGEzLWIwYmEtYWI1NGVkNGZlMWNjIn0.mIej6fqq6Q9uEuIrUxR7S1EyJMB7f03pTpPiFHTb9QBpkP3lFcungi-yu2CoOlZSCKJZnmFC7icyaeSBQoq60DzgqzguJ8hX0xY823YPrwKjvzKH_vENskfUpMbYlF23HEP3npNd6pEppDECTZXhwgprJAHQtd9GH_Vo2mSOd_OuNLzp1WC0R22IXs6Yu4U0-ga28dr7Ei08EaxmmE-xscHTRfMcrAitTZ1Va77HoJJGL2LmflvI32REWy_5b6wS-0In7OrSCwajhbjCA5-WsxdLdMtOVbe0LaoXeJ6nvsKQkG525_TVnr8dO2OF7aSOzSe6B3pbcZWGkUZI7zn-9A"),
            http("request_5")
			.get("/hpp-webapp/api/hentMetadataHppBruker")
			.headers(headers_1),
            http("request_6")
			.get("/hpp-webapp/api/autorisasjonsprosess/hentBrukergruppevalg")
			.headers(headers_1)))
		.pause(4)
		.exec(http("request_7")
			.post("/hpp-webapp/api/autorisasjonsprosess/velgBrukergruppe")
			.headers(headers_7)
			.body(RawFileBody("magnus/onsdagsimulation/0007_request.json"))
			.resources(http("request_8")
			.get("/hpp-webapp/api/hentMetadataHppBruker")
			.headers(headers_1),
            http("request_9")
			.get("/hpp-webapp/api/driftsmelding/nyeDriftsmeldinger")
			.headers(headers_1)))
		.pause(3)
		.exec(http("request_10")
			.post("/hpp-webapp/api/personsok/sok")
			.headers(headers_7)
			.body(RawFileBody("magnus/onsdagsimulation/0010_request.json"))
			.resources(http("request_11")
			.get("/hpp-webapp/api/driftsmelding/nyeDriftsmeldinger")
			.headers(headers_1),
            http("request_12")
			.post("/hpp-webapp/api/personsok/settSamtykke")
			.headers(headers_7)
			.body(RawFileBody("magnus/onsdagsimulation/0012_request.json")),
            http("request_13")
			.get("/hpp-webapp/api/driftsmelding/nyeDriftsmeldinger")
			.headers(headers_1),
            http("request_14")
			.post("/hpp-webapp/api/sperrePlakat/hentSperretModulStatus/")
			.headers(headers_7)
			.body(RawFileBody("magnus/onsdagsimulation/0014_request.json")),
            http("request_15")
			.get("/hpp-webapp/assets/c8ddf1e5e5bf3682bc7bebf30f394148.woff")
			.headers(headers_15),
            http("request_16")
			.get("/hpp-webapp/api/personalia/NThhYTNlNmQtNDg4Yy00NzVmLThhYzktZDgzMjJiNDdmODFj/folkeregistrertbostedsadresse")
			.headers(headers_1),
            http("request_17")
			.get("/hpp-webapp/api/kontaktpersoner/NThhYTNlNmQtNDg4Yy00NzVmLThhYzktZDgzMjJiNDdmODFj")
			.headers(headers_1),
            http("request_18")
			.get("/hpp-webapp/api/kommunikasjon/NThhYTNlNmQtNDg4Yy00NzVmLThhYzktZDgzMjJiNDdmODFj")
			.headers(headers_1),
            http("request_19")
			.get("/hpp-webapp/api/sykdomsepisoder/NThhYTNlNmQtNDg4Yy00NzVmLThhYzktZDgzMjJiNDdmODFj")
			.headers(headers_1),
            http("request_20")
			.get("/hpp-webapp/api/sykdomsepisoder/NThhYTNlNmQtNDg4Yy00NzVmLThhYzktZDgzMjJiNDdmODFj/tilVurdering")
			.headers(headers_1),
            http("request_21")
			.get("/hpp-webapp/api/kritiskInfo/NThhYTNlNmQtNDg4Yy00NzVmLThhYzktZDgzMjJiNDdmODFj/kategoriStatus")
			.headers(headers_1),
            http("request_22")
			.get("/hpp-webapp/api/legemidler/NThhYTNlNmQtNDg4Yy00NzVmLThhYzktZDgzMjJiNDdmODFj/oversikt")
			.headers(headers_1),
            http("request_23")
			.get("/hpp-webapp/assets/433bd595f2c773c0d50cb81b3ba6af95.svg")
			.headers(headers_23),
            http("request_24")
			.get("/hpp-webapp/api/kritiskInfo/NThhYTNlNmQtNDg4Yy00NzVmLThhYzktZDgzMjJiNDdmODFj")
			.headers(headers_1)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}