package test;

public class SalaryComparator implements java.util.Comparator<Emp1>{
public int compare(Emp1 emp1, Emp1 emp2){
	return emp1.getSalary()-emp2.getSalary();
}
}
