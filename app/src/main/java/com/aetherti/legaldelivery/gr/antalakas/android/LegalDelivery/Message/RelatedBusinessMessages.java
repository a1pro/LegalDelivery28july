package com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message;
// Generated by proto2javame, Wed Nov 21 12:25:56 IST 2012.

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


import com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message.RelatedBusinessMessage.Builder;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;

public final class RelatedBusinessMessages extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final java.util.Vector relatedBusinessMessage;
	private static final int fieldNumberRelatedBusinessMessage = 1;


	public static Builder newBuilder() {
		return new Builder();
	}

	private RelatedBusinessMessages(final Builder builder) {
		this.relatedBusinessMessage = builder.relatedBusinessMessage;
	}

	public static class Builder {
		private java.util.Vector relatedBusinessMessage = new java.util.Vector();
		private boolean hasRelatedBusinessMessage = false;


		private Builder() {
		}

		public Builder setRelatedBusinessMessage(final java.util.Vector relatedBusinessMessage) {
			if(!hasRelatedBusinessMessage) {
				hasRelatedBusinessMessage = true;
			}
			this.relatedBusinessMessage = relatedBusinessMessage;
			return this;
		}


		public Builder addElementRelatedBusinessMessage(final com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message.RelatedBusinessMessage element) {
			if(!hasRelatedBusinessMessage) {
				hasRelatedBusinessMessage = true;
			}
			relatedBusinessMessage.addElement(element);
			return this;
		}

		public RelatedBusinessMessages build() {
			return new RelatedBusinessMessages(this);
		}
	}

	public java.util.Vector getRelatedBusinessMessage() {
		return relatedBusinessMessage;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "relatedBusinessMessage = " + this.relatedBusinessMessage + TAB;
		retValue += ")";

		return retValue;
	}

	// Override
	public int computeSize() {
		int totalSize = 0;
		totalSize += computeNestedMessageSize();

		return totalSize;
	}

	private int computeNestedMessageSize() {
		int messageSize = 0;
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberRelatedBusinessMessage, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, relatedBusinessMessage);

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeList(fieldNumberRelatedBusinessMessage, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, relatedBusinessMessage);
		writer.writeData();
	}

	static RelatedBusinessMessages parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final RelatedBusinessMessages.Builder builder = RelatedBusinessMessages.newBuilder();

		while (nextFieldNumber > 0) {
			if(!populateBuilderWithField(reader, builder, nextFieldNumber)) {
				reader.getPreviousTagDataTypeAndReadContent();
			}
			nextFieldNumber = getNextFieldNumber(reader);
		}

		return builder.build();
	}

	static int getNextFieldNumber(final InputReader reader) throws IOException {
		return reader.getNextFieldNumber();
	}

	static boolean populateBuilderWithField(final InputReader reader, final Builder builder, final int fieldNumber) throws IOException {
		boolean fieldFound = true;
		switch (fieldNumber) {
			case fieldNumberRelatedBusinessMessage:
				Vector vcRelatedBusinessMessage = reader.readMessages(fieldNumberRelatedBusinessMessage);
				for(int i = 0 ; i < vcRelatedBusinessMessage.size(); i++) {
					byte[] eachBinData = (byte[]) vcRelatedBusinessMessage.elementAt(i);
					com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message.RelatedBusinessMessage.Builder builderRelatedBusinessMessage = com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message.RelatedBusinessMessage.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolRelatedBusinessMessage = true;
					int nestedFieldRelatedBusinessMessage = -1;
					while(boolRelatedBusinessMessage) {
						nestedFieldRelatedBusinessMessage = getNextFieldNumber(innerInputReader);
						boolRelatedBusinessMessage = com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message.RelatedBusinessMessage.populateBuilderWithField(innerInputReader, builderRelatedBusinessMessage, nestedFieldRelatedBusinessMessage);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementRelatedBusinessMessage(builderRelatedBusinessMessage.build());
				}
				break;
		default:
			fieldFound = false;
		}
		return fieldFound;
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		RelatedBusinessMessages.unknownTagHandler = unknownTagHandler;
	}

	public static RelatedBusinessMessages parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static RelatedBusinessMessages parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static RelatedBusinessMessages parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}