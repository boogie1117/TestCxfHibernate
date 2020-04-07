package ts.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
//@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "ClientInfo")
@XmlRootElement(name = "ClientInfo")
public class ClientInfo implements Serializable {

	private static final long serialVersionUID = -2067152606765151497L;

	public ClientInfo() {
	}
	
	
//	@GeneratedValue(generator = "MODEL_CLIENTINFO_CID_GENERATOR")
//	@org.hibernate.annotations.GenericGenerator(name = "MODEL_CLIENTINFO_CID_GENERATOR", strategy = "native")
	@Column(name = "cid", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;


	@Column(name = "password", nullable = true, length = 50)
	private String password;

	@Column(name = "telphone", nullable = true, length = 11)
	private String telphone;

	@Column(name = "cname", nullable = true, length = 20)
	private String cname;
	

//	@OneToMany(targetEntity = CustomerInfo.class)
//	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//			org.hibernate.annotations.CascadeType.LOCK })
//	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
//	private java.util.Set<CustomerInfo> customerInfo = new java.util.HashSet<CustomerInfo>();

//	@OneToMany(targetEntity = Appointment.class)
//	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//			org.hibernate.annotations.CascadeType.LOCK })
//	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
//	private java.util.Set<Appointment> appointment = new java.util.HashSet<Appointment>();

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
//
//	public void setCustomerInfo(java.util.Set<CustomerInfo> value) {
//		this.customerInfo = value;
//	}
//
//	public java.util.Set<CustomerInfo> getCustomerInfo() {
//		return customerInfo;
//	}

//	public void setAppointment(java.util.Set<Appointment> value) {
//		this.appointment = value;
//	}
//
//	public java.util.Set<Appointment> getAppointment() {
//		return appointment;
//	}

	@Override
	public String toString() {
		return "ClientInfo [cid=" + cid + ", password=" + password + ", telphone=" + telphone + ", cname=" + cname
				+ "]";
	}

	@Transient
	private boolean _saved = false;

	public void onSave() {
		_saved = true;
	}

	public void onLoad() {
		_saved = true;
	}

	public boolean isSaved() {
		return _saved;
	}

}
