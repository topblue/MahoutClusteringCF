package data.normalize;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import weka.core.Instances;

public class ArffGetErrorDns {

	String source = "/Users/yufengzhu/Documents/Yuan Ze University/__研究方向__/Experiment/bigramOutput_Clustering-84-f20b20.error.arff";
	String dictionaryPath = "/Users/yufengzhu/Documents/Yuan Ze University/__研究方向__/Experiment/20141210_1221_84_f20b20.result";
	
	public static void main(String[] args) throws Exception{
		ArffGetErrorDns aged = new ArffGetErrorDns();
		ArrayList<String> arr = aged.dnsList(aged.dictionaryPath);
		aged.readArff(aged.source,arr);
		
	}
	void readArff(String source,ArrayList<String> arr) throws FileNotFoundException, IOException{
		Instances data = new Instances(new BufferedReader(new FileReader(source)));
		System.out.println(data.size());
		for(int i=0;i<data.size();i++){
			String[] lineSplit = data.instance(i).toString().split(",");
			String predicted = lineSplit[lineSplit.length-2];
			String given = lineSplit[lineSplit.length-1];
			if(!given.equals(predicted)){
				System.out.println(arr.get(i)+"\tgiven:"+given+"\t,predicted:"+predicted);
			}
		}
	}
	ArrayList<String> dnsList(String dictionaryPath) throws IOException{
		ArrayList<String> arr = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(dictionaryPath));
		while(br.ready()){
			String line = br.readLine();
			if(!line.equals("")){
				String[] dns = line.split(",");
				arr.add(dns[0]);
			}
		}
		br.close();
		return arr;
	}

}
