package observer;

import java.sql.SQLException;
import java.util.List;

import domain.model.Book;

public interface Subscriber {
    public void update() throws SQLException;
}
