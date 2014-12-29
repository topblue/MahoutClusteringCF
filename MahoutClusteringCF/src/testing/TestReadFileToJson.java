package testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;

public class TestReadFileToJson {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("______1______");
		JSONArray jsaaa = new JSONArray();
		
		String str = "[xhamster.com/user/video,______2______]";
		System.out.println(StringUtils.substringBeforeLast(StringUtils.substringAfter(str, "[") , "]"));
		System.out.println(StringUtils.substringAfter(str, "["));
//		jsaaa.put("xhamster.com/user/video");
//		jsaaa.put("______2______");
		System.out.println("______2______"+jsaaa);
		BufferedReader br = new BufferedReader(new FileReader("data/logs20141210_1221/temp/dictionary.txt"));
		int i = 0;
		JSONArray urlArr;
		JSONArray bigramArr;
		while(br.ready()){
			String line = br.readLine();
			System.out.println("dictionary順序："+i);
			try{
				if(i==1){
					System.out.println("i==1  dictionary順序："+i);
					String lineRep = StringUtils.substringBeforeLast(StringUtils.substringAfter(line, "[") , "]");
					String[] strcom = lineRep.split(",");
					bigramArr = new JSONArray();
					for(int j = 0;j<strcom.length;j++){
						System.out.println();
						bigramArr.put(strcom[j].replaceAll(" ", ""));
					}
				}else if(i==4){
					System.out.println("i==4  dictionary順序："+i);
					urlArr = new JSONArray();
					String lineRep = StringUtils.substringBeforeLast(StringUtils.substringAfter(line, "[") , "]");
					String[] strcom = lineRep.split(",");
					bigramArr = new JSONArray();
					for(int j = 0;j<strcom.length;j++){
						System.out.println();
						urlArr.put(strcom[j].replaceAll(" ", ""));
					}
				}else{
					
				}
			}catch(Exception e){
//				String str = "data/logs20141210_1221/temp/canDelte.temp";
//				bem.cleanFile(str);
				String[] temstr = line.split(",");
				for(int temi=0;temi<temstr.length;temi++){
//					bem.resultToWrite(str, temstr[temi]);
				}
				System.out.println("問題："+e.getMessage());
//				System.out.println(line);
			}
			i++;
		}
		br.close();
	}

}
