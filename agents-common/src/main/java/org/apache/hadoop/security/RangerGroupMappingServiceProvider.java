package org.apache.hadoop.security;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ranger.admin.client.RangerAdminRESTClient;
import org.apache.ranger.authorization.hadoop.config.RangerConfiguration;

public class RangerGroupMappingServiceProvider implements GroupMappingServiceProvider {

	private static final Log LOG = LogFactory.getLog(RangerGroupMappingServiceProvider.class);
	
	private RangerAdminRESTClient ret = new RangerAdminRESTClient();
	{
		RangerConfiguration.getInstance().addResourcesForServiceType("hdfs");
		ret.init("userGroup", "userGroup", "ranger.plugin.hdfs");
	}

	@Override
	public void cacheGroupsAdd(List<String> arg0) throws IOException {
		// TODO Auto-generated method stub
	}

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
		RangerGroupMappingServiceProvider p =new RangerGroupMappingServiceProvider();
		p.getGroups("aa");
	}
}
