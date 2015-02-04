package wekaClustering;

import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.converters.ConverterUtils.DataSource;


public class ReadCsvArff {

	public static void main(String[] args) throws Exception {
		DataSource source = new DataSource("/Users/yufengzhu/Documents/Yuan Ze University/__研究方向__/Experiment/bigramOutput_Clustering-73.csv");
		Instances data = source.getDataSet();
		// setting class attribute if the data format does not provide this information
		// For example, the XRFF format saves the class attribute information as well
		System.out.println(data.size());
		System.out.println(data.get(0));
		System.out.println(data.get(1999));
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);
	}

}
