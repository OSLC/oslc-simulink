package edu.gatech.mbsec.adapter.simulink;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Acceptance test: the OSLC service provider catalog must be reachable.
 *
 * <p>Runs against the adapter deployed in Jetty (see the {@code acceptance}
 * Maven profile). The catalog is populated from a Simulink XMI fixture loaded
 * by {@code XmiFileSimulationModelBackend} (selected via
 * {@code -Dsimulink.backend=xmi}), so no MATLAB is required.
 */
public class SpCatalogIT {

	@Test
	public void catalogIsReachable() {
		RestAssured.baseURI = "http://localhost:8080";
		given()
				.header("OSLC-Core-Version", "2.0")
				.accept("application/rdf+xml")
		.when()
				.get("/services/catalog/singleton")
		.then()
				.statusCode(equalTo(200));
	}
}
