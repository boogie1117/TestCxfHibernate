package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.CustomerInfo;
import ts.model.ExpressSheet;

public class ExpressSheetDao extends BaseDao<ExpressSheet,String> {
	private RegionDao regionDao;
	private CustomerInfoDao customerInfoDao;
	public CustomerInfoDao getCustomerInfoDao() {
		return customerInfoDao;
	}
	public void setCustomerInfoDao(CustomerInfoDao customerInfoDao) {
		this.customerInfoDao = customerInfoDao;
	}
	public RegionDao getRegionDao() {
		return regionDao;
	}
	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}

	public ExpressSheetDao(){
		super(ExpressSheet.class);
	}

	//��д��get����,���ͻ��������ַ�������
	public ExpressSheet get(String id) {
		ExpressSheet es = super.get(id);
		CustomerInfo ci = es.getRecever();
		if(ci!= null)
			ci.setRegionString(regionDao.getRegionNameByID(ci.getRegionCode()));	//��ȡ����������ַ���
		CustomerInfo cs = es.getSender();
		if(cs != null) 
			cs.setRegionString(regionDao.getRegionNameByID(cs.getRegionCode()));	//��ȡ����������ַ���
		return es;
	}

	//���ָ������ID�����п���б�
	public List<ExpressSheet> getListInPackage(String pkg_id) {	
		String sql = "{alias}.ID in (select ExpressID from TransPackageContent where Status = 0 and PackageID = '"+pkg_id+"')";
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		list = findBy("ID", true, Restrictions.sqlRestriction(sql));		
		return list;
	}
	//获得指定收件人的所有快件列表
		public List<ExpressSheet> getReceverListExpressSheet(Integer cid) {	
			String sql = "{alias}.ID in (select ID from ExpressSheet where Recever = '"+cid+"')";
			List<ExpressSheet> list = new ArrayList<ExpressSheet>();
			list = findBy("ID", true, Restrictions.sqlRestriction(sql));		
			return list;
		}
		//获得指定寄件人的所有快件列表
		public List<ExpressSheet> getSentListExpressSheet(Integer cid) {	
			String sql = "{alias}.ID in (select ID from ExpressSheet where Senter = '"+cid+"')";
			List<ExpressSheet> list = new ArrayList<ExpressSheet>();
			list = findBy("ID", true, Restrictions.sqlRestriction(sql));		
			return list;
		}
		//通过电话号查看该手机号的要接收或已接收的快件
		public List<ExpressSheet> getReceiveListByTel(String tel){

//			List<CustomerInfo> customers=new ArrayList<>();
//			System.out.println("****");
//			customers=customerInfoDao.findByTelCode(tel);
//			for(CustomerInfo customer:customers) {
//				System.out.println(customer+"...");
//			}
			List<ExpressSheet> list = new ArrayList<ExpressSheet>();
			List<ExpressSheet> listAll = new ArrayList<ExpressSheet>();	
//			for(CustomerInfo customer:customers) {
//				list=getReceverListExpressSheet(customer.getID());
//				listAll.addAll(list);
//			}
			list=getAll();
			for(ExpressSheet ep :list) {
				if(tel.equals(ep.getRecever().getTelCode())) {
					listAll.add(ep);
				}
			}
			return listAll;
		}
		//通过电话号查看该手机号的寄出的快件
				public List<ExpressSheet> getSendListByTel(String tel){

//					List<CustomerInfo> customers=new ArrayList<>();
					System.out.println("****");
//					customers=customerInfoDao.findByTelCode(tel);
//					for(CustomerInfo customer:customers) {
//						System.out.println(customer+"...");
//					}
					List<ExpressSheet> list = new ArrayList<ExpressSheet>();
					List<ExpressSheet> listAll = new ArrayList<ExpressSheet>();	
					list=getAll();
					for(ExpressSheet ep :list) {
						if(tel.equals(ep.getSender().getTelCode())) {
							listAll.add(ep);
						}
					}
//					for(CustomerInfo customer:customers) {
//						list=getSentListExpressSheet(customer.getID());
//						listAll.addAll(list);
//					}
					return listAll;
				}
		//通过电话号码获取订单列表
		public List<ExpressSheet> getExpressListByTel(String tel) {	
//			List<CustomerInfo> customers=new ArrayList<>();
//			System.out.println("****");
//			customers=customerInfoDao.findByTelCode(tel);
//			for(CustomerInfo customer:customers) {
//				System.out.println(customer+"...");
//			}
			List<ExpressSheet> list = new ArrayList<ExpressSheet>();	
			List<ExpressSheet> listAll = new ArrayList<ExpressSheet>();	
//			for(CustomerInfo customer:customers) {
//				list=getReceverListExpressSheet(customer.getID());
//				listAll.addAll(list);//把这个手机号收到的或要收到的快递单加进去
//				list=getSentListExpressSheet(customer.getID());
//				listAll.addAll(list);//把这个手机号寄出的快递单加进去
//			}
			list=getAll();
			for(ExpressSheet ep:list) {
				System.out.println(ep+"000");
				System.out.println(ep.getSender().getTelCode());
				System.out.println(tel);
			}
			for(ExpressSheet ep :list) {
				if(tel.equals(ep.getSender().getTelCode())||tel.equals(ep.getRecever().getTelCode())) {
					listAll.add(ep);
				}
			}
			return listAll;
		}
}
