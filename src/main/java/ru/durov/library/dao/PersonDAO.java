package ru.durov.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.durov.library.model.Book;
import ru.durov.library.model.Person;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getListOfPeople() {
        return jdbcTemplate.query("SELECT * FROM library.Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> findFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM library.Person WHERE full_name=?", new BeanPropertyRowMapper<>(Person.class),
                new Object[]{fullName}).stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM library.Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class),
                new Object[]{id}).stream().findFirst().orElse(null);

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO library.Person (full_name, birth_year) VALUES (?, ?)", person.getFullName(),
                person.getBirthYear());
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE library.Person SET full_name=?, birth_year=? WHERE id=?", person.getFullName(), person.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM library.Person WHERE id=?", id);
    }

    public List<Book> getBookListByPersonId(int id) {
        return jdbcTemplate.query("SELECT title, author, year, reader_name FROM library.person JOIN library.book ON person.full_name = book.reader_name WHERE library.person.id=?",
                new BeanPropertyRowMapper<>(Book.class), new Object[]{id});
    }
}
