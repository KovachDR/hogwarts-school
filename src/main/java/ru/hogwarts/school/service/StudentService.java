package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentsByAge(int min, int max) {
        logger.info("Was invoked method for find students by age");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer findAllStudentsInSchool() {
        logger.info("Was invoked method for find all students in school");
        return studentRepository.findAllStudentsInSchool();
    }

    public Integer findAverageAgeStudents() {
        logger.info("Was invoked method for find average age students");
        return studentRepository.findAverageAgeStudents();
    }

    public Collection<Student> findLastFiveStudents() {
        logger.info("Was invoked method for find last five students");
        return studentRepository.findLastFiveStudents();
    }

    public List<String> findStudentsWithNameByA() {
        logger.info("Was invoked method for find students with name started A");
        return studentRepository.findAll().stream()
                .filter(s -> s.getName().startsWith("A"))
                .map(s -> s.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public Double findAverageAgeStudentsWithStream() {
        logger.info("Was invoked method for find average age students with stream");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    public void printStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.size() > 6) {
            students.subList(0, 2).forEach(this::printStudentInfo);
            printStudentsInNewThread(students.subList(2, 4));
            printStudentsInNewThread(students.subList(4, 6));
        }
    }

    public void printStudentsSync() {
        List<Student> students = studentRepository.findAll();
        if (students.size() > 6) {
            students.subList(0, 2).forEach(this::printStudentInfo);
            printStudentsInNewThreadSync(students.subList(2, 4));
            printStudentsInNewThreadSync(students.subList(4, 6));
        }
    }


    private void printStudentInfo(Student student) {
        logger.info("Student, id=" + student.getId() + ", name=" + student.getName());
    }
    private synchronized void printStudentInfoSync(Student student) {
        logger.info("Student, id=" + student.getId() + ", name=" + student.getName());
    }

    private void printStudentsInNewThread(List<Student> students) {
        new Thread(() -> {
            students.forEach(this::printStudentInfo);
        }).start();
    }
    private void printStudentsInNewThreadSync(List<Student> students) {
        new Thread(() -> {
            students.forEach(this::printStudentInfoSync);
        }).start();
    }

}
