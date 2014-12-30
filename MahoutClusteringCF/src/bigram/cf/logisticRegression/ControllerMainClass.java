package bigram.cf.logisticRegression;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ControllerMainClass {

	NameBeans beans = new NameBeans();
	public static void main(String[] args) throws Exception {
		System.out.println("start");
		ControllerMainClass controller = new ControllerMainClass();
		NameBeans beans = controller.dataInitial();
		
		Main_SparseMatrix mainSparseMatrix = new Main_SparseMatrix();
		mainSparseMatrix.mainclass(beans);
		System.out.println("1 Main_SparseMatrix");
		
		Main_MahoutCF_itemBase mainMahoutCFitemBase = new Main_MahoutCF_itemBase();
		mainMahoutCFitemBase.mainclass(beans);
		System.out.println("2 Main_MahoutCF_itemBase");
		
		Main_ForClustering mainForClustering = new Main_ForClustering();
		mainForClustering.mainclass(beans);
		System.out.println("3 Main_ForClustering");

		System.out.println("finish");
	}
	NameBeans dataInitial(){
		NameBeans beans = new NameBeans();
		String filePath = "data/logs20141210_1221/";
		
		beans.setRawDomain(filePath+	"mergeToRaw/merge-10tNormal-2hDga.txt"); //資料來源，例：a17l68o41k27nqpwe21o61p22c59i45muo51f32iwas.info,n
		beans.setDictionaryFilename(filePath+	"temp/dictionary.txt");	//	字典資料記錄
		beans.setBigramSparseMatrix(filePath+	"temp/bigramOutput.txt"); //計算好的陣列名稱
		beans.setBigramCFresult(filePath+	"temp/bigramOutput_CF.txt");	//推薦好的bigram答案
		beans.setBigramCFToClustering(filePath+	"weka/bigramOutput_Clustering.csv");	//給weka的clustering的資料格式
		beans.setGetMainDNIndex(2);	//取最後倒數第二個，就是不娶tw,com,net，這個的前一個。www.google.com，只取google，直就給2。a17l68o41k27nqpwe21o61p22c59i45muo51f32iwas.info,n取a17l68o41k27nqpwe21o61p22c59i45muo51f32iwas.info
		beans.setNgramLimit(2);	//給2是2-gram
		beans.setCfItems(100);//推薦的個數
		return beans;
	}

}
