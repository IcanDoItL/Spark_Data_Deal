package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.opensymphony.xwork2.ActionSupport;

public class UploadFileAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File[] file;
	private String[] fileFileName;
	private String[] fileContentType;

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		System.out.println("upload");
		File[] files = file;
		try {
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					String path = "/home/james/graduate/submit/" + fileFileName[i];
					// System.out.println(fileContentType[i]);
					FileInputStream inputStream = new FileInputStream(files[i]);
					FileOutputStream outputStream = new FileOutputStream(path);
					byte[] buf = new byte[1024];
					int length = 0;
					while ((length = inputStream.read(buf)) != -1) {
						outputStream.write(buf, 0, length);
					}
					inputStream.close();
					outputStream.flush();
					outputStream.close();
					// 文件保存的完整路径
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		String submitpath = "/home/james/graduate/shell/uploadFile.sh";
		ExecuteShell("sh " + submitpath);
		String Uploadshellpath = "/home/james/graduate/shell/uploadShell.sh";
		String commod = "sh " + Uploadshellpath;
		ExecuteShell(commod);
		return SUCCESS;
	}

	public void ExecuteShell(String commod) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(commod);
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}