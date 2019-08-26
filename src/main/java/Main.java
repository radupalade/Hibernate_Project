import com.sda.entities.Classes;
import com.sda.entities.Students;
import com.sda.entities.Teachers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("MENU");
        System.out.println("1. insert student");
        System.out.println("2. insert teacher");
        System.out.println("3. insert classes");
        System.out.println("4. delete student");
        System.out.println("5. update student");
        SessionFactory sessionFactory = returnSession();
        if (sessionFactory == null) {
            System.out.println("session was not created");
        }

        Scanner scanner = new Scanner(System.in);
        Integer menu = scanner.nextInt();
        System.out.println("you selected : " + menu);
        switch (menu) {
            case 1:
                List<Students> studentList = new ArrayList<>();
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                insertStudentsfromInput(session, studentList);
                transaction.commit();
                session.close();
                break;
            case 2:
                Session session1 = sessionFactory.openSession();
                Transaction transaction1 = session1.beginTransaction();
                insertTeacher(session1);
                transaction1.commit();
                session1.close();
                break;
            case 3:
                Session session2 = sessionFactory.openSession();
                Transaction transaction2 = session2.beginTransaction();
                insertClasses(session2);
                transaction2.commit();
                session2.close();
                break;
            case 4:
                Session session3 = sessionFactory.openSession();
                Transaction transaction3 = session3.beginTransaction();
                deleteStudent(session3);
                transaction3.commit();
                session3.close();
                break;
            case 5:
                Session session4 = sessionFactory.openSession();
                Transaction transaction4 = session4.beginTransaction();
                updateStudent(session4);
                transaction4.commit();
                session4.close();
                break;
            case 6:
                Session session5 = sessionFactory.openSession();
                Transaction transaction5 = session5.beginTransaction();
                Query query = session5.getNamedQuery("get_student_by_name");
                List<Students> students = query.setParameter("first_name", "mihaela").getResultList();
                for (Students student : students) {
                    System.out.println(student);
                }
                System.out.println(query.toString());
                transaction5.commit();
                session5.close();
                break;

        }


    }

    public static void insertStudentsfromInput(Session session, List<Students> studentList) {
        System.out.println("How many students do you want to insert?");
        Scanner scanner = new Scanner(System.in);
        int numberOfStudents = scanner.nextInt();
        int i = 0;

        while (i < numberOfStudents) {
            System.out.print("Please enter first name: ");
            String firstName = scanner.next();
            //scanner.nextLine();
            System.out.print("Please enter last name: ");
            String lastName = scanner.next();

            System.out.print("Please enter the age: ");
            int age = scanner.nextInt();

            /*System.out.print("Please enter the locker:");
            int locker  = scanner.nextInt();*/

            studentList.add(new Students(firstName, lastName, age));
            System.out.println("The student has been added to the list");
            i++;
        }

        for (Students student : studentList) {
            session.persist(student);
        }
    }

    public static void insertTeacher(Session session) {
        Teachers teacher1 = new Teachers("popescu", "florin", 44, "chimie");
        Teachers teacher2 = new Teachers("mihaila", "vasilescu", 40, "romana");
        Teachers teacher3 = new Teachers("ionescu", "popescu", 48, "matematica");

        session.persist(teacher1);
        session.persist(teacher2);
        session.persist(teacher3);

    }

    public static void insertClasses(Session session) {
        Classes classes1 = new Classes();
        Classes classes2 = new Classes();
        Classes classes3 = new Classes();
        session.persist(classes1);
        session.persist(classes2);
        session.persist(classes3);
    }

    public static void deleteStudent(Session session) {
        Students student = session.find(Students.class, 4);
        if (student != null) {
            session.delete(student);
        }
    }

    public static void updateStudent(Session session) {
        Students students = new Students();
        Students students1 = session.find(Students.class, 8);
        if (students1 != null) {
            students1.setFirstName("ionelaa");
        }

    }

    public static SessionFactory returnSession() {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources sources = new MetadataSources(registry);
        Metadata metadata = sources.getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }
}

