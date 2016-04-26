package net.jking.pageModel;

public class Location {
	private String local;
	private String num;
	private String status;
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Location [local=" + local + ", num=" + num + ", status="
				+ status + "]";
	}
	
	
}
