import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.TreeMap;

public class RandomFileChallenge {

    private static final RandomAccessFile ra;
    private final NavigableMap<Integer,Long> employeeIdToPointer = new TreeMap<>(Comparator.naturalOrder());

    static {
        try {
            ra = new RandomAccessFile("employees.dat", "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        RandomFileChallenge solve = new RandomFileChallenge();
        solve.readEmployeesIndexes();
        solve.listEmployees();
        solve.readEmployeeById(21);
        solve.updateEmployeeSalaryById(21, 1000);
        solve.readEmployeeById(21);

    }

    public void listEmployees(){
        employeeIdToPointer.forEach((key, value) -> System.out.printf("ID: %d - Pointer: %d\n", key, value));
    }

    public void updateEmployeeSalaryById(int employeeId, double employeeNewSalary){
        try{
            ra.seek(employeeIdToPointer.get(employeeId));
            ra.readInt();
            ra.writeDouble(employeeNewSalary);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void readEmployeeById(int employeeId){
        try {
            ra.seek(employeeIdToPointer.get(employeeId));
            ra.readInt();
            double employeeSalary = ra.readDouble();
            String employeeFirstName = ra.readUTF();
            String employeeLastName = ra.readUTF();

            System.out.printf("ID: %d\nSalary: %.2f\nFirst Name: %s\nLast Name: %s\n",
                    employeeId, employeeSalary, employeeFirstName, employeeLastName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readEmployeesIndexes(){

        try{
            ra.seek(0);
            int totalEmployees = ra.readInt();
            System.out.printf("Total de empregados = %d\n", totalEmployees);
            for(int i = 0; i < totalEmployees; i++){
                int employeeID = ra.readInt();
                long employeePointer = ra.readLong();
                employeeIdToPointer.put(employeeID, employeePointer);
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }


}
