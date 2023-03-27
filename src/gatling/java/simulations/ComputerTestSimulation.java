package simulations;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ComputerTestSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://computer-database.gatling.io")
            .inferHtmlResources(AllowList(), DenyList())
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate");

//    FeederBuilder<Object> translations = jdbcFeeder("jdbc:postgresql://localhost:5432/basket_platform", "postgres",
//            "", "SELECT id FROM translation");

//
//    FeederBuilder<Object> subscriptions = jdbcFeeder("jdbc:postgresql://localhost:5432/basket_platform", "postgres",
//            "", "SELECT id FROM subscription");
//

//    ChainBuilder watch = feed(translations).exec(http("WatchTranslation")
//            .get("http://localhost:8080/platforma/translations/watch/${id}")
//            .check(status().is(200))
//    );

    ChainBuilder api = repeat(20).on(exec(http("GetApi")
            .get("http://localhost:8080/platforma/translations")
            .check(status().is(200)))
            .pause(1));

//    ChainBuilder delete = feed(subscriptions).exec(http("DeleteTranslation")
//            .post("/subscriptions/delete/${id}")
//            .check(status().is(200)));


//    ChainBuilder updateSubscription = http("UpdateSubscription")
//            .put("/subscription /")


    ScenarioBuilder scn = scenario("MyComputerTest").exec(api);

    {
        setUp(scn.injectOpen(
                constantUsersPerSec(2000).during(15)
        )).protocols(httpProtocol);
    }
}
