package example.com.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.com.assessment.entity.Task;
import java.util.*;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByUserId(Long userId);
}
