package bigram.cf.logisticRegression;

import org.json.JSONArray;

public class NameBeans {

	public String rawDomain = "";	//要處理的檔案名稱，檔案格式只有domain name名稱
	public String bigramSparseMatrix = "";	//計算好的陣列名稱
	public String dictionaryFilename = "";	//字典資料記錄
	public String bigramCFresult = "";
	public String bigramCFToClustering = "";
	
	public Integer getMainDNIndex ;		//取最後倒數第二個，就是不娶tw,com,net，這個的前一個。www.google.com，只取google，直就給2
	public Integer ngramLimit ; //給2是2-gram
	public Integer cfItems ;	//推薦的個數

	public JSONArray urlArr ;	//url list有答案的
	public JSONArray bigramArr ;	//domain name 的 bigram
	
	
	public JSONArray getUrlArr() {
		return urlArr;
	}
	public void setUrlArr(JSONArray urlArr) {
		this.urlArr = urlArr;
	}
	public JSONArray getBigramArr() {
		return bigramArr;
	}
	public void setBigramArr(JSONArray bigramArr) {
		this.bigramArr = bigramArr;
	}
	
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
