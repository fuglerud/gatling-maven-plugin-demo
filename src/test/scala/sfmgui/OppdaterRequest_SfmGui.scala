package sfmgui

import io.gatling.commons.stats.KO
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._

import java.io.PrintWriter



object OppdaterRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val writer1: PrintWriter = {
    val fos = new java.io.FileOutputStream("sfm_gui_vasking.txt")
    new java.io.PrintWriter(fos, true)}

  val oppdater =

  feed(csv("data/pid.csv").circular)

    //repeat(1) {

      //feed(csv("data/pid.csv").circular)

    .exec(http("patientTicket")
      .post("/api/PatientTicket")
      .headers(headers_0)
      .body(ElFileBody("bodies/pid.json"))
      .check(jsonPath("$..*").saveAs("PatientTicket")))


      .exec(http("prescriptions")
        .get("https://server.qa.forskrivning.no/api/Prescriptions")
        //.get("https://server.staging.sfm.cloud/api/Prescriptions")
        //.get("https://server.test2.forskrivning.no/api/Prescriptions")
        .headers(headers_2)
        .queryParam("PatientTicket","${PatientTicket}")
        .check(jsonPath("$.libItems").exists))

      //  .pace(1)

    //}



    .exec((session: io.gatling.core.session.Session) => {
      if (session.status == KO) {
        writer1.println(session.attributes("pid"))
      }
      session
    })

}

