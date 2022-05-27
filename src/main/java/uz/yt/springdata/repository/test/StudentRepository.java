package uz.yt.springdata.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yt.springdata.dao.test.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
