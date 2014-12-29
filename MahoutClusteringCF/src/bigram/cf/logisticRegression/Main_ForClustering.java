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

	JSONArray urlArr = new JSONArray();
	JSONArray bigramArr = new JSONArray();
	JSONObject domainAnsMap = new JSONObject();
	
	public void mainclass(NameBeans beans) throws IOException, JSONException {
		
		bem.cleanFile(beans.getBigramCFToClustering());//移除資料
		
		toMatrix = sparseMatrixToMatrixMarke(beans.getBigramCFresult());
		
		makeDictionaryList(beans.getDictionaryFilename());
//		System.out.println(beans.getDictionaryFilename());
//		System.out.println(urlArr);

		
		String title = "";
		for(int i=0; i<bigramArr.length();i++){
			title=bigramArr.get(i)+","+title;
		}
		title = title+"answer";
		title = title.replace('-', '_');
		domainAns(beans.getRawDomain());
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
			result = result+","+domainAnsMap.get((String) urlArr.get(i));
			bem.resultToWrite(beans.getBigramCFToClustering(), result);
		}

	}
	Matrix sparseMatrixToMatrixMarke(String path) throws FileNotFoundException{
		Matrix matrix = new CCSMatrix(Matrices.asMatrixMarketSource(
                new FileInputStream(path)));
		return matrix;
	}
	void domainAns(String domanAnsPath) throws IOException, JSONException{
		BufferedReader br = new BufferedReader(new FileReader(domanAnsPath));
		int i = 0;
//		System.out.println(urlArr);
		while(br.ready()){
			domainAnsMap.put(urlArr.getString(i), br.readLine().split(",")[1]);
			i++;
		}
	}
	void makeDictionaryList(String dictionaryPath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(dictionaryPath));
		int i = 0;
		while(br.ready()){
			String line = br.readLine();
//			System.out.println("dictionary順序："+i);
			try{
				if(i==1){
//					System.out.println("i==1  dictionary順序："+i);
					bigramArr = new JSONArray();
					String lineRep = StringUtils.substringBeforeLast(StringUtils.substringAfter(line, "[") , "]");
					String[] strcom = lineRep.split(",");
					bigramArr = new JSONArray();
					for(int j = 0;j<strcom.length;j++){
//						System.out.println();
						bigramArr.put(strcom[j].replaceAll(" ", ""));
					}
				}else if(i==4){
//					System.out.println("i==4  dictionary順序："+i);
					urlArr = new JSONArray();
					String lineRep = StringUtils.substringBeforeLast(StringUtils.substringAfter(line, "[") , "]");
					String[] strcom = lineRep.split(",");
					urlArr = new JSONArray();
					for(int j = 0;j<strcom.length;j++){
//						System.out.println();
						urlArr.put(strcom[j].replaceAll(" ", ""));
					}
				}else{
					
				}
			}catch(Exception e){
				String str = "data/logs20141210_1221/temp/canDelte.temp";
				bem.cleanFile(str);
				String[] temstr = line.split(",");
				for(int temi=0;temi<temstr.length;temi++){
					bem.resultToWrite(str, temstr[temi]);
				}
//				System.out.println("問題："+e.getMessage());
//				System.out.println(line);
			}
			i++;
		}
		br.close();
	}
}
