package uz.yt.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yt.springdata.dao.Authorities;

import java.util.List;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

    List<Authorities> findAllByUsername(String username);

}
