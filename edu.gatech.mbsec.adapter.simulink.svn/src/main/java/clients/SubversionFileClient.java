package clients;

import util.FileMetadata;

/**
 * NO-OP STUB of the single Subversion file synchronizer, shaded locally so the
 * Simulink adapter compiles without the external {@code svnkit-client} module.
 */
public class SubversionFileClient {
	public FileMetadata syncFile(String svnurl, String localDir, String user, String password) {
		return null;
	}
}
