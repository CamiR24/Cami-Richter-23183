import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Audit extends User implements IUserFactory{
    @Override
    
    public void showOptions() {
        System.out.println("1. consultar resumen de notas del estudiante");
        System.out.println("2. consultar resumen de pagos del estudiante");
        System.out.println("3. revisar pagos a docentes");
    }

    @Override
    void action(int option) {
        switch(option){
            case 1:
                ConsultStudentGrades();
                break;
            case 2:
                ConsultStudentPayment();
                break;
            case 3:
                AssignPayment();
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }

    public void AssignPayment(){

    }
    
    public void ConsultStudentGrades(){

    }

    public void ConsultStudentPayment(){

    }

    @Override
    public User createUser(int userType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }
}
