package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class HelsenorgeFull extends Simulation {

  val httpProtocol = http
    .baseUrl("https://helsenorge.hn.test.nhn.no")
    //.inferHtmlResources()

  val hn = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Connection" -> "keep-alive",
    "Host" -> "helsenorge.hn.test.nhn.no",
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "none",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")

  val hn_helsenorge = Map(
    "Accept" -> "application/json",
    "content-type" -> "application/json",
    "hnanonymoushash" -> "undefined",
    "hnauthenticatedhash" -> "undefined",
    "hntimestamp" -> "undefined",
    "hntjeneste" -> "undefined",
    "Host" -> "minhelse.hn.test.nhn.no",
    "Origin" -> "https://helsenorge.hn.test.nhn.no",
    "Referer" -> "https://helsenorge.hn.test.nhn.no/",
    "Sec-Fetch-Dest" -> "empty",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36",
    "x-hn-hendelselogg" -> "undefined")

  val hn_UIResource = Map(
    "Accept" -> "application/json",
    "content-type" -> "application/json",
    "hnanonymoushash" -> "undefined",
    "hnauthenticatedhash" -> "undefined",
    "hntimestamp" -> "undefined",
    "hntjeneste" -> "undefined",
    "Origin" -> "https://helsenorge.hn.test.nhn.no",
    "Referer" -> "${loc}",
    "Sec-Fetch-Dest" -> "empty",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36",
    "x-hn-hendelselogg" -> "undefined")


  val hn_GetEnvironment = Map(
    "accept" -> "application/json",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Connection" -> "keep-alive",
    "content-type" -> "application/json",
    "Host" -> "minhelse.hn.test.nhn.no",
    "Origin" -> "https://helsenorge.hn.test.nhn.no",
    //"Referer" -> "${loc}",
    "Sec-Fetch-Dest: " -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-site",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")


  val feeder1 = sitemap("magnus/HelsenorgeTest01SiteMap.xml").random
  val feeder2 = sitemap("magnus/HelsenorgeTest01SiteMap.xml").random
  val feeder3 = sitemap("magnus/HelsenorgeTest01SiteMap.xml").random


  val helsenorgeSiteMap = scenario("helsenorge_page")


    .exec(http(requestName = "Helsenorge.no")
      .get("https://helsenorge.hn.test.nhn.no/")
      .headers(hn)
      .check(status.is(expected = 200))
    )
    /*
        .exec(http(requestName = "GetEnvironment")
          .get(url = "https://minhelse.hn.test.nhn.no/api/v1/Portal/GetEnvironment")
          .headers(hn_GetEnvironment)
          .check(status.is(expected = 200))
        )

      .exec(http(requestName = "SecurityFramework_1")
        .get(url = "https://minhelse.hn.test.nhn.no/proxy/sot/api/v1/UIResource")
        .headers(hn_helsenorge)
        .queryParam("Culture", "nb-NO")
        .queryParam("Filename", "HN.MinHelse.SecurityFramework")
        .queryParam("Rev", "2014.5.1.1")
        .check(status.is(expected = 200))
      )*/
    .pause(1,4)




    .feed(feeder1)

    .exec(http(requestName = "Feeder 1 "+"${loc}")
      .get("${loc}")
      .headers(hn)
      .check(status.is(expected = 200))
    )
    /*
       .exec(http(requestName = "GetEnvironment")
         .get(url = "https://minhelse.hn.test.nhn.no/api/v1/Portal/GetEnvironment")
         .headers(hn_GetEnvironment)
         .check(status.is(expected = 200))
       )

       .exec(http(requestName = "SecurityFramework_2")
        .get(url = "https://minhelse.hn.test.nhn.no/proxy/sot/api/v1/UIResource")
         .headers(hn_UIResource)
         .queryParam("Culture", "nb-NO")
         .queryParam("Filename", "HN.MinHelse.SecurityFramework")
         .queryParam("Rev", "2014.5.1.1")
         .check(status.is(expected = 200))
       )
    */
    .pause(1,4)


    .feed(feeder2)

    .exec(http(requestName = "Feeder 2 "+"${loc}")
      .get("${loc}")
      .headers(hn)
      .check(status.is(expected = 200))
    )

    .pause(1,4)

    .feed(feeder3)

    .exec(http(requestName = "Feeder 3 "+"${loc}")
      .get("${loc}")
      .headers(hn)
      .check(status.is(expected = 200))
    )

  //setUp(helsenorgeSiteMap.inject(constantUsersPerSec(rate = 1) during (1)).protocols(httpProtocol))
  //setUp(helsenorgeSiteMap.inject(atOnceUsers(250)).protocols(httpProtocol))
  //setUp(helsenorgeSiteMap.inject(rampConcurrentUsers(10) to(200) during(280 seconds)).protocols(httpProtocol))
  //setUp(helsenorgeSiteMap.inject(rampUsersPerSec(1) to(100) during(1200 seconds)).protocols(httpProtocol))
  setUp(helsenorgeSiteMap.inject(atOnceUsers(2)).protocols(httpProtocol))
  //setUp(helsenorgeSiteMap.inject(constantConcurrentUsers(2) during(2500)).protocols(httpProtocol))
  //setUp(helsenorgeSiteMap.inject(constantConcurrentUsers(1) during(3660)).protocols(httpProtocol))

}