package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CICDKJ extends Simulation{

  val httpProtocol = http
    .baseUrl("https://helseid-sts.test.nhn.no")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")

  val headers_token = Map("Content-Type" -> "application/json")

  val headers_access_token = Map(
    "Content-Type"->"application/json;charset=UTF-8",
    "Authorization"->"Bearer ${access_token}")

  val scn = scenario("pung")

 /*   .exec(http("request_access_token")
      .post("/connect/token")
      .header("Authorization", "Basic Y2ZhOWM4ZmItOGRkNi00ZDdjLWIxNDItNzg2YzNkNzc0ZjY0OiUyQjRTbmdMcHV6Um5zbkk3SjVoQ0xHTVlwcUFTVUJRdjJjUGRrZU01JTJCYUIwZHhSSjlCU3diajJlaSUyRlNCdyUyRkF6VWZkSG81VENWVVpJSW1xcEtOY0RRSUElM0QlM0Q=")
      .formParam("grant_type", "client_credentials")
      .formParam("client_id", "cfa9c8fb-8dd6-4d7c-b142-786c3d774f64")
      .formParam("client_secret", "%2B4SngLpuzRnsnI7J5hCLGMYpqASUBQv2cPdkeM5%2BaB0dxRJ9BSwbj2ei%2FSBw%2FAzUfdHo5TCVUZIImqpKNcDQIA%3D%3D")
      .formParam("audience", "https://ehelse.no/kjernejournal/kj_api")
      .check(regex("access_token\":\"(.*?)\"").saveAs("access_token")))

    .exec {session =>
      println(session("access_token").as[String])
      session}

    .exec(http("request_ticket")
      .post("https://api.qa1.kjernejournal-test.no:8000/v1/helseindikator/")
      .header("Authorization", "Basic Y2ZhOWM4ZmItOGRkNi00ZDdjLWIxNDItNzg2YzNkNzc0ZjY0OiUyQjRTbmdMcHV6Um5zbkk3SjVoQ0xHTVlwcUFTVUJRdjJjUGRrZU01JTJCYUIwZHhSSjlCU3diajJlaSUyRlNCdyUyRkF6VWZkSG81VENWVVpJSW1xcEtOY0RRSUElM0QlM0Q=")
      .headers(headers_access_token)
      .body(ElFileBody("magnus/epj_ticket_request.json"))
      .check(regex("ticket\" : \"(.*?)\"").transform(rawTicketValue => java.net.URLEncoder.encode(rawTicketValue, "UTF-8")).saveAs("ticket")))*/

    /*.exec(http("request_ticket")
      .post("https://api.qa1.kjernejournal-test.no:8000/v1/helseindikator/")
      .header("Authorization", "Basic Y2ZhOWM4ZmItOGRkNi00ZDdjLWIxNDItNzg2YzNkNzc0ZjY0OiUyQjRTbmdMcHV6Um5zbkk3SjVoQ0xHTVlwcUFTVUJRdjJjUGRrZU01JTJCYUIwZHhSSjlCU3diajJlaSUyRlNCdyUyRkF6VWZkSG81VENWVVpJSW1xcEtOY0RRSUElM0QlM0Q=")
      .headers(headers_access_token)
      .body(ElFileBody("magnus/epj_ticket_request.json"))
      .check(regex("ticket\" : \"(.*?)\"").transform(rawTicketValue => java.net.URLEncoder.encode(rawTicketValue, "UTF-8")).saveAs("ticket")))*/


  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}