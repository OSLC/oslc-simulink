package edu.gatech.mbsec.adapter.simulink.application;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import simulink.WorkingDirectory;

/**
 * Standalone implementation of {@link SimulationModelBackend}: loads a
 * pre-generated Simulink XMI fixture from disk without invoking
 * {@code matlab.exe}. By default it reads {@code simulinkWorkDir.xmi} from the
 * classpath (packaged under {@code src/main/resources}); an explicit resource
 * name or filesystem path may be supplied via the constructor, which makes the
 * demo-data location configurable.
 *
 * <p>This is the implementation selected by default (see the {@code simulink.backend}
 * configuration flag), so the OSLC server starts and serves the catalog and
 * resource endpoints even when MATLAB is not installed. The returned
 * {@link WorkingDirectory} is the same coherent model the MATLAB-backed
 * implementation would produce.
 */
public class SimulationModelBackendStandaloneImpl implements SimulationModelBackend {

	private final String location;

	public SimulationModelBackendStandaloneImpl() {
		this("simulinkWorkDir.xmi");
	}

	public SimulationModelBackendStandaloneImpl(final String location) {
		this.location = location;
	}

	@Override
	public WorkingDirectory loadWorkingDirectory() throws Exception {
		final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
		final File xmi;
		if (in != null) {
			xmi = File.createTempFile("simulinkWorkDir", ".xmi");
			xmi.deleteOnExit();
			Files.copy(in, xmi.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} else {
			xmi = new File(location);
		}
		return SimulinkManager.loadWorkingDirectoryFromXmi(xmi);
	}
}
