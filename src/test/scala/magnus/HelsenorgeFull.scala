package magnus

import java.io.PrintWriter

import io.gatling.commons.stats.{KO, OK}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class HelsenorgeFull extends Simulation {

  val httpProtocol = http
    .baseUrl("https://helsenorge.hn.test.nhn.no")
    .inferHtmlResources()

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

  val hn_UIResource = Map(
    "Accept" -> "application/json",
    "content-type" -> "application/json",
    "hnanonymoushash" -> "undefined",
    "hnauthenticatedhash" -> "undefined",
    "hntimestamp" -> "undefined",
    "hntjeneste" -> "undefined",
    "Origin" -> "https://helsenorge.hn.test.nhn.no",
    "Referer" -> "${site}",
    "Sec-Fetch-Dest" -> "empty",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36",
    "x-hn-hendelselogg" -> "undefined")

  //val feeder1 = sitemap("magnus/HelsenorgeTest01SiteMap.xml").random

 /* val writer1: PrintWriter = {
        val fos = new java.io.FileOutputStream("cmsOK.txt")
        new java.io.PrintWriter(fos, true)}*/


  val helsenorgeSiteMap = scenario("helsenorge_page")

    .feed(csv("magnus/sitemapKjoring.csv").circular)

    .exec(http(requestName = "startside")
      .get("https://helsenorge.hn.test.nhn.no/")
      .headers(hn)
      .check(status.is(expected = 200)))

    .exec(http(requestName = "startsidefooter")
      .get("https://helsenorge.hn.test.nhn.no/contentapi/internal/v1/footer")
      .headers(hn)
      .check(status.is(expected = 200)))

    .exec(http(requestName = "startsidesot")
      .get(url = "https://tjenester.hn.test.nhn.no/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.CoreFrontend.Micro")
      .headers(hn_UIResource)
      .check(status.is(expected = 200)))


    .pause(1,4)

    //.feed(feeder1)

    .exec(http(requestName = "1 "+"${site}")
      .get("${site}")
      .headers(hn)
      .check(status.is(expected = 200)))

    .exec(http(requestName = "1footer")
      .get("https://helsenorge.hn.test.nhn.no/contentapi/internal/v1/footer")
      .headers(hn)
      .check(status.is(expected = 200)))

    .exec(http(requestName = "1sot")
      .get(url = "https://tjenester.hn.test.nhn.no/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.CoreFrontend.Micro")
      .headers(hn_UIResource)
      .check(status.is(expected = 200)))

   /* .exec((session: io.gatling.core.session.Session) => {
           if (session.status == OK) {
             writer1.println(session.attributes("loc"))
           }
           session
        })*/

    .pause(1,4)

    .exec(http(requestName = "2 "+"${site}")
      .get("${site}")
      .headers(hn)
      .check(status.is(expected = 200)))

    .exec(http(requestName = "2footer")
      .get("https://helsenorge.hn.test.nhn.no/contentapi/internal/v1/footer")
      .headers(hn)
      .check(status.is(expected = 200)))

    .exec(http(requestName = "2sot")
      .get(url = "https://tjenester.hn.test.nhn.no/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.CoreFrontend.Micro")
      .headers(hn_UIResource)
      .check(status.is(expected = 200)))

    .pause(1,4)

    //.feed(feeder3)

    .exec(http(requestName = "3 "+"${site}")
      .get("${site}")
      .headers(hn)
      .check(status.is(expected = 200)))

    .exec(http(requestName = "3footer")
      .get("https://helsenorge.hn.test.nhn.no/contentapi/internal/v1/footer")
      .headers(hn)
      .check(status.is(expected = 200)))

    .exec(http(requestName = "3sot")
      .get(url = "https://tjenester.hn.test.nhn.no/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.CoreFrontend.Micro")
      .headers(hn_UIResource)
      .check(status.is(expected = 200)))


 /* val selectedProfile = System.getProperty("selectedProfile") match {
    case "profile1" => helsenorgeSiteMap.inject(atOnceUsers(1))
    case "profile2" => helsenorgeSiteMap.inject(rampUsersPerSec(1) to 150 during (15 minutes),constantUsersPerSec(150) during(48 hours))
    case "profile3" => helsenorgeSiteMap.inject(constantUsersPerSec(500) during(60))
    case "profile4" => helsenorgeSiteMap.inject(rampConcurrentUsers(5) to(200) during(120))
    case "profile5" => helsenorgeSiteMap.inject(constantConcurrentUsers(10) during (120), rampConcurrentUsers(10) to (100) during (120))
    case "profile6" => helsenorgeSiteMap.inject(incrementUsersPerSec(5).times(5).eachLevelLasting(10).separatedByRampsLasting(10).startingFrom(10))
    case "profile7" => helsenorgeSiteMap.inject(rampUsersPerSec(1) to 15 during (3 minutes),constantUsersPerSec(15) during(5 minutes))
    case "profile8" => helsenorgeSiteMap.inject(constantUsersPerSec(1) during(1481))

  }*/

  //setUp(helsenorgeSiteMap.inject(atOnceUsers(1))).protocols(httpProtocol)
  setUp(helsenorgeSiteMap.inject(rampUsersPerSec(1) to 30 during (30 minutes),constantUsersPerSec(30) during(48 hours)).protocols(httpProtocol))
  //setUp(helsenorgeSiteMap.inject(constantUsersPerSec(1) during(1481)))
}