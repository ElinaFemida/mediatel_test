package ru.mediatel.task.repository;

import ru.mediatel.task.model.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EdgeRepository extends JpaRepository<Edge, Integer> {
    Optional<Edge> findEdgeById (Integer id);
}