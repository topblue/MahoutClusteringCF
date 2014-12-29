package wekaClustering;

import java.util.Random;

import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.FilteredClassifier;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.StringToWordVector;
import bigram.cf.logisticRegression.BigramExpandMethod;

public class LogisticEvaluation {

	BigramExpandMethod bem = new BigramExpandMethod();
	void deleteFileData(String path){
		bem.cleanFile(path);
	}
	
    public void useTrainSet(Instances training_data,Instances testing_data,String path) throws Exception{
    	BigramExpandMethod bem = new BigramExpandMethod();
        String summary = training_data.toSummaryString();
        int number_samples = training_data.numInstances();
        int number_attributes_per_sample = training_data.numAttributes();
        
        String data = "Number of attributes in model = "+ number_attributes_per_sample;
        data = data +"\r\n"+ "Number of samples = " + number_samples;
//        data = data +"\r\n"+ "Summary: " + summary;
        data = data +"\r\n"+ "Summary: " + testing_data.size();
        bem.resultToWrite(path, data);

        Logistic logistic = new Logistic();

        Remove rm = new Remove();
        rm.setAttributeIndices("1");

        FilteredClassifier fc = new FilteredClassifier();
        fc.setFilter(rm);
        fc.setClassifier(logistic);
        fc.buildClassifier(training_data);
        
        for (int i = 0; i < testing_data.numInstances(); i++) {
            double pred = fc.classifyInstance(testing_data.instance(i));
            String given = testing_data.classAttribute().value( (int) testing_data.instance(i).classValue());
            String predicted = testing_data.classAttribute().value((int) pred);
            if(given != predicted){
                String dataline = "given value:"+given+ " . predicted value:"+predicted;
                bem.resultToWrite(path, dataline);
            }
//            bem.resultToWrite(path, String.valueOf(i));
        }
    }
    
    public void run(Instances data) throws Exception {
		System.out.println("Bayesian network analysis with cross-validation...");
		Evaluation eval=new Evaluation(data);
		eval.crossValidateModel(new Logistic(), data,10, new Random(1));
		System.out.println("Confusion Matrix:\n\t\t" + eval.confusionMatrix()[0][0] + "\t"+ eval.confusionMatrix()[0][1]+ 
				"\n\t\t"+ eval.confusionMatrix()[1][0]+ "\t"+ eval.confusionMatrix()[1][1]);
//		System.out.println(eval.getMetricsToDisplay());
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
        System.out.println("# eval.numInstances() of clusters: " + eval.numInstances());
        System.out.println("# eval.errorRate() of clusters: " + eval.errorRate());
	}

    public void crossEval(Instances data) throws Exception {
    	Evaluation eval=new Evaluation(data);
		eval.crossValidateModel(new Logistic(), data,10, new Random(1));
		Classifier cls = new Logistic();
		cls.buildClassifier(data);
		eval.evaluateModel(cls, data);
    }
    
    public void percentage(Instances training_data) throws Exception{
    	FilteredClassifier model = new FilteredClassifier();
        model.setFilter(new StringToWordVector());
        model.setClassifier(new Logistic());
        
        int trainSize = (int) Math.round(training_data.numInstances() * 0.7 );
        int testSize = training_data.numInstances() - trainSize;
        Instances train = new Instances(training_data, 0, trainSize);
        Instances test = new Instances(training_data, trainSize, testSize);
        model.buildClassifier(train);
        
    	System.out.println(model.classifierTipText());
        for (int i = 0; i < train.numInstances(); i++) { 
        	double pred = model.classifyInstance(train.instance(i));
        	double[] preds = model.distributionForInstance(train.instance(i)); 
        	for(int j=0; j< preds.length;j++){
        		System.out.print(preds[j]+" , ");
        	}
        	System.out.println(preds);
        }
        
		for (int i = 0; i < train.numInstances(); i++) {
			double pred = model.classifyInstance(train.instance(i));
			System.out.print(i+"\t ID: " + train.instance(i).value(0));
			System.out.print(", actual: " + train.classAttribute().value((int) train.instance(i).classValue()));
			System.out.println(", predicted: " + train.classAttribute().value((int) pred));
		}
    }
    
    
}
