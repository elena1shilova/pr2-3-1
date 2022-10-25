package web.dao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.Person;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
//@Repository
@Component
@Transactional(value = "transactionManager", readOnly = true)
public class PersonDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;
    public List<Person> index() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();
    }

    public Person show(int id) {
        TypedQuery<Person> q = entityManager.createQuery("select p from Person p where p.id = :id", Person.class);
        q.setParameter("id", id);
        //entityManager.flush();
        return q.getResultList().stream().findAny().orElse(null);
    }
    @Transactional("transactionManager")
    public void save(Person person) {
        entityManager.persist(person);
    }
    @Transactional("transactionManager")
    public void update(int id, Person updatePerson) {
        entityManager.find(Person.class, id);
        entityManager.merge(updatePerson);
    }
    @Transactional("transactionManager")
    public void delete(int id) {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }
}
