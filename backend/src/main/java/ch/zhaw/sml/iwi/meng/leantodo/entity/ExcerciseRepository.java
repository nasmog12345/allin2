package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcerciseRepository extends JpaRepository<Excercise,Long>{
    Optional<Excercise> findByName(String name);
}
