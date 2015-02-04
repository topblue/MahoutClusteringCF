package wekaClustering;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;

public class J48_onTheFly {

	public static void main(String[] args) throws Exception {
		DataSource source = new DataSource("/Users/yufengzhu/Documents/Yuan Ze University/__研究方向__/Experiment/bigramOutput_Clustering-73.csv");
		Instances data = source.getDataSet();

		Instances train = source.getDataSet();	// from somewhere
		Instances test = source.getDataSet();	// from somewhere
		// filter
		Remove rm = new Remove();
		rm.setAttributeIndices("1");  // remove 1st attribute
		// classifier
		J48 j48 = new J48();
		j48.setUnpruned(true);        // using an unpruned J48
		// meta-classifier
		FilteredClassifier fc = new FilteredClassifier();
//		fc.setFilter(rm);
		fc.setClassifier(j48);
		// train and make predictions
//		fc.buildClassifier(train);
//		for (int i = 0; i < test.numInstances(); i++) {
//			double pred = fc.classifyInstance(test.instance(i));
//			System.out.print("ID: " + test.instance(i).value(0));
//			System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
//			System.out.println(", predicted: " + test.classAttribute().value((int) pred));
//		}
		
	}

}
