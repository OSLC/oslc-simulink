package edu.gatech.mbsec.adapter.simulink.application;

import java.io.File;

import simulink.WorkingDirectory;

import edu.gatech.mbsec.adapter.simulink.matlab.Simulink2XMIThread2;
import edu.gatech.mbsec.adapter.simulink.services.OSLC4JSimulinkApplication;

/**
 * Production backend: runs MATLAB (via {@link Simulink2XMIThread2}) to convert
 * the Simulink working directory into {@code simulinkWorkDir.xmi} and loads it.
 */
public class MatlabSimulationModelBackend implements SimulationModelBackend {

	@Override
	public WorkingDirectory loadWorkingDirectory() throws Exception {
		final Simulink2XMIThread2 thread = new Simulink2XMIThread2();
		thread.start();
		thread.join();
		final File xmi = new File(OSLC4JSimulinkApplication.simulinkModelsDirectory + "/simulinkWorkDir.xmi");
		return SimulinkManager.loadWorkingDirectoryFromXmi(xmi);
	}
}
