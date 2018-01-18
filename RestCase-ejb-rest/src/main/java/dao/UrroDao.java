package dao;

import dao.api.AbstractEntityDao;
import rolo.Urro;
import rolo.UrroId;

import javax.ejb.Stateless;

@Stateless
public class UrroDao extends AbstractEntityDao<UrroId, Urro> {

    public UrroDao() {
        super(Urro.class);
    }
}
