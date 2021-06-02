package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._

object LoginRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  /*val writer2: PrintWriter = {
    val fos = new java.io.FileOutputStream("sfm_gui_vasking_tokens.txt")
    new java.io.PrintWriter(fos, true)}*/

  val login =

    feed(csv("data/tokens.csv").circular)
      //.feed(csv("data/pid.csv").circular)

      .exec(http("create")
        .post("/api/Session/create")
        .headers(headers_0)
        .body(ElFileBody("bodies/create_nonce.json"))
        .check(jsonPath("$..id").saveAs("Id"))
        .check(jsonPath("$..code").saveAs("Code"))
        .check(jsonPath("$..clientAddress").saveAs("ClientAddress"))
        .check(jsonPath("$..apiAddress").saveAs("ApiAddress")))


      .exec(http("loggInn")
        .post("https://server.qa.forskrivning.no/api/Login")
        //.post("https://server.staging.sfm.cloud/api/Login")
        //.post("https://server.test2.forskrivning.no/api/Login")
        .headers(headers_1)
        .body(StringBody("""t7+pB0i9UFCt0e4hLPrRjO+YAIVJzuPLCWEmd1yAdjLBNES1lEsL7XLNMo4oJis1ZXeT7ZbqYWPYdlN65LXWAA=="""))
        .check(headerRegex("Set-Cookie", "(?s).*__Host-sfm_session=([^;]*).*").saveAs("Cookie")))



    /*.exec((session: io.gatling.core.session.Session) => {
      if (session.status == OK) {
        writer2.println(session.attributes("token"))
      }
      session
    })*/

}

