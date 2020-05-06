package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class KjerneJournalProveSvarSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://qa1.kjernejournal-test.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("application/json, text/javascript, */*; q=0.01")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0")
    .disableCaching

  val headers_0 = Map(
    "Connection"->"keep-alive",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map("X-Requested-With" -> "XMLHttpRequest")

  val headers_4 = Map(
    "Content-Type" -> "application/json",
    "Origin" -> "https://qa1.kjernejournal-test.no",
    "X-Kj-CsrfToken" -> "${csrfToken}",
    "X-Requested-With" -> "XMLHttpRequest")

  val headers_5 = Map("X-Requested-With" -> "XMLHttpRequest")

  val headers_6 = Map(
    "X-Kj-CsrfToken" -> "${csrfToken}",
    "Host"->"qa1.kjernejournal-test.no",
    "Connection"->"keep-alive",
    "Content-Length"->"2",
    "Accept"->"*/*",
    "Sec-Fetch-Dest"->"empty",
    "X-Requested-With"->"XMLHttpRequest",
    "User-Agent"->"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36",
    "Content-Type"->"application/json",
    "Origin"->"https://qa1.kjernejournal-test.no",
    "Sec-Fetch-Site"->"same-origin",
    "Sec-Fetch-Mode"->"cors",
    "Referer"->"https://qa1.kjernejournal-test.no/hpp-webapp/kjernejournal.html",
    "Accept-Encoding"->"gzip, deflate, br",
    "Accept-Language"->"en-US,en;q=0.9")


  val scn = scenario("VisOmPasientenSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("data/VisOmPasientenTestData.csv").circular)
    .feed(csv("data/unike_fnr.csv").random)

    .exec(http("request_hpp-webapp")
      .get("/hpp-webapp/")
      .headers(headers_0))

    .exec(http("request_Mockinnlogging")
      .get("/kj-innlogging/Innlogging/Mockinnlogging?dropdown=${dropdown}&nin=${nin}&personid=${personid}")
      .headers(headers_0)
      .check(regex("<input type=\"hidden\" name=\"jwt\" value=\"(.*?)\"").saveAs("jwt")))

    .exec(http("request_hpp-webapp_2")
      .post("/hpp-webapp/")
      .headers(headers_0)
      .formParam("jwt", "${jwt}"))

    .exec(http("request_autorisasjon")
      .get("/hpp-webapp/autorisasjon")
      .headers(headers_0))

    .exec(http("request_kjernejournal")
      .get("/hpp-webapp/kjernejournal.html")
      .headers(headers_0))

    .exec(http("request_hentMetadataHppBruker")
      .get("/hpp-webapp/api/hentMetadataHppBruker")
      .headers(headers_0)
      .check(jsonPath("$.csrfToken").saveAs("csrfToken")))

    .exec(http("request_hentBrukergruppevalg")
      .get("/hpp-webapp/api/autorisasjonsprosess/hentBrukergruppevalg")
      .headers(headers_0))

    .exec(http("request_velgBrukergruppe")
      .post("/hpp-webapp/api/autorisasjonsprosess/velgBrukergruppe")
      .headers(headers_4)
      .body(ElFileBody("magnus/visompasientensimulation/velgBrukergruppe_request.json"))
      .resources(http("request_hentMetadataHppBruker")
        .get("/hpp-webapp/api/hentMetadataHppBruker")
        .headers(headers_0)
        .check(jsonPath("$.innloggetBrukerNavn").is("${innloggetBrukerNavn}")),
        http("request_nyeDriftsmeldinger")
          .get("/hpp-webapp/api/driftsmelding/nyeDriftsmeldinger")
          .headers(headers_0)))

    .exec(http("request_personsok/sok")
      .post("/hpp-webapp/api/personsok/sok")
      .headers(headers_4)
      .body(ElFileBody("magnus/visompasientensimulation/sokPasient_request.json"))
      .check(jsonPath("$.uuid").saveAs("uuid"))
      .check(jsonPath("$.fnr").saveAs("pasientFnr"))
      .check(jsonPath("$.historiskFnr").saveAs("historiskFnr"))
      .check(jsonPath("$.erDoed").saveAs("erDoed"))
      .check(jsonPath("$.erReservertDoed").saveAs("erReservertDoed"))
      .check(jsonPath("$.erDiskret").saveAs("erDiskret"))
      .resources(http("request_nyeDriftsmeldinger")
        .get("/hpp-webapp/api/driftsmelding/nyeDriftsmeldinger")
        .headers(headers_1)))

    .exec(http("request_settSamtykke")
      .post("/hpp-webapp/api/personsok/settSamtykke")
      .headers(headers_4)
      .body(ElFileBody("magnus/visompasientensimulation/nyeDriftsmeldinger_request.json"))
      .resources(http("request_nyeDriftsmeldinger")
        .get("/hpp-webapp/api/driftsmelding/nyeDriftsmeldinger")
        .headers(headers_1),
        http("request_sperrePlakat_hentSperretModulStatus")
          .post("/hpp-webapp/api/sperrePlakat/hentSperretModulStatus/")
          .headers(headers_4)
          .body(ElFileBody("magnus/visompasientensimulation/personalia_request.json")),
        http("request_personalia")
          .get("/hpp-webapp/api/personalia/${uuid}")
          .headers(headers_1),
        http("request_kritiskInfo")
          .get("/hpp-webapp/api/kritiskInfo/${uuid}")
          .headers(headers_1),
        http("request_kontaktpersoner")
          .get("/hpp-webapp/api/kontaktpersoner/${uuid}")
          .headers(headers_1),
        http("request_kommunikasjon")
          .get("/hpp-webapp/api/kommunikasjon/${uuid}")
          .headers(headers_1),
        http("request_sykdomsepisoder")
          .get("/hpp-webapp/api/sykdomsepisoder/${uuid}")
          .headers(headers_1),
        http("request_kritiskInfo")
          .get("/hpp-webapp/api/kritiskInfo/${uuid}")
          .headers(headers_1),
        http("request_oversikt")
          .get("/hpp-webapp/api/legemidler/${uuid}/oversikt")
          .headers(headers_1)
          .check(jsonPath("$.criticalInformationMissing").is("false")),
      ))


    .exec(http("request_provesvar")
      .post("/hpp-webapp/api/labresult/${uuid}/metadata")
      .headers(headers_6)
      .check(status.is(expected = 200)))


    .exec(http("request_kategoriStatus")
      .get("/hpp-webapp/api/kritiskInfo/${uuid}/kategoriStatus")
      .headers(headers_6)
      .check(status.is(expected = 200)))


  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(constantUsersPerSec(rate=5) during(60))).protocols(httpProtocol)
}