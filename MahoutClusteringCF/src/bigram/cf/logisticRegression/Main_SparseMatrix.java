package bigram.cf.logisticRegression;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;

public class Main_SparseMatrix {
	
	public void mainclass(NameBeans beans) throws Exception {
		BigramExpandMethod method = new BigramExpandMethod();
		
		BufferedReader br = new BufferedReader(new FileReader(beans.rawDomain));
		while(br.ready()){
			String line = br.readLine();
			String mainDN = reciprocalMainDN(line,beans.getMainDNIndex);
			method.crateDictionaryAndDomainList(mainDN,beans.ngramLimit);
		}
		br.close();

//		這行要特別小心，因為可能會將檔案清楚，所以記得把output的資料放入，不要把input資料
		method.cleanFile(beans.getDictionaryFilename());
		method.resultToWrite(beans.getDictionaryFilename(), "Bigram Dictionary List , Value:Bigram , Use:Index");
		method.resultToWrite(beans.getDictionaryFilename(), method.dictionaryList.toString());
		method.resultToWrite(beans.getDictionaryFilename(), String.valueOf(method.dictionaryList.size()));
		method.resultToWrite(beans.getDictionaryFilename(), "Unique Domain List");
		method.resultToWrite(beans.getDictionaryFilename(), method.domainNameList.toString());
		method.resultToWrite(beans.getDictionaryFilename(), String.valueOf(method.domainNameList.size()));

//		這行要特別小心，因為可能會將檔案清楚，所以記得把output的資料放入，不要把input資料
		method.cleanFile(beans.getBigramSparseMatrix());
		
		br = new BufferedReader(new FileReader(beans.getRawDomain()));
		for(String mainDN:method.domainNameList){
			String domainNameListIndex = String.valueOf(method.domainNameList.indexOf(mainDN)+1);
//			domain name 切割bigram並計算重複的累加
			HashMap<String,Integer> singleDomainBigramMap = method.singleDomainBigramToMap(mainDN, beans.getNgramLimit());
//			接下來就將整理好的資料轉index寫入
			for(Entry<String,Integer> entry : singleDomainBigramMap.entrySet()){
//				System.out.println(entry.getKey());
				int bigramIndex = method.dictionaryList.indexOf(entry.getKey());
				if(bigramIndex>=0){
					int count = entry.getValue();
					bigramIndex++;
					String resultData = domainNameListIndex+","+bigramIndex+","+count;	//domain和bigram轉成index用逗號分格，加讓出現的次數
					method.resultToWrite(beans.getBigramSparseMatrix(), resultData);
				}
			}
		}
		br.close();
	}


	String reciprocalMainDN(String str,int getMainDNIndex){	//取最後倒數第二個，就是不娶tw,com,net，這個的前一個。www.google.com，只取google
		String[] strarr = str.split(",");
		int getMainIndex = strarr.length- getMainDNIndex;
		return strarr[getMainIndex];
	}
}
