package data.normalize;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

import bigram.cf.logisticRegression.BigramExpandMethod;

public class CuttingNomalDga {

	BigramExpandMethod bem = new BigramExpandMethod();

	String filename = "data/logs20141210_1221/";
	String test_data = filename + "weka/test_Clustering.csv";
	String training_data = filename + "weka/training_Clustering.csv";
	String test_dictionary = filename + "weka/test_dictionary.txt";
	String training_dictionary = filename + "weka/training_dictionary.txt";

	
	public static void main(String[] args) throws IOException, JSONException {
		CuttingNomalDga cn = new CuttingNomalDga();
		SplitNumber sn = new SplitNumber(100, 20, 10);

//		String path = cn.filename + "weka/bigramOutput_Clustering.csv";
		String path = "data/logs20141210_1221/nonRecommend/output.csv";
		String path_dic = cn.filename + "temp/dictionary.txt";

		System.out.println(sn.normal_testing+","+sn.normal_training+",");
		System.out.println(sn.dga_training+","+sn.dga_testing+",");
		
		JSONArray urlArr = new JSONArray();	//url list有答案的
		urlArr = cn.dictionary(path_dic);
		
		cn.cutting(path, urlArr,sn);
		
	}

	JSONArray dictionary(String path) throws IOException, JSONException{
		JSONArray urlArr = new JSONArray();

		BufferedReader br = new BufferedReader(new FileReader(path));
		int i = 0;
		while(br.ready()){
			String line = br.readLine();
			if(i==4){
				urlArr = new JSONArray(line);
			}else{}
			i++;
		}
		return urlArr;
	}
	void cutting(String path,JSONArray urlArr,SplitNumber sn ) throws IOException, JSONException{
		BufferedReader br = new BufferedReader(new FileReader(path));
		bem.cleanFile(test_data);
		bem.cleanFile(training_data);
		bem.cleanFile(test_dictionary);
		bem.cleanFile(training_dictionary);
		int i = 0;
		while(br.ready()){
			String line = br.readLine();
			if(i==0){	//抬頭
				bem.resultToWrite(test_data, line);
				bem.resultToWrite(training_data, line);
			}else if(i>0 && i<=sn.normal_testing){		//testing
				bem.resultToWrite(test_data, line);
				bem.resultToWrite(test_dictionary, urlArr.getString(i-1));
			}else if(i>sn.normal_testing && i<=sn.normal_training){	//training
				bem.resultToWrite(training_data, line);
				bem.resultToWrite(training_dictionary, urlArr.getString(i-1));
			}else if(i>sn.normal_training && i<=sn.dga_training){	//training
				bem.resultToWrite(training_data, line);
				bem.resultToWrite(training_dictionary, urlArr.getString(i-1));
			}else if(i>sn.dga_training && i<=sn.dga_testing){	//testing
				bem.resultToWrite(test_data, line);
				bem.resultToWrite(test_dictionary, urlArr.getString(i-1));
			}
//			System.out.println(line);
			i++;
		}
	}
	
//	normal_testing	=>	normal_training	=>	dga_training	=>	dga_testing
	private static class SplitNumber{
		int normal_testing,normal_training,dga_training,dga_testing;
		public SplitNumber(int normal, int dga , int persen){
			this.normal_testing = (int) (normal *(persen/100.0));
			this.normal_training = normal;
			this.dga_training = (int) (normal_training + (dga * ((100-persen)/100.0) ));
			this.dga_testing = normal+dga;
		}
		
	}

}
