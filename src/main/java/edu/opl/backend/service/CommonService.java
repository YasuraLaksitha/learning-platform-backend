package edu.opl.backend.service;

import java.util.List;

public sealed interface CommonService<T, V>
        permits AdminService, CourseService, InstructorService, StudentService {
    T create(T t);

    T findById(final V v);

    List<T> findAll();

    void update(final T t);

    void delete(final T t);
}
