import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Professor extends User implements IUserFactory{
    @Override
    public void showOptions() {
        System.out.println("1. Ingresar notas del estudiante en una asignatura");
        System.out.println("2. cobrar pago");
        System.out.println("3. historial de pagos");
    }

    @Override
    void action(int option) {
        switch(option){
            case 1:
                AddGrades();
                break;
            case 2:
                charge();
                break;
            case 3:
                ConsultStudentPayment();
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }
    
    public void AddGrades(){

    }

    public void charge(){

    }

    public void ConsultStudentPayment(){

    }

    @Override
    public User createUser(int userType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }
}
