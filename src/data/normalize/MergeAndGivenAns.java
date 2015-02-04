package data.normalize;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import bigram.cf.logisticRegression.BigramExpandMethod;

public class MergeAndGivenAns {
	String filePath = "data/logs20141210_1221/";
	String dga = filePath + "raw/logs20141210_1221.txt.uniq";
//	String normal = filePath + "raw/top-1m-onlyDomain.txt";
	String normal = filePath + "raw/top-1m-takeout-sign.uniq";
	String resultPath = filePath + "mergeToRaw/merge-1tNormal-2hDga.txt";
	
	BigramExpandMethod bem = new BigramExpandMethod();
	
	public static void main(String[] args) throws IOException {
		int num_normal = 1000;
		int num_dga = 200;
		MergeAndGivenAns mag = new MergeAndGivenAns();
		ArrayList<String> arr = new ArrayList<String>();
		
		arr = mag.readFile(arr,mag.normal,"normal",num_normal);
		arr = mag.readFile(arr,mag.dga,"dga",num_dga);
		
		mag.readArrayAndWriteToFile(arr);	//寫到檔案中
		System.out.println(arr.size());
	}
	ArrayList<String> readFile(ArrayList<String> arr,String path,String ans,int limit) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(path));
		int i=0;
		while(br.ready()){
			if(i>=limit){
				break;
			}
			String line = br.readLine();
			if(!line.equals("")){
				line = line+","+ans;
				arr.add(line);
			}else{
//				System.out.println("空值");
			}
			i++;
		}
		return arr;
	}
	void readArrayAndWriteToFile(ArrayList<String> arr){
		bem.cleanFile(resultPath);
		for(int i=0;i<arr.size();i++){
			bem.resultToWrite(resultPath, arr.get(i));
//			System.out.println(arr.get(i));
		}
	}

}
