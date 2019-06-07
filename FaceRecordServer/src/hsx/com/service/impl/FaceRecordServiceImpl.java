package hsx.com.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import hsx.com.dao.FaceRecordDao;
import hsx.com.entity.FaceRecord;
import hsx.com.service.FaceRecordService;

@Service("faceRecordService")
public class FaceRecordServiceImpl implements FaceRecordService{

	@Resource
	private FaceRecordDao faceRecordDao;

	@Override
	public int facerecordset(FaceRecord faceRecord) {
		return faceRecordDao.facerecordsetup(faceRecord);
	}

	@Override
	public ArrayList<FaceRecord> readfacerecordset() {
		return faceRecordDao.readfacerecordsetup();
	}

	@Override
	public int deleterecordset(Integer id) {
		return faceRecordDao.deleterecordsetup(id);
	}

	@Override
	public int deleteallset() {
		return faceRecordDao.deleteallsetup();	
	}
	
}
