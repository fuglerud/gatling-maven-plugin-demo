package sfmgui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import sfmgui.Headers_SfmGui._

object LogoutRequest_SfmGui {

  exec(flushCookieJar)
    .exec(flushHttpCache)

  val loggav =

    exec(http("loggAv")
      .post("/api/session/end")
      .headers(headers_0))
}

