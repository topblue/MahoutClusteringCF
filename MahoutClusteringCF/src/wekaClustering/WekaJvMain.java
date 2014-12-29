package wekaClustering;

import java.util.Random;

import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
public class WekaJvMain {
    public static void main(String[] args) {
        try{
            //             CSV2Arff converter =new CSV2Arff();
            //             converter.convert();
        	String pathSource = "/Users/yufengzhu/Documents/Yuan Ze University/__研究方向__/Experiment/bigramOutput_Clustering-73.csv";
            DataSource source = new DataSource(pathSource);
            Instances train = source.getDataSet();

            train.setClassIndex(train.numAttributes() - 1);  // setting class attribute

            // classifier
            J48 j48 = new J48();
            j48.setUnpruned(true);        // using an unpruned J48

            //evaluate j48 with cross validation
            Evaluation eval=new Evaluation(train);

            //first supply the classifier
            //then the training data
            //number of folds
            //random seed
            eval.crossValidateModel(j48, train, 5, new Random(1));
            System.out.println("Percent correct: "+Double.toString(eval.pctCorrect()));

//            j48.buildClassifier(train);
//            System.out.print(j48.graph());
    		System.out.println("Confusion Matrix:\t\t" + eval.confusionMatrix()[0][0] + "\t"+ eval.confusionMatrix()[0][1]+ 
    				"\n\t\t"+ eval.confusionMatrix()[1][0]+ "\t"+ eval.confusionMatrix()[1][1]);
    		

        }
        catch(Exception e){
            e.printStackTrace();
        }      
    }
} 
