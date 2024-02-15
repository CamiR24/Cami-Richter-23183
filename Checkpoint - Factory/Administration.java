import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Administration extends User implements IUserFactory {
    @Override
    public void showOptions() {
        System.out.println("1. crear curso");
        System.out.println("2. crear docente");
        System.out.println("3. crear estudiante");
        System.out.println("4. asignar estudiante al curso");
        System.out.println("5. asignar docente al curso");
        System.out.println("6. asignar pago al catedrático");
        System.out.println("7. consultar resumen de notas del estudiante");
        System.out.println("8. consultar resumen de pagos del estudiante");
    }

    @Override
    void action(int option) {
        switch(option){
            case 1:
                createCourse();
                break;
            case 2:
                createProfessor();
                break;
            case 3:
                createStudent();
                break;
            case 4:
                AssignStudent();
                break;
            case 5:
                AssignProfessor();
                break;
            case 6:
                AssignPayment();
                break;
            case 7:
                ConsultStudentGrades();
                break;
            case 8:
                ConsultStudentPayment();
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }

    public void createCourse(){

    }

    public void createProfessor(){

    }

    public void createStudent(){

    }
    
    public void AssignStudent(){

    }

    public void AssignProfessor(){

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
