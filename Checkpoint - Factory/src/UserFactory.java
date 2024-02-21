package src;

public class UserFactory {
    
    public static final int AUDIT_TYPE= 1;
    public static final int PROFESSOR_TYPE = 2;
    public static final int STUDENT_TYPE = 3;
    public static final int ADMINISTRATION_TYPE = 4;

    public static Users createUser(int userType, String id, String name, String password){
        switch (userType) {
            case AUDIT_TYPE:
                return new Audit(id, name, password);    

            case PROFESSOR_TYPE:
                return new Professor(id, name, password);

            case STUDENT_TYPE:
                return new Student(id, name, password);
        
            case ADMINISTRATION_TYPE:
                return new Administration(id, name, password);
            default:
                return new Student(id, name, password);
        }
    }
}
