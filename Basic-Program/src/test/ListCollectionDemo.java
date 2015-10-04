package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ListCollectionDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<Emp> emplist = new ArrayList<Emp>();//Gurantee of order
emplist.add(new Emp("Aafak",10000,"S/W"));
emplist.add(new Emp("Ramesh",60000,"S/W"));
emplist.add(new Emp("Aman",30000,"S/W"));
emplist.add(new Emp("Raj",80000,"S/W"));
emplist.add(null);// null allow here
//for(Emp emp: emplist){
//System.out.println(emp.getName()+", "+emp.getSalary()+", "+emp.getJob());
//}
System.out.println(emplist.contains(new Emp("Aafak",10000,"S/W")));//Emp class must override equals method of Object class, in order to work it

HashSet<Emp3>empSet = new HashSet<Emp3>();// order is not guaranteed
empSet.add(new Emp3("Aafak",10000,"S/W"));
empSet.add(new Emp3("Ramesh",60000,"S/W"));
empSet.add(new Emp3("Aman",30000,"S/W"));
empSet.add(new Emp3("Aafak",10000,"S/W"));//will not add because of duplicate
empSet.add(null);

System.out.println(empSet.contains(new Emp3("Aafak",10000,"S/W")));//Emp3 class must override both equals and hashCode()method of Object class, in order to work it
//for(Emp3 emp: empSet){
//System.out.println(emp.getName()+", "+emp.getSalary()+", "+emp.getJob());
//}

Map<String,Emp3>empMap = new HashMap<String,Emp3>();// order is not guaranteed
empMap.put("1", new Emp3("Aafak",10000,"S/W"));
empMap.put("2", new Emp3("Ramesh",60000,"S/W"));
empMap.put("3", new Emp3("Aman",30000,"S/W"));
empMap.put("1", new Emp3("Aafak2",10000,"S/W"));//will override first one

//for(Map.Entry<String,Emp3> emp :empMap.entrySet()){
//	Emp3 e = emp.getValue();
//	System.out.println(emp.getKey()+", "+e.getName()+", "+e.getSalary()+", "+e.getJob());
//}

//OR
//for(String key :empMap.keySet()){
//	Emp3 e = empMap.get(key);//treeMap.containsKey(key);
//	System.out.println(key+", "+e.getName()+", "+e.getSalary()+", "+e.getJob());
//}


Map<String,Emp3>treeMap = new TreeMap<String,Emp3>();// Store in ascending order of key
treeMap.put("1", new Emp3("Aafak",10000,"S/W"));
treeMap.put("2", new Emp3("Ramesh",60000,"S/W"));
treeMap.put("3", new Emp3("Aman",30000,"S/W"));
treeMap.put("1", new Emp3("Aafak2",10000,"S/W"));//will override first one, because of same key

//for(Map.Entry<String,Emp3> emp :treeMap.entrySet()){
//	Emp3 e = emp.getValue();
//	System.out.println(emp.getKey()+", "+e.getName()+", "+e.getSalary()+", "+e.getJob());
//}
//OR
//for(String key :treeMap.keySet()){
//	Emp3 e = treeMap.get(key);//treeMap.containsKey(key);
//	System.out.println(key+", "+e.getName()+", "+e.getSalary()+", "+e.getJob());
//}

//Note: For TreeSet Object must be comparable
TreeSet<Emp1>treeSet = new TreeSet<Emp1>();// Store in ascending order by name, so user defined classes must be comparable
//TreeSet<Emp1>treeSet = new TreeSet<Emp1>(new SalaryComparator());// Store in ascending order by salary, so user defined classes must be comparable
//TreeSet<Emp1>treeSet = new TreeSet<Emp1>(new JobComparator());// Store in ascending order by job, so user defined classes must be comparable
treeSet.add(new Emp1("Aafak",10000,"Software Engineer"));
treeSet.add(new Emp1("Ramesh",60000,"Assistence software eng"));
treeSet.add(new Emp1("Aman",30000,"Trainee")); 
treeSet.add(new Emp1("Aafak",40000,"Teacher"));//will not add because of duplicate name
treeSet.add(new Emp1("Aafak",10000,"Software Engineer"));//will not add because of duplicate name
//Note: if u use salaryComparator, then any element with duplicate salary will not add, compareTo() of Emp1 will override, if u pass explicitly a comparator
//same for JobComparator
System.out.println(treeSet.contains(new Emp1("Aafak",10000,"S/W")));//Emp1 class must implement Comparable interface and override compareTo()method of, in order to work it

for(Emp1 emp: treeSet){
	System.out.println(emp.getName()+", "+emp.getSalary()+", "+emp.getJob());
}

//Program to display the frequency of a number in list
List<Integer>numbers = new ArrayList<Integer>();
numbers.add(1111);
numbers.add(2);
numbers.add(5);
numbers.add(5);
numbers.add(1);
numbers.add(1);
numbers.add(2);
numbers.add(5);
//Collections.sort(numbers);
//Collections.sort(numbers, Collections.reverseOrder());
//System.out.println(numbers);
//System.out.println(numbers.get(0));
Map<Integer,Integer>numMap = new HashMap<Integer, Integer>();
for(Integer num: numbers){
	if(numMap.containsKey(num)){
		numMap.put(num, numMap.get(num)+1);//update the value of key by increasing its frequency
	}else{
		numMap.put(num, 1);
	}
}
//for(Map.Entry<Integer, Integer>num : numMap.entrySet()){
//	System.out.println("Fequency of "+num.getKey()+" is "+num.getValue());
//}



//sorting:

List<Emp1>empList = new ArrayList<Emp1>();
empList.add(new Emp1("Aafak",10000,"Software Engineer"));
empList.add(new Emp1("Ramesh",60000,"Assistence software eng"));
empList.add(new Emp1("Aman",30000,"Trainee")); 
empList.add(new Emp1("Aafak",10000,"Teacher"));
Collections.sort(empList);
Collections.sort(empList, Collections.reverseOrder());
Collections.sort(empList, new SalaryComparator());
Collections.sort(empList, new JobComparator());
//for(Emp1 emp: empList){
//	System.out.println(emp.getName()+", "+emp.getSalary()+", "+emp.getJob());
//}
		
	}

}
