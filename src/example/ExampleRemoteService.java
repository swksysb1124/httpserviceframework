package example;

import java.util.ArrayList;
import java.util.List;

import remoteservice.BaseRemoteService;
import request.ExecutorRequestManager;
import request.HeaderField;
import request.QueryAttribute;
import request.RequestManager;
import response.Response;
import util.JSONParserUtil;

public class ExampleRemoteService 
	extends BaseRemoteService 
	implements ExampleWebServiceAPI{

	private ExampleRemoteService() {}
	
	private static ExampleRemoteService instance;
	
	public static ExampleRemoteService getInstance() {
		if(instance == null) {
			synchronized(ExampleRemoteService.class) {
				if(instance == null) {
					instance = new ExampleRemoteService();
				}
			}
		}
		return instance;
	}
	

	@Override
	public void testGET(String value1, String value2) {
		List<QueryAttribute> rqParams = new ArrayList<>();
		rqParams.add(new QueryAttribute("key1", value1));
		rqParams.add(new QueryAttribute("key2", value2));
		
		List<HeaderField> rqProperties = new ArrayList<>();
		rqProperties.add(new HeaderField("Content-Type","application/json"));
		rqProperties.add(new HeaderField("Accept","application/json"));
		rqProperties.add(new HeaderField("User-Agent","json.app"));
		
		invoke("testGET", rqProperties, rqParams, null);
	}

	@Override
	public void testPUT(String email, String password) {
		List<HeaderField> rqProperties = new ArrayList<>();
		rqProperties.add(new HeaderField("Content-Type","application/json"));
		rqProperties.add(new HeaderField("Accept","application/json"));
		rqProperties.add(new HeaderField("User-Agent","json.app"));
		
		String body = JSONParserUtil.toJson(new UserAccount(email, password));
		
		invoke("testPUT", rqProperties, null, body);
	}

	@Override
	public void testPOST(String email, String password) {
		List<HeaderField> rqProperties = new ArrayList<>();
		rqProperties.add(new HeaderField("Content-Type","application/json"));
		rqProperties.add(new HeaderField("Accept","application/json"));
		rqProperties.add(new HeaderField("User-Agent","json.app"));
		
		String body = JSONParserUtil.toJson(new UserAccount(email, password));
		
		invoke("testPOST", rqProperties, null, body);
	}

	@Override
	public void testDELETE() {
		List<HeaderField> rqProperties = new ArrayList<>();
		rqProperties.add(new HeaderField("Content-Type","application/json"));
		rqProperties.add(new HeaderField("Accept","application/json"));
		rqProperties.add(new HeaderField("User-Agent","json.app"));
		
		invoke("testDELETE", rqProperties, null, null);
	}

	@Override
	public void onSuccess(String key, Response response, String content) {
		if(getOnDataReceivedListener() != null) {
			getOnDataReceivedListener().onSuccess(key, content);
		}
	}

	@Override
	public void onFail(String key, Response response, int errorType, String errorMessage) {
		if(getOnDataReceivedListener() != null) {
			getOnDataReceivedListener().onFail(key, errorType, errorMessage);
		}
	}
}
