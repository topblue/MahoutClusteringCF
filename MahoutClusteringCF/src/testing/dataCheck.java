package testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import bigram.cf.logisticRegression.BigramExpandMethod;

public class dataCheck {

	public static void main(String[] args) throws IOException {

		BigramExpandMethod bem = new BigramExpandMethod();
		dataCheck dc = new dataCheck();
		String path = "data/bigramOutput_Clustering.txt";
		dc.checkColumn(path);
	}
	void checkColumn(String path) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(path));
		while(br.ready()){
			int num = 1136;
			String[] element = br.readLine().split(",");
			System.out.println(element[num]);
		}
	}

}
