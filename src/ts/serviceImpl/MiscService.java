package ts.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import ts.daoImpl.ClientInfoDao;
import ts.daoImpl.CustomerInfoDao;
import ts.daoImpl.ExpressSheetDao;
import ts.daoImpl.RegionDao;
import ts.daoImpl.TransNodeDao;
import ts.model.ClientInfo;
import ts.model.CodeNamePair;
import ts.model.CustomerInfo;
import ts.model.ExpressSheet;
import ts.model.Region;
import ts.model.TransNode;
import ts.serviceInterface.IMiscService;

public class MiscService implements IMiscService {
	// TransNodeCatalog nodes;
	// RegionCatalog regions;
	private TransNodeDao transNodeDao;
	private RegionDao regionDao;
	private CustomerInfoDao customerInfoDao;
	private ClientInfoDao clientInfoDao;
	private ExpressSheetDao expressSheetDao;
	public ExpressSheetDao getExpressSheetDao() {
		return expressSheetDao;
	}

	public void setExpressSheetDao(ExpressSheetDao expressSheetDao) {
		this.expressSheetDao = expressSheetDao;
	}

	public ClientInfoDao getClientInfoDao() {
		return clientInfoDao;
	}

	public void setClientInfoDao(ClientInfoDao clientInfoDao) {
		this.clientInfoDao = clientInfoDao;
	}

	public TransNodeDao getTransNodeDao() {
		return transNodeDao;
	}

	public void setTransNodeDao(TransNodeDao dao) {
		this.transNodeDao = dao;
	}

	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}

	public CustomerInfoDao getCustomerInfoDao() {
		return customerInfoDao;
	}

	public void setCustomerInfoDao(CustomerInfoDao dao) {
		this.customerInfoDao = dao;
	}

	public MiscService() {
//		nodes = new TransNodeCatalog();
//		nodes.Load();
//		regions = new RegionCatalog();
//		regions.Load();
	}

	@Override
	public TransNode getNode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransNode> getNodesList(String regionCode, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerInfo> getCustomerListByName(String name) {
//		List<CustomerInfo> listci = customerInfoDao.findByName(name);
//		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
//		for(CustomerInfo ci : listci){
//			CodeNamePair cn = new CodeNamePair(String.valueOf(ci.getID()),ci.getName());
//			listCN.add(cn);
//		}
//		return listCN;
		return customerInfoDao.findByName(name);
	}

	@Override
	public List<CustomerInfo> getCustomerListByTelCode(String TelCode) {
//		List<CustomerInfo> listci = customerInfoDao.findByTelCode(TelCode);
//		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
//		for(CustomerInfo ci : listci){
//			CodeNamePair cn = new CodeNamePair(String.valueOf(ci.getID()),ci.getName());
//			listCN.add(cn);
//		}
//		return listCN;
		return customerInfoDao.findByTelCode(TelCode);
	}

	@Override
	public Response getCustomerInfo(String id) {
		CustomerInfo cstm = customerInfoDao.get(Integer.parseInt(id));
//		try{
//			cstm.setRegionString(regionDao.getRegionNameByID(cstm.getRegionCode()));	//�ⲿ�ֹ��ܷŵ�DAO��ȥ��
//		}catch(Exception e){}
		return Response.ok(cstm).header("EntityClass", "CustomerInfo").build();
	}

	@Override
	public Response deleteCustomerInfo(int id) {
		customerInfoDao.removeById(id);
		return Response.ok("Deleted").header("EntityClass", "D_CustomerInfo").build();
	}

	@Override
	public Response saveCustomerInfo(CustomerInfo obj) {
		try {
			customerInfoDao.save(obj);
			return Response.ok(obj).header("EntityClass", "R_CustomerInfo").build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public List<CodeNamePair> getProvinceList() {
		List<Region> listrg = regionDao.getProvinceList();
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for (Region rg : listrg) {
			CodeNamePair cn = new CodeNamePair(rg.getORMID(), rg.getPrv());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public List<CodeNamePair> getCityList(String prv) {
		List<Region> listrg = regionDao.getCityList(prv);
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for (Region rg : listrg) {
			CodeNamePair cn = new CodeNamePair(rg.getORMID(), rg.getCty());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public List<CodeNamePair> getTownList(String city) {
		List<Region> listrg = regionDao.getTownList(city);
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for (Region rg : listrg) {
			CodeNamePair cn = new CodeNamePair(rg.getORMID(), rg.getTwn());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public String getRegionString(String code) {
		return regionDao.getRegionNameByID(code);
	}

	@Override
	public Region getRegion(String code) {
		return regionDao.getFullNameRegionByID(code);
	}

	@Override
	public void CreateWorkSession(int uid) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean doLogin(int uid, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doLogOut(int uid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void RefreshSessionList() {
		// TODO Auto-generated method stub

	}
	
	
	//新增获取客户列表
	@Override
	public List<ClientInfo> getClientList() {
//		List<ClientInfo> clients = new ArrayList<>();
//		clients = clientInfoDao.getClientList();
//		return Response.ok(clients).header("EntityClass", "ClientInfo").build();
		return clientInfoDao.getAll();
		
	}
	
	//登录判断是否存在账户若不存在则保存账户
	@Override
	public ClientInfo login(String telphone) {

		ClientInfo client=new ClientInfo();
		boolean isExist=clientInfoDao.isExist(telphone);
		if(!isExist) {
			client.setCname("匿名用户");
			client.setTelphone(telphone);
			clientInfoDao.save(client);			
		}else {
			client=clientInfoDao.getClientByTelphone(telphone);
		}
		return client;
			
	}

	//忘记密码，重新输入，更新clientInfo
	@Override
	public void resetPassword(String telphone,String password) {
		ClientInfo client=clientInfoDao.getClientByTelphone(telphone);
		client.setPassword(password);
		clientInfoDao.save(client);
	}

	@Override
	public List<CustomerInfo> getCustomerListByClient(int cid) {
		return customerInfoDao.findByClient(cid);
	}

	@Override
	public Response saveClient(ClientInfo client) {
		try {
			clientInfoDao.save(client);
			return Response.ok(client).header("EntityClass", "R_CLIENTINFO").build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public ClientInfo getClientById(int cid) {
		return clientInfoDao.get(cid);
	}

	@Override
	public ClientInfo resetClientNameById(int cid, String name) {
		ClientInfo client = clientInfoDao.get(cid);
		client.setCname(name);
		return client;
	}


}
