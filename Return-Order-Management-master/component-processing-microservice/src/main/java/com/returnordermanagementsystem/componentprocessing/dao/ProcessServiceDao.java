package com.returnordermanagementsystem.componentprocessing.dao;

import com.returnordermanagementsystem.componentprocessing.model.ProcessResponse;

public interface ProcessServiceDao {
	ProcessResponse processDetails(int userId);
}
