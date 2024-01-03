package com.boc.ett.extracts.sl_customs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.encoretheme.eod.utility.*;


import com.xsd.slcustoms.BankData;
import com.xsd.slcustoms.Table1;

public class SLCustomsDAO implements ActionConstants {

	static SLCustomsDAO dao;
	private static Logger logger = Logger.getLogger(SLCustomsDAO.class
			.getName());

	public static SLCustomsDAO getDAO() {
		if (dao == null) {
			dao = new SLCustomsDAO();
		}
		return dao;
	}

	public String fetchQuery(SLCustomVO slcustomVO) {

		BOCCommonUtil commonMethods = null;
		String branchSelectionQuery = FETCHINPUTBRANCHDETAILS;
		commonMethods = new BOCCommonUtil();
		logger.info(ENTERING_METHOD);
		try {
			if (slcustomVO != null)

			{
				if (!commonMethods.isNull(slcustomVO.getBranchCode())
						|| !commonMethods.isNull(slcustomVO.getBranchName())
						|| !commonMethods.isNull(slcustomVO.getBranchCity())) {
					branchSelectionQuery += " WHERE ";

					if (!commonMethods.isNull(slcustomVO.getBranchCode())) {

						System.out.println("branch code : "
								+ slcustomVO.getBranchCode());
						branchSelectionQuery += " TRIM(UPPER(CABBN)) LIKE '0"
								+ slcustomVO.getBranchCode().trim()
										.toUpperCase() + "%' ";

					}

					if (!commonMethods.isNull(slcustomVO.getBranchCode())
							&& !commonMethods
									.isNull(slcustomVO.getBranchName())) {

						branchSelectionQuery += SQL_AND
								+ " TRIM(UPPER(FULLNAME)) LIKE '"
								+ slcustomVO.getBranchName().trim()
										.toUpperCase() + "%' ";

					}

					if (!commonMethods.isNull(slcustomVO.getBranchCode())
							&& !commonMethods
									.isNull(slcustomVO.getBranchCity())) {

						branchSelectionQuery += SQL_AND
								+ " TRIM(UPPER(CITY)) LIKE '"
								+ slcustomVO.getBranchCity().trim()
										.toUpperCase() + "%' ";

					}

					if (commonMethods.isNull(slcustomVO.getBranchCode())) {
						if (!commonMethods.isNull(slcustomVO.getBranchName())) {

							branchSelectionQuery += " TRIM(UPPER(FULLNAME)) LIKE '"
									+ slcustomVO.getBranchName().trim()
											.toUpperCase() + "%' ";

						} else {
							branchSelectionQuery += " TRIM(UPPER(CITY)) LIKE '"
									+ slcustomVO.getBranchCity().trim()
											.toUpperCase() + "%' ";
						}

						if (!commonMethods.isNull(slcustomVO.getBranchName())
								&& !commonMethods.isNull(slcustomVO
										.getBranchCity()))

							branchSelectionQuery += SQL_AND
									+ " TRIM(UPPER(CITY)) LIKE '"
									+ slcustomVO.getBranchCity().trim()
											.toUpperCase() + "%' ";
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(EXITING_METHOD);
		return branchSelectionQuery;
	}

	public ArrayList<SLCustomVO> fetchInputBranchDetailsDAO(String branchQuery) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<SLCustomVO> branchDetailsList = new ArrayList<SLCustomVO>();
		logger.info(ENTERING_METHOD);
		try {

			connection = DBConnectionUtility.getConnection();
			ps = connection.prepareStatement(branchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {

				SLCustomVO slCustomVO = new SLCustomVO();

				slCustomVO.setBrncode(rs.getString("CABBN").substring(1, 4)
						.trim());

				slCustomVO.setBrnName(rs.getString("FULLNAME").trim());

				slCustomVO.setBrnCity(rs.getString("CITY").trim());

				slCustomVO.setBrnCountry(rs.getString("COUNTRY").trim());

				branchDetailsList.add(slCustomVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBConnectionUtility.surrenderDB(connection, ps, rs);
		}

		logger.info(EXITING_METHOD);
		return branchDetailsList;
	}

	public StringBuffer fetchExtractsDAO(SLCustomVO slcustomVO) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = ActionConstants.FETCHSLCUSTOMSEXTRACTQUERY;
		BOCCommonUtil commonMethods = null;
		StringBuffer sb = new StringBuffer();
		logger.info(ENTERING_METHOD);
		try {

			commonMethods = new BOCCommonUtil();
			if (slcustomVO != null)

			{

				if (!commonMethods.isNull(slcustomVO.getBranchCode())
						&& commonMethods.isNull(slcustomVO.getSubPrdType())) {

					query += " TRIM(UPPER(BRANCH)) LIKE '"
							+ slcustomVO.getBranchCode().trim().toUpperCase()
							+ "%'" + SQL_AND + "PRDCODE<>'CPCO' ";

				}

				if (!commonMethods.isNull(slcustomVO.getBranchCode())
						&& !commonMethods.isNull(slcustomVO.getSubPrdType())) {

					query += " TRIM(UPPER(BRANCH)) LIKE '"
							+ slcustomVO.getBranchCode().trim().toUpperCase()
							+ "%' " + SQL_AND
							+ " TRIM(UPPER(SUBPRDTYPE)) LIKE '"
							+ slcustomVO.getSubPrdType().trim().toUpperCase()
							+ "%' ";

				}
			}
			logger.info("Extracts Query : " + query);

			connection = DBConnectionUtility.getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			BankData bd = new BankData();

			while (rs.next()) {
				Table1 table = new Table1();
				table.setBank(rs.getString("BANK").trim());
				table.setBranch(rs.getString("BRANCH").trim());
				table.setName(rs.getString("NAME").trim());
				table.setAdd1(rs.getString("ADD1").trim());
				table.setAdd2(rs.getString("ADD2").trim());
				table.setAdd3(rs.getString("ADD3").trim());
				table.setNICCRBR(rs.getString("NIC_CR_BR").trim());
				table.setVatNo(rs.getString("VATNO").trim());
				table.setBName(rs.getString("BNAME").trim());
				table.setBAdd(rs.getString("BADD").trim());
				table.setBCountry(rs.getString("BCOUNTRY").trim());
				table.setECA(rs.getInt("ECA"));
				table.setFEP(rs.getInt("FEP"));
				table.setCurrCode(rs.getString("CURRCODE").trim());
				table.setAmount(rs.getBigDecimal("AMOUNT"));
				table.setToP(rs.getString("TOP").trim());
				table.setToD(rs.getString("TOD").trim());
				table.setRefNo(rs.getString("REFNO").trim());
				table.setLCNo(rs.getString("LCNO").trim());
				table.setDate(rs.getDate("DATE"));
				bd.getTable1().add(table);

				updateRowStatus(rs.getInt("ID"));

			}

			sb.append(BOCCommonUtil.getXmlFromObject(bd));

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBConnectionUtility.surrenderDB(connection, ps, rs);
		}

		logger.info(EXITING_METHOD);
		return sb;
	}

	public void updateRowStatus(int id) {
		logger.info(ENTERING_METHOD);
		Connection conUpdateRowStatus = null;
		PreparedStatement psUpdateRowStatus = null;
		String query = ActionConstants.UPDATEROWSTATUS;
		try {

			logger.info("Update Status Query : " + query);
			if (id != 0) {
				conUpdateRowStatus = DBConnectionUtility.getConnection();
				psUpdateRowStatus = conUpdateRowStatus.prepareStatement(query);
				psUpdateRowStatus.setInt(1, id);
				psUpdateRowStatus.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(conUpdateRowStatus,
					psUpdateRowStatus, null);
		}

		logger.info(EXITING_METHOD);

	}

}
