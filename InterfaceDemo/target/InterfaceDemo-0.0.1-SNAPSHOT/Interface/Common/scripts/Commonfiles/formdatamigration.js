function migratecalfuncLoad(CheckMigr,ProcessID)
{
	
	var formfields="";
	var tablenames="";
	

	//Centrum Migration Start

	if (CheckMigr == "UP50LProcessNote") {

	    //ApplicationForm TO Enclosures
		Fromfields = "01_1_BCDT_PrcsID,02_1_BCDT_BrID,03_1_BCDT_BrName,04_1_BCDT_Name,05_1_BCDT_CusID,06_1_BCDT_ProdID,07_1_BCDT_PropNo,08_1_BCDT_Date";
		Tofields = "01_1_PNBD_PrcsID,02_1_PNBD_BrID,03_1_PNBD_BrName,04_1_PNBD_ApplName,05_1_PNBD_CusID,06_1_PNBD_ProdID,07_1_PNBD_PropNo,08_1_PNBD_Date";
	    Fromtable = "1_LSW_tUT50LLNBasicDetails";
	    Totable = "LSW_tUT50LPNBasicDetails";
		
    migrateformdata(Fromfields, Tofields, Fromtable, Totable, ProcessID);
}
	
	if (CheckMigr == "JWLProcessNote") {

	    //ApplicationForm TO Enclosures
		Fromfields = "01_1_BCDT_PrcsID,02_1_BCDT_BrID,03_1_BCDT_BrName,04_1_BCDT_Name,05_1_BCDT_CusID,06_1_BCDT_ProdID,07_1_BCDT_PropNo,08_1_BCDT_Date";
		Tofields = "01_1_PNBD_PrcsID,02_1_PNBD_BrID,03_1_PNBD_BrName,04_1_PNBD_ApplName,05_1_PNBD_CusID,06_1_PNBD_ProdID,07_1_PNBD_PropNo,08_1_PNBD_Date";
	    Fromtable = "1_LSW_tJWLNBasicDetails";
	    Totable = "LSW_tJWPNBasicDetails";
		
    migrateformdata(Fromfields, Tofields, Fromtable, Totable, ProcessID);
}
	if (CheckMigr == "DDCProcessNote") {

	    //ApplicationForm TO Enclosures
		Fromfields = "01_1_BCDT_PrcsID,02_1_BCDT_BrID,03_1_BCDT_BrName,04_1_BCDT_PropNo,05_1_BCDT_CusID,06_1_BCDT_ProdID,07_1_BCDT_SchmOpted,08_1_BCDT_CusName,09_1_BCDT_CusDOJ,10_1_BCDT_CusMbrshipNo,11_1_BCDT_CusNoOfYrsDDC,12_1_BCDT_ProdName";
		Tofields = "01_1_PNBD_PrcsID,02_1_PNBD_BrID,03_1_PNBD_BrName,04_1_PNBD_PropNo,05_1_PNBD_CusID,06_1_PNBD_ProdID,07_1_PNBD_SchmOpted,08_1_PNBD_DDCName,09_1_PNBD_DOJ,10_1_PNBD_MbrshipNo,11_1_PNBD_NoOfYrsDDC,12_1_PNBD_ProdName";
	    Fromtable = "1_LSW_tDDCBasicDetails";
	    Totable = "LSW_tDDCPNBasicDetails";
		
    migrateformdata(Fromfields, Tofields, Fromtable, Totable, ProcessID);
	
	
}
   
	if (CheckMigr == "DDCGrossCommission") {

	    //ApplicationForm TO Enclosures
		Fromfields = "01_1_GRCE_PrcsID,02_1_GRCE_Mnth6,03_1_GRCE_Mnth5,04_1_GRCE_Mnth4,05_1_GRCE_Mnth3,06_1_GRCE_Mnth2,07_1_GRCE_Mnth1,06_1_GRCE_TotGrossComsn,06_1_GRCE_AvggrossComsn,06_1_GRCE_LonAmtReq,06_1_GRCE_Purp,06_1_GRCE_TwoWhlrDescrptn,06_1_GRCE_TwoWhlrCost,06_1_GRCE_DocInvQtnAttch,06_1_GRCE_DealerName,06_1_GRCE_Margin,06_1_GRCE_NetMnth12,06_1_GRCE_NetMnth11,06_1_GRCE_NetMnth10,06_1_GRCE_NetMnth9,06_1_GRCE_NetMnth8,06_1_GRCE_NetMnth7,06_1_GRCE_NetMnth6,06_1_GRCE_NetMnth5,06_1_GRCE_NetMnth4,06_1_GRCE_NetMnth3,06_1_GRCE_NetMnth2,06_1_GRCE_NetMnth1,06_1_GRCE_TotNetComsn,06_1_GRCE_AvgNetComsn,06_1_GRCE_Mnth12,06_1_GRCE_Mnth11,06_1_GRCE_Mnth10,06_1_GRCE_Mnth9,06_1_GRCE_Mnth8,06_1_GRCE_Mnth7";
		Tofields = "01_1_GRCM_PrcsID,02_1_GRCM_Mnth6,03_1_GRCM_Mnth5,04_1_GRCM_Mnth4,05_1_GRCM_Mnth3,06_1_GRCM_Mnth2,07_1_GRCM_Mnth1,06_1_GRCM_TotGrossComsn,06_1_GRCM_AvggrossComsn,06_1_GRCM_LonAmtReq,06_1_GRCM_Purp,06_1_GRCM_TwoWhlrDescrptn,06_1_GRCM_TwoWhlrCost,06_1_GRCM_DocInvQtnAttch,06_1_GRCM_DealerName,06_1_GRCM_Margin,06_1_GRCM_NetMnth12,06_1_GRCM_NetMnth11,06_1_GRCM_NetMnth10,06_1_GRCM_NetMnth9,06_1_GRCM_NetMnth8,06_1_GRCM_NetMnth7,06_1_GRCM_NetMnth6,06_1_GRCM_NetMnth5,06_1_GRCM_NetMnth4,06_1_GRCM_NetMnth3,06_1_GRCM_NetMnth2,06_1_GRCM_NetMnth1,06_1_GRCM_TotNetComsn,06_1_GRCM_AvgNetComsn,06_1_GRCM_Mnth12,06_1_GRCM_Mnth11,06_1_GRCM_Mnth10,06_1_GRCM_Mnth9,06_1_GRCM_Mnth8,06_1_GRCM_Mnth7";
	    Fromtable = "1_LSW_tDDCGrossCommissionEarned";
	    Totable = "LSW_tDDCPNGrossCommissionEarned";
		
    migrateformdata(Fromfields, Tofields, Fromtable, Totable, ProcessID);
}
	
if (CheckMigr == "StaffProcessNote") {

	    //ApplicationForm TO Enclosures
		Fromfields = "01_1_BCDT_PrcsID,02_1_BCDT_BrID,03_1_BCDT_BrName,04_1_BCDT_PropNo,05_1_BCDT_EmpPensnrName,06_1_BCDT_Date,07_1_BCDT_DOJ,08_1_BCDT_NoOfYrsServc,09_1_BCDT_DOConfrm,10_1_BCDT_NoOfYrsServcLeft,11_1_BCDT_DORetrmnt";
		Tofields = "01_1_PNBD_PrcsID,02_1_PNBD_BrID,03_1_PNBD_BrName,04_1_PNBD_PropNo,05_1_PNBD_EmpPensnrName,06_1_PNBD_Date,07_1_PNBD_DOJ,08_1_PNBD_NoOfYrsServc,09_1_PNBD_DOConfrm,10_1_PNBD_NoOfYrsServcLeft,11_1_PNBD_DORetrmnt";
	    Fromtable = "1_LSW_tStaffLNBasicDetails";
	    Totable = "LSW_tStaffPNBasicDetails";
		
    migrateformdata(Fromfields, Tofields, Fromtable, Totable, ProcessID);
	
	
}
if (CheckMigr == "RMFRecm") {

	    //ApplicationForm TO Enclosures
		Fromfields = "01_1_BCDT_PrcsID,02_1_BCDT_GrpName,03_1_BCDT_Age,04_1_BCDT_LonAmtReq,05_1_BCDT_RepaymntPrd";
		Tofields = "01_1_RECM_PrcsID,02_1_RECM_GrpName,03_1_RECM_Age,04_1_RECM_LonRecmd,05_1_RECM_RepaymntPrd";
	    Fromtable = "1_LSW_tRMBasicDetails";
	    Totable = "LSW_tRMBrRecm";
		
    migrateformdata(Fromfields, Tofields, Fromtable, Totable, ProcessID);
	
	
}




if (CheckMigr == "RMFHo") {

	    //ApplicationForm TO Enclosures
		Fromfields = "01_1_RECM_PrcsID,02_1_RECM_GrpName,03_1_RECM_LonRecmd,04_1_RECM_RepaymntPrd,05_1_RECM_ROI,06_2_LCUS_ProcsgFee,07_2_LCUS_CGST,08_2_LCUS_SGST,09_2_LCUS_TotPFGST";
		Tofields = "01_1_HOPR_PrcsID,02_1_HOPR_GrpName,03_1_HOPR_Amt,04_1_HOPR_RepaymntPrdMnths,05_1_HOPR_ROI,06_2_HOPR_ProcsgFeeFFS,07_2_HOPR_CGSTFFS,08_2_HOPR_SGSTFFS,09_2_HOPR_TotFFS";
	    Fromtable = "1_LSW_tRMBrRecm,2_LSW_tRMCusLon";
	    Totable = "LSW_tRMHOProcessing";
		
    migrateformdata(Fromfields, Tofields, Fromtable, Totable, ProcessID);
	
	
}







}