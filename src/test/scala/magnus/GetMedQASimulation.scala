package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetMedQASimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://base-fhir.qa.sfm.cloud")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .header("Content-Type", "application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .authorizationHeader("Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkIyMEFFMzZDMTQ5M0M5MEI0QkJDMEM5NkFENzRBQ0Y1QTZFODg1MTQiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJzZ3JqYkJTVHlRdEx2QXlXclhTczlhYm9oUlEifQ.eyJuYmYiOjE1OTgwMjk2OTUsImV4cCI6MTc1NTcwOTY5NSwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2UvU0ZNLmFwaSIsImNsaWVudF9pZCI6IjYzMThlNzBkLWU3NDAtNDhmNC1hYzBmLWY1ODJhNTc1MWViMSIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiOTk5OTQ0NzA3IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvY29tbW9uX25hbWUiOiJWaW5qZXJvbmRlbiB0ZXN0c3lrZWh1cyIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2VjL2V4cCI6MTYxMTAxNDM0MCwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvb3JnbnJfcGFyZW50IjoiOTk5OTQ0NzA3IiwiY2xpZW50X2FtciI6InByaXZhdGVfa2V5X2p3dCIsInN1YiI6IldMNkQvSVpVVzhqdEpsUkdGZHFBVm1NNkx4a2dTdFZUaFlVRkZTZCtQcU09IiwiYXV0aF90aW1lIjoxNTk3OTk4NjA0LCJpZHAiOiJ0ZXN0aWRwLW9pZGMiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIjoiNCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvcGlkIjoiMDgxMjgzMTU5NzgiLCJoZWxzZWlkOi8vY2xhaW1zL2lkZW50aXR5L2Fzc3VyYW5jZV9sZXZlbCI6ImhpZ2giLCJoZWxzZWlkOi8vY2xhaW1zL2hwci9ocHJfbnVtYmVyIjoiMTAxMDAzOCIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2FtciI6InJzYV9wcml2YXRlX2tleSIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2NsYWltcy9vcmducl9wYXJlbnQiOiI5OTk5NDQ3MDciLCJqdGkiOiJiMFVQQkZtNmdhN1A5M0FZZU5vV1FnIiwic2NvcGUiOlsicHJvZmlsZSIsIm9wZW5pZCIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvcGlkIiwiaGVsc2VpZDovL3Njb3Blcy9pZGVudGl0eS9zZWN1cml0eV9sZXZlbCIsImhlbHNlaWQ6Ly9zY29wZXMvaHByL2hwcl9udW1iZXIiLCJlLWhlbHNlL3NmbS5hcGkvc2ZtLmFwaSJdLCJhbXIiOlsicHdkIl19.FWGqh1As5iC9iE_x0FE9GRlzZdASeHu9EG9HdaY_utOhulIKWPzR6haV59GY52dnf7BC7i6knUH3KVA_vp3OXNtFTsYS2CAa6_6HB-9Tgr_CTmss45cKBN-rUsOXOsHzO_ycFsMUY7bV3NY1B6IIHXDnc1kKOGOero6PqrE-fEw1eNGvMcHFBByapWAKTfxsBGvlm11olJ6TEN_gxm380SqdXOGVVH55cVCnJOBLMNzUUuElU5-vE_PDsHPGUwsApeNm0WWuSlTHf1f8RBUjXJKPrIvN-PcvetUwPvBfO8U6RRVF9d92eKDKi77tS-aimkVFioXMjDt2LufjmACrEA")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
    .disableCaching

  val scn = scenario("GetMedQASimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .exec(http("getMedication")
      .post("/Patient/$getMedication")
      .body(ElFileBody("magnus/xxx_request_body.json"))
      .check(status.is(200))
      .check(jsonPath("$..name").is("medication"))
      .check(jsonPath("$..[?(@.name==\"RFM912Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"RFM96Feilkode\")].valueCodeableConcept.text").is("OK"))
      .check(jsonPath("$..[?(@.name==\"KJFeilkode\")].valueCodeableConcept.text").is("OK")))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}