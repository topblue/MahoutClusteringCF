package data.normalize;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import bigram.cf.logisticRegression.BigramExpandMethod;

public class MergeAndGivenAns {
	String dga = "data/logs20141210_1221/raw/logs20141210_1221.txt.uniq";
	String normal = "data/logs20141210_1221/raw/top-1m-onlyDomain.txt";
//	String resultPath = "data/logs20141210_1221/20141210_1221_64.result";
	String resultPath = "data/logs20141210_1221/raw/merge10tNormal2hDga.txt";
	BigramExpandMethod bem = new BigramExpandMethod();
	
	public static void main(String[] args) throws IOException {
		int total = 2000;
		int normalNum = (int) (total*0.6);
		int dgaNum = 2000-normalNum;
		MergeAndGivenAns mag = new MergeAndGivenAns();
		ArrayList<String> arr = new ArrayList<String>();
//		arr = mag.readFile(arr,mag.normal,"normal",normalNum);
//		arr = mag.readFile(arr,mag.dga,"dga",dgaNum);
		arr = mag.readFile(arr,mag.normal,"normal",10000);
		arr = mag.readFile(arr,mag.dga,"dga",200);
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
