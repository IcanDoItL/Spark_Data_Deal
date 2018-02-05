package action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
//import java.util.ArrayList;

//import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

import dao.CommunityMethod;

public class EvaluateAction extends ActionSupport {

	private static String CommunityMethod = "cpm";

	public String getCommunityMethod() {
		return CommunityMethod;
	}

	public void setCommunityMethod(String communityMethod) {
		CommunityMethod = communityMethod;
	}

	String classpath = this.getClass().getResource("/").getPath();
	
	String path = classpath.replaceAll("/WEB-INF/classes/", "/json/");
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("evaluate");
		System.out.println(CommunityMethod);
		CommunityMethod communityMethod = new CommunityMethod();
		// 这里每次文件名字不一样
		String shellpath = "/home/james/graduate/shell/CalResult.sh";
		String commod = "sh " + shellpath + " " + "hdfs://192.168.10.101:8020/user/hu/" + CommunityMethod
				+ "/1000community.txt" + " " + "hdfs://192.168.10.101:8020/user/hu/" + CommunityMethod
				+ "/1000communities.txt" + " " + "/opt/hu/result.txt" + " " + CommunityMethod + " " + "spark://master1.jie.com:7077";
		System.out.println(commod);
		ExecuteShell(commod);
		System.out.println("spark calculate end ");
		ReadResult("ftp://uftp:root@192.168.10.101/../../opt/hu/result.txt", communityMethod, "utf-8");
		writeJson(communityMethod, path + "result.json");
		System.out.println("end");
		return SUCCESS;
	}

	public void ReadResult(String sourceFilePath, CommunityMethod method, String encode)// 这里面传个List是评估完之后的结果
																						// 和评估函数的名字
	{
		try {
			URL url = new URL(sourceFilePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), encode));
			String tem = br.readLine();
			String[] array = null;
			while (tem != null) {
				System.out.println(tem);
				array = tem.split(",");
				method.getPrecision().add(Double.parseDouble(array[0]));
				method.getRecall().add(Double.parseDouble(array[1]));
				method.getF_measure().add(Double.parseDouble(array[2]));
				tem = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

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

	public void writeJson(Object object, String path) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(path);
		try {
			mapper.writeValue(file, object);
			System.out.println("write finash");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
