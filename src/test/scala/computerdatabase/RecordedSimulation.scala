package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://tjenester.hn.test.nhn.no")
		.inferHtmlResources()
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:77.0) Gecko/20100101 Firefox/77.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"content-type" -> "application/json",
		"hnanonymoushash" -> "JFfQPvKtT-TxcSXai6x79_SbF1jtSwty-CiNxTIOlfk1",
		"hnauthenticatedhash" -> "K0wmFHqJa8tsgfzPigthObHO9W1XiIDsCl93kRq51_81",
		"hntimestamp" -> "2020-06-11T07:28:58.1003432Z",
		"hntjeneste" -> "78",
		"x-hn-hendelselogg" -> "InnsynSmittestopp")

	val headers_2 = Map("Accept" -> "*/*")

	val headers_5 = Map(
		"Accept" -> "*/*",
		"Access-Control-Request-Headers" -> "content-type",
		"Access-Control-Request-Method" -> "GET",
		"Origin" -> "https://tjenester.hn.test.nhn.no")

	val headers_11 = Map("Accept" -> "image/webp,*/*")

	val headers_12 = Map(
		"Accept" -> "*/*",
		"Origin" -> "https://tjenester.hn.test.nhn.no")

	val headers_13 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity",
		"Origin" -> "https://tjenester.hn.test.nhn.no")

    val uri2 = "https://ehelse.d3.sc.omtrdc.net/b/ss/ehelsehelsenorge-dev/1/JS-2.20.0-LATI/s48610086278150"
    val uri3 = "https://epi-helsenorge.hn.test.nhn.no"
    val uri4 = "https://static.hn.test.nhn.no"
    val uri5 = "https://minhelse.hn.test.nhn.no/innsynsmittestopp"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get(uri5 + "/?pnr=25059421177&tlfnr=90618517")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.CoreFrontend.Microweb.HeaderFooter&Rev=2014.5.1.1")
			.headers(headers_1),
            http("request_2")
			.get(uri3 + "/contentapi/internal/v1/footer")
			.headers(headers_2),
            http("request_3")
			.get("/api/v1/MinHelse/GetRepresentasjonsforhold?FetchUnreadMessages=true")
			.headers(headers_1),
            http("request_4")
			.get("/api/v1/MinHelse/GetUnreadMessagesCount")
			.headers(headers_1),
            http("request_5")
			.options(uri3 + "/contentapi/internal/v1/footer")
			.headers(headers_5),
            http("request_6")
			.get("/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.InnsynSmittestopp&Rev=2014.5.1.1")
			.headers(headers_1),
            http("request_7")
			.get("/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.MinHelse.Common.JS&Rev=2014.5.1.1")
			.headers(headers_1),
            http("request_8")
			.get("/proxy/InnsynSmittestopp/api/v1/PhoneNumber/Verify")
			.headers(headers_1),
            http("request_9")
			.get("/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.MinHelse.SecurityFramework&Rev=2014.5.1.1")
			.headers(headers_1),
            http("request_10")
			.get("/proxy/sot/api/v1/UIResource?Culture=nb-no&Filename=HN.MinHelse.Content&Rev=2014.5.1.1")
			.headers(headers_1),
            http("request_11")
			.get(uri2 + "?AQB=1&ndh=1&pf=1&t=11%2F5%2F2020%209%3A28%3A59%204%20-120&mid=72923423834279259650105238635521349713&aamlh=6&ce=UTF-8&pageName=tjenester%3Ainnsynsmittestopp&g=tjenester.hn.test.nhn.no%2Finnsynsmittestopp%2F&cc=NOK&ch=innsynsmittestopp&server=tjenester.hn.test.nhn.no&events=event19&aamb=6G1ynYcLPuiQxYZrsz_pkqfLG9yMXBpb2zX5dvJdYQJzPXImdj0y&c1=D%3Dv3&v1=tjenester%3Ainnsynsmittestopp&c2=D%3Dv4&v2=innsynsmittestopp&c3=D%3Dv5&c4=D%3Dv6&v4=tjenester.hn.test.nhn.no&c5=D%3Dv7&v5=tjenester.hn.test.nhn.no%2Finnsynsmittestopp%2F&v6=tjenester%3Ainnsynsmittestopp&c14=mozilla%2F5.0%20%28windows%20nt%2010.0%3B%20win64%3B%20x64%3B%20rv%3A77.0%29%20gecko%2F20100101%20firefox%2F77.0&v15=innsynsmittestopp&c16=Repeat&v16=Repeat&c17=8%3A28%20AM%7CThursday&c19=15&c20=72923423834279259650105238635521349713&v22=innlogget&c24=D%3Dv22&s=1792x1120&c=24&j=1.6&v=N&k=Y&bw=1792&bh=1006&mcorgid=CB983DEB5A4CBDFE0A495EF7%40AdobeOrg&AQE=1")
			.headers(headers_11),
            http("request_12")
			.get(uri3 + "/_vti_bin/portal/status.svc/minhelse")
			.headers(headers_12)
			.check(status.is(404))))
		.pause(1)
		.exec(http("request_13")
			.get(uri4 + "/forside/static/media/source-sans-pro-700-bold.e07bc930.woff")
			.headers(headers_13)
			.resources(http("request_14")
			.get("/proxy/InnsynSmittestopp/api/v1/Lokasjonslogg?date=2020-06-11T07:29:01.963Z&timeSlot=10")
			.headers(headers_1),
            http("request_15")
			.get(uri4 + "/core/fonts/vPcynSL0qHq_6dX7lKVByfesZW2xOQ-xsNqO47m55DA.woff")
			.headers(headers_13)))
		.pause(11)
		.exec(http("request_16")
			.get("/proxy/InnsynSmittestopp/api/v1/Lokasjonslogg?date=2020-05-19T22:00:00.000Z&timeSlot=10")
			.headers(headers_1))
		.pause(2)
		.exec(http("request_17")
			.get("/proxy/InnsynSmittestopp/api/v1/Lokasjonslogg?date=2020-05-19T22:00:00.000Z&timeSlot=12")
			.headers(headers_1))
		.pause(12)
		// varslinger
		.exec(http("request_18")
			.get("/proxy/InnsynSmittestopp/api/v1/varsling")
			.headers(headers_1))
		.pause(13)
		// logg over bruk
		.exec(http("request_19")
			.get("/proxy/InnsynSmittestopp/api/v1/LoggOverBruk")
			.headers(headers_1))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}