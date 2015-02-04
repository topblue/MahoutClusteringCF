package bigram.cf.logisticRegression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.json.JSONArray;
import org.json.JSONException;

public class Main_MahoutCF_itemBase {

	ArrayList<String> resultList = new ArrayList<String>();
	BigramExpandMethod bem = new BigramExpandMethod();
	
	public void mainclass(NameBeans beans) throws Exception {

		bem.cleanFile(beans.getBigramCFresult());//這個是刪除檔案
		itemBaseCF(beans.getBigramSparseMatrix(),beans.getCfItems());	//放到 ArrayList
		rewriteTo(beans.getBigramSparseMatrix());	//放到 ArrayList
		resultWrite(beans.getDictionaryFilename(), beans.getBigramCFresult());	// ArrayList 的資料寫入到檔案中,並且寫入的是轉矩陣之用
		
	}
	
	void resultWrite(String dictionaryPath,String distPath) throws IOException, JSONException{
		BufferedReader br = new BufferedReader(new FileReader(dictionaryPath));
		int i = 0;
		String bigram = "";
		String url = "";
		while(br.ready()){
			if(i==2){
				bigram = br.readLine();
			}else if(i==5){
				url = br.readLine();
			}else{br.readLine();}
			i++;
		}
		br.close();
		bem.resultToWrite(distPath, "%%MatrixMarket matrix coordinate real general");
		bem.resultToWrite(distPath, "% rows columns non-zero-values");
		bem.resultToWrite(distPath, url+" "+bigram+" "+resultList.size());
		bem.resultToWrite(distPath, "% row column value");
		for(String data:resultList){
			bem.resultToWrite(distPath, data);
		}
	}
//	將原來的資料寫入到資料夾
	void rewriteTo(String sourcePath) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(sourcePath));
		while(br.ready()){
			String[] str = br.readLine().split(",");
			String data = str[0]+" "+str[1]+" "+str[2];
		    resultList.add(data);
		}
		br.close();
	}
	void itemBaseCF(String sourcePath,int cfItems) throws Exception {
		DataModel model = new FileDataModel(new File(sourcePath));
		ItemSimilarity sim = new LogLikelihoodSimilarity(model);
		Recommender recommender= new GenericItemBasedRecommender(model, sim);
		LongPrimitiveIterator iter = model.getUserIDs();
		while (iter.hasNext()) {
			long uid = iter.nextLong();
			List<RecommendedItem> list = recommender.recommend(uid,cfItems);
			for (RecommendedItem ritem : list) {
			    String data = uid+" "+" "+ritem.getItemID()+" "+ritem.getValue();
			    resultList.add(data);
			}
		}
	}
	
}


