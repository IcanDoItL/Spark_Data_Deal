package dao;

import java.util.ArrayList;
import java.util.List;

public class CommunityMethod {

	private List<Double> Precision;
	private List<Double> Recall;
	private List<Double> F_measure;
	public List<Double> getPrecision() {
		return Precision;
	}
	public void setPrecision(List<Double> precision) {
		Precision = precision;
	}
	public List<Double> getRecall() {
		return Recall;
	}
	public void setRecall(List<Double> recall) {
		Recall = recall;
	}
	public List<Double> getF_measure() {
		return F_measure;
	}
	public void setF_measure(List<Double> f_measure) {
		F_measure = f_measure;
	}
	public CommunityMethod() {
		// TODO Auto-generated constructor stub
		this.Precision=new ArrayList<>();
		this.Recall=new ArrayList<>();
		this.F_measure=new ArrayList<>();
	}
	
}
