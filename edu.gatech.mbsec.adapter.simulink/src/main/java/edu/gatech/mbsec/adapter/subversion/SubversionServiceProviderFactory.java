package edu.gatech.mbsec.adapter.subversion;

import java.net.URISyntaxException;

import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;

/**
 * NO-OP STUB of the Subversion service-provider factory, shaded locally so the
 * Simulink adapter compiles without the external {@code oslc4jsubversion}
 * module. Returns a minimal (empty) service provider; the real Subversion
 * integration is intended to be reimplemented behind a backend interface in a
 * later milestone.
 */
public class SubversionServiceProviderFactory {
	private SubversionServiceProviderFactory() {
		super();
	}

	public static ServiceProvider createServiceProvider(final String baseURI, final String product)
			throws OslcCoreApplicationException, URISyntaxException {
		return new ServiceProvider();
	}
}
