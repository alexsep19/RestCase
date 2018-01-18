package rolo;

import api.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.Arrays;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles", schema = "ROLO")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq_gen")
	@SequenceGenerator(name = "roles_seq_gen", sequenceName = "rolo.roles_id_seq",allocationSize=1)
	private Long id;
	private String code;

	//bi-directional many-to-one association to Urro
	@OneToMany(mappedBy="role", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	@JsonBackReference
	private List<Urro> urros;

	public Role() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Urro> getUrros() {
		return this.urros;
	}

	public void setUrros(List<Urro> urros) {
		this.urros = urros;
	}

//	public Urro addUrro(Urro urro) {
//		getUrros().add(urro);
//		urro.setRole(this);
//
//		return urro;
//	}
//
//	public Urro removeUrro(Urro urro) {
//		getUrros().remove(urro);
//		urro.setRole(null);
//		return urro;
//	}

}