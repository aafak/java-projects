package interfaceUseCase;

class Sony implements Amount
{ public int getAmount()
  {
	  return 2000;}

  public int getWarrunty()
  {return 2;}

}


class LG implements Amount
{
   public int getAmount()
   {return 10000;}
     public int getWarrunty()
  {return 3;}

}


class Nokia implements Amount
{
   public int getAmount()
   {return 30000;}
  public int getWarrunty()
  {return 5;}

}


class AmountMain
{
  public static void main(String ad[])
  {
    Amount amt;
    Sony s=new Sony();
    amt=s;//  this is dynamic method dispatching
    System.out.println("The sony Price is:"+amt.getAmount() + "  and Warrunty is: "+s.getWarrunty()+"years");


    LG lg=new LG();
    amt=lg;  //  this is dynamic method dispatching
    System.out.println("Th LG Price is:"+amt.getAmount()+ "   and Warrunty is: "+lg.getWarrunty()+"years");

    Nokia n=new Nokia();
    System.out.println("Th Nokia Price is:"+n.getAmount()+ "  and Warrunty is: "+n.getWarrunty()+"years");

  }


}