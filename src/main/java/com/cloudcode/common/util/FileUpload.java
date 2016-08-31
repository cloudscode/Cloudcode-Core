package com.cloudcode.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cloudcode.framework.utils.DateFormat;
import com.cloudcode.framework.utils.statics.StaticVar;

public class FileUpload {

	public static String uploadFile(File file, String fileName,
			String filePathModel) {
		if (file == null) {
			return null;
		}
		String filepath = StaticVar.filepath + "/";

		List<String> pathList = new ArrayList<String>();
		pathList.add(filepath);

		for (String path : filePathModel.split("/")) {
			filepath += path + "/";
			pathList.add(filepath);
		}
		filepath += DateFormat.getDate("yyyyMMdd")+ "/";
		pathList.add(filepath);

		// String newFileName = UUID.randomUUID().toString().replaceAll("-",
		// "");
		// pathList.add(newFileName);
		FileUtil.isExist(pathList);

		int index_ = fileName.lastIndexOf(".");

		String fileName1 = fileName.substring(0, index_)
				+ UUID.randomUUID().toString().replaceAll("-", "");
		String fileName2 = fileName.substring(index_);
		fileName = fileName1 + fileName2;
		String fullNewFileName = filepath + (fileName);
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
			File file2 = new File(fullNewFileName);
			output = new BufferedOutputStream(new FileOutputStream(file2));
			int size = 1024;
			byte[] b = new byte[size];
			int j = 0;
			while ((j = input.read(b, 0, size)) > 0) {
				output.write(b, 0, j);
				output.flush();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getClass() + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getClass() + e.getMessage());
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e.getClass() + e.getMessage());
			}
			try {
				input.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getClass() + e.getMessage());
			}
		}
		return fullNewFileName.replace(StaticVar.filepath, "");
	}

	public static void deleteFiles(String filepath) {
		File file = new File(StaticVar.filepath + "/" + filepath);
		file.delete();
	}

}
