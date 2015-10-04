package test;

import java.util.ArrayList;
import java.util.List;
/*
Q. What are the different ways to get code reuse?
A. There are 3 approaches

1. Implementation inheritance with abstract classes.
2. Composition.
3. Delegation to a helper class.
*/
/*
 * The purpose of abstract classes is to function as base classes 
 * which can be extended by sub classes to create a full implementation. 
 * The base class is used for code reuse and gives polymorphism by up casting to it.
 */
/*
Abstract methods are good for implementing template method and composite design patterns 
with state and behavior.

Interfaces

If you need to change your design frequently, 
you should prefer using interfaces to abstract classes. 
Coding to an interface reduces coupling and interface inheritance can achieve code 
reuse with the help of object composition. For example: The Spring frameworks’ 
dependency injection promotes code to an interface principle. Another justification 
for using interfaces is that they solve the ‘diamond problem’ of traditional multiple 
inheritance. Java does not support multiple inheritance, but supports multiple behavior inheritance. 
*/
abstract class Employee{
	String name;
	String dob;
	int salary;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Employee(String name, String dob, int salary) {
		super();
		this.name = name;
		this.dob = dob;
		this.salary = salary;
	}

	public void printSalarySlip(){
		System.out.println("Salaray for Emp: "+name+" is :"+getSalary());
	}
	public void printEmpDetails(){
		System.out.println("Name: "+name);
		System.out.println("DOB: "+dob);
		System.out.println("Privileges: ");
		for(String privilige: getPrivileges()){
			System.out.println("privilige: "+privilige);
		}
	}

	public abstract List<String> getPrivileges() ;

}

class Manager extends Employee{

	public Manager(String name, String dob, int salary){
		super(name, dob, salary);
	}

	@Override
	public List<String> getPrivileges() {
		List<String> privileges = new ArrayList<String>();
		privileges.add("Medical");
		privileges.add("Travel");
		return privileges;

	}

}

class Director extends Employee{
	public Director(String name, String dob, int salary){
		super(name, dob, salary);
	}

	@Override
	public List<String> getPrivileges() {
		List<String> privileges = new ArrayList<String>();
		privileges.add("Medical");
		privileges.add("Travel");
		privileges.add("Flat");
		privileges.add("Car");
		privileges.add("Bonuus");
		return privileges;

	}

}

//Exp2
/*
 *  Template Method design pattern is a good example of using an abstract class 
 *  and this pattern is used very prevalently in application frameworks. 
 *  The Template Method design pattern is about providing partial implementations 
 *  in the abstract base classes, and the subclasses can complete when extending 
 *  the Template Method base class(es). Here is an example
 */
//cannot be instantiated
abstract class BaseTemplate {

	public void process() {
		fillHead();
		//some default logic
		fillBody();
		//some default logic
		fillFooter();
	}

	//to be overridden by sub class
	public abstract void fillBody();

	//template method. Sub classes can override or reuse this default implementation
	public void fillHead() {
		System.out.println("Simple header");
	}

	//template method. Sub classes can override or reuse this default implementation
	public void fillFooter() {
		System.out.println("Simple footer");
	}

	//more template methods can be defined here
}
class InvoiceLetterProcessor extends BaseTemplate {

	@Override
	public void fillBody() {
		System.out.println("Invoice body" );
	}

	// template method
	public void fillHead() {
		System.out.println("Invoice header");
	}
}

public class AbstractClassUseCase {

	public static void main(String [] args){
		Employee manager = new Manager("Senthil", "17/03/1965",100000);
		manager.printSalarySlip();
		manager.printEmpDetails();
		Employee director = new Director("Kiran", "17/03/1960",500000);
		director.printSalarySlip();
		director.printEmpDetails();

		//Exp2
		//subclass is up cast to base class -- polymorphism
		BaseTemplate template = new InvoiceLetterProcessor();
		template.process();
	}
}
