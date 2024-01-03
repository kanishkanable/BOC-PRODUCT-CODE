package com.encore.extracts.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.encore.extracts.dao.PostingDAO;
import com.encore.extracts.vo.PostingVO;
import com.encore.extracts.vo.SwiftVO;
import com.opensymphony.xwork2.ActionSupport;

public class PostingAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(PostingAction.class.getName());
	PostingVO postingVO;
	String ejbreq;
	ArrayList<PostingVO> postingList;
	ArrayList<SwiftVO> swiftList;

	public PostingVO getPostingVO() {
		return postingVO;
	}

	public void setPostingVO(PostingVO postingVO) {
		this.postingVO = postingVO;
	}

	public ArrayList<PostingVO> getPostingList() {
		return postingList;
	}

	public void setPostingList(ArrayList<PostingVO> postingList) {
		this.postingList = postingList;
	}

	public ArrayList<SwiftVO> getSwiftList() {
		return swiftList;
	}

	public void setSwiftList(ArrayList<SwiftVO> swiftList) {
		this.swiftList = swiftList;
	}

	/*
	 * public String fetchPostingListDontUse() { try {
	 * 
	 * System.out.println("Masteref : "+postingVO.getMasterRef());
	 * System.out.println("EventRef : "+postingVO.getEventRef());
	 * 
	 * postingList = new ArrayList<PostingVO>();
	 * 
	 * PostingVO aPostingVOTmp = new PostingVO();
	 * aPostingVOTmp.setMasterRef("testMasterRef");
	 * aPostingVOTmp.setEventRef("testEventRef");
	 * aPostingVOTmp.setAccountNumber("testAccNo");
	 * aPostingVOTmp.setStatus("testStatus");
	 * aPostingVOTmp.setTranAmount("testTranAmount");
	 * aPostingVOTmp.setCreditDebit("testCreditDebit");
	 * 
	 * postingList.add(aPostingVOTmp); postingList.add(aPostingVOTmp);
	 * postingList.add(aPostingVOTmp); postingList.add(aPostingVOTmp);
	 * postingList.add(aPostingVOTmp); postingList.add(aPostingVOTmp);
	 * postingList.add(aPostingVOTmp); postingList.add(aPostingVOTmp);
	 * postingList.add(aPostingVOTmp); postingList.add(aPostingVOTmp);
	 * 
	 * 
	 * setPostingList(postingList);
	 * 
	 * swiftList = new ArrayList<SwiftVO>();
	 * 
	 * SwiftVO aSwiftVOTmp = new SwiftVO();
	 * aSwiftVOTmp.setMasterRef("testMasterRef");
	 * aSwiftVOTmp.setEventRef("testEventRef");
	 * aSwiftVOTmp.setProcessedDate("testProcessedDate");
	 * aSwiftVOTmp.setReceivedDate("testReceivedDate");
	 * aSwiftVOTmp.setStatus("testStatus");
	 * 
	 * swiftList.add(aSwiftVOTmp); swiftList.add(aSwiftVOTmp);
	 * swiftList.add(aSwiftVOTmp); swiftList.add(aSwiftVOTmp);
	 * swiftList.add(aSwiftVOTmp); swiftList.add(aSwiftVOTmp);
	 * swiftList.add(aSwiftVOTmp); swiftList.add(aSwiftVOTmp);
	 * 
	 * setSwiftList(swiftList);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return "success"; }
	 */

	public String statusChange() {
		try {
			PostingDAO postingDAO = PostingDAO.getDAO();

			if (postingVO.getMasterRef() != null && postingVO.getEventRef() != null
					&& !postingVO.getMasterRef().isEmpty() && !postingVO.getEventRef().isEmpty()) {
				postingDAO.updatePostingStatus(postingVO.getMasterRef(), postingVO.getEventRef());
				// nextline changed by padhu
				postingDAO.updateSwiftStatus(postingVO.getMasterRef(), postingVO.getEventRef());
				postingDAO.updatePostingForRelease(postingVO.getMasterRef(), postingVO.getEventRef());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String fetchPostingList() {
		try {
			PostingDAO postingDAO = PostingDAO.getDAO();
			postingList = new ArrayList<PostingVO>();
			swiftList = new ArrayList<SwiftVO>();
			if (postingVO.getMasterRef() != null && postingVO.getEventRef() != null
					&& !postingVO.getMasterRef().isEmpty() && !postingVO.getEventRef().isEmpty()) {
				postingList = postingDAO.getPostingDetails(postingVO.getMasterRef(), postingVO.getEventRef());
				swiftList = postingDAO.getSwiftDetails(postingVO.getMasterRef(), postingVO.getEventRef());
			}
			setSwiftList(swiftList);
			setPostingList(postingList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/*
	 * public String submitRequest() {
	 * 
	 * System.out.println("------------------------" + ejbreq);
	 * 
	 * return "success";
	 * 
	 * }
	 */
}
