package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._


object SendeLegemiddelRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val send =

    feed(csv("data/pid.csv").circular)

    .exec(http("patientTicket")
      .post("/api/PatientTicket")
      .headers(headers_0)
      .body(ElFileBody("bodies/pid.json"))
      .check(jsonPath("$..*").saveAs("PatientTicket")))

      .exec(http("getQueue")
        .get("https://server.qa.forskrivning.no/api/patients/${PatientTicket}/getQueue.xml")
        .headers(headers_2))

      .exec(http("getQueueIds")
        .get("https://server.qa.forskrivning.no/api/patients/${PatientTicket}/getQueueIds")
        .headers(headers_2))

      .exec(http("signAndSend")
        .put("https://server.qa.forskrivning.no/api/patients/${PatientTicket}/signAndSend")
        .headers(headers_2)
        .body(ElFileBody("bodies/sign_and_send.json")))

      .exec(http("prescriptions")
        .get("https://server.qa.forskrivning.no/api/Prescriptions")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .check(jsonPath("$.libItems").exists))

      .exec(http("patients")
        .get("https://server.qa.forskrivning.no/api/patients")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        //.check(jsonPath("$.libItems").exists)
      )



}

