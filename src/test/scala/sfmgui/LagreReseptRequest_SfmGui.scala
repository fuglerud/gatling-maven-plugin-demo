package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._

object LagreReseptRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val lagreresept =

    feed(csv("data/pid.csv").circular)

    .exec(http("patientTicket")
      .post("/api/PatientTicket")
      .headers(headers_0)
      .body(ElFileBody("bodies/pid.json"))
      .check(jsonPath("$..*").saveAs("PatientTicket")))

      .exec(http("warnings")
        .get("https://server.qa.forskrivning.no/api/medications/ID_86EC1402-96E6-4DC5-AB9A-434EC440575F/warnings")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .queryParam("drugType","Brand"))

      .exec(http("shortdose")
        .get("https://server.qa.forskrivning.no/api/medications/ID_86EC1402-96E6-4DC5-AB9A-434EC440575F/shortdose")
        .headers(headers_2))

      .exec(http("properties")
        .get("https://server.qa.forskrivning.no/api/medications/ID_86EC1402-96E6-4DC5-AB9A-434EC440575F/properties")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}"))

      .exec(http("packages")
        .get("https://server.qa.forskrivning.no/api/medications/ID_86EC1402-96E6-4DC5-AB9A-434EC440575F/packages")
        .headers(headers_2))

      .exec(http("codingsystems")
        .get("https://server.qa.forskrivning.no/api/codingsystems/7488")
        .headers(headers_2))

      .exec(http("users")
        .get("https://server.qa.forskrivning.no/api/users")
        .headers(headers_2)
        .queryParam("userRoles","Doctor"))

      .exec(http("reimbursements")
        .get("https://server.qa.forskrivning.no/api/medications/ID_86EC1402-96E6-4DC5-AB9A-434EC440575F/reimbursements?")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .queryParam("forskrevetAv","b0e5a3e8-5e54-4bf8-b9f3-17a1f0339c1e"))

      .exec(http("prescriptions")
        .post("https://server.qa.forskrivning.no/api/prescriptions")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .body(ElFileBody("bodies/resept_lagre.json"))
        .check(jsonPath("$..success").is("true")))

      .exec(http("getPrescriptions")
        .get("https://server.qa.forskrivning.no/api/prescriptions")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .check(jsonPath("$.libItems").exists))

}