package rolo;

import api.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the urro database table.
 * 
 */
@Entity
@Table(schema = "ROLO")
@NamedQuery(name="Urro.findAll", query="SELECT u FROM Urro u")
public class Urro extends AbstractEntity<UrroId> {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UrroId id;

	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @JsonBackReference
    private Role role;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

	public Urro() {
	}

	public UrroId getId() {
		return this.id;
	}

	public void setId(UrroId id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}