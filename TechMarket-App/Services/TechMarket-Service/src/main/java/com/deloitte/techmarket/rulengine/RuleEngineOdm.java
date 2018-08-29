package com.deloitte.techmarket.rulengine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.model.RequestJsonODM;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Qualifier("odm")
public class RuleEngineOdm implements RuleEngineInterface {

	@Override
	public String getCategory(UserVO userVo) {
		RestTemplate restTemplate = new RestTemplate();
		String getCategoryODM = CommonConstants.ODM_GET_CATEGORY_URL;
		RequestJsonODM requestJsonODM = new RequestJsonODM();
		requestJsonODM.set__DecisionID__(CommonConstants.ODM_DECISION_ID);
		Map<String, Integer> user = new HashMap<String, Integer>();
		user.put(CommonConstants.AGE_STRING, userVo.getAge());
		requestJsonODM.setUser(user);
		ResponseEntity<String> response = restTemplate.postForEntity(getCategoryODM, requestJsonODM, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode odmResponseObject = null;
		try {
			odmResponseObject = mapper.readTree(response.getBody());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (odmResponseObject != null) {
			return odmResponseObject.get("category").get("category").toString();
		}
		return null;
	}

}
