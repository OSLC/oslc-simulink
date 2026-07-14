package edu.gatech.mbsec.adapter.subversion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import util.FileMetadata;

/**
 * NO-OP STUB of the Subversion backend, shaded locally so the Simulink adapter
 * compiles without the external {@code oslc4jsubversion}/{@code svnkit-client}
 * modules. The real Subversion integration is intended to be reimplemented
 * behind a backend interface (e.g. backed by git) in a later milestone.
 */
public class SubversionManager {

	static Map<String, SubversionManager> subversionManagerMap = new HashMap<String, SubversionManager>();

	private static SubversionManager instance;

	private Map<String, SubversionFile> qNameOslcSubversionFileMap = new HashMap<String, SubversionFile>();
	private String baseHTTPURI;

	public SubversionManager(String baseHTTPURI) {
		this.baseHTTPURI = baseHTTPURI;
		instance = this;
		if (baseHTTPURI.contains("magicdraw")) {
			subversionManagerMap.put("magicdraw", this);
		} else if (baseHTTPURI.contains("simulink")) {
			subversionManagerMap.put("simulink", this);
		} else if (baseHTTPURI.contains("amesim")) {
			subversionManagerMap.put("amesim", this);
		}
	}

	public static SubversionManager getSubversionManager(String baseURIString) {
		if (baseURIString != null) {
			if (baseURIString.contains("magicdraw")) {
				return subversionManagerMap.get("magicdraw");
			} else if (baseURIString.contains("simulink")) {
				return subversionManagerMap.get("simulink");
			} else if (baseURIString.contains("amesim")) {
				return subversionManagerMap.get("amesim");
			}
		}
		return instance;
	}

	public Collection<SubversionFile> getSubversionFiles() {
		return qNameOslcSubversionFileMap.values();
	}

	public SubversionFile getFileByURI(String string) {
		return qNameOslcSubversionFileMap.get(string);
	}

	public void convertFileMetaDataIntoRDFSubversionFileResources(ArrayList<FileMetadata> fileMetaDatas) {
		// no-op stub
	}
}
