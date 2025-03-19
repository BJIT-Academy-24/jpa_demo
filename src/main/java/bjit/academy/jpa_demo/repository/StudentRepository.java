package bjit.academy.jpa_demo.repository;

import bjit.academy.jpa_demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student findByEmail(String email);

}
