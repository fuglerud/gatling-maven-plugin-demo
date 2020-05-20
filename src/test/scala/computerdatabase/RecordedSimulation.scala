package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://base-fhir.staging.sfm.cloud")
		.inferHtmlResources()
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.authorizationHeader("Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkIyMEFFMzZDMTQ5M0M5MEI0QkJDMEM5NkFENzRBQ0Y1QTZFODg1MTQiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJzZ3JqYkJTVHlRdEx2QXlXclhTczlhYm9oUlEifQ.eyJuYmYiOjE1ODc3MjE2MjYsImV4cCI6MTc0NTQwMTYyNiwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2UvU0ZNLmFwaSIsImNsaWVudF9pZCI6ImJmNzI0NjAzLTFhOTItNDA1Yi1iOWU2LTVlYmVkZTY5YmExMCIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiMzIzMjMyMzIzIiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvY29tbW9uX25hbWUiOiJIw7hncm9uZGVuIHRlc3RzeWtlaHVzIiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvZXhwIjoxNjExMDE0MzQwLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9vcmducl9wYXJlbnQiOiIzMjMyMzIzMjMiLCJjbGllbnRfYW1yIjoicHJpdmF0ZV9rZXlfand0Iiwic3ViIjoiV0w2RC9JWlVXOGp0SmxSR0ZkcUFWbU02THhrZ1N0VlRoWVVGRlNkK1BxTT0iLCJhdXRoX3RpbWUiOjE1ODc3MjA4MTksImlkcCI6InRlc3RpZHAtb2lkYyIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiOiI0IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9waWQiOiIwODEyODMxNTk3OCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvYXNzdXJhbmNlX2xldmVsIjoiaGlnaCIsImhlbHNlaWQ6Ly9jbGFpbXMvaHByL2hwcl9udW1iZXIiOiIxMDEwMDM4IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvYW1yIjoicnNhX3ByaXZhdGVfa2V5IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvY2xhaW1zL29yZ25yX3BhcmVudCI6IjMyMzIzMjMyMyIsImp0aSI6Ikg0Yk5qLVB0d2RlWElNQnNGTG9GV2ciLCJzY29wZSI6WyJwcm9maWxlIiwib3BlbmlkIiwiaGVsc2VpZDovL3Njb3Blcy9pZGVudGl0eS9waWQiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3NlY3VyaXR5X2xldmVsIiwiaGVsc2VpZDovL3Njb3Blcy9ocHIvaHByX251bWJlciIsImUtaGVsc2Uvc2ZtLmFwaS9zZm0uYXBpIl0sImFtciI6WyJwd2QiXX0.l6mhSI17gvvA0hsy-46ijyNRYgubWmp_zlKElF_jJbyrHJzIhZcXubdwJR7vq0gEnu2965IXfpcN0QavjopzxhrQryDdhRQUMGDre5RUJ55-nyGe8mJH1cTrKp97zSsO4PFV1b99Idf0OQa3Vs11TE5OD3SFkbxTm8-BtA2r-l_9PK-72SQ5tbQKsyTG8JbfT4UDbBh2zljjh2KfmbKdYIyXLndSFy_ybdP8krumh07qmxKZXOr4mDxdypz1JHfPEw7KIbkKo0ShrMnBil3L5M_eNQBqYX2-HON6Zxoc2r2qABGtbLsTRgfpKd04B9hnfIOaFa3Ync-X-ifRTaarsQ")
		.contentTypeHeader("application/json")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_0 = Map("Origin" -> "https://base-fhir.staging.sfm.cloud")



	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.post("/api/v1/Patient/$getMedication")
			.headers(headers_0)
			.body(RawFileBody("computerdatabase/recordedsimulation/0000_request.json")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}