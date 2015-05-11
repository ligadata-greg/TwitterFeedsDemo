package com.ligadata.twitterfeeds.kafkaproducer;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
//		String[] args2 = {"The Wall Street Journal,wsj,investors business daily,financial times,investors,barrons,business week,businessweek,the economist,economist,forbes,fortune,kiplinger,inside business,money magazine,insidebusiness,moneymagazine,theeconomist,investorsbusinessdaily,aetna,aflac,allstate,amfam,americanfamilyinsurance,aig,assurant,axa,farmersinsurance,geico,gmac,hanoverinsurance,thehartford,johnhancock,libertymutual,nylife,newyorklife,nationwide,pacificlife,pemco,progressive,prudential,statefarm,travelers,usaa,bluecross,deutsche,deutsche_news,fidelityinvestments,fidelity,mssb,morganstanley,morgan stanley,credit suisse,creditsuise,goldmansachs,goldman sachs,jpmorgan chase,merrill lynch,merrill,merrilllynch,citigroup,barclayscaptial,ubs,hsbc,rbccapital,rbccapitalmarkets,bnpparibas,bnp paribas,rbsgroup,tdsecurities,td securities,bmo capital markets,bmocaptial,societegenerale,societe generale,blackstone,blackrock,bofa,bofa_careers,bofa_community,bofa_help,bofa_news,barclays,barclayswealth,barclayscycle,wealthinsights,barclaysstockbroker,citi,askciti,citijobs,citibankaus,bank of america,allybank,amex,americanexpress,openforum,askamex,american express,anzmoneymanager,arvest,arvestbank,asb,asbbank,banco sabadell,bancosabadell,barclays wealth,chase,chasegiving,cibc,cibcnews,commonweatlh,netbank,first direct,first_direct,fnb,rbjacobs,grameen,grameenbank,guaranty trust,gtbank,halifax,halifax_online,icici,icicibank_care,ing direct,ingdirect,ceo_ingdirect,superstarsaver,lloyds tsb,lloydstsbonline,mastercard,mastercardnews,nab,north shore,northshorebank,pinnacle,pinnaclebanksc,rbc,standard,standardbankgrp,td_canada,tdbank_us,ubank,usaa,usaa_help,wachovia,wells fargo,wellsfargo,aks_wellsfargo,wellsfargobank,wells fargo bank,westpac,hsbc,rbs"};
//		args[0] = "";
		if (args!=null) {
//			 List<String> list =  Arrays.asList("C:\\Users\\Saleh\\Desktop\\alldata.txt,C:\\Users\\Saleh\\Desktop\\filtered.txt".split(","));
			 List<String> list = Arrays.asList(args[0].split(","));
			
			 for (String filepath : list) {
				 ReadFromFile f = new ReadFromFile(filepath);
				 Thread t = new Thread(f);
				 t.start();
			 }

//			FilterStreamExample f = new FilterStreamExample(
//					"JET4XHa2TpAdcpSKY1r6gn47Y",
//					"u0EDXjhzUeRECuV2PiPFgJgEpdZUUD79eGGcXEHmXdiHG5sYr7",
//					"334088612-wYvtA22ZnLkhNgsXheM8FJMU8Zu3guuraiEgQbiX",
//					"cM4lMELZxP0xpX2SY7tNDD7JMwkEdwc4e5ELg3Tx5HiiB", args2[0]);// "apple"
//			
//			Thread t = new Thread(f);
//			t.start();

		} else {
			throw new IllegalArgumentException();
		}
		// new SampleStreamExample().run("JET4XHa2TpAdcpSKY1r6gn47Y",
		// "u0EDXjhzUeRECuV2PiPFgJgEpdZUUD79eGGcXEHmXdiHG5sYr7",
		// "334088612-wYvtA22ZnLkhNgsXheM8FJMU8Zu3guuraiEgQbiX",
		// "cM4lMELZxP0xpX2SY7tNDD7JMwkEdwc4e5ELg3Tx5HiiB");

	}

}
