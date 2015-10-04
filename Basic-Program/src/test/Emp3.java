package test;

public class Emp3 {

	String name;
	int salary;
	String job;
	
	public Emp3(String name, int salary, String job) {
		super();
		this.name = name;
		this.salary = salary;
		this.job = job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Emp3 emp = (Emp3)obj;
		return (this.name.equals(emp.getName())&& this.salary==emp.getSalary()&&this.job.equals(emp.getJob()));
	}
		@Override
	public int hashCode(){
		return this.name.hashCode()+this.job.hashCode();
	}
}
