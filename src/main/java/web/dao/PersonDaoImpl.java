package web.dao;
import org.springframework.stereotype.Repository;
import web.models.Person;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao{

    @PersistenceContext
    private EntityManager entityManager;
    public List<Person> index() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();
    }

    public Person show(int id) {
        TypedQuery<Person> q = entityManager.createQuery("select p from Person p where p.id = :id", Person.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    public void save(Person person) {
        entityManager.persist(person);
    }

    public void update(int id, Person updatePerson) {

        entityManager.merge(updatePerson);
        entityManager.flush();
    }

    public void delete(int id) {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }
}
