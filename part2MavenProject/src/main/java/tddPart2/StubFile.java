package tddPart2;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class StubFile extends Task{
	
	private String fileLocation = "\0";
	private String fileName = "\0";
	private String fullPath = "\0";
	private long size = 0;
	@SuppressWarnings("unused")
	private String errorMsg = null;

	@Override
	public boolean configure(List<String> params) {
		fileLocation = "src/test/resources";
		fileName = "test.png";
		fullPath = fileLocation + "\\" + fileName;
		setFileSize();
		return true;
	}

	@Override
	public String run() {
		DecimalFormat df2 = new DecimalFormat("#.##");
		return "FileSize: " + df2.format(convFromBytesToKb());
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
	
	
	protected double convFromBytesToKb() {
		double inBytes = (double) size;
		double inKb = inBytes / 1024;
		return inKb;
	}


}
