package wekaClustering;

import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.FilteredClassifier;
//import weka.classifiers.trees.ADTree;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import bigram.cf.logisticRegression.BigramExpandMethod;

public class J48Test1 {
    public static void main(String[] args) throws Exception {
    	BigramExpandMethod bem = new BigramExpandMethod();
    	String path = "data/weka/test73.txt";
    	bem.cleanFile(path);	//這行要特別注意，因為這行是清楚檔案內容

		DataSource source = new DataSource("/Users/yufengzhu/Documents/Yuan Ze University/__研究方向__/Experiment/bigramOutput_Clustering.csv");
//		Instances data = source.getDataSet();
        Instances training_data = source.getDataSet();
        training_data.setClassIndex(training_data.numAttributes() - 1);

//        Instances testing_data = new Instances(new BufferedReader(new FileReader("test_data/weather.arff")));
        Instances testing_data = source.getDataSet();
        testing_data.setClassIndex(training_data.numAttributes() - 1);

        String summary = training_data.toSummaryString();
        int number_samples = training_data.numInstances();
        int number_attributes_per_sample = training_data.numAttributes();
        String data = "Number of attributes in model = "+ number_attributes_per_sample;
        data = data +"\r\n"+ "Number of samples = " + number_samples;
        data = data +"\r\n"+ "Summary: " + summary;
        bem.resultToWrite(path, data);

//         J48 j48 = new J48();
        Logistic logistic = new Logistic();
//        ADTree adt = new ADTree();

        Remove rm = new Remove();

        rm.setAttributeIndices("1");

        FilteredClassifier fc = new FilteredClassifier();
        fc.setFilter(rm);
//        fc.setClassifier(j48);
        fc.setClassifier(new Logistic());
//        fc.setClassifier(adt);

        fc.buildClassifier(training_data);
        System.out.println(testing_data.size());
        bem.resultToWrite(path, String.valueOf(testing_data.size()));
        
        for (int i = 0; i < testing_data.numInstances(); i++) {
            double pred = fc.classifyInstance(testing_data.instance(i));
//            System.out.println(pred);
            String dataline = "given value: "+ testing_data.classAttribute().value( (int) testing_data.instance(i).classValue());
            dataline = dataline+". predicted value: "+ testing_data.classAttribute().value((int) pred);
            bem.resultToWrite(path, dataline);
            bem.resultToWrite(path, String.valueOf(i));
            
            if(!testing_data.classAttribute().value( (int) testing_data.instance(i).classValue()).equals(testing_data.classAttribute().value((int) pred))){
            	System.out.println(testing_data.classAttribute().value( (int) testing_data.instance(i).classValue())+
            			","+testing_data.classAttribute().value((int) pred));
            }
        }
    }
}

