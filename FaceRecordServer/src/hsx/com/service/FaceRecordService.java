package hsx.com.service;

import java.util.ArrayList;

import hsx.com.entity.FaceRecord;

public interface FaceRecordService {

	public int facerecordset(FaceRecord faceRecord);

	public ArrayList<FaceRecord> readfacerecordset();

	public int deleterecordset(Integer id);

	public int deleteallset();

}
