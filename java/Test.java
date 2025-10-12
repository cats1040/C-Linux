// package java;

public class Test {
  // Auto-widening
  public static void print(long i) {
    System.out.println("I am long " + i);
  }

  // Best-fit
  public static void print(int i) {
    System.out.println("I am long " + i);
  }

  // Var-args
  public static void print(int... i) {
    System.out.println("I am long " + i[0]);
  }

  // Auto-boxing
  public static void print(Integer i) {
    System.out.println("I am long " + i);
  }

  // Best fit -> Auto-widening -> Auto-boxing -> Var-args

  public static void main(String[] args) {
    Integer i = 5;
    print(i); // Calls print(Integer i)
  }
}
