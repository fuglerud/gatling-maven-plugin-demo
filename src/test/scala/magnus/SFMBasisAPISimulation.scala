package magnus

import io.gatling.commons.stats.KO
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.io.{FileOutputStream, PrintWriter}

class SFMBasisAPISimulation extends Simulation {

  val httpProtocol = http
    //.baseUrl("https://base-fhir.staging.sfm.cloud")
    .baseUrl("https://base-fhir.qa.forskrivning.no")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .header("Content-Type", "application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .authorizationHeader("Bearer ${token}")
    //.authorizationHeader("Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDFSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6InRNcmtVc2kycUpPMnFFQ3NqSVQ3QndwQ2JrRSJ9.eyJuYmYiOjE2MTA5NjU4NjEsImV4cCI6MTc2ODY0NTg2MSwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2UvU0ZNLmFwaSIsImNsaWVudF9pZCI6ImEwOGI5ODNmLWQ1YmMtNGJiZS1hYzBlLTI1ZmExZThjNTNlNyIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiOTk5OTQ0NzA2IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvY29tbW9uX25hbWUiOiJVcmFub3N0aW5kZW4gdGVzdHN5a2VodXMiLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9leHAiOjE5MjQ5OTIwMDAsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2VjL29yZ25yX3BhcmVudCI6Ijk5OTk0NDcwNiIsImNsaWVudF9hbXIiOiJwcml2YXRlX2tleV9qd3QiLCJzdWIiOiJFYVlyRzRyWnkyc3UvcE5ZclhFNFNlbDdTYkhnenhhK3g5eG9scER4a1c0PSIsImF1dGhfdGltZSI6MTYxMDk2NTg2MCwiaWRwIjoidGVzdGlkcC1vaWRjIiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCI6IjQiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3BpZCI6IjA4MTI4MzE1OTc4IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9hc3N1cmFuY2VfbGV2ZWwiOiJoaWdoIiwiaGVsc2VpZDovL2NsYWltcy9ocHIvaHByX251bWJlciI6IjEwMTAwMzgiLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9hbXIiOiJyc2FfcHJpdmF0ZV9rZXkiLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9jbGFpbXMvb3JnbnJfcGFyZW50IjoiOTk5OTQ0NzA2IiwianRpIjoiNTQ3RDQ4M0U5MzZDNEMyOTVCOUMxOENCNDFGNEMxMzQiLCJzaWQiOiIxQzc1NjAzODVENjk2RDI3NDdGODhCNDJDRTM2QzBBNCIsImlhdCI6MTYxMDk2NTg2MSwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvcGlkIiwiaGVsc2VpZDovL3Njb3Blcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCIsImhlbHNlaWQ6Ly9zY29wZXMvaHByL2hwcl9udW1iZXIiLCJlLWhlbHNlL3NmbS5hcGkvc2ZtLmFwaSJdLCJhbXIiOlsiZXh0ZXJuYWwiXX0.CpK_Ew0-_4wJTXwX36pi_6ffcXgJrk4hpspClLuW8Pf5H93asiYfleuqhjjZMlvhT5pzS5zsAqCl9Ir7FMdiCCU8X89QXR1BSCk6d1YWFnslvizSgTmS-HmVpg0d6npvH8ZK8K-2QcLkGZQEAy0bEcABMiVx3zDa-UX-PMt6lglQeFEL6vIkQaWO9fD9aatvcOmUE9TaPYMexCvpa163SadJd60lZVtYjQQxC6Vgc5DiImo5QPz9IeS1J4EykY3xGQRtFSdTtOkBswBQ8B_kHX2go_jOKt_c6de_GGMXeyXzTMcI5ZoBo4IFDyAc9r6CfPsuw8yg8acLiCXn2s1-EA")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
    .disableCaching



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
    val fos = new java.io.FileOutputStream("feiledeLeger.txt")
    new java.io.PrintWriter(fos, true)}

  val writer4: PrintWriter = {
    val fos = new java.io.FileOutputStream("feiledePasienter.txt")
    new java.io.PrintWriter(fos, true)}

  val writer5: PrintWriter = {
    val fos = new java.io.FileOutputStream("feilkoder.txt")
    new java.io.PrintWriter(fos, true)}

  val scn = scenario("SFMBasisAPISimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/Tokens.csv").random)
    .feed(csv("magnus/SFM_BASIS_100.csv").random)




 /* .exec(http("practitioner")
     // .get("/Practitioner/${rekvirent}")
    .get("/Practitioner/018b0501-8815-4ff3-bdb6-6ec3d9cceacc")
      .check(status.is(200))
      //.check(jsonPath("$..id").is("${rekvirent}"))
  )*/

    /*.exec(http("organization")
      .get("/Organization?name=${organizationname}")
      .check(status.is(200))
      .check(jsonPath("$..resource.id").is("${organizationid}")))*/

    //http://base-fhir.qa.forskrivning.no/Person?identifier=04017870292&_format=json"


   /* .exec(http("organization")
      .get("/Organization/${org}")
      .check(status.is(200))
      .check(jsonPath("$..id").is("${org}"))
    )*/


   /* .exec(http("Person")
      .get("/Person/")
      .queryParam("active", "true")
      .check(status.is(200))
    )*/



  .exec(http("getMedication")
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

   /*.exec(session=>{
     println("KJFeilkode:")
     println(session("KJFeilkode").as[String])
     session})*/

/*
    .exec((session: io.gatling.core.session.Session) => {
      if (session.status == OK) {
        val newJson = json.JSONObject.equals("RESPONSE_DATA")
        newJson.equals("relatesTo", Seq(Map("code" -> "replaces")))
        println("Respect:")
        println(session("newJson").as[String])
      }
      session
    })
*/


    .exec((session: io.gatling.core.session.Session) => {
      if (session.status == KO) {
        writer3.println(session.attributes("legeFNR"))
      }
      session
    })



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



/*
    .exec(http("sendMedication")
      .post("/Patient/$sendMedication")
      .body(ElFileBody("magnus/SendMedication_request_2021.json"))
      //.body(ElFileBody("magnus/sendRequest.json")).asJson
      .check(status.is(200)))

*/






  //setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //setUp(scn.inject(rampUsersPerSec(1) to 3 during (30),constantUsersPerSec(3) during(240))).protocols(httpProtocol)
  setUp(scn.inject(constantUsersPerSec(1) during(100))).protocols(httpProtocol)
  //setUp(scn.inject(rampConcurrentUsers(1) to(5) during(240))).protocols(httpProtocol)
  //setUp(scn.inject(constantConcurrentUsers(1) during (60), rampConcurrentUsers(1) to (10) during (60))).protocols(httpProtocol)
  //setUp(scn.inject(incrementUsersPerSec(2).times(4).eachLevelLasting(30).separatedByRampsLasting(10).startingFrom(1))).protocols(httpProtocol)

}
