package com.misys.tiplus2.apps.ti.service.messages;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "faxService", "toFaxNumber", "branchFaxNo",
		"contact", "documents"

})
public class FaxNotification {

	@XmlElement(name = "faxService", required = true)
	String faxService;
	@XmlElement(name = "toFaxNumber", required = true)
	String toFaxNumber;
	@XmlElement(name = "branchFaxNo", required = true)
	String branchFaxNo;
	@XmlElement(name = "contact", required = true)
	String contact;
	@XmlElement(name = "Documents", required = true)
	Document documents;

	public Document getDocuments() {
		return documents;
	}

	public void setDocuments(Document documents) {
		this.documents = documents;
	}

	public String getFaxService() {
		return faxService;
	}

	public void setFaxService(String faxService) {
		this.faxService = faxService;
	}

	public String getToFaxNumber() {
		return toFaxNumber;
	}

	public void setToFaxNumber(String toFaxNumber) {
		this.toFaxNumber = toFaxNumber;
	}

	public String getBranchFaxNo() {
		return branchFaxNo;
	}

	public void setBranchFaxNo(String branchFaxNo) {
		this.branchFaxNo = branchFaxNo;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "documents"

	})
	public static class Document {
		@XmlElement(name = "Document", required = true)
		List<Documents> documents;

		public List<Documents> getDocuments() {
			if (documents == null) {
				documents = new ArrayList<Documents>();
			}
			return documents;
		}

		public void setDocuments(List<Documents> documents) {

			this.documents = documents;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "documentsID", "mimeType",
				"documentName", "documentContent"

		})
		public static class Documents {
			@XmlElement(name = "documentsID", required = true)
			String documentsID;
			@XmlElement(name = "mimeType", required = true)
			String mimeType;
			@XmlElement(name = "documentName", required = true)
			String documentName;
			@XmlElement(name = "documentContent", required = true)
			byte[] documentContent;

			public String getDocumentsID() {
				return documentsID;
			}

			public void setDocumentsID(String documentsID) {
				this.documentsID = documentsID;
			}

			public String getMimeType() {
				return mimeType;
			}

			public void setMimeType(String mimeType) {
				this.mimeType = mimeType;
			}

			public String getDocumentName() {
				return documentName;
			}

			public void setDocumentName(String documentName) {
				this.documentName = documentName;
			}

			public byte[] getDocumentContent() {
				return documentContent;
			}

			public void setDocumentContent(byte[] documentContent) {
				this.documentContent = documentContent;
			}

		}
	}

}
