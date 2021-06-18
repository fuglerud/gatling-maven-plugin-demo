package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._

object EndreLegemiddelRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val endrelegemiddel =

    feed(csv("data/pid.csv").circular)

    .exec(http("patientTicket")
      .post("/api/PatientTicket")
      .headers(headers_0)
      .body(ElFileBody("bodies/pid.json"))
      .check(jsonPath("$..*").saveAs("PatientTicket")))

      .exec(http("shortdose")
        .get("https://server.qa.forskrivning.no/api/medications/ID_4BE71D12-8C56-4406-8FA4-14DAA6BDD296/shortdose")
        .headers(headers_2))

      .exec(http("properties")
        .get("https://server.qa.forskrivning.no/api/medications/ID_4BE71D12-8C56-4406-8FA4-14DAA6BDD296/properties")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}"))

      .exec(http("packages")
        .get("https://server.qa.forskrivning.no/api/medications/ID_4BE71D12-8C56-4406-8FA4-14DAA6BDD296/packages")
        .headers(headers_2))

      .exec(http("codingsystems")
        .get("https://server.qa.forskrivning.no/api/codingsystems/7488")
        .headers(headers_2))

      .exec(http("users")
        .get("https://server.qa.forskrivning.no/api/users")
        .headers(headers_2)
        .queryParam("userRoles","Doctor"))

      .exec(http("change")
        .put("https://server.qa.forskrivning.no/api/prescriptions/d3543aa2-53e8-45bb-2103-08d7b3a2334c/change/")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .body(ElFileBody("bodies/endreforskrivning_change.json"))
        .check(jsonPath("$..success").is("true")))

      .exec(http("prescriptions")
        .get("https://server.qa.forskrivning.no/api/prescriptions/")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .queryParam("type","lib")
        .check(jsonPath("$.libItems").exists))

}
