package clients;

import java.util.ArrayList;

import util.FileMetadata;

/**
 * NO-OP STUB of the Subversion working-copy synchronizer, shaded locally so the
 * Simulink adapter compiles without the external {@code svnkit-client} module.
 */
public class SubversionClient {
	public static ArrayList<FileMetadata> syncWorkingCopy(String svnurl, String localDir, String user, String password) {
		return new ArrayList<FileMetadata>();
	}
}
