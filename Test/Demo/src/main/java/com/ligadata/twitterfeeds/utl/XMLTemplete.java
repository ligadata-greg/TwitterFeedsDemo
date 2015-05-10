package com.ligadata.twitterfeeds.utl;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

public class XMLTemplete {

	private ST st;

	private static final STGroup group = new STGroupString(

			"header(name) ::= << "
					+ "\\<PMML xmlns='http://www.dmg.org/PMML-4_1' version='4.1'\\> \n"
					+ "\\<Header copyright='LigaDATA Copyright 2015' description='Plus Functionality Test'\\>\n"
					+ "\\<Application name='<name>' version='00.01.00'/\\>\n"
					+ "\\</Header\\>\n"
					+ ">>"
					+ "DD() ::= << "
					+ "\\<DataDictionary numberOfFields='7'> /n"
					+ "\\<DataField name='msg' displayName='msg' optype='categorical' dataType='PlusIntIntTestMsg'/\\> \n"
					+ "\\<DataField name='gCtx' displayName='globalContext' optype='categorical' dataType='EnvContext'/\\> \n"
					+ "\\<DataField name='parameters' displayName='parameters' dataType='container'> \n"
					+ "\\<Value value='gCtx' property='valid' /\\> \n"
					+ "\\<Value value='msg' property='valid'/\\> \n"
					+ "\\</DataField>\n"
					+ "\\<DataField name='integer1' displayName='integer1' optype='categorical' dataType='integer'/\\> \n"
					+ "\\<DataField name='integer2' displayName='integer2' optype='categorical' dataType='integer'/\\> \n"
					+ "\\<DataField name='result' displayName='result' optype='categorical' dataType='integer'/\\> \n"
					+ "\\<DataField name='TestHit' displayName='TestHit' optype='categorical' dataType='integer'/\\> \n"
					+ "\\</DataDictionary>\n"
					+ ">>"
					+ "TD() ::= <<"
					+ "\\<TransformationDictionary>\n"
					+ "\\<DerivedField name='PlusIntIntResult' dataType='integer' optype='categorical'>\n"
					+ "\\<Apply function='+'>\n"
					+ "\\<FieldRef field='msg.integer1'/>\n"
					+ "\\<FieldRef field='msg.integer2'/>\n"
					+ "\\</Apply>\n"
					+ "\\</DerivedField>\n"
					+ "\\<DerivedField name='PlusIntIntTest' dataType='boolean' optype='categorical'>\n"
					+ "\\<Apply function='and'>\n"
					+ "\\<Apply function='Put'>\n"
					+ "\\<Constant dataType='string'>result\\</Constant>\n"
					+ "\\<FieldRef field='PlusIntIntResult'/>\n"
					+ "\\</Apply>"
					+ "\\<Apply function='Put'>"
					+ "\\<Constant dataType='string'>integer1\\</Constant>"
					+ "\\<FieldRef field='msg.integer1'/>"
					+ "\\</Apply>\n"
					+ "\\<Apply function='Put'>\n"
					+ "\\<Constant dataType='string'>integer2\\</Constant>\n"
					+ "\\<FieldRef field='msg.integer2'/>\n"
					+ "\\</Apply>\n"
					+ "\\</Apply>\n"
					+ "\\</DerivedField>\n"
					+ "\\</TransformationDictionary>\n"
					+ ">>"
					+ "ruleset() ::= <<"
					+ "\\<RuleSetModel modelName='PlusIntIntTest' functionName='classification' algorithmName='RuleSet'>"
					+ "\\<MiningSchema>"
					+ "\\<MiningField name='TestHit' usageType='predicted'/>"
					+ "\\<MiningField name='result' usageType='supplementary'/>"
					+ "\\<MiningField name='integer1' usageType='supplementary'/>"
					+ "\\<MiningField name='integer2' usageType='supplementary'/>"
					+ "\\</MiningSchema>"
					+ "\\<RuleSet defaultScore='0'>"
					+ "\\<RuleSelectionMethod criterion='firstHit'/>"
					+ "\\<SimpleRule id='Result' score='1'>"
					+ "\\<SimplePredicate field='PlusIntIntTest' operator='equal' value='true'/>"
					+ "\\</SimpleRule>" 
					+ "\\</RuleSet>" 
					+ "\\</RuleSetModel>"
					+ "\\</PMML\\>" 
					+ ">>");

	public String getTHeaderRequestXML(String name) {

		st = group.getInstanceOf("header");
		st.add("name", name);

		return st.render();
	}

	public String getDD() {

		st = group.getInstanceOf("DD");

		return st.render();
	}

	public String getTD() {

		st = group.getInstanceOf("TD");

		return st.render();
	}

	public String getRlue() {

		st = group.getInstanceOf("ruleset");

		return st.render();
	}

	public static void main(String[] arg) {
		System.out.println(new XMLTemplete()
				.getTHeaderRequestXML("PlusIntIntTest")
				+ new XMLTemplete().getDD() + new XMLTemplete().getTD()+new XMLTemplete().getRlue());
//		System.out.println(new XMLTemplete().getRlue());

	}

}
