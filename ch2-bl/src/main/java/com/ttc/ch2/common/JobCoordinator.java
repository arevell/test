package com.ttc.ch2.common;

import java.util.EnumMap;

import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.search.export.IndexSynchronizerVO;


public interface JobCoordinator {
	
	public static enum Params{FILE_COLLECT_VO,INDEX_VO}
	
	public void setup(EnumMap<Params,Object> params);
	
	public void setupIndexingResult(IndexSynchronizerVO iVo);
	
	public void setupFileCollectResult(FileCollectVO iVo);
}
