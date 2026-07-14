package edu.gatech.mbsec.adapter.simulink.application;

import simulink.WorkingDirectory;

/**
 * Abstraction over the source of the Simulink working-directory model.
 *
 * <p>The adapter needs a {@link WorkingDirectory} (an EMF model of the Simulink
 * models, blocks, ports, lines and parameters) to populate the OSLC catalog.
 * Historically this was produced by running MATLAB (see
 * {@link MatlabSimulationModelBackend}). Decoupling the loading behind this
 * interface lets the adapter be tested and run without {@code matlab.exe}, and
 * paves the way for additional simulation-model backends (Octave / Scilab-Xcos,
 * OpenModelica, Simulab-py, ...).
 */
public interface SimulationModelBackend {

	/**
	 * @return the loaded Simulink working directory, or {@code null} if none
	 *         could be loaded
	 * @throws Exception if loading fails
	 */
	WorkingDirectory loadWorkingDirectory() throws Exception;
}
