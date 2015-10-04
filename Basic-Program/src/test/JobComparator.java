package test;

import java.util.Comparator;

public class JobComparator implements Comparator<Emp1>{
public int compare(Emp1 emp1, Emp1 emp2){
	return emp1.getJob().compareTo(emp2.getJob());
}
}
