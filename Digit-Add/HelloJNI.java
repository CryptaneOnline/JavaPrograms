import java.util.*;
public class HelloJNI {
   static {
      System.loadLibrary("hello"); //Loading shared library
   }
 
   private native void sayHello(int a,int b); //Method to be declared in C with parallel
 
 
   public static void main(String[] args) {
	System.out.println("Adding corresponding digits of 2 numbers with parallel computing");
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter numbers to add digits");
	int a=sc.nextInt();
	int b=sc.nextInt();
      new HelloJNI().sayHello(a,b); //Calling the parallel computing routine
   }
}
