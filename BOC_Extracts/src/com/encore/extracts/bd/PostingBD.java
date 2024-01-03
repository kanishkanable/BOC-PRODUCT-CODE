package com.encore.extracts.bd;

import java.util.ArrayList;

import com.encore.extracts.dao.PostingDAO;
import com.encore.extracts.vo.PostingVO;
import com.encore.extracts.vo.SwiftVO;

public class PostingBD {

	static PostingBD postingBD;

	public static PostingBD getBD() {
		if (postingBD == null) {
			postingBD = new PostingBD();
		}
		return postingBD;
	}

	public ArrayList<PostingVO> getPostingDetails(String masterRef, String eventRef) {
		PostingDAO postingDAO = null;
		ArrayList<PostingVO> postingDetailsList = null;
		try {
			postingDAO = PostingDAO.getDAO();
			postingDetailsList = postingDAO.getPostingDetails(masterRef, eventRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postingDetailsList;
	}
	
	public ArrayList<SwiftVO> getSwiftDetails(String masterRef, String eventRef) {
		PostingDAO postingDAO = null;
		ArrayList<SwiftVO> swiftDetailsList = null;
		try {
			postingDAO = PostingDAO.getDAO();
			swiftDetailsList = postingDAO.getSwiftDetails(masterRef, eventRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return swiftDetailsList;
	}
	
	public void updatePostingForRelease(String masterRef, String eventRef) {
		PostingDAO postingDAO = null;
		try {
			postingDAO = PostingDAO.getDAO();
			postingDAO.updatePostingForRelease(masterRef, eventRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updatePostingStatus(String masterRef, String eventRef) {
		PostingDAO postingDAO = null;
		try {
			postingDAO = PostingDAO.getDAO();
			postingDAO.updatePostingStatus(masterRef, eventRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
