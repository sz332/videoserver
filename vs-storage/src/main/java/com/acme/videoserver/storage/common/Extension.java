package com.acme.videoserver.storage.common;

public class Extension {

	private String fileName;

	public Extension(String fileName) {
		this.fileName = fileName;
	}

	// https://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
	public String value() {
		char ch;
		int len;

		if (fileName == null || (len = fileName.length()) == 0 || (ch = fileName.charAt(len - 1)) == '/' || ch == '\\'
				|| // in the case of a directory
				ch == '.') { // in the case of . or ..
			return "";
		}

		int dotInd = fileName.lastIndexOf('.');
		int sepInd = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (dotInd <= sepInd) {
			return "";
		} else {
			return fileName.substring(dotInd + 1).toLowerCase();
		}
	}

}
