package com.king.automata.rules;

public class RuleSetFactory {

	private static RuleSet ruleSet;
	
	static {
		ruleSet = new DefaultRuleSet();
	}
	
	private RuleSetFactory(){
		
	}
	
	public static RuleSet getRuleSet() {
		return ruleSet;
	}
}
