package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.ClientInfo;

public class ClientInfoDao extends BaseDao<ClientInfo, Integer> {

	public ClientInfoDao() {
		super(ClientInfo.class);
	}

	public List<ClientInfo> getClientList() {
		List<ClientInfo> clients = new ArrayList<>();
		clients = getAll();
		return clients;
	}

	public ClientInfo getClientByTelphone(String telphone) {
		List<ClientInfo> clients = findBy("telphone", telphone, "cid", true);
		return clients.get(0);
	}
	
	public boolean isExist(String telphone) {
		List<ClientInfo> clients = findBy("telphone", telphone, "cid", true);
		if (clients.isEmpty()) {
			return false;
		}
		return true;
	}

	public String isCheck(String telphone, String password) {

		List<ClientInfo> client = findBy("telphone", telphone, "cid", true);
		if (client.isEmpty() || client.size() > 1)
			return "error";
		String password1 = client.get(0).getPassword();
		if (password1.equals(password)) {
			String name1 = client.get(0).getCname();
			return name1;

		}
		return "error";
	}
}
