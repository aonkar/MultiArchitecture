package com.deloitte.techmarket.rulengine;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.drools.DroolsRuleEngine;

@Component
@Qualifier("drools")
public class RuleEngineDrools implements RuleEngineInterface{

	@Override
	public String getCategory(UserVO userVo) {
		DroolsRuleEngine rule = new DroolsRuleEngine();
		String category = rule.getCategory(userVo);
		return category;
	}

}
