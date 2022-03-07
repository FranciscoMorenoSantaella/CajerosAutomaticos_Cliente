package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
@NamedQueries({
	@NamedQuery(name = "findClientByUsername", query = "SELECT c FROM Client c WHERE c.username=:username"),
	@NamedQuery(name = "findClientByUsernamePassword", query = "SELECT c FROM Client c WHERE c.username=:username AND c.password=:password"),
  @NamedQuery(name = "findClientsByAdmin", query = "SELECT c FROM Client c WHERE c.admin=:adminId"),
	@NamedQuery(name = "findAllClient", query = "SELECT c FROM Client c")
})
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private List<Account> accounts;
	@ManyToOne()
	@JoinColumn(name = "adminId")
	private Admin admin;

	public Client(String name, String surname, String username, String password, List<Account> accounts, Admin admin) {
		this.id = -1L;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.accounts = accounts;
		this.admin = admin;
	}

	public Client() {
		this("", "", "", "", new ArrayList<Account>(), new Admin());
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password=" + password + ", admin=" + admin + "]";
	}

}
