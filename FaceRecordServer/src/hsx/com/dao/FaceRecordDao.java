package hsx.com.dao;

import java.util.ArrayList;

import hsx.com.entity.FaceRecord;

public interface FaceRecordDao {

	public int facerecordsetup(FaceRecord faceRecord);

	public ArrayList<FaceRecord> readfacerecordsetup();

	public int deleterecordsetup(Integer id);

	public int deleteallsetup();

}
