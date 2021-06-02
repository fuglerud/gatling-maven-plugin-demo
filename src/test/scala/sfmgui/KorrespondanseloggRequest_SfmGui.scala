package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._


object KorrespondanseloggRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val hentKorrespondanselogg =

    feed(csv("data/pid.csv").circular)

    .exec(http("patientTicket")
      .post("/api/PatientTicket")
      .headers(headers_0)
      .body(ElFileBody("bodies/pid.json"))
      .check(jsonPath("$..*").saveAs("PatientTicket")))

      .exec(http("tokens")
        .get("https://server.qa.forskrivning.no/api/tokens/${PatientTicket}")
        .headers(headers_2))

      .exec(http("messagelogs")
        .post("https://server.qa.forskrivning.no/api/patients/${PatientTicket}/messagelogs")
        .body(ElFileBody("bodies/korrespondanselogg.json"))
        .headers(headers_2)
        .check(jsonPath("$..messageLogType").exists))
}


