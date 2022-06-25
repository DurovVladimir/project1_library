package ru.durov.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.durov.library.model.Book;
import ru.durov.library.model.Person;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getListOfBooks() {
        return jdbcTemplate.query("SELECT * FROM library.book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBookInfo(int id) {
        return jdbcTemplate.query("SELECT * FROM library.book WHERE id=?", new BeanPropertyRowMapper<>(Book.class),
                new Object[]{id}).stream().findFirst().orElse(null);

    }

    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO library.book (title, author, year) VALUES (?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }

    public void updateBook(Book book, int id) {
        jdbcTemplate.update("UPDATE library.book SET title=?, author=?, year=?, reader_name=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getReaderName(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM library.book WHERE id=?", id);
    }

    public void releaseBook(int id) {
        jdbcTemplate.update("UPDATE library.book SET reader_name=NULL WHERE id=?", id);
    }

    public void assignBook(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE library.book SET reader_name=? WHERE id=?", selectedPerson.getFullName(), id);
    }
}
