package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, _}

class KubtestSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://kubtest.kj.nhn.no")
    .inferHtmlResources()
    .acceptHeader("application/json, text/javascript, */*; q=0.01")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:84.0) Gecko/20100101 Firefox/84.0")

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map("Accept" -> "text/css,*/*;q=0.1")

  val headers_2 = Map("Accept" -> "*/*")

  val headers_5 = Map("X-Requested-With" -> "XMLHttpRequest")

  val headers_6 = Map("Accept" -> "image/webp,*/*")

  val headers_7 = Map(
    "Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
    "Accept-Encoding" -> "identity")

  val headers_11 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Origin" -> "null",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_12 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Origin" -> "https://kubtest.kj.nhn.no",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_CsrfToken = Map(
    "Content-Type" -> "application/json",
    "Origin" -> "https://kubtest.kj.nhn.no",
    "X-Kj-CsrfToken" -> "${csrfToken}",
    "X-Requested-With" -> "XMLHttpRequest")

  val uri1 = "https://helseid-sts.test.nhn.no/signin-testidp-oidc"

  val scn = scenario("KubtestSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/kubtest_fnr.csv").circular)

    .exec(http("index")
      .get("/innlogging-webapp/index.html")
      .headers(headers_0))

    .exec(http("users")
      .get("/innlogging-webapp/mock/Innlogging/users")
      .headers(headers_5))

    .exec(http("nyeDriftsmeldinger")
      .get("/innlogging-webapp/api/driftsmelding/nyeDriftsmeldinger")
      .headers(headers_5))

    .exec(http("mock")
      .get("/innlogging-webapp/mock/Innlogging/login?nin=05085600143&resource_url=https%3A%2F%2Fkubtest.kj.nhn.no%2Fhpp-webapp")
      .headers(headers_0)
      .check(regex("name='state' value='(.*?)' />").saveAs("state"))
      .check(xpath(".//input[@type=\"hidden\" and @name=\"session_state\"]/@value").saveAs("session_state"))
      .check(xpath(".//input[@type=\"hidden\" and @name=\"scope\"]/@value").saveAs("scope"))
      .check(xpath(".//input[@type=\"hidden\" and @name=\"code\"]/@value").saveAs("code"))
      .check(regex("name='id_token' value='(.*?)' />").saveAs("id_token"))
      .resources(http("signin-testidp-oidc")
        .post(uri1)
        .headers(headers_11)
        .formParam("code", "${code}")
        .formParam("id_token", "${id_token}")
        .formParam("scope", "${scope}")
        .formParam("state", "${state}")
        .formParam("session_state", "${session_state}")
        .check(xpath(".//INPUT[@TYPE=\"HIDDEN\" and @NAME=\"jwt\"]/@VALUE").saveAs("jwt"))))

    .exec(http("hpp-webapp_m_jwt")
      .post("/hpp-webapp")
      .headers(headers_12)
      .formParam("jwt", "${jwt}"))

    .exec(http("hentMetadataHppBruker1")
      .get("/hpp-webapp/api/hentMetadataHppBruker")
      .headers(headers_1)
      .check(jsonPath("$.csrfToken").saveAs("csrfToken")))

    .exec(http("hentBrukergruppeValg")
      .get("/hpp-webapp/api/autorisasjonsprosess/hentBrukergruppevalg")
      .headers(headers_1))

    .exec(http("velgBrukergruppe")
      .post("/hpp-webapp/api/autorisasjonsprosess/velgBrukergruppe")
      .headers(headers_CsrfToken)
      .body(ElFileBody("magnus/velgBrukergruppe.json")))

    .exec(http("hentMetadataHppBruker2")
      .get("/hpp-webapp/api/hentMetadataHppBruker")
      .headers(headers_1))

    .exec(http("nyeDriftsmeldinger")
      .get("/hpp-webapp/api/driftsmelding/nyeDriftsmeldinger")
      .headers(headers_1))

    .exec(http("personsok/sok")
      .post("/hpp-webapp/api/personsok/sok")
      .headers(headers_CsrfToken)
      .body(ElFileBody("magnus/sokPasient.json"))
      .check(jsonPath("$.uuid").saveAs("uuid"))
      .check(jsonPath("$.fnr").saveAs("pasientFnr"))
      .check(jsonPath("$.historiskFnr").saveAs("historiskFnr"))
      .check(jsonPath("$.erDoed").saveAs("erDoed"))
      .check(jsonPath("$.erReservertDoed").saveAs("erReservertDoed"))
      .check(jsonPath("$.erDiskret").saveAs("erDiskret")))

    .exec(http("settSamtykke")
      .post("/hpp-webapp/api/personsok/settSamtykke")
      .headers(headers_CsrfToken)
      .body(ElFileBody("magnus/settSamtykke.json")))

    .exec(http("hentSperretModulStatus")
      .post("/hpp-webapp/api/sperrePlakat/hentSperretModulStatus/")
      .headers(headers_CsrfToken)
      .body(ElFileBody("magnus/hentSperretModulStatus.json")))

    .exec(http("oversikt")
      .get("/hpp-webapp/api/legemidler/${uuid}/oversikt")
      .headers(headers_1))


  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}