package wekaClustering;

import java.util.Random;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;
import bigram.cf.logisticRegression.BigramExpandMethod;
import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

import java.util.Random;

public class LogisticTest1 {

    public static void main(String[] args) throws Exception {
    	LogisticEvaluation logistcEva = new LogisticEvaluation();
    	FilteringOnTheFly filteringonthefly = new FilteringOnTheFly();
    	String path = "data/weka/logistic73.txt";
    	String pathSource = "/Users/yufengzhu/Documents/Yuan Ze University/__研究方向__/Experiment/bigramOutput_Clustering-73.csv";

		DataSource source = new DataSource(pathSource);
        Instances training_data = source.getDataSet();
        training_data.setClassIndex(training_data.numAttributes() - 1);
        
        Instances testing_data = source.getDataSet();
        testing_data.setClassIndex(training_data.numAttributes() - 1);

        logistcEva.deleteFileData(path);	//這行要特別注意，因為這行是清楚檔案內容
        
//        logistcEva.useTrainSet(training_data, testing_data, path);
        System.out.println("===============1====================");
//        logistcEva.run(training_data);
        logistcEva.crossEval(training_data);
        System.out.println("===============2====================");
//        logistcEva.percentage(training_data);
        System.out.println("===============3====================");
//        filteringonthefly.mainFilteringOnTheFly(training_data, training_data);
    }
    

}
