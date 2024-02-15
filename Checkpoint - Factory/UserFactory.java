public class UserFactory {
    
    public static final int AUDIT_TYPE= 1;
    public static final int PROFESSOR_TYPE = 2;
    public static final int STUDENT_TYPE = 3;
    public static final int ADMINISTRATION_TYPE = 4;

    public static IUserFactory getUserInstance(int formatType){
        switch (formatType) {
            case AUDIT_TYPE:
                return new Audit();    

            case PROFESSOR_TYPE:
                return new Professor();

            case STUDENT_TYPE:
                return new Student();
        
            case ADMINISTRATION_TYPE:
                return new Administration();
            default:
                return new Student();
        }
    }
}
