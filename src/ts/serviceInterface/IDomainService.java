package ts.serviceInterface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ts.model.Appointment;
import ts.model.ExpressSheet;
import ts.model.TransPackage;

@Path("/Domain")	//业务操作
public interface IDomainService {

	
	//创建预约单并保存
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveAppointment") 
	public Response saveAppointment(Appointment app);
	
  //修改预约单信息
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateAppointment") 
	public Response updateAppointment(Appointment app);
	
  //通过预约单号删除预约单(规定快件状态后可用数据库的触发器删除)
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deleteAppointment/{id}") 
	public Response deleteAppointment(@PathParam("id")int id);
    
  //通过客户获取预约单列表
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getAppointmentListByClient/{cid}") 
	public List<Appointment> getAppointmentListByClient(@PathParam("cid")Integer cid);
    
    //通过预约单号获取预约单列表
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getAppointmentListById/{id}") 
    public Appointment getAppointmentListById(@PathParam("id")Integer id);
    
	//通过电话号码获取订单列表
	  @GET
	    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	    @Path("/getExpressListByTel/{tel}") 
	   	public List<ExpressSheet> getExpressListByTel(@PathParam("tel")String tel);
	
//sender客户查询本人的寄件
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getMyReceiveExpressSheet/{telCode}") 
   	public List<ExpressSheet> getMyReceiveExpressSheet(@PathParam("telCode")String telCode);

    
    //receive客户查询本人的收件
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getMySentExpressSheet/{telCode}") 
   	public List<ExpressSheet> getMySentExpressSheet(@PathParam("telCode")String telCode);
    
    
    
    
    //快件操作访问接口=======================================================================
	//获取快件列表（{Property}/{Restrictions}/{Value}）
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressList/{Property}/{Restrictions}/{Value}") 
	public List<ExpressSheet> getExpressList(@PathParam("Property")String property, @PathParam("Restrictions")String restrictions, @PathParam("Value")String value);

    //获取某包裹中的快件列表（包裹ID）
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressListInPackage/PackageId/{PackageId}") 
	public List<ExpressSheet> getExpressListInPackage(@PathParam("PackageId")String packageId);

    //获取包裹表单（表单ID）
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressSheet/{id}") 
	public ExpressSheet getExpressSheet(@PathParam("id")String id);

    //newExpressSheet（id和uid）
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/newExpressSheet/id/{id}/uid/{uid}") 
	public Response newExpressSheet(@PathParam("id")String id, @PathParam("uid")int uid);
    
    //将ExpressSheet对象持久化
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/saveExpressSheet") 
	public Response saveExpressSheet(ExpressSheet obj);
    
    //sender
    //receive
    
    //收取ExpressSheet的表单（id和uid），返回结果给Response
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/receiveExpressSheetId/id/{id}/uid/{uid}") 
	public Response ReceiveExpressSheetId(@PathParam("id")String id, @PathParam("uid")int uid);
    
    //发送
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/dispatchExpressSheetId/id/{id}/uid/{uid}") 
	public Response DispatchExpressSheet(@PathParam("id")String id, @PathParam("uid")int uid);
    
    //分拣
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deliveryExpressSheetId/id/{id}/uid/{uid}") 
	public Response DeliveryExpressSheetId(@PathParam("id")String id, @PathParam("uid")int uid);

    //包裹操作访问接口=======================================================================
    //获取到包裹列表
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getTransPackageList/{Property}/{Restrictions}/{Value}") 
	public List<TransPackage> getTransPackageList(@PathParam("Property")String property, @PathParam("Restrictions")String restrictions, @PathParam("Value")String value);

    //通过id获取包裹
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getTransPackage/{id}") 
	public Response getTransPackage(@PathParam("id")String id);

    //新增包裹，id和uid
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/newTransPackage") 
    public Response newTransPackage(String id, int uid);

    //持久化包裹对象
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveTransPackage") 
	public Response saveTransPackage(TransPackage obj);
    
}
