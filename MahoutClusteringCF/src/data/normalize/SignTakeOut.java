package data.normalize;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import bigram.cf.logisticRegression.BigramExpandMethod;

/**
 * 將原來資料中"/"分隔檔案夾的domain給取出，並且將資料務必是為一值
 * **/
public class SignTakeOut {

	public static void main(String[] args) throws IOException {
		SignTakeOut sto = new SignTakeOut();
		
		String filePath = "data/logs20141210_1221/raw/";
//		String dga = "data/logs20141210_1221/raw/logs20141210_1221.txt.uniq";
		String normal = filePath + "top-1m-onlyDomain.txt";
		String result_path = filePath + "top-1m-takeout-sign.uniq";
		
		ArrayList<String> fileToArr = sto.readFile(normal);	//檔案讀取後放到arraylist，並且將資料夾名稱移除
		ArrayList<String> uniqArr = sto.setUniq(fileToArr);	//arraylist中的資料取唯一值
		System.out.println(uniqArr.size());
		sto.writeToFile(uniqArr, result_path);	//將arraylist中的資料取出並寫入result_path檔案
	}
	ArrayList<String> readFile(String path) throws IOException{
		ArrayList<String> arr = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		while(br.ready()){
			String line = br.readLine();
			String[] lineSplit = line.split("/");
			if(lineSplit.length>1){
				arr.add(lineSplit[0]);
			}else{
				arr.add(lineSplit[0]);
			}
		}
		return arr;
	}
	ArrayList<String> setUniq(ArrayList<String> arr){
		HashSet h = new HashSet(arr);
		arr.clear();
		arr.addAll(h);
		return arr;
	}
	
	void writeToFile(ArrayList<String> arr,String path){
		BigramExpandMethod bem = new BigramExpandMethod();
		bem.cleanFile(path);
		for(int i=0;i<arr.size();i++){
			bem.resultToWrite(path, arr.get(i));
		}
	}
	
}
