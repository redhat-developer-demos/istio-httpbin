import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HttpBinServiceSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://192.168.99.100:30420")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:52.0) Gecko/20100101 Firefox/52.0")

  val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

  val uri1 = "http://192.168.99.100:30420/httpbin/delay"

  val scn = scenario("HttpBinServiceSimulation")
    .exec(http("request_0")
      .get("/httpbin/delay")
      .headers(headers_0))

  setUp(scn.inject(atOnceUsers(20))).protocols(httpProtocol)
}
