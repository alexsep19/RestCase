package dao;

import dao.api.AbstractEntityDao;
import rolo.Role;

import javax.ejb.Stateless;

@Stateless
public class RoleDao extends AbstractEntityDao<Long, Role> {
    public RoleDao() {super(Role.class);}
}
