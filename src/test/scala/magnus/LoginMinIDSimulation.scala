package magnus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class LoginMinIDSimulation extends Simulation{

  val httpProtocol = http
    .baseUrl("https://minhelse.hn.test.nhn.no")
    .disableCaching

  val headers_0 = Map(
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Language" -> "en-US,en;q=0.5",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Connection" -> "keep-alive",
    "Referer" -> "https://minhelse.hn.test.nhn.no/")

  val headers_1 = Map(
    "Host" -> "idporten-yt2.difi.eon.no",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Language" -> "en-US,en;q=0.5",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Referer" -> "https://idporten-yt2.difi.eon.no/opensso/UI/Login?realm=/norge.no&spEntityID=oidc-yt2.difi.eon.no&service=IDPortenLevel3List&goto=http://idporten-yt2.difi.eon.no/opensso/SSORedirect/metaAlias/norge.no/idp4?ReqID%3D${ReqID}%26index%3Dnull%26acsURL%3Dhttps://oidc-yt2.difi.eon.no:443/idporten-oidc-provider/assertionconsumer%26spEntityID%3Doidc-yt2.difi.eon.no%26binding%3Durn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST",
    "Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_2 = Map(
    "Host" -> "idporten-yt2.difi.eon.no",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Language" -> "en-US,en;q=0.5",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Referer" -> "https://idporten-yt2.difi.eon.no/opensso/UI/Login?realm=norge.no&goto=http://idporten-yt2.difi.eon.no/opensso/SSORedirect/metaAlias/norge.no/idp4?ReqID=${ReqID}&index=null&acsURL=https://oidc-yt2.difi.eon.no:443/idporten-oidc-provider/assertionconsumer&spEntityID=oidc-yt2.difi.eon.no&binding=urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST&service=MinIDChain&ForceAuth=&locale=nb&gx_charset=UTF-8",
    "Content-Length" -> "124",
    "Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_3 = Map(
    "Host" -> "idporten-yt2.difi.eon.no",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Language" -> "en-US,en;q=0.5",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Referer" -> "https://idporten-yt2.difi.eon.no/opensso/UI/Login?realm=norge.no&goto=http://idporten-yt2.difi.eon.no/opensso/SSORedirect/metaAlias/norge.no/idp4?ReqID=${ReqID}&index=null&acsURL=https://oidc-yt2.difi.eon.no:443/idporten-oidc-provider/assertionconsumer&spEntityID=oidc-yt2.difi.eon.no&binding=urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST&service=MinIDChain&ForceAuth=&locale=nb&gx_charset=UTF-8",
    "Content-Length" -> "84",
    "Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_4 = Map(
    "User-Agent" -> "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Language" -> "en-US,en;q=0.5",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Referer" -> "https://idporten-yt2.difi.eon.no/opensso/SSORedirect/metaAlias/norge.no/idp4?ReqID=${ReqID}",
    "Content-Type" -> "application/x-www-form-urlencoded",
    "Content-Length" -> "16969",
    "Cookie" -> "JSESSIONID=98F7F3A362611E28BF8C8784BF386762; APLBCOOKIE=APACHE.app01",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_5 = Map(
    "accept" -> "application/json",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Connection" -> "keep-alive",
    "content-type" -> "application/json",
    "hnanonymoushash" -> "${AnonymousHash}",
    "hnauthenticatedhash" -> "${AuthenticatedHash}",
    "hntimestamp" -> "${TimeStamp}",
    "hntjeneste" -> "${TjenesteType}",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")

  val scn = scenario("MinIDSimulation")

    .exec(flushCookieJar)
    .exec(flushHttpCache)

    .feed(csv("magnus/MinID.csv").circular)

    .exec(http(requestName = "request_Signin")
      .get("/auth/signin")
      .headers(headers_0)
      .check(status.is(expected = 200))
      .check(regex("ReqID=(.*?)&").saveAs("ReqID")))

    .exec(session=>{
      println("ReqID:")
      println(session("ReqID").as[String])
      session})

    .exec(http(requestName = "request_login1")
      .get("https://idporten-yt2.difi.eon.no/opensso/UI/Login")
      .headers(headers_1)
      .queryParam("realm","norge.no")
      .queryParam("goto","http://idporten-yt2.difi.eon.no/opensso/SSORedirect/metaAlias/norge.no/idp4?ReqID%3d${ReqID}")
      .queryParam("index","null")
      .queryParam("acsURL","https://oidc-yt2.difi.eon.no:443/idporten-oidc-provider/assertionconsumer")
      .queryParam("spEntityID","oidc-yt2.difi.eon.no")
      .queryParam("binding","urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST")
      .queryParam("service","MinIDChain")
      .queryParam("ForceAuth","")
      .queryParam("locale","nb")
      .queryParam("gx_charset","UTF-8"))

    .exec(http(requestName = "request_login2")
      .post("https://idporten-yt2.difi.eon.no/opensso/UI/Login?realm=norge.no&goto=http://idporten-yt2.difi.eon.no/opensso/SSORedirect/metaAlias/norge.no/idp4?ReqID=_1c3e97261c5a23dd73394c21f59369b5&index=null&acsURL=https://oidc-yt2.difi.eon.no:443/idporten-oidc-provider/assertionconsumer&spEntityID=oidc-yt2.difi.eon.no&binding=urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST&service=MinIDChain&ForceAuth=&locale=nb&gx_charset=UTF-8")
      .headers(headers_2)
      .formParamMap(Map("gx_charset" -> "UTF-8", "pageid" -> "a1", "input_USERNAME_IDPORTEN" -> "${pnr}", "input_PASSWORD_IDPORTEN" -> "password01", "idporten.inputbutton.NEXT" -> ""))
      .check(status.is(expected = 200)))

    .exec(http(requestName = "request_login3")
      .post("https://idporten-yt2.difi.eon.no/opensso/UI/Login?realm=norge.no&goto=http://idporten-yt2.difi.eon.no/opensso/SSORedirect/metaAlias/norge.no/idp4?ReqID=${ReqID}&index=null&acsURL=https://oidc-yt2.difi.eon.no:443/idporten-oidc-provider/assertionconsumer&spEntityID=oidc-yt2.difi.eon.no&binding=urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST&service=MinIDChain&ForceAuth=&locale=nb&gx_charset=UTF-8")
      .headers(headers_3)
      .formParamMap(Map("gx_charset" -> "UTF-8", "pageid" -> "a22", "input_PINCODE1_IDPORTEN" -> "12345", "idporten.inputbutton.NEXT" -> ""))
      .check(xpath("//input[@type=\"hidden\" and @name=\"SAMLResponse\"]/@value").saveAs("SAMLResponse")))

    .exec(http(requestName = "request_assertionconsumer")
      .post("https://oidc-yt2.difi.eon.no/idporten-oidc-provider/assertionconsumer")
      .headers(headers_4)
      .formParam("SAMLResponse", "${SAMLResponse}")
      .check(status.is(expected = 200)))

    .exec(http(requestName = "request_minhelse")
      .get("/")
      .headers(headers_0)
      .check(regex("\"__AnonymousHash__\": \"(.*?)\"").saveAs("AnonymousHash"))
      .check(regex("\"__AuthenticatedHash__\": \"(.*?)\"").saveAs("AuthenticatedHash"))
      .check(regex("\"__TimeStamp__\": \"(.*?)\"").saveAs("TimeStamp"))
      .check(regex("\"__TjenesteType__\": \"(.*?)\"").saveAs("TjenesteType"))
      .check(regex("\"__HendelseLoggType__\": \"(.*?)\"").saveAs("HendelseLoggType")))

    .exec(session=>{
      println("AuthenticatedHash:")
      println(session("AuthenticatedHash").as[String])
      session})

    .exec(http(requestName = "request_getRepresentasjonsforhold")
      .get("/api/v1/MinHelse/GetRepresentasjonsforhold")
      .queryParam("FetchUnreadMessages","true")
      .headers(headers_5)

    )


  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
