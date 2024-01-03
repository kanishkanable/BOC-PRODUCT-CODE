package com.util.npaeod;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.misys.tiplus2.enigma.job.UserExitActionStatus;
import com.misys.tiplus2.framework.async.processing.execution.step.StepContext;

public class NPAEODPROCESS implements com.misys.tiplus2.enigma.job.BatchUserExitAction {

//	private static Logger logger = Logger.getLogger(NPAEODPROCESS.class
	// .getName());

	public static void main(String[] args) {
		System.out.println("Main Method in EOD");
		// logger.info("Main Method in EOD");

		new NPAEODPROCESS().run(0L, "0", (StepContext) null);

	}

	@Override
	public UserExitActionStatus checkStatus(Long arg0, String arg1, StepContext arg2) {

		System.out.println("Hello NPAEODPROCESS");
		// logger.info("Hello NPAEODPROCESS");

		return UserExitActionStatus.COMPLETE;
	}

	public UserExitActionStatus run(Long arg0, String arg1, StepContext arg2) {
		System.out.println("Going to start EOD for NPA");
		// logger.info("Going to start EOD for NPA");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		String errorDesc = "";
		int i = 0;
		try {
			/* Added by N able to call data source in IBM websphere */
			con = ConnectionMaster.getConnection();
			CallableStatement npaCallableStatement = null;
			System.out.println("NPA EOD Data procedure called");
			String getData = "call ETT_NPA_EODPROCESS()";
			System.out.println("ETT_NPA_EODPROCESS " + getData);
			String apiserverCheck = "select * from apiserver where status='WAITING'";

			PreparedStatement pst = null;
			ResultSet rst = null;
			System.out.println("before calling get data ");
			npaCallableStatement = con.prepareCall(getData);
			System.out.println("Result of EOD Data procedure - " + npaCallableStatement);
			int result = npaCallableStatement.executeUpdate();
			System.out.println("Result of EOD Data procedure - " + result);

			System.out.println("After print result value");

			System.out.println("After print result value" + npaCallableStatement);

			if (npaCallableStatement != null)
				npaCallableStatement.close();

			System.out.println("After print result value not null ");

			while (i == 0) {
				pst = con.prepareStatement(apiserverCheck);
				rst = pst.executeQuery();
				if (!rst.next())
					i = 1;
				Thread.sleep(10000L);
			}
			return UserExitActionStatus.COMPLETE;
		}

		catch (Exception e) {

			e.printStackTrace();
			errorDesc = e.getMessage();
			System.out.println("Exception!!!" + errorDesc);

			return (UserExitActionStatus.FAILED);
		} finally {
			System.out.println("Finally Occurred in saveDetail");
			ConnectionMaster.surrenderDB(con, ps, res);
		}
	}

}