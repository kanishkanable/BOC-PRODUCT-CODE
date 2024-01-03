package com.misys.tiplus2.apps.ti.service.messages;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { 
		"fromEmailAddress",
		"toEmailAddress",
		"emailSubject",
		"emailContent",
		"documents" })
public class EmailNotification {

	@XmlElement(name = "fromEmailAddress", required = true)
	String fromEmailAddress;
	@XmlElement(name = "toEmailAddress", required = true)
	String toEmailAddress;
	@XmlElement(name = "emailSubject", required = true)
	String emailSubject;
	@XmlElement(name = "emailContent", required = true)
	String emailContent;
	@XmlElement(name = "Documents", required = true)
	Document documents;

	public String getFromEmailAddress() {
		return fromEmailAddress;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}

	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public Document getDocuments() {
		return documents;
	}

	public void setDocuments(Document documents) {
		this.documents = documents;
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
