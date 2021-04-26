package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._

object KritiskeReaksjonerRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val kritiskereaksjoner =

    exec(http("patientTicket")
      .post("/api/PatientTicket")
      .headers(headers_0)
      .body(ElFileBody("bodies/pid.json"))
      .check(jsonPath("$..*").saveAs("PatientTicket")))

      .exec(http("allergyReactionsGrouped")
        .get("https://server.qa.forskrivning.no/api/codingsystems/allergyReactionsGrouped")
        .headers(headers_2))

      .exec(http("codingsystems1")
        .get("https://server.qa.forskrivning.no/api/codingsystems/7520")
        .headers(headers_2))

      .exec(http("codingsystems2")
        .get("https://server.qa.forskrivning.no/api/codingsystems/7521")
        .headers(headers_2))

      .exec(http("codingsystems3")
        .get("https://server.qa.forskrivning.no/api/codingsystems/7498")
        .headers(headers_2))

      .exec(http("allergies")
        .post("https://server.qa.forskrivning.no/api/allergies")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .body(ElFileBody("bodies/kritiske_reaksjoner.json")))

      .exec(http("patients")
        .get("https://server.qa.forskrivning.no/api/patients")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}"))

      .exec(http("prescriptions")
        .get("https://server.qa.forskrivning.no/api/prescriptions/")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .queryParam("type","Lib"))


}

