package uz.yt.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yt.springdata.dao.Authorities;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

    List<Authorities> findAllByUsername(String username);

}
