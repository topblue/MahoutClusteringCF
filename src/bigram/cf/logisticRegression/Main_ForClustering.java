package bigram.cf.logisticRegression;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.la4j.matrix.Matrices;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.sparse.CCSMatrix;


public class Main_ForClustering {

	BigramExpandMethod bem = new BigramExpandMethod();
	Matrix toMatrix = null;

//	JSONArray urlArr = new JSONArray();
//	JSONArray bigramArr = new JSONArray();
	JSONObject domainAnsMap = new JSONObject();
	
	public void mainclass(NameBeans beans) throws IOException, JSONException {
		
		bem.cleanFile(beans.getBigramCFToClustering());//移除資料
		
		toMatrix = sparseMatrixToMatrixMarke(beans.getBigramCFresult());
		
		makeDictionaryList(beans.getDictionaryFilename(),beans);
		
		String title = "";
		for(int i=0; i<beans.getBigramArr().length();i++){
			title=beans.getBigramArr().get(i)+","+title;
		}
		title = title+"answer";
		title = title.replace('-', '_');
		domainAns(beans.getRawDomain(),beans);
		bem.resultToWrite(beans.getBigramCFToClustering(), title);
		for(int i=0;i<toMatrix.rows();i++){
			String result = "";
			for(int j=0;j<toMatrix.columns();j++){
				double values = toMatrix.get(i, j);
				if(j==0){
					result = ""+values;
				}else{
					result = result+","+values;
				}
			}
//			result = result+","+domainAnsMap.get((String) urlArr.get(i));
			result = result+","+domainAnsMap.get(beans.getUrlArr().getString(i));
			bem.resultToWrite(beans.getBigramCFToClustering(), result);
		}

	}
	Matrix sparseMatrixToMatrixMarke(String path) throws FileNotFoundException{
		Matrix matrix = new CCSMatrix(Matrices.asMatrixMarketSource(
                new FileInputStream(path)));
		return matrix;
	}
	void domainAns(String domanAnsPath,NameBeans beans) throws IOException, JSONException{
		BufferedReader br = new BufferedReader(new FileReader(domanAnsPath));
		int i = 0;
//		System.out.println(urlArr);
		while(br.ready()){
//			domainAnsMap.put(urlArr.getString(i), br.readLine().split(",")[1]);
			domainAnsMap.put(beans.getUrlArr().getString(i), br.readLine().split(",")[1]);
			i++;
		}
	}
	void makeDictionaryList(String dictionaryPath,NameBeans beans) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(dictionaryPath));
		int i = 0;
		while(br.ready()){
			String line = br.readLine();
			try{
				if(i==1){
					beans.setBigramArr( new JSONArray(line) );
//					bigramArr = new JSONArray(line);
//					String lineRep = StringUtils.substringBeforeLast(StringUtils.substringAfter(line, "[") , "]");
//					String[] strcom = lineRep.split(",");
//					bigramArr = new JSONArray();
//					for(int j = 0;j<strcom.length;j++){
//						bigramArr.put(strcom[j].replaceAll(" ", ""));
//					}
				}else if(i==4){
					beans.setUrlArr( new JSONArray(line) );
//					urlArr = new JSONArray(line);
//					String lineRep = StringUtils.substringBeforeLast(StringUtils.substringAfter(line, "[") , "]");
//					String[] strcom = lineRep.split(",");
//					urlArr = new JSONArray();
//					for(int j = 0;j<strcom.length;j++){
//						urlArr.put(strcom[j].replaceAll(" ", ""));
//					}
				}else{
					
				}
			}catch(Exception e){
				//這段是出現錯誤的話寫入到檔案中
				String str = "data/logs20141210_1221/temp/canDelte.temp";
				bem.cleanFile(str);
				String[] temstr = line.split(",");
				for(int temi=0;temi<temstr.length;temi++){
					bem.resultToWrite(str, temstr[temi]);
				}
			}
			i++;
		}
		br.close();
	}
}
