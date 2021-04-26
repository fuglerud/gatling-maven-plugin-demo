package sfmgui

import io.gatling.core.Predef._
import io.gatling.core.structure.PopulationBuilder
import io.gatling.http.Predef.http

class SfmGuiSimulation extends Simulation {

  val sim: PopulationBuilder = sfmgui.SfmGuiScenario.SCN_SFMGUI.inject(constantUsersPerSec(1) during (1))

  val httpProtocol = http
    .baseUrl("https://session.qa.forskrivning.no")
    //.baseUrl("https://session.staging.sfm.cloud")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
    .authorizationHeader("Bearer ${token}")
    .contentTypeHeader("application/json")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:85.0) Gecko/20100101 Firefox/85.0")

  setUp(sim).protocols(httpProtocol)

}
