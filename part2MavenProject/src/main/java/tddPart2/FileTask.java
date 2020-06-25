package tddPart2;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class FileTask extends Task {

	private long size = 0;
	private String fileLocation = "\0";
	private String fileName = "\0";
	private String fullPath = "\0";
	private String errorMsg = null;

	protected long getSize() {
		return size;
	}

	protected String showErrorMsg() {
		return errorMsg;
	}

	// argument 1 is file location, argument 2 is the name and extension of the file
	@Override
	public boolean configure(List<String> fileSettings) {

		if (fileSettings != null) {

			if (fileSettings.size() == 2) {

				if (fileSettings.get(0) != null && fileSettings.get(1) != null) {

					if (fileSettings.get(0).equals("") || fileSettings.get(1).equals("")) {
						return false;
					}

					fileLocation = fileSettings.get(0);
					fileName = fileSettings.get(1);
					fullPath = fileLocation + "\\" + fileName;
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public String run() {
		
		DecimalFormat df2 = new DecimalFormat("#.##");
		
		if (setFileSize()) {
			return "FileSize: " + df2.format(convFromBytesToKb());
		}
		
		return errorMsg;
	}

	protected double convFromBytesToKb() {
		double inBytes = (double) size;
		double inKb = inBytes / 1024;
		return inKb;
	}

	protected boolean setFileSize() {

		try {
			FileInputStream stream = new FileInputStream(fullPath);
			size = stream.getChannel().size();
			stream.close();
			return true;
		} catch (IOException e) {
			errorMsg = e.getMessage();
		}

		return false;
	}

}
