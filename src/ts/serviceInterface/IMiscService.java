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

import ts.model.ClientInfo;
import ts.model.CodeNamePair;
import ts.model.CustomerInfo;
import ts.model.ExpressSheet;
import ts.model.Region;
import ts.model.TransNode;

@Path("/Misc")
public interface IMiscService {
	

	//根据clientId获取clientInfo
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getClientById/{cid}")
	public ClientInfo getClientById(@PathParam("cid") int cid);
	
	//修改用户名称
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getClientById/{cid}/{name}")
	public ClientInfo resetClientNameById(@PathParam("cid") int cid,@PathParam("name") String name);
	
	//客户登录
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/login/{telphone}")
	public ClientInfo login(@PathParam("telphone") String telphone);
	
	//客户忘记密码
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/resetPassword/{telphone}/{password}")              
	public void resetPassword(@PathParam("telphone") String telphone, @PathParam("password") String password);
	
	

	// 获取客户列表
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getClientList")
	public List<ClientInfo> getClientList();
	
	//保存客户
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveClient")
	public Response saveClient(ClientInfo client);
	
	// 通过name获取客户地址列表
		@GET
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		@Path("/getCustomerListByClient/{cid}")
		public List<CustomerInfo> getCustomerListByClient(@PathParam("cid") int cid);

	// 通过NodeCode网点码来获取某个网点,ID
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getNode/{NodeCode}")
	public TransNode getNode(@PathParam("NodeCode") String code);

	// 通过regionCode和type来获取某一地点的某类网点，其实是根据regionCode获取了region集合，数据库中type都是0
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getNodesList/{RegionCode}/{Type}")
	public List<TransNode> getNodesList(@PathParam("RegionCode") String regionCode, @PathParam("Type") int type);

	// ===============================================================================================
	// 通过name获取客户列表（模糊查询？）
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCustomerListByName/{name}")
	public List<CustomerInfo> getCustomerListByName(@PathParam("name") String name);

	// 通过TelCode获取客户列表
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCustomerListByTelCode/{TelCode}")
	public List<CustomerInfo> getCustomerListByTelCode(@PathParam("TelCode") String TelCode);

	// 通过id获取客户
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCustomerInfo/{id}")
	public Response getCustomerInfo(@PathParam("id") String id);

	// 通过id删除客户
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/deleteCustomerInfo/{id}")
	public Response deleteCustomerInfo(@PathParam("id") int id);

	// 保存客户信息
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveCustomerInfo")
	public Response saveCustomerInfo(CustomerInfo obj);

	// ===============================================================================================
	// 获取省列表
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getProvinceList")
	public List<CodeNamePair> getProvinceList();

	// 获取城市列表
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCityList/{prv}")
	public List<CodeNamePair> getCityList(@PathParam("prv") String prv);

	// 获取城镇列表
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getTownList/{city}")
	public List<CodeNamePair> getTownList(@PathParam("city") String city);

	// 通过id获取地域的String
	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Path("/getRegionString/{id}")
	public String getRegionString(@PathParam("id") String id);

	// 通过id获取地域对象
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getRegion/{id}")
	public Region getRegion(@PathParam("id") String id);

	// ===============================================================================================
	// 创建工作Session
	public void CreateWorkSession(int uid);

	// 登录（使用用户id和密码）
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/doLogin/{uid}/{pwd}")
	public boolean doLogin(@PathParam("uid") int uid, @PathParam("pwd") String pwd);

	// 退出（使用用户id）
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/doLogOut/{uid}")
	public void doLogOut(@PathParam("uid") int uid);

	public void RefreshSessionList();
}
