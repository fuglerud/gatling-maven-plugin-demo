package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class JMeter extends Simulation  {

  val httpProtocol = http
    .baseUrl("https://epj.qa.forskrivning.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")

  val headers_1 = Map(
    "Content-Type" -> "application/json",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Update-Insecure-Requests" -> "1",
    "Host" -> "epj.qa.forskrivning.no",
    "Set-Fetch-User" -> "?1")




  val scn = scenario("JMeterSimulation")

    .exec(http(requestName = "epj_1")
      .get(url = "/")
      .headers(headers_1)
     // .check(xpath("//input[@type=\"hidden\" and @name=\"__RequestVerificationToken\").saveAs(\"runtimeInfoStatus\"))
      )

       /* .exec(http(requestName = "epj_2")
          .get(url = "/")
          .headers(headers_1))*/



        /*
        SelectedPortalId: PatientPortal
  submitButton: select
  SelectedPortalId:
  IsSelectingInstallation: False
  SelectedEnvironmentId:
  __RequestVerificationToken: CfDJ8EFL88cTHoVEolin_P1b-nluJFCCUUHe9UBewhG8ts19PDJh1CrRY0KbexOe18clv7yZ4SyHOnEnGhlz5zDsr5CMbG-MGLW4fZ4ORUBxkIh3-avxUM9RS1oP7oWdBcZiz7aMqjHr7mQs_S8_Fu77XdEdPBOcnK6s0kU-BNK0gsC9Y9BHvqGkEelt9RAK11jdIw
         */



        setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
