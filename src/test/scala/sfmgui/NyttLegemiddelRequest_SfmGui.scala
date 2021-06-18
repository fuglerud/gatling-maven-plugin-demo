package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._

object NyttLegemiddelRequest_SfmGui {

    exec(flushCookieJar)
      .exec(flushHttpCache)

    val nyttlegemiddel =

      feed(csv("data/pid.csv").circular)

      .exec(http("patientTicket")
        .post("/api/PatientTicket")
        .headers(headers_0)
        .body(ElFileBody("bodies/pid.json"))
        .check(jsonPath("$..*").saveAs("PatientTicket")))

        .exec(http("warnings")
          .get("https://server.qa.forskrivning.no/api/medications/ID_86EC1402-96E6-4DC5-AB9A-434EC440575F/warnings")
          .headers(headers_2)
          .queryParam("PatientTicket","${PatientTicket}"))

        .exec(http("shortdose")
          .get("https://server.qa.forskrivning.no/api/medications/ID_86EC1402-96E6-4DC5-AB9A-434EC440575F/shortdose")
          .headers(headers_2))

        .exec(http("properties")
          .get("https://server.qa.forskrivning.no/api/medications/ID_86EC1402-96E6-4DC5-AB9A-434EC440575F/properties")
          .headers(headers_2)
          .queryParam("PatientTicket","${PatientTicket}"))

        .exec(http("codingsystems")
          .get("https://server.qa.forskrivning.no/api/codingsystems/7488")
          .headers(headers_2))

        .exec(http("users")
          .get("https://server.qa.forskrivning.no/api/users")
          .headers(headers_2))

        .exec(http("prescriptions")
          .get("https://server.qa.forskrivning.no/api/prescriptions")
          .headers(headers_2)
          .queryParam("PatientTicket","${PatientTicket}")
          .body(ElFileBody("bodies/prescriptions.json"))
          .check(jsonPath("$.libItems").exists))

        .exec(http("prescriptions2")
          .get("https://server.qa.forskrivning.no/api/prescriptions")
          .headers(headers_2)
          .queryParam("PatientTicket","${PatientTicket}")
          .queryParam("type","Lib")
          .check(jsonPath("$.libItems").exists)
          .check(jsonPath("$..treatmentId").saveAs("treatmentId"))
          .check(jsonPath("$..id").saveAs("id")))

    /* .exec(session=>{
       println("treatmentId:")
       println(session("treatmentId").as[String])
       session})

         .exec(session=>{
           println("id:")
           println(session("id").as[String])
           session})*/





  }



