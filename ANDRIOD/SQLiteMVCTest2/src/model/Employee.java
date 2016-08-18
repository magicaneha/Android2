package model;

public class Employee {
	private int eno;
	private String ename,email;
	private float basic;
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getBasic() {
		return basic;
	}
	public void setBasic(float basic) {
		this.basic = basic;
	}
	public Employee(int eno, String ename, String email, float basic) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.email = email;
		this.basic = basic;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return eno+":"+ename+":"+basic+":"+email;
	}
	
	
}
