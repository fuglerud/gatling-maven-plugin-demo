package sfmgui

import io.gatling.core.Predef._
import io.gatling.core.structure.PopulationBuilder
import io.gatling.http.Predef.http

class SfmGuiSimulation extends Simulation {

  val sim: PopulationBuilder = sfmgui.SfmGuiScenario.SCN_SFMGUI.inject(constantUsersPerSec(1) during (1))

  val httpProtocol = http
    .baseUrl("https://session.qa.forskrivning.no")
    //.baseUrl("https://session.staging.sfm.cloud")
    //.baseUrl("https://session.test2.forskrivning.no")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("nb-NO,nb;q=0.9,no-NO;q=0.8,no;q=0.6,nn-NO;q=0.5,nn;q=0.4,en-US;q=0.3,en;q=0.1")
    .authorizationHeader("Bearer ${token}")
    //.authorizationHeader("Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkI0Q0FFNDUyQzhCNkE4OTNCNkE4NDBBQzhDODRGQjA3MEE0MjZFNDEiLCJ4NXQiOiJ0TXJrVXNpMnFKTzJxRUNzaklUN0J3cENia0UiLCJ0eXAiOiJKV1QifQ.eyJuYmYiOjE2MTk3NzAwOTQsImV4cCI6MTYxOTc3MzY5NCwiaXNzIjoiaHR0cHM6Ly9oZWxzZWlkLXN0cy50ZXN0Lm5obi5ubyIsImF1ZCI6ImUtaGVsc2U6c2ZtLmFwaSIsImNsaWVudF9pZCI6ImRlMjcxMjQzLTY5ZDQtNGEzOC1iMWIzLTM2ZTA2MTkyN2Q3YiIsImhlbHNlaWQ6Ly9jbGFpbXMvY2xpZW50L2tqL29yZ25yIjoiMTAwMTkzOTU3IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvb3JnbnJfcGFyZW50IjoiMTAwMTkzOTU3IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvZWMvZXhwIjoxOTI0OTkyMDAwLCJoZWxzZWlkOi8vY2xhaW1zL2NsaWVudC9lYy9jb21tb25fbmFtZSI6IkdhbGRow7hwaWdnZW4gdGVzdGxlZ2Vrb250b3IiLCJjbGllbnRfYW1yIjoicHJpdmF0ZV9rZXlfand0Iiwic3ViIjoiWVN5RUMrWG5LMy94YVJ2ZVNDOGtiNG0yOFhaZHc0SjRWd1pTVWNTR0ZZTT0iLCJhdXRoX3RpbWUiOjE2MTk3NzAwOTQsImlkcCI6InRlc3RpZHAtb2lkYyIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiOiI0IiwiaGVsc2VpZDovL2NsYWltcy9pZGVudGl0eS9waWQiOiIwODEyODMxNTk3OCIsImhlbHNlaWQ6Ly9jbGFpbXMvaWRlbnRpdHkvYXNzdXJhbmNlX2xldmVsIjoiaGlnaCIsImhlbHNlaWQ6Ly9jbGFpbXMvaHByL2hwcl9udW1iZXIiOiIxMDEwMDM4IiwiaGVsc2VpZDovL2NsYWltcy9jbGllbnQvYW1yIjoicnNhX3ByaXZhdGVfa2V5IiwiZS1oZWxzZTpzZm0uYXBpL2NsaWVudC9jbGFpbXMvc2ZtLWlkIjoiZGUyNzEyNDMtNjlkNC00YTM4LWIxYjMtMzZlMDYxOTI3ZDdiIiwic2lkIjoiMjEyREM3NDI5QzQxOTExNTYwNTdBNEEzNTdEQzgwNkIiLCJpYXQiOjE2MTk3NzAwOTQsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJoZWxzZWlkOi8vc2NvcGVzL2lkZW50aXR5L3BpZCIsImhlbHNlaWQ6Ly9zY29wZXMvaWRlbnRpdHkvc2VjdXJpdHlfbGV2ZWwiLCJoZWxzZWlkOi8vc2NvcGVzL2hwci9ocHJfbnVtYmVyIiwiZS1oZWxzZTpzZm0uYXBpL3NmbS5hcGkiXSwiYW1yIjpbImV4dGVybmFsIl19.C7jNFZdNWu3ndNEC9RRW8w924oWPpIPirOWMLUgwXVdb5QlWmJ-KZxk6qOlZ_UiSNXDk6CGW_bRJuUM3I5G-qYT8-G8icz4vPIEfVbD0jDeEP-DR4uvZmn2t3Vl4gcabyIfqjkIXHgYkSNP04_ixKSzMGIa1rRy9ijRj6wrAumO2gAQbM7NWxTdA0OpzKU88wjhCC-KEvSu-msfRCmb7G61ik8cDiLu77VvLzLvK6PJfVzraws3helL3pBvkDcwgveG9zWNcspqemMv9eKxw56DzGbqeRXMf7sZGwLV6jB_fs_Z3KKeW-41EupTx6A44BgufwuD0kz4QXGeM9LLvxw")
    .contentTypeHeader("application/json")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:85.0) Gecko/20100101 Firefox/85.0")


  setUp(sim).protocols(httpProtocol)

}
