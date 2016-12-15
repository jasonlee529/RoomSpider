package cn.lee.jason.cp.entity;

import java.util.List;

/**
 * 开奖结果类
 * @author jason
 *
 */
public class Result {

	private String seq;
	private String origin;
	private List<String> reds;
	private List<String> blue;
	private String date;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public List<String> getReds() {
		return reds;
	}
	public void setReds(List<String> reds) {
		this.reds = reds;
	}
	public List<String> getBlue() {
		return blue;
	}
	public void setBlue(List<String> blue) {
		this.blue = blue;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
