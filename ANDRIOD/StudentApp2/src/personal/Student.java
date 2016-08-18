package personal;

import java.io.Serializable;

public class Student implements Serializable {
	
	int rno;String name;float fees;


	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getFees() {
		return fees;
	}


	public void setFees(float fees) {
		this.fees = fees;
	}


	public Student(int rno, String name, float fees) {
		super();
		this.rno = rno;
		this.name = name;
		this.fees = fees;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
