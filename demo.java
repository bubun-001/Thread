class A extends Thread{
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println("in A");
            try {
                Thread.sleep(10); 
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }
    }
}

class B extends Thread{
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println("in B");
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                // TODO: handle exception
            }
            

        }
    }
}

public class demo {
    public static void main(String[] args) {
        A obj1 = new A();
        B obj2 = new B();
        obj1.start();
        obj2.start();
    }
}