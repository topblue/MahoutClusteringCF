package bigram.cf.logisticRegression;

public class NameBeans {

	public String rawDomain = "data/domain.txt";	//要處理的檔案名稱，檔案格式只有domain name名稱
	public String bigramSparseMatrix = "data/bigramOutput.txt";	//計算好的陣列名稱
	public String dictionaryFilename = "data/dictionary.txt";	//字典資料記錄
	public String bigramCFresult = "data/bigramOutput_CF.txt";
	public String bigramCFToClustering = "data/bigramOutput_Clustering.txt";
	
	public Integer getMainDNIndex = 2;		//取最後倒數第二個，就是不娶tw,com,net，這個的前一個。www.google.com，只取google，直就給2
	public Integer ngramLimit = 2; //給2是2-gram
	public Integer cfItems = 5;	//推薦的個數
	
	public String getRawDomain() {
		return rawDomain;
	}
	public void setRawDomain(String rawDomain) {
		this.rawDomain = rawDomain;
	}
	public String getBigramSparseMatrix() {
		return bigramSparseMatrix;
	}
	public void setBigramSparseMatrix(String bigramSparseMatrix) {
		this.bigramSparseMatrix = bigramSparseMatrix;
	}
	public String getDictionaryFilename() {
		return dictionaryFilename;
	}
	public void setDictionaryFilename(String dictionaryFilename) {
		this.dictionaryFilename = dictionaryFilename;
	}
	public String getBigramCFresult() {
		return bigramCFresult;
	}
	public void setBigramCFresult(String bigramCFresult) {
		this.bigramCFresult = bigramCFresult;
	}
	public String getBigramCFToClustering() {
		return bigramCFToClustering;
	}
	public void setBigramCFToClustering(String bigramCFToClustering) {
		this.bigramCFToClustering = bigramCFToClustering;
	}
	public Integer getGetMainDNIndex() {
		return getMainDNIndex;
	}
	public void setGetMainDNIndex(Integer getMainDNIndex) {
		this.getMainDNIndex = getMainDNIndex;
	}
	public Integer getNgramLimit() {
		return ngramLimit;
	}
	public void setNgramLimit(Integer ngramLimit) {
		this.ngramLimit = ngramLimit;
	}
	public Integer getCfItems() {
		return cfItems;
	}
	public void setCfItems(Integer cfItems) {
		this.cfItems = cfItems;
	}
}