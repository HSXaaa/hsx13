package hsx.com.service.impl;

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
	
}
