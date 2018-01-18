package test;

import rolo.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class testo {
    EntityManager em = null;
    public testo() {
        em = Persistence.createEntityManagerFactory("jpaRolo").createEntityManager();
    }

    public void SelCol(){
        List<User> l = null;
        try{
            l = em.createNamedQuery("User.findAll", User.class).getResultList();
            for(User c: l){
                System.out.println(c.getId() + " " + c.getName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testo o = new testo();
        o.SelCol();
    }

}
