package cn.lee.jason.cp.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import cn.lee.jason.cp.entity.Result;

public class FileReader {

	public static void main(String[] args) throws Exception {
		File file = new File("101-2001-06-01-2016-06-03.txt");
		List<String> list = FileUtils.readLines(file);
		List<Result> results = new ArrayList<Result>(list.size());
		for(int i = 3;i<list.size() ;i++){
			String key  = list.get(i);
			System.out.println(key);
			Result result = new Result();
			String [] abc = StringUtils.splitByWholeSeparator(key, "                         ");
			result.setSeq(abc[0]);
			result.setOrigin(abc[1]);
			result.setDate(abc[2]);
			results.add(result);
		}
	}
}
