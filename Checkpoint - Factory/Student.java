import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements IUserFactory{
    @Override
    public void showOptions() {
        System.out.println("1. consultar nota en una asignatura");
        System.out.println("2. realizar pago");
        System.out.println("3. consultar pagos");
    }

    @Override
    void action(int option) {
        switch(option){
            case 1:
                ConsultStudentGrades();
                break;
            case 2:
                pay();
                break;
            case 3:
                consultPayment();
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }
    
    public void ConsultStudentGrades(){

    }

    public void pay(){

    }

    public void consultPayment(){

    }

    @Override
    public User createUser(int userType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }
}
