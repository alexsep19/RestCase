package rolo;

import javax.persistence.*;

import api.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.jboss.resteasy.util.Base64;
import java.security.MessageDigest;
import java.util.List;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users", schema = "ROLO")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
	@SequenceGenerator(name = "user_seq_gen", sequenceName = "rolo.users_id_seq",allocationSize=1)
	private Long id;
	private String mail;
	private String name;
	private String pass;

	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	@JsonBackReference
	private List<Urro> urros;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		if (!pass.trim().isEmpty()){
		  try{
		   MessageDigest md = MessageDigest.getInstance("SHA-256");
		   this.pass = Base64.encodeBytes(md.digest(pass.getBytes()));
		  }catch(Exception e){}
		}
	}

	public List<Urro> getUrros() {
		return this.urros;
	}

	public void setUrros(List<Urro> urros) {
		this.urros = urros;
	}

//	public Urro addUrro(Urro urro) {
//		getUrros().add(urro);
//		urro.setUser(this);
//
//		return urro;
//	}
//
//	public Urro removeUrro(Urro urro) {
//		getUrros().remove(urro);
//		urro.setUser(null);
//
//		return urro;
//	}

}