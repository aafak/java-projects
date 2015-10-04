package mypack;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name="emp2")
public class Emp {
@Id	
int id;
String name;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
