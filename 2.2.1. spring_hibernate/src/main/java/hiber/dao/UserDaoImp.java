package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        String hql = "from User";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql,User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        String hql = "from User where car.model =: carModelParam and car.series =: carSeriesParam";
        return sessionFactory.getCurrentSession().createQuery(hql, User.class)
                .setParameter("carModelParam", model)
                .setParameter("carSeriesParam", series)
                .getSingleResult();
    }
}
