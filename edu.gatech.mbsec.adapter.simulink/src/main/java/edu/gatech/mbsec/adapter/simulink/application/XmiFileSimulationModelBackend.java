package edu.gatech.mbsec.adapter.simulink.application;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import simulink.WorkingDirectory;

/**
 * Test double: loads a pre-generated Simulink XMI fixture without invoking
 * {@code matlab.exe}. By default reads {@code simulinkWorkDir.xmi} from the
 * classpath; an explicit resource name or filesystem path may be supplied.
 *
 * <p>The returned {@link WorkingDirectory} is the same coherent model the
 * MATLAB backend would produce, so the OSLC catalog and resource endpoints can
 * be exercised end-to-end in environments where MATLAB is unavailable.
 */
public class XmiFileSimulationModelBackend implements SimulationModelBackend {

	private final String location;

	public XmiFileSimulationModelBackend() {
		this("simulinkWorkDir.xmi");
	}

	public XmiFileSimulationModelBackend(final String location) {
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
