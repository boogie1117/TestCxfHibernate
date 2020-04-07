package ts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Appointment")
@XmlRootElement(name="Appointment")
public class Appointment implements Serializable{

	private static final long serialVersionUID = -7525129022427770282L;
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_APPOINTMENT_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_APPOINTMENT_ID_GENERATOR", strategy="native")	
	private int id;
	
	@ManyToOne(targetEntity=ClientInfo.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="cid", referencedColumnName="cid", nullable=false) })	
	private ClientInfo clientInfo;
	
	@ManyToOne(targetEntity=CustomerInfo.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="Sender", referencedColumnName="ID") })	
	private CustomerInfo sender;
	
	@ManyToOne(targetEntity=CustomerInfo.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="Receiver", referencedColumnName="ID") })	
	private CustomerInfo receiver;
	
	@Column(name="weight", nullable=false ,length=4)	
	private Float weight;
	
	@Column(name="type", nullable=false)	
	private int type;

	@Column(name="Status", nullable=true, length=4)	
	private Integer status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public CustomerInfo getSender() {
		return sender;
	}

	public void setSender(CustomerInfo sender) {
		this.sender = sender;
	}

	public CustomerInfo getRecever() {
		return receiver;
	}

	public void setRecever(CustomerInfo recever) {
		this.receiver = recever;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

//	@Override
//	public String toString() {
//		return "Appointment [id=" + id + ", clientInfo=" + clientInfo + ", sender=" + sender + ", recever=" + recever
//				+ ", weight=" + weight + ", type=" + type + "]";
//	}



	@Transient	
	private boolean _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public boolean isSaved() {
		return _saved;
	}
	
	public static final class STATUS{
		public static final int STATUS_CREATED = 0;//未取件
		public static final int STATUS_RECEIVED = 1;//已取件可删除预约单
	}
}
