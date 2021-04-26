package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._


object SettPinKodeRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val settpinkode =

    exec(http("patientTicket")
      .post("/api/PatientTicket")
      .headers(headers_0)
      .body(ElFileBody("bodies/pid.json"))
      .check(jsonPath("$..*").saveAs("PatientTicket")))

      .exec(http("tokens")
        .get("https://server.qa.forskrivning.no/api/tokens/${PatientTicket}")
        .headers(headers_2))

      .exec(http("setPinCode")
        .post("https://server.qa.forskrivning.no/api/users/setPinCode")
        .headers(headers_2)
        .body(StringBody("""1234""")))

      .exec(http("verifyPinCode")
        .post("https://server.qa.forskrivning.no/api/users/verifyPinCode")
        .headers(headers_2)
        .body(StringBody("""1234""")))
}


