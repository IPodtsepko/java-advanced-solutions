package info.kgeorgiy.ja.Podtsepko.student;

import info.kgeorgiy.java.advanced.student.GroupName;
import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentQuery;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDB implements StudentQuery {
    private static final Comparator<? super Student> COMPARING_BY_NAME = Comparator
            .comparing(Student::getLastName)
            .thenComparing(Student::getFirstName)
            .reversed()
            .thenComparing(Student::getId);

    private <T> Stream<T> getStudentsPropertiesStream(List<Student> students, Function<Student, T> mapper) {
        return students.stream().map(mapper);
    }

    private <T> List<T> getStudentsPropertiesList(List<Student> students, Function<Student, T> mapper) {
        return getStudentsPropertiesStream(students, mapper).toList();
    }

    private Stream<String> getFirstNamesStream(List<Student> students) {
        return getStudentsPropertiesStream(students, Student::getFirstName);
    }

    @Override
    public List<String> getFirstNames(List<Student> students) {
        return getFirstNamesStream(students).toList();
    }

    @Override
    public List<String> getLastNames(List<Student> students) {
        return getStudentsPropertiesList(students, Student::getLastName);
    }

    @Override
    public List<GroupName> getGroups(List<Student> students) {
        return getStudentsPropertiesList(students, Student::getGroup);
    }

    private static String getStudentFullName(Student student) {
        return String.format("%s %s", student.getFirstName(), student.getLastName());
    }

    @Override
    public List<String> getFullNames(List<Student> students) {
        return getStudentsPropertiesList(students, StudentDB::getStudentFullName);
    }

    @Override
    public Set<String> getDistinctFirstNames(List<Student> students) {
        return getFirstNamesStream(students).collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public String getMaxStudentFirstName(List<Student> students) {
        return students
                .stream()
                .max(Comparator.comparingInt(Student::getId))
                .map(Student::getFirstName)
                .orElse("");
    }

    private Stream<Student> getOrderedBy(Collection<Student> students, Comparator<? super Student> comparator) {
        return students.stream().sorted(comparator);
    }

    private Stream<Student> getOrderedByName(Collection<Student> students) {
        return getOrderedBy(students, COMPARING_BY_NAME);
    }

    @Override
    public List<Student> sortStudentsById(Collection<Student> students) {
        return getOrderedBy(students, Comparator.comparing(Student::getId)).toList();
    }

    @Override
    public List<Student> sortStudentsByName(Collection<Student> students) {
        return getOrderedByName(students).toList();
    }

    public <T> Stream<Student> getFilteredStream(Collection<Student> students, Function<Student, T> getter, T value) {
        return getOrderedByName(students.stream().filter(student -> Objects.equals(getter.apply(student), value)).toList());
    }

    public <T> List<Student> getFilteredList(Collection<Student> students, Function<Student, T> getter, T value) {
        return getFilteredStream(students, getter, value).toList();
    }

    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
        return getFilteredList(students, Student::getFirstName, name);
    }

    @Override
    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
        return getFilteredList(students, Student::getLastName, name);
    }

    @Override
    public List<Student> findStudentsByGroup(Collection<Student> students, GroupName group) {
        return getFilteredList(students, Student::getGroup, group);
    }

    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, GroupName group) {
        return getFilteredStream(students, Student::getGroup, group)
                .collect(Collectors.toMap(
                        Student::getLastName,
                        Student::getFirstName,
                        BinaryOperator.minBy(Comparator.naturalOrder())
                ));
    }
}
