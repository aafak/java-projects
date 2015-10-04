package xmltest;

public class Employee {

	String name;
	int id;
	int age;
	String type;
	
	public Employee(String name, int id, int age, String type) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id:"+id+", Name:"+name+", Age:"+age+", Type:"+type;
	}
	
	

}
