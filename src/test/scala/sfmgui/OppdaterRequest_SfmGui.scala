package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._



object OppdaterRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val oppdater =

  //feed(csv("data/pid.csv").circular)

    repeat(4) {

      feed(csv("data/pid.csv").circular)

    .exec(http("patientTicket")
      .post("/api/PatientTicket")
      .headers(headers_0)
      .body(ElFileBody("bodies/pid.json"))
      .check(jsonPath("$..*").saveAs("PatientTicket")))


      .exec(http("prescriptions")
        .get("https://server.qa.forskrivning.no/api/Prescriptions")
        //.get("https://server.staging.sfm.cloud/api/Prescriptions")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .check(jsonPath("$.libItems").exists))

    }
}

