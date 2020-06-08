package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ContentAPISimulation extends Simulation{

  val httpProtocol = http
    .inferHtmlResources()
    .acceptHeader("application/json")

  val headers_0 = Map(
    "Content-Type"->"application/json")

  val headers_1 = Map(
    "Content-Type" -> "application/xml",
   // "Authorization" -> "Bearer ${access_token}",
    "Authorization" -> "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsInZlciI6MSwidHlwXzIiOiJyZWYifQ.eyJqdGkiOiIwNWMzNzZiYi0zZmQ4LTQ5MzgtOTgzZS04NWZjMDgwYjA0NzciLCJjbGllbnRfaWQiOiIxN2NlODA2My1hYmRjLTQyNjYtYTUyNC1kYzk3ZTBhMzA1ZWEiLCJjbGllbnRfbmFtZSI6IkZlbGxlcyBOZXR0bMO4c25pbmcgRm9yIFNwZXNpYWxpc3RoZWxzZXRqZW5lc3RlbiIsInJlZl90b2tlbiI6InB2eWN1X2M2WE9xYktBOXdfNW9hQ1AzSWt5Und3ZFVWNXNlcklHbXlseDAiLCJyZWZfdG9rZW5fdHlwZSI6IkZ1bGwiLCJ6b25lIjoiZWtzdGVybl9pbnRlcm5ldHQiLCJlbmR1c2VydHlwZSI6InN5c3RlbSIsIm5iZiI6MTU5MTYxMjkxMSwiZXhwIjoxNTkxNjE0NzExLCJpYXQiOjE1OTE2MTI5MTEsImlzcyI6InNpa2tlcmhldC1pbnRlcm4uaGVsc2Vub3JnZS5ubyJ9.sDgFEOM3JsLzbYtHrWxjJpnvrpCE4Z0Uu4SzmvmfqBQ"
  )

  val scn = scenario("ContentAPISimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/ContentAPI.csv").circular)
/*
    .exec(http("request_access_token")
      .post("https://eksternapi-hn-mas-01.int-hn.nhn.no/sts/Token")
      .headers(headers_0)
        .body(ElFileBody("magnus/ContentAPITestData.json"))
      .check(regex("access_token\":\"(.*?)\"").saveAs("access_token")))
    */

    .exec(http("Sykdomsinfo")
      .get("https://eksternapi-hn-mas-01.int-hn.nhn.no/contentapi/v1/Sykdomsinfo")
      .headers(headers_1)
      .queryParam("Sykdomtilstand","${sykdomtilstand}")
      .queryParam("Maalgruppe","${maalgruppe}")
      .check(status.is(expected = 200)))


  val selectedProfile = System.getProperty("selectedProfile") match {
    case "profile1" => scn.inject(atOnceUsers(1))
    case "profile2" => scn.inject(rampUsersPerSec(1) to 5 during (30),constantUsersPerSec(5) during(600))
    case "profile3" => scn.inject(constantUsersPerSec(500) during(60))
    case "profile4" => scn.inject(rampConcurrentUsers(5) to(300) during(120))
    case "profile5" => scn.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))
    case "profile6" => scn.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10))

  }

  setUp(selectedProfile)
  }

