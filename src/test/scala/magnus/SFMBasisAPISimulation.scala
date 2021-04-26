package magnus

import io.gatling.commons.stats.KO
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.io.PrintWriter

class SFMBasisAPISimulation extends Simulation {

  val httpProtocol = http
    //.baseUrl("https://base-fhir.test.sfm.cloud")
    .baseUrl("https://base-fhir.qa.forskrivning.no")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .header("Content-Type", "application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .authorizationHeader("Bearer ${token}")
    //.authorizationHeader("Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UiLCJ0eXAiOiJKV1QifQ.eyJuYmYiOjE2MTg0OTE0NDYsImV4cCI6MTc3NjE3MTQ0NiwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2U6c2ZtLmFwaSIsImNsaWVudF9pZCI6ImEwOGI5ODNmLWQ1YmMtNGJiZS1hYzBlLTI1ZmExZThjNTNlNyIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiOTk5OTQ0NzA2IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvY29tbW9uX25hbWUiOiJVcmFub3N0aW5kZW4gdGVzdHN5a2VodXMiLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9leHAiOjE5MjQ5OTIwMDAsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2VjL29yZ25yX3BhcmVudCI6Ijk5OTk0NDcwNiIsImNsaWVudF9hbXIiOiJwcml2YXRlX2tleV9qd3QiLCJzdWIiOiJFYVlyRzRyWnkyc3UvcE5ZclhFNFNlbDdTYkhnenhhK3g5eG9scER4a1c0PSIsImF1dGhfdGltZSI6MTYxODQ5MTQ0NCwiaWRwIjoidGVzdGlkcC1vaWRjIiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCI6IjQiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3BpZCI6IjA4MTI4MzE1OTc4IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9hc3N1cmFuY2VfbGV2ZWwiOiJoaWdoIiwiaGVsc2VpZDovL2NsYWltcy9ocHIvaHByX251bWJlciI6IjEwMTAwMzgiLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9hbXIiOiJyc2FfcHJpdmF0ZV9rZXkiLCJlLWhlbHNlOnNmbS5hcGkvY2xpZW50L2NsYWltcy9zZm0taWQiOiJhMDhiOTgzZi1kNWJjLTRiYmUtYWMwZS0yNWZhMWU4YzUzZTciLCJqdGkiOiIxODkzNzI1QkVFNDdDOTQ0NTE5MjE2RkRFQTRBNTMxNSIsInNpZCI6IkEyRDA2NEIyN0Y4NzA3QTg5MUMxMzM0RjNGRTAxQTEwIiwiaWF0IjoxNjE4NDkxNDQ2LCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIiwiZS1oZWxzZTpzZm0uYXBpL3NmbS5hcGkiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3BpZCIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiLCJoZWxzZWlkOi8vc2NvcGVzL2hwci9ocHJfbnVtYmVyIl0sImFtciI6WyJleHRlcm5hbCJdfQ.goZE5t-YO3zqlGXrpVITXob76Pf76X_Qr55pAdHDeRiTQIzYDM8JCGE41snQ06-tF7Pan4f4uLvtiYxzynbnP7UHsV181vQkeKvU1Qy_ccz5TjP8eNoP93jCjR9Bh0d1r9TEGjIjDfxgOXF69jjddB4f7idHKOIh7ChPxY97Lh38tBJdVs36I2Vk1OLCt6Zd6e42iXSqIHmJtMfx9sHAziGUN-3cbaGDNDg2w-tqGYqH2l8Jza_9vSk5P-ywau-hrpiCmFVvf10d2fIRBUQXWJxPH-sMCzBWB6mhoXPTV6LI3DQR2_wshp7eeL_9oD-2yiPEkWL3lj6Tw4BxsZA1yg")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
    .disableCaching


  val writer1: PrintWriter = {
    val fos = new java.io.FileOutputStream("sfm_500_feil.txt")
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

  val scn = scenario("SFMBasisAPISimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    //.feed(csv("magnus/TokensLeger.csv").random)
    .feed(csv("magnus/SFM_BASIS_100.csv").circular)


 /* .exec(http("practitioner")
     // .get("/Practitioner/${rekvirent}")
    .get("/Practitioner/018b0501-8815-4ff3-bdb6-6ec3d9cceacc")
      .check(status.is(200))
      //.check(jsonPath("$..id").is("${rekvirent}")))*/

    /*.exec(http("organization")
      .get("/Organization?name=${organizationname}")
      .check(status.is(200))
      .check(jsonPath("$..resource.id").is("${organizationid}")))*/



  .exec(http("getMedication")
    .post("/Patient/$getMedication")
    .body(ElFileBody("magnus/0000_request.json"))
    .check(status.is(200))
    .check(jsonPath("$..name").is("medication"))
    //.check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").is("OK"))
    //.check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").is("OK"))
      //.check(jsonPath("$..[?(@.use==\"official\")].value").saveAs("official"))
      //.check(jsonPath("$..*").saveAs( "RESPONSE_DATA")).asJson
      //.check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").saveAs("KJFeilkode"))
      //.check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").saveAs("RFM912Feilkode"))
      //.check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").saveAs("RFM96Feilkode"))
      //.check(jsonPath("$..*").ofType[Map[String, Any]].saveAs("myJson"))
  )

    .exec((session: io.gatling.core.session.Session) => {
      if (session.status == KO) {
        writer1.println(session.attributes("identifier"))
      }
      session
    })

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



  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(rampUsersPerSec(1) to 3 during (30),constantUsersPerSec(3) during(120))).protocols(httpProtocol)
  //setUp(scn.inject(constantUsersPerSec(5) during(120))).protocols(httpProtocol)
  //setUp(scn.inject(rampConcurrentUsers(1) to(5) during(240))).protocols(httpProtocol)
  //setUp(scn.inject(constantConcurrentUsers(1) during (60), rampConcurrentUsers(1) to (10) during (60))).protocols(httpProtocol)
  //setUp(scn.inject(incrementUsersPerSec(2).times(4).eachLevelLasting(30).separatedByRampsLasting(10).startingFrom(1))).protocols(httpProtocol)

}
