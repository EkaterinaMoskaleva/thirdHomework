package readingfromfile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ReadingFromFile {
    // class student реализует станд. интерфейс сравнения
    static class Student implements Comparable<Student> {
        private int id, age;
        private String firstName, lastName;

        // constructor
        public Student(int id, String lastName, String firstName, int age) {
            this.id = id;
            this.age = age;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        // getter
        public int getId() { return this.id; }
        public int getAge() { return this.age; }
        public String getFirstName() { return this.firstName; }
        public String getLastName() { return this.lastName; }

        // пер. method toString
        public String toString() { return this.getLastName() + " " + this.getFirstName() + "{" + this.getAge() + "}"; }

        // обязат. испол. функции интерфейса Comparable
        public int compareTo(Student o) {
            if (o == null) return 1;
            if (o.getAge() > this.getAge()) return -1;
            if (o.getAge() < this.getAge()) return 1;
            return 0;
        }
    }

    // вывод студентов, чьи фамилии начинаются на од. букву
    public static List<Student> filter(List<Student> list, String prefix) {
        List<Student> result = new ArrayList<>();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getLastName().startsWith(prefix)) result.add(list.get(i));
        }
        return result;
    }

    // average age
    public static float getAverage(List<Student> list) {
        float acc = 0.0f;
        for (Student student : list) {
            acc += student.getAge();
        }
        return acc / list.size();
    }

    public static void main(String[] args) throws IOException {
        List<Student> students = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("D:/Automation school/third hometask/students.txt"));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] arg = line.split(","); // ["1", "Petr", "Petrov", "21"]
                Student student = new Student(Integer.parseInt(arg[0]), arg[1], arg[2], Integer.parseInt(arg[3]));
                students.add(student);
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       Collections.sort(students);
       // Path file1 = Paths.get("answer.txt");

        System.out.println(students);

        System.out.println(getAverage(students));

        System.out.print("Enter any letter:");
        Scanner let = new Scanner(System.in);
        String letter = let.next();
        System.out.println(letter);
        System.out.println(filter(students, letter));
    }
}
