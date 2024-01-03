package com.ett.backofficebatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ett.swift.outgoing.SwiftOutUpdation;
import com.ett.util.BOCCommonUtil;
import com.ett.util.Constants;

public class BackOfficeBatchResponseImpl {

	public void PostingResponseUpdate() throws InterruptedException {

		SwiftOutUpdation aSwiftOutUpdation = new SwiftOutUpdation();

		System.out.println("ENTERING INTO POSTING LISTENER CODE");
		try {

			if (BOCCommonUtil.isFilesPresent(Constants.POSTINGRESPONSE_FILEPATH)) {

				getPostingResponse();
				System.out.println("Posting Response Method Completed");
				// insertPostingEntries(aBackOfficeBatchResponseRequestModel);

				aSwiftOutUpdation.swiftoutUpdate();
			} else {
				aSwiftOutUpdation.swiftoutUpdate();
			}

		} catch (Exception e) {
			System.out.println("Exception in the reading File Reponse" + e);
		}

		TimeUnit.SECONDS.sleep(5);

	}

	private void insertPostingEntries(BackOfficeBatchResponseRequestModel aBackOfficeBatchResponseRequestModel) {

		String responseStatus = Constants.STATUS_SUCCEEDED;
		System.out.println("Entering into insertPostingEntries");
		int i = 0;
		int filechek = 0;
		Boolean migCheck = false;
		String unqRef = "";
		for (BackOfficeBatchResponseReqModel BbR : aBackOfficeBatchResponseRequestModel
				.getBackOfficeBatchResponseReqModel()) {
			try {
				BOCCommonUtil.updatePostingResponseEntries(BbR,"");
				if (unqRef.length() < 23) {
					migCheck = true;
				}

				System.out.println("After Updation of Posting Entries");
				if (unqRef.equals("")) {
					unqRef = BbR.getReferenceNumber();
					System.out.println("UnqRef-->" + unqRef);
					filechek++;
				}
				System.out.println("Before check");
				if (!unqRef.equals(BbR.getReferenceNumber()) && unqRef != null) {
					System.out.println("Entering into updatePostingResToLogTable");
					if (migCheck) {
						BOCCommonUtil.updatePostingResToLogTable(responseStatus, unqRef.split("-")[0],
								unqRef.split("-")[1], Constants.BACKOFFICE_BATCH_STAGING_TABLE);
					} else {
						BOCCommonUtil.updatePostingResToLogTable(responseStatus, unqRef.substring(0, 16),
								unqRef.substring(17, 23), Constants.BACKOFFICE_BATCH_STAGING_TABLE);
					}
					unqRef = "";
					responseStatus = Constants.STATUS_SUCCEEDED;
					migCheck = false;
				}
				System.out.println("Before failed check");
				if (BbR.getPostingStatus().equalsIgnoreCase("F")) {
					System.out.println("Posting status === " + BbR.getPostingStatus());
					responseStatus = Constants.STATUS_FAILED;
				}

				i++;
			} catch (Exception e) {
				System.out.println("Exception in insertPostingEntries-->" + e.getMessage());
				e.printStackTrace();
			}

		}
		System.out.println("filechek-->" + filechek);
		if (filechek == 1) {
			System.out.println("Entering into updatePostingResToLogTable");
			System.out.println("File 0--->" + aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel()
					.get(0).getReferenceNumber().substring(0, 16));
			if (migCheck) {
				BOCCommonUtil.updatePostingResToLogTable(responseStatus,
						aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get(0)
								.getReferenceNumber().split("-")[0],
						aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get(0)
								.getReferenceNumber().split("-")[1],
						Constants.BACKOFFICE_BATCH_STAGING_TABLE);
			} else {
				BOCCommonUtil.updatePostingResToLogTable(responseStatus,
						aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get(0)
								.getReferenceNumber().substring(0, 16),
						aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get(0)
								.getReferenceNumber().substring(17, 23),
						Constants.BACKOFFICE_BATCH_STAGING_TABLE);
			}

		} else {
			System.out.println("Entering into updatePostingResToLogTable");
			System.out.println("File i--->" + aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel()
					.get(0).getReferenceNumber().substring(0, 16));
			if (migCheck) {
				BOCCommonUtil.updatePostingResToLogTable(responseStatus,
						aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get(i)
								.getReferenceNumber().split("-")[0],
						aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get(i)
								.getReferenceNumber().split("-")[1],
						Constants.BACKOFFICE_BATCH_STAGING_TABLE);
			} else {
				BOCCommonUtil.updatePostingResToLogTable(responseStatus,
						aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get(i)
								.getReferenceNumber().substring(0, 16),
						aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get(i)
								.getReferenceNumber().substring(17, 23),
						Constants.BACKOFFICE_BATCH_STAGING_TABLE);
			}
		}

		System.out.println("Completion of Posting Staus Update");

	}
	/*
	 * private void insertPostingEntries(BackOfficeBatchResponseRequestModel
	 * aBackOfficeBatchResponseRequestModel) {
	 * 
	 * String responseStatus = Constants.STATUS_SUCCEEDED;
	 * System.out.println("Entering into insertPostingEntries"); int i=0; for
	 * (BackOfficeBatchResponseReqModel BbR : aBackOfficeBatchResponseRequestModel
	 * .getBackOfficeBatchResponseReqModel()) {
	 * BOCCommonUtil.updatePostingResponseEntries(BbR);
	 * System.out.println("After Updation of Posting Entries");
	 * 
	 * if (BbR.getPostingStatus().equalsIgnoreCase("F")) {
	 * System.out.println("Posting status === " + BbR.getPostingStatus());
	 * responseStatus = Constants.STATUS_FAILED; }
	 * System.out.println("Entering into updatePostingResToLogTable");
	 * 
	 * BOCCommonUtil.updatePostingResToLogTable(responseStatus,
	 * aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get
	 * (i).getReferenceNumber() .substring(0, 16),
	 * aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get
	 * (i).getReferenceNumber() .substring(17, 23),
	 * Constants.BACKOFFICE_BATCH_STAGING_TABLE); i++; }
	 * 
	 * 
	 * BOCCommonUtil.updatePostingResToLogTable(responseStatus,
	 * aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get
	 * (0).getReferenceNumber() .substring(0, 16),
	 * aBackOfficeBatchResponseRequestModel.getBackOfficeBatchResponseReqModel().get
	 * (0).getReferenceNumber() .substring(17, 23),
	 * Constants.BACKOFFICE_BATCH_STAGING_TABLE);
	 * 
	 * System.out.println("Completion of Posting Staus Update");
	 * 
	 * }
	 */

	private static void getPostingResponse() {

		List<BackOfficeBatchResponseReqModel> backOfficeBatchResponseReqModelList = new LinkedList<>();
		// BackOfficeBatchResponseRequestModel aBackOfficeBatchResponseRequestModel =
		// new BackOfficeBatchResponseRequestModel();

		File file = new File(BOCCommonUtil.retreiveFromProperties(Constants.POSTINGRESPONSE_FILEPATH));

		System.out.println(
				"GETTING POSTING FILE PATH" + BOCCommonUtil.retreiveFromProperties(Constants.POSTINGRESPONSE_FILEPATH));
		File[] listOfFiles = file.listFiles();
		InputStream is = null;
		BufferedReader buf = null;
		try {
			System.out.println("Entering getPosting Response method");

			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println("Entering into for loop ");
				if (listOfFiles[i].isFile()) {
					backOfficeBatchResponseReqModelList = new LinkedList<>();
					String fileName = listOfFiles[i].getName();
					is = new FileInputStream(listOfFiles[i]);
					buf = new BufferedReader(new InputStreamReader(is));
					String line = buf.readLine();
					while (line != null) {
						System.out.println("Entering into while loop ");
						BackOfficeBatchResponseReqModel aBackOfficeBatchResponseReqModel = new BackOfficeBatchResponseReqModel();
						aBackOfficeBatchResponseReqModel.setReferenceNumber(line.substring(0, 40).trim());
						aBackOfficeBatchResponseReqModel.setTransactionID(line.substring(59, 80).trim());
						aBackOfficeBatchResponseReqModel.setAccountNumber(line.substring(80, 92).trim());
						aBackOfficeBatchResponseReqModel.setAccountType(line.substring(92, 94).trim());
						aBackOfficeBatchResponseReqModel.setDebitCreditCode(line.substring(94, 95).trim());
						aBackOfficeBatchResponseReqModel.setTranAmountTranCcy(line.substring(98, 115).trim());
						aBackOfficeBatchResponseReqModel.setTranAmountLocalCcy(line.substring(115, 132).trim());
						aBackOfficeBatchResponseReqModel.setPostingStatus(line.substring(132, 133).trim());
						aBackOfficeBatchResponseReqModel.setStatusDesc(line.substring(133, 143).trim());

						line = buf.readLine();
						backOfficeBatchResponseReqModelList.add(aBackOfficeBatchResponseReqModel);
						System.out.println("File list" + backOfficeBatchResponseReqModelList);
						System.out.println(aBackOfficeBatchResponseReqModel.getReferenceNumber());
						System.out.println(aBackOfficeBatchResponseReqModel.getAccountNumber());
						System.out.println(aBackOfficeBatchResponseReqModel.getAccountType());
						System.out.println(aBackOfficeBatchResponseReqModel.getPostingStatus());
						System.out.println(aBackOfficeBatchResponseReqModel.getDebitCreditCode());
						System.out.println(aBackOfficeBatchResponseReqModel.getStatusDesc());
						System.out.println(aBackOfficeBatchResponseReqModel.getTranAmountLocalCcy());
						System.out.println(aBackOfficeBatchResponseReqModel.getTranAmountTranCcy());

					}
					try {
						updatePostingResponseEntries(backOfficeBatchResponseReqModelList,fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}

					
					  Files.move( Paths.get(BOCCommonUtil.retreiveFromProperties(Constants.
					  POSTINGRESPONSE_FILEPATH) + fileName),
					  Paths.get(BOCCommonUtil.retreiveFromProperties(Constants.
					  POSTINGRESPONSE_FILEPATH) +
					  BOCCommonUtil.retreiveFromProperties(Constants.POSTING_FILEPATH_PROCESSED) +
					  fileName));
					 
					System.out.println("Posting File Name" + fileName);
				}
			}
			System.out.println("FILE PATH PROCESSED");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// aBackOfficeBatchResponseRequestModel.setBackOfficeBatchResponseReqModel(BbR);
		// System.out.println("list size---" + BbR.size());
		// return aBackOfficeBatchResponseRequestModel;
	}

	private static void updatePostingResponseEntries(
			List<BackOfficeBatchResponseReqModel> backOfficeBatchResponseReqModelList, String fileName) {
		try {
			String responseStatus = Constants.STATUS_SUCCEEDED;
			String masterRef = "";
			String eventRef = "";
			boolean unBalancedPosting = false;
			for (BackOfficeBatchResponseReqModel backOfficeBatchResponseReqModel : backOfficeBatchResponseReqModelList) {
				try {
					if (backOfficeBatchResponseReqModel.getReferenceNumber() != null
							&& !backOfficeBatchResponseReqModel.getReferenceNumber().isEmpty()) {

						System.out.println("ReferenceNumber : " + backOfficeBatchResponseReqModel.getReferenceNumber());

						if (!backOfficeBatchResponseReqModel.getReferenceNumber()
								.equalsIgnoreCase("Trade Finance Batch FTI")) {

							BOCCommonUtil.updatePostingResponseEntries(backOfficeBatchResponseReqModel,fileName);

							try {

								if (backOfficeBatchResponseReqModel.getPostingStatus() != null
										&& !backOfficeBatchResponseReqModel.getPostingStatus().isEmpty()) {

									if (backOfficeBatchResponseReqModel.getPostingStatus().equalsIgnoreCase("F")) {
										responseStatus = Constants.STATUS_FAILED;
									}
								}
								if (backOfficeBatchResponseReqModel.getReferenceNumber().contains("-")) {
									try {
										masterRef = backOfficeBatchResponseReqModel.getReferenceNumber().split("-")[0];
										eventRef = backOfficeBatchResponseReqModel.getReferenceNumber().split("-")[1];
									} catch (Exception e) {
										e.printStackTrace();
										System.out.println("Error while split master and event ref " + e.getMessage());
										masterRef = backOfficeBatchResponseReqModel.getReferenceNumber().substring(0,
												16);
										eventRef = backOfficeBatchResponseReqModel.getReferenceNumber().substring(17,
												23);
									}

								} else {
									masterRef = backOfficeBatchResponseReqModel.getReferenceNumber().substring(0, 16);
									eventRef = backOfficeBatchResponseReqModel.getReferenceNumber().substring(17, 23);
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							if (backOfficeBatchResponseReqModel.getReferenceNumber()
									.equalsIgnoreCase("Trade Finance Batch FTI") && !unBalancedPosting) {
								unBalancedPosting = true;
							}
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			System.out.println("masterRef -" + masterRef);
			System.out.println("eventRef -" + eventRef);
			System.out.println("unBalancedPosting -" + unBalancedPosting);

			BOCCommonUtil.updatePostingResToLogTable(responseStatus, masterRef, eventRef,
					Constants.BACKOFFICE_BATCH_STAGING_TABLE);
			if (unBalancedPosting) {
				BOCCommonUtil.updateUnBalancedPostingResponseEntries(masterRef, eventRef);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		/*
		 * BackOfficeBatchResponseImpl BbRI = new BackOfficeBatchResponseImpl(); try {
		 * BbRI.PostingResponseUpdate(); } catch (InterruptedException e) {
		 * 
		 * e.printStackTrace(); }
		 */
	}
}
