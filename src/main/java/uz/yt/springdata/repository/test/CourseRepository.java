package uz.yt.springdata.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yt.springdata.dao.test.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
