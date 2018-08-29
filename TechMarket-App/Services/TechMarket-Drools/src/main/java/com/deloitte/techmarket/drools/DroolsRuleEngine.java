package com.deloitte.techmarket.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.deloitte.techmarket.common.model.UserVO;

/**
 * KIE - Knowledge Is Everything
 * This class will perform Rule based classification of Age. 
 *
 */
public class DroolsRuleEngine {
	
	public static final String KIE_SESSION_RULE = "kiesession-rule";
	
	/**
	 * This method will return String based on the user age provided
	 * 
	 * @param UserVO
	 * @return String
	 */
	public String getCategory(UserVO user) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession(KIE_SESSION_RULE);

		kieSession.insert(user);
		kieSession.fireAllRules();
		return user.getCategory();
	}
}
