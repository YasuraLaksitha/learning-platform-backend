package edu.opl.backend.service;

import java.util.List;

public sealed interface CommonService<T, V>
        permits AdminService, AssignmentService, CourseService, InstructorService, StudentService, RoleService {
    T create(T t);

    T findById(final V v);

    List<T> findAll();

    T update(final T t);

    void delete(final T t);
}
