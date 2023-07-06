package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;


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

    public Collection<Student> findStudentsWithNameByA() {
        logger.info("Was invoked method for find students with name started A");
        return studentRepository.findStudentsWithNameStartedA();
    }

    public void testThreadsLesson() {
        System.out.println(studentRepository.findById(1L));
        System.out.println(studentRepository.findById(2L));

        new Thread(() -> {
                System.out.println(studentRepository.findById(3L));
                System.out.println(studentRepository.findById(4L));
        }).start();

        new Thread(() -> {
            System.out.println(studentRepository.findById(5L));
            System.out.println(studentRepository.findById(6L));
        }).start();
    }

    public synchronized void writeNameForTest(Long id1, Long id2) {
        System.out.println(studentRepository.findById(id1));
        System.out.println(studentRepository.findById(id2));
    }

    public void testThreadsLesson2() {
        writeNameForTest(1L,2L);
        new Thread(() -> {
            writeNameForTest(3L,4L);
        }).start();
        new Thread(() -> {
            writeNameForTest(5L,6L);
        }).start();
    }
}
