package test;

abstract class BaseCmd{
	
}
abstract class AsyncBaseCmd extends BaseCmd{
	
}

abstract class BaseListCmd extends BaseCmd{
	
}

class AsyncBaseCmd1 extends AsyncBaseCmd{
	
}
class AsyncBaseCmd2 extends AsyncBaseCmd{
	
}
class Basecmd1 extends BaseCmd{
	
}
class Basecmd2 extends BaseCmd{
	
}
public class TestInstance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseCmd basecmd1 =new Basecmd1();
		BaseCmd basecmd2 =new Basecmd1();
		
		System.out.println("basecmd1"+basecmd1.getClass());
		AsyncBaseCmd asyncBaseCmd1 =new AsyncBaseCmd1();
		BaseCmd asyncBaseCmd2 =new AsyncBaseCmd1();
		System.out.println("asyncBaseCmd1"+asyncBaseCmd1.getClass());

		if(basecmd1 instanceof BaseCmd){
			System.out.println("basecmd1 instance of BaseCmd");
		}
		
		
		if(asyncBaseCmd1 instanceof BaseCmd){
			System.out.println("asyncBaseCmd1 instance of BaseCmd");
		} else {
			System.out.println("asyncBaseCmd1 is not instance of BaseCmd");
		}
		
		
		
		
		if(basecmd1 instanceof AsyncBaseCmd){
			System.out.println("basecmd1 instance of AsyncBaseCmd");
		} else {
			System.out.println("basecmd1 is not instance of AsyncBaseCmd");
		}
		
	}

}
