package web.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.PersonDao;
import web.dao.PersonDaoImpl;
import web.models.Person;

import java.util.List;
@Component
@Transactional
public class PersonServiceImpl implements PersonService{
    @Autowired
    private final PersonDao personDao = new PersonDaoImpl();

    @Override
    public List<Person> index() {
        return personDao.index();
    }

    @Override
    public Person show(int id) {
        return personDao.show(id);
    }

    @Override
    public void save(Person person) {
        personDao.save(person);
    }

    @Override
    public void update(int id, Person updatePerson) {
        personDao.update(id, updatePerson);
    }

    @Override
    public void delete(int id) {
        personDao.delete(id);
    }
}
