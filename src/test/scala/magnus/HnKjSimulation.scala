package magnus

import io.gatling.commons.stats.KO
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class HnKjSimulation extends Simulation{

  val httpProtocol = http
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("application/json, text/javascript, */*; q=0.01")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0")
    .disableCaching

    val headers_0 = Map(
      "Accept"->"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
      "Sec-Fetch-Mode"->"navigate",
      "Sec-Fetch-Site"->"none",
      "Upgrade-Insecure-Requests"->"1")

    val headers_1 = Map(
      "Accept" -> "application/json",
      "Accept-Encoding" -> "gzip, deflate, br",
      "Accept-Language" -> "en-US,en;q=0.9",
      "Connection" -> "keep-alive",
      "Content-type" -> "application/json",
      "hnanonymoushash" -> "${AnonymousHash}",
      "hnauthenticatedhash" -> "${AuthenticatedHash}",
      "hntimestamp" -> "${TimeStamp}",
      "hntjeneste" -> "${TjenesteType}",
      "Sec-Fetch-Mode" -> "cors",
      "Sec-Fetch-Site" -> "same-origin",
      "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36",
      "x-hn-hendelselogg" -> "${HendelseLoggType}")

  val headers_2 = Map(
    "Accept" -> "application/json",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Connection" -> "keep-alive",
    "Content-type" -> "application/json",
    "hnanonymoushash" -> "${AnonymousHash}",
    "hnauthenticatedhash" -> "${AuthenticatedHash}",
    "hntimestamp" -> "${TimeStamp}",
    "hntjeneste" -> "${TjenesteType}",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin",
    "Referer" -> "https://tjenester.hn.test.nhn.no/helsekontakter",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36",
    "x-hn-hendelselogg" -> "${HendelseLoggType}")


    val writer = {
      val fos = new java.io.FileOutputStream("testresultater.txt")
      new java.io.PrintWriter(fos,true)}


    val scn = scenario("HnKjSimulation")

      .exec(flushCookieJar)
      .exec(flushHttpCache)

      .feed(csv("magnus/HnTestdata.csv").circular)
      .feed(csv("magnus/HnTestdata_Kontaktpersoner.csv").circular)

      .exec(http("login")
        .get("https://tjenester.hn.test.nhn.no/?pnr=${pnr}")
        .headers(headers_0)
        .check(status.is(expected = 200))
        .check(regex("\"__Authorized__\": (.*?),").saveAs("Authorized"))
        .check(regex("\"__AnonymousHash__\": \"(.*?)\"").saveAs("AnonymousHash"))
        .check(regex("\"__HendelseLoggType__\": \"(.*?)\"").saveAs("HendelseLoggType")))


      .exec(http(requestName = "kj_donokort")
          .get("https://tjenester.hn.test.nhn.no/donorkort")
          .headers(headers_0)
          .check(status.is(expected = 200))
          .check(regex("\"__AnonymousHash__\": \"(.*?)\"").saveAs("AnonymousHash"))
          .check(regex("\"__AuthenticatedHash__\": \"(.*?)\"").saveAs("AuthenticatedHash"))
          .check(regex("\"__TimeStamp__\": \"(.*?)\"").saveAs("TimeStamp"))
          .check(regex("\"__TjenesteType__\": \"(.*?)\"").saveAs("TjenesteType"))
          .check(regex("\"__HendelseLoggType__\": \"(.*?)\"").saveAs("HendelseLoggType")))

        .exec(http(requestName = "getDonokort")
          .get("https://minhelse.hn.test.nhn.no/api/v1/MinHelse/GetDonorkort")
          .headers(headers_1)
          .check(status.is(expected = 200))
          .check(jsonPath("$..Donor").saveAs("betingelse")))

        .doIfEqualsOrElse(session => session("betingelse").as[String], "true") {
          exec(http("slettDonorkort")
            .post("https://tjenester.hn.test.nhn.no/api/v1/MinHelse/UpdateDonorkort")
            .body(ElFileBody("magnus/HnKj_donorkort_slett_request_body.json"))
            .headers(headers_1)
            .check(status.is(expected = 200)))
        } {
          exec(http("opprettDonorkort")
            .post("https://tjenester.hn.test.nhn.no/api/v1/MinHelse/UpdateDonorkort")
            .body(ElFileBody("magnus/HnKj_donorkort_opprett_request_body.json"))
            .headers(headers_1)
            .check(status.is(expected = 201)))}

        //Kontaktpersoner
        .exec(http(requestName = "helsekontakter")
          .get("https://tjenester.hn.test.nhn.no/helsekontakter")
          .headers(headers_1)
          .check(status.is(expected = 200))
          .check(regex("\"__AnonymousHash__\": \"(.*?)\"").saveAs("AnonymousHash"))
          .check(regex("\"__AuthenticatedHash__\": \"(.*?)\"").saveAs("AuthenticatedHash"))
          .check(regex("\"__TimeStamp__\": \"(.*?)\"").saveAs("TimeStamp"))
          .check(regex("\"__TjenesteType__\": \"(.*?)\"").saveAs("TjenesteType"))
          .check(regex("\"__HendelseLoggType__\": \"(.*?)\"").saveAs("HendelseLoggType")))

        .exec(http(requestName = "hentKontaktperson")
          .get("https://minhelse.hn.test.nhn.no/api/v1/kontaktperson/hent")
          .headers(headers_1)
          .check(status.is(expected = 200)))

      .exec(http(requestName = "LeggTilKJKontaktPerson")
        .post("https://tjenester.hn.test.nhn.no/api/v1/MinHelse/UpdateKJKontaktPerson")
        .body(ElFileBody("magnus/HnKj_LeggTil_Kontaktperson_request_body.json"))
        .headers(headers_1)
        .check(status.is(expected = 200)))

      .exec(http(requestName = "GetKJKontaktPerson")
        .get("https://tjenester.hn.test.nhn.no/api/v1/MinHelse/GetKJKontaktPersoner?KontaktKategorier=1")
        .check(jsonPath("$..Id").saveAs("Id"))
        .check(jsonPath("$..Donorkontakt").saveAs("Donorkontakt"))
        .check(jsonPath("$..Kode").saveAs("Kode"))
        .check(jsonPath("$..HarVersjonsnummer").saveAs("HarVersjonsnummer"))
        .check(jsonPath("$..Versjon").saveAs("Versjon"))
        .check(jsonPath("$..HarVersjonsnummer").saveAs("HarVersjonsnummer"))
        .check(jsonPath("$..EndretDato").saveAs("EndretDato"))
        .check(jsonPath("$..Type").saveAs("Type"))
        .check(jsonPath("$..Navn").is("${Navn}"))
        .headers(headers_1)
        .check(status.is(expected = 200)))

      .exec(http(requestName = "SlettKJKontaktPerson")
        .delete("https://tjenester.hn.test.nhn.no/api/v1/MinHelse/DeleteKJKontaktPerson")
        .body(ElFileBody("magnus/HnKj_Slett_Kontaktperson_request_body.json"))
        .headers(headers_1)
        .check(status.is(expected = 200)))





        //Besøkshistorikk - merknad
        //Egenregistrerte sykdommer
        //Kommunikasjonsutfordringer
        //Innbygger
        //Kodeverk
        //Legemiddel
        //PasientensFelter
        //Sperring
        //Besøkshistorikk
        //Brukslogg
        //Innsyn
        //KritiskInfo
        //Reservasjon
        //Varslingsprofil

        .exec((session: io.gatling.core.session.Session) => {
          if (session.status == KO) {
            writer.println(session.attributes("pnr"))}
          session})


        //setUp(scn.inject(atOnceUsers(2))).protocols(httpProtocol)
        setUp(scn.inject(constantUsersPerSec(4) during(30))).protocols(httpProtocol)

    }