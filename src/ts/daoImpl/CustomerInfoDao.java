package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.Appointment;
import ts.model.CustomerInfo;

public class CustomerInfoDao extends BaseDao<CustomerInfo, Integer>{
	private RegionDao regionDao;
	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}
	
	public CustomerInfoDao(){
		super(CustomerInfo.class);
	}
	
	public CustomerInfo get(int id) {
		CustomerInfo ci = super.get(id);
		ci.setRegionString(regionDao.getRegionNameByID(ci.getRegionCode()));	//��ȡ����������ַ���
		return ci;
	}

	public List<CustomerInfo> findByName(String name) {
		return findLike("name", name+"%", "telCode", true);
	}

	public List<CustomerInfo> findByTelCode(String telCode) {
		return findBy("telCode", telCode, "telCode", true);
	}
	public List<CustomerInfo> findByClient(int cid) {
		List<CustomerInfo> customers=new ArrayList<>();
		List<CustomerInfo> list=new ArrayList<>();
		customers=getAll();
		for(CustomerInfo customer:customers) {
			if(customer.getClient().getCid()==cid) {
				list.add(customer);
			}
		}
		return list;
	}
}
