package com.acme.videoserver.storage.common;

public class FileExtension {

	private String fileName;

	public FileExtension(String fileName) {
		this.fileName = fileName;
	}

	public String extension() {
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
