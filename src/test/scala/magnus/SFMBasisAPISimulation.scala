package magnus

import io.gatling.commons.stats._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.io.PrintWriter

class SFMBasisAPISimulation extends Simulation {

  val httpProtocol = http
    //.baseUrl("https://base-fhir.test.sfm.cloud")//Thula
    .baseUrl("https://base-fhir.qa.forskrivning.no")
    //.baseUrl("https://base-fhir.test2.forskrivning.no")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .header("Content-Type", "application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    //.authorizationHeader("Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UiLCJ0eXAiOiJKV1QifQ.eyJuYmYiOjE2MjAwMzY1NTEsImV4cCI6MTc3NzcxNjU1MSwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2U6c2ZtLmFwaSIsImNsaWVudF9pZCI6ImEwOGI5ODNmLWQ1YmMtNGJiZS1hYzBlLTI1ZmExZThjNTNlNyIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiOTk5OTQ0NzA2IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvY29tbW9uX25hbWUiOiJVcmFub3N0aW5kZW4gdGVzdHN5a2VodXMiLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9leHAiOjE5MjQ5OTIwMDAsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2VjL29yZ25yX3BhcmVudCI6Ijk5OTk0NDcwNiIsImNsaWVudF9hbXIiOiJwcml2YXRlX2tleV9qd3QiLCJzdWIiOiJFYVlyRzRyWnkyc3UvcE5ZclhFNFNlbDdTYkhnenhhK3g5eG9scER4a1c0PSIsImF1dGhfdGltZSI6MTYyMDAzNjU1MSwiaWRwIjoidGVzdGlkcC1vaWRjIiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCI6IjQiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3BpZCI6IjA4MTI4MzE1OTc4IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9hc3N1cmFuY2VfbGV2ZWwiOiJoaWdoIiwiaGVsc2VpZDovL2NsYWltcy9ocHIvaHByX251bWJlciI6IjEwMTAwMzgiLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9hbXIiOiJyc2FfcHJpdmF0ZV9rZXkiLCJlLWhlbHNlOnNmbS5hcGkvY2xpZW50L2NsYWltcy9zZm0taWQiOiJhMDhiOTgzZi1kNWJjLTRiYmUtYWMwZS0yNWZhMWU4YzUzZTciLCJqdGkiOiJCRjgxNzczRThERjVCMjgwNDc5RDdFNENCQkJFMTI2QiIsInNpZCI6IjBCMjlGNTY0QjkzRjY3MUMxMDk4OTk5NERBQ0YyMjE0IiwiaWF0IjoxNjIwMDM2NTUxLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIiwiaGVsc2VpZDovL3Njb3Blcy9pZGVudGl0eS9waWQiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIiwiaGVsc2VpZDovL3Njb3Blcy9ocHIvaHByX251bWJlciIsImUtaGVsc2U6c2ZtLmFwaS9zZm0uYXBpIl0sImFtciI6WyJleHRlcm5hbCJdfQ.dDiiKcjE08SMGt15ohnUnFVeVrTmII0z9idNu6hEybkhYKUQ-FGce1SLqJWI4zpWF_eLMlOBjQZb7DsI7bFjHb1ZNnUF9JjAKHy_I_d7yOeprGBz0kyaZuygrd1gE77E5n0KrEcU6v7BcUSWkh3E38l3NbbTd7z7D8nM37mf-_WD51g-IqspNZkQueqk5xyez-oMt-fi_kiKGwJGmDNb2_tPRVvmhjgCzXfMDw4OTnHsexIxZ4VFhI64yvxX1mkP4pLdvOpzQeFnG02PvWPebsyNYFQ1p5s3cWLe-o6cp0Ta3TXWUEJFJj97EPhwUPY6dtum1uZllT9p1av6cII0tg")
    .authorizationHeader("Bearer ${token}")
    //.authorizationHeader("Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UiLCJ0eXAiOiJKV1QifQ.eyJuYmYiOjE2MTg0OTE4MjIsImV4cCI6MTc3NjE3MTgyMiwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2U6c2ZtLmFwaSIsImNsaWVudF9pZCI6ImEwOGI5ODNmLWQ1YmMtNGJiZS1hYzBlLTI1ZmExZThjNTNlNyIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiOTk5OTQ0NzA2IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvY29tbW9uX25hbWUiOiJVcmFub3N0aW5kZW4gdGVzdHN5a2VodXMiLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9leHAiOjE5MjQ5OTIwMDAsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2VjL29yZ25yX3BhcmVudCI6Ijk5OTk0NDcwNiIsImNsaWVudF9hbXIiOiJwcml2YXRlX2tleV9qd3QiLCJzdWIiOiIvemlReHAxUm5ROW5oc3h5TGpSMSt0cExDNlVBRjlWSjQzTWxDTzBCcTdRPSIsImF1dGhfdGltZSI6MTYxODQ5MTgyMCwiaWRwIjoidGVzdGlkcC1vaWRjIiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCI6IjQiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3BpZCI6IjI0MDY5MTAwMTk0IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9hc3N1cmFuY2VfbGV2ZWwiOiJoaWdoIiwiaGVsc2VpZDovL2NsYWltcy9ocHIvaHByX251bWJlciI6IjQzMTAwMjc5MCIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2FtciI6InJzYV9wcml2YXRlX2tleSIsImUtaGVsc2U6c2ZtLmFwaS9jbGllbnQvY2xhaW1zL3NmbS1pZCI6ImEwOGI5ODNmLWQ1YmMtNGJiZS1hYzBlLTI1ZmExZThjNTNlNyIsImp0aSI6IkY2OThGODBGQ0MyQzMwRkY5MjkyQjJDMzJDQzc2MEE0Iiwic2lkIjoiRkVCREZFNDdGNkRCMUZCMzY0NDE2MTRBRDhCQkRBMUEiLCJpYXQiOjE2MTg0OTE4MjIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJlLWhlbHNlOnNmbS5hcGkvc2ZtLmFwaSIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvcGlkIiwiaGVsc2VpZDovL3Njb3Blcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCIsImhlbHNlaWQ6Ly9zY29wZXMvaHByL2hwcl9udW1iZXIiXSwiYW1yIjpbImV4dGVybmFsIl19.Zha1kNd6AvuXVQD0UXnTiQ1x4sCa50QUALxe8iumKezFHmz5fUcy9e36aHgC7qdsXmiRssDPDyiMz_HAQJ7VxJPPdZePodK2fxqTEeTkOTL5JlN-WVnn10Z7VT1suMtXpSPv0e5PwNeuE0lEFp4JpbpW4y8ccqojsegcvx4mFMhMO_bxYy3qTKUXwmKRDvMYcwEYQfXpM78AoCUoH4YzT_IojKItDsIJ_SCodxpH7O4eLpARSppPSL4OZx1zr0W5wp4Wk2clWlteIcHq9PM2ykumvMUVX7D5jpwtBS2Sb8brPR61nPQBOq2FkIs1mdmuuDarc5StaT5pgsMl-VYKoA")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
    .disableCaching


  val writer1: PrintWriter = {
    val fos = new java.io.FileOutputStream("sfm_vasking.csv")
    new java.io.PrintWriter(fos, true)}


/*
  val nameOfMyFile = "sendRequest.json"
  val filePath = "/Users/magnusjfuglerud/IdeaProjects/gatling-maven-plugin-demo/src/test/resources/magnus/sendRequest.json"

  val output = new FileOutputStream( filePath)

  /* file writer object */
  val writer0 = {
    val output = new FileOutputStream( filePath)
    val pw = new java.io.PrintWriter( output, true)
    pw
  }

  val writer1: PrintWriter = {
    val fos = new java.io.FileOutputStream("official.txt")
    new java.io.PrintWriter(fos, true)}

  val writer2: PrintWriter = {
    val fos = new java.io.FileOutputStream("responsebodies.json")
    new java.io.PrintWriter(fos, true)}

  val writer3: PrintWriter = {
    val fos = new java.io.FileOutputStream("OKLeger.txt")
    new java.io.PrintWriter(fos, true)}
*/

  /*val writer4: PrintWriter = {
    val fos = new java.io.FileOutputStream("timestamp.txt")
    new java.io.PrintWriter(fos, true)}*/

/*

  val writer5: PrintWriter = {
    val fos = new java.io.FileOutputStream("feilkoder.txt")
    new java.io.PrintWriter(fos, true)}
*/

  //private val failureStatus: Int = 500

  val scn = scenario("SFMBasisAPISimulationSTEADY")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/TokensLeger.csv").circular)
    //.feed(csv("magnus/SFM_BASIS_40000.csv").circular)
    //.feed(csv("magnus/PasienterBasisAPITest2.csv").random)
    //.feed(csv("magnus/thulaPasienter.csv").circular)


 /* .exec(http("practitioner")
     // .get("/Practitioner/${rekvirent}")
    .get("/Practitioner/018b0501-8815-4ff3-bdb6-6ec3d9cceacc")
      .check(status.is(200))
      //.check(jsonPath("$..id").is("${rekvirent}")))*/

    /*.exec(http("organization")
      .get("/Organization?name=${organizationname}")
      .check(status.is(200))
      .check(jsonPath("$..resource.id").is("${organizationid}")))*/

  .repeat(720)
  {

    feed(csv("magnus/SFM_BASIS_40000.csv").circular)
    //.feed(csv("magnus/1001-5000.csv").circular)
    //.feed(csv("magnus/SFM_BASIS_3000.csv").circular)
    //.feed(csv("magnus/sfm_vasking.csv").circular)

    .exec(http("getMedicationSTEADY")
      .post("/Patient/$getMedication")
      .body(ElFileBody("magnus/0000_request.json"))
      .check(status.is(200))
      .check(jsonPath("$..name").is("medication"))
      .check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").is("OK"))
      //.check(jsonPath("$..[?(@.use==\"official\")].value").saveAs("official"))
      //.check(jsonPath("$..*").saveAs( "RESPONSE_DATA")).asJson
      //.check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").saveAs("KJFeilkode"))
      //.check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").saveAs("RFM912Feilkode"))
      //.check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").saveAs("RFM96Feilkode"))
      //.check(jsonPath("$..*").ofType[Map[String, Any]].saveAs("myJson"))
  )

     /* .exec(session=>{
      println("Pasient FNR:")
      println(session("identifier").as[String])
      session})*/

   .pace(10)

      .exec((session: io.gatling.core.session.Session) => {
        if (session.status == KO) {
          writer1.println(session.attributes("identifier"))
        }
        session
      })
  }

   /*.exec(session=>{
     println("KJFeilkode:")
     println(session("KJFeilkode").as[String])
     session})*/

     /*.exec((session: io.gatling.core.session.Session) => {
      if (session.status == OK) {
        val newJson = json.JSONObject.equals("RESPONSE_DATA")
        newJson.equals("relatesTo", Seq(Map("code" -> "replaces")))
        println("Respect:")
        println(session("newJson").as[String])
      }
      session
    })*/

    /*.exec((session: io.gatling.core.session.Session) => {
      if (session.status == OK) {
        writer3.println(session.attributes("legeFNR"))
      }
      session
    })*/

   /* .exec((session: io.gatling.core.session.Session) => {
      if (session.status == KO) {
        writer4.println(System.currentTimeMillis())
      }
      session
    })*/

    /*.exec(session=>{
      println("official:")
      println(session("official").as[String])
      session})*/

   /* .exec(session=>{
      println("RESPONSE_DATA:")
      println(session("RESPONSE_DATA").as[String])
      session})*/

   /* .exec(session=>{
      println("myJson:")
      println(session("myJson").as[String])
      session})

    .exec((session: io.gatling.core.session.Session) => {
      if (session.status == OK) {
        writer1.println(session.attributes("official"))
        writer1.println(session.attributes("identifier"))
      }
      session
    })

    .exec((session: io.gatling.core.session.Session) => {
      if (session.status == OK) {
        writer0.println(session.attributes("RESPONSE_DATA"))
      }
      session
    })*/

       //.pause(3)

         //""""relatesTo":[{"code": "replaces","targetIdentifier":{"use": "official","value": "3720076a-b74c-4781-af4b-9e3f567f8457"}}]"""

      /*.exec(http("sendMedication")
      .post("/Patient/$sendMedication")
      .body(ElFileBody("magnus/SendMedication_request_2021.json"))
      //.body(ElFileBody("magnus/sendRequest.json")).asJson
      .check(status.is(200)))*/

  //7200
  //14400

  //setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(rampUsersPerSec(1) to 30 during (30),constantUsersPerSec(30) during(120))).protocols(httpProtocol)
  //setUp(scn.inject(constantUsersPerSec(1) during(1))).protocols(httpProtocol)
  //setUp(scn.inject(rampConcurrentUsers(1) to(5) during(240))).protocols(httpProtocol)
  //setUp(scn.inject(constantConcurrentUsers(1) during (60), rampConcurrentUsers(1) to (10) during (60))).protocols(httpProtocol)
  //setUp(scn.inject(incrementUsersPerSec(2).times(4).eachLevelLasting(30).separatedByRampsLasting(10).startingFrom(1))).protocols(httpProtocol)
  //setUp(scn.inject(rampConcurrentUsers(1) to(1) during(3 minutes), constantConcurrentUsers(3) during (3 seconds), rampConcurrentUsers(3) to(1) during(3 minutes))).protocols(httpProtocol)
  //setUp(scn.inject(constantUsersPerSec(1) during(1), heavisideUsers(3).during(1)).protocols(httpProtocol))
  //setUp(scn.inject(constantUsersPerSec(1).during(1.minutes)).throttle(reachRps(3).in(1.seconds), holdFor(3.seconds)).protocols(httpProtocol))
  //setUp(scn.inject(constantUsersPerSec(1) during(10)).pauses(customPauses(60).protocols(httpProtocol))


  val scn2 = scenario("SFMBasisAPISimulationPEAK")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/TokensLeger.csv").circular)

    .repeat(120)
    {

    feed(csv("magnus/SFM_BASIS_40000.csv").circular)

    .exec(http("getMedicationPEAK")
      .post("/Patient/$getMedication")
      .body(ElFileBody("magnus/0000_request.json"))
      .check(status.is(200))
      .check(jsonPath("$..name").is("medication"))
      .check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").is("OK")))

    .pause(60)
    }

  setUp(scn.inject(constantUsersPerSec(1) during(1)).protocols(httpProtocol), scn2.inject(constantUsersPerSec(4) during(1))).protocols(httpProtocol)

  //setUp(scn2.inject(constantUsersPerSec(2) during(1))).protocols(httpProtocol)

}
