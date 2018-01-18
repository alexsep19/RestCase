package dao;

import dao.api.AbstractEntityDao;
import rolo.User;
import javax.ejb.Stateless;

@Stateless
public class UserDao extends AbstractEntityDao<Long, User> {

    public UserDao() {
        super(User.class);
    }
}
