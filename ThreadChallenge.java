public class ThreadChallenge {

    public static void main(String[] args) {
        Subthread even = new Subthread();
        Runnable odd = () -> {
            for(int i = 0; i < 5; i++){
                System.out.printf("%d ", 2*i+1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        even.start();
        new Thread(odd).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        even.interrupt();
    }
}
class Subthread extends Thread{
    public Subthread() {
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            System.out.printf("%d ", 2*i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.print("Even thread interrupted! ");
                return;
            }
        }
    }
}
