package test;

import org.junit.Test;

import ts.daoImpl.ClientInfoDao;
import ts.model.ClientInfo;

public class ClientTest {

	@Test
	public void test1() {
		ClientInfo client = new ClientInfo();
		client.setCid(3);
		client.setCname("ccc");
		client.setPassword("111");
		client.setTelphone("13137100076");
		ClientInfoDao clientInfoDao = new ClientInfoDao();
		System.out.println(client);
		clientInfoDao.save(client);
	}

}
