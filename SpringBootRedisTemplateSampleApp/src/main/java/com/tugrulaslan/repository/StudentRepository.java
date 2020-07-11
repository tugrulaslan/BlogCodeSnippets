package com.tugrulaslan.repository;

import com.tugrulaslan.dto.Student;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private static final String KEY = "Student";
    private final HashOperations hashOperations;

    public StudentRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(Student student) {
        hashOperations.put(KEY, student.getId(), student);
    }

    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }

    public Student find(String id) {
        return (Student) hashOperations.get(KEY, id);
    }

    public List<Student> findAll() {
        return (List<Student>) hashOperations.entries(KEY)
                .values()
                .stream()
                .collect(Collectors.toList());
    }
}
