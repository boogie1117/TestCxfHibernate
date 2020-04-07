package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.Appointment;
import ts.model.CustomerInfo;
import ts.model.ExpressSheet;

public class AppointmentDao extends BaseDao<Appointment, Integer>{

	private RegionDao regionDao;
	public RegionDao getRegionDao() {
		return regionDao;
	}
	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}
	
	public AppointmentDao() {
		super(Appointment.class);
	}
	
	//重写的get方法,将客户的区域字符串加入
		public Appointment get(int id) {
			Appointment app = super.get(id);
			CustomerInfo ci = app.getRecever();
			if(ci!= null)
				ci.setRegionString(regionDao.getRegionNameByID(ci.getRegionCode()));	//获取区域的名字字符串
			CustomerInfo cs = app.getSender();
			if(cs != null) 
				cs.setRegionString(regionDao.getRegionNameByID(cs.getRegionCode()));	//获取区域的名字字符串
			return app;
		}
		
		/**
		 * 根据预约单号(id)获取预约单的信息
		 */
		public Appointment getAppointmentById(Integer id) {
			Appointment app=new Appointment();
			app= super.get(id);
			return app;
		}
		/**
		 * 根据客户(cid)获取预约单的信息
		 */
		public List<Appointment> getAppointmentByCId(Integer cid) {
					
//			String sql = "{alias}.CID in (select cid from appointment where cid = "+cid+")";
			List<Appointment> list = new ArrayList<Appointment>();
			List<Appointment> list2 = new ArrayList<Appointment>();
//			list = findBy("CID", true, Restrictions.sqlRestriction(sql));		
//			list = findBy("cid",cid,"cid",true);		
//			list = findBy("cid", cid, "cid", true);	
			list=getAll();
			for(Appointment app :list) {
				if(app.getClientInfo().getCid()==cid) {
					list2.add(app);
				}
			}
			return list2;
		}
}
