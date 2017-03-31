package org.apache.hadoop.security;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ranger.admin.client.RangerAdminRESTClient;
import org.apache.ranger.admin.client.datatype.VXUser;
import org.apache.ranger.authorization.hadoop.config.RangerConfiguration;
import org.apache.ranger.plugin.util.RangerRESTClient;
import org.apache.ranger.plugin.util.RangerRESTUtils;
import org.glassfish.jersey.client.ClientResponse;

public class RangerGroupMappingServiceProvider implements GroupMappingServiceProvider {

	private static final Log LOG = LogFactory.getLog(RangerGroupMappingServiceProvider.class);
	
	private RangerAdminRESTClient ret = new RangerAdminRESTClient();
	{
		RangerConfiguration.getInstance().addResourcesForServiceType("hdfs");
		ret.init("userGroup", "userGroup", "ranger.plugin.hdfs");
	}

	/**
	 * acl中使用，不需要实现
	 */
	@Override
	public void cacheGroupsAdd(List<String> arg0) throws IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * acl中使用，不需要实现
	 */
	@Override
	public void cacheGroupsRefresh() throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<String> getGroups(String arg0) throws IOException {
		try {
			return ret.getUserGroup(arg0);
		} catch (Exception e) {
			LOG.error(arg0, e);
			throw new IOException(e);
		}
	}

	
	public static void main(String[] args) throws IOException{
		RangerRESTClient restClient = new RangerRESTClient("http://ip.taobao.com/service/getIpInfo.php?", null);
		WebTarget webResource = restClient.getResource("ip=63.223.108.42");;
		Response response = webResource.request(MediaType.APPLICATION_JSON_TYPE).get();
		System.out.println(response.readEntity(VXUser.class));
		
		RangerGroupMappingServiceProvider p =new RangerGroupMappingServiceProvider();
		p.getGroups("aa");
	}
	
	
}
