package com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message;
// Generated by proto2javame, Wed Nov 21 12:25:56 IST 2012.

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message.LegalDeliveryMessage.Builder;
import net.jarlehansen.protobuf.javame.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.output.OutputWriter;

public final class LegalDeliveryMessages extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();
	@SuppressWarnings("rawtypes")
	private final java.util.Vector legalDeliveryMessage;
	private static final int fieldNumberLegalDeliveryMessage = 1;
	public static Builder newBuilder() {
		return new Builder();
	}
	@SuppressWarnings("unused")
	private LegalDeliveryMessages(final Builder builder) {
		if (true) {
			this.legalDeliveryMessage = builder.legalDeliveryMessage;
		} else {
			throw new UninitializedMessageException("Not all required fields were included (false = not included in message), " + 
				"");
		}
	}
	public static class Builder {
		@SuppressWarnings("rawtypes")
		private java.util.Vector legalDeliveryMessage = new java.util.Vector();
		private boolean hasLegalDeliveryMessage = false;


		private Builder() {
		}

		public Builder setLegalDeliveryMessage(@SuppressWarnings("rawtypes") final java.util.Vector legalDeliveryMessage) {
			if(!hasLegalDeliveryMessage) {
				hasLegalDeliveryMessage = true;
			}
			this.legalDeliveryMessage = legalDeliveryMessage;
			return this;
		}


		@SuppressWarnings("unchecked")
		public Builder addElementLegalDeliveryMessage(final LegalDeliveryMessage element) {
			if(!hasLegalDeliveryMessage) {
				hasLegalDeliveryMessage = true;
			}
			legalDeliveryMessage.addElement(element);
			return this;
		}

		public LegalDeliveryMessages build() {
			return new LegalDeliveryMessages(this);
		}
	}

	@SuppressWarnings("rawtypes")
	public java.util.Vector getLegalDeliveryMessage() {
		return legalDeliveryMessage;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "legalDeliveryMessage = " + this.legalDeliveryMessage + TAB;
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
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberLegalDeliveryMessage, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, legalDeliveryMessage);

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeList(fieldNumberLegalDeliveryMessage, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, legalDeliveryMessage);
		writer.writeData();
	}

	static LegalDeliveryMessages parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final LegalDeliveryMessages.Builder builder = LegalDeliveryMessages.newBuilder();

		while (nextFieldNumber > 0) {
			if(!populateBuilderWithField(reader, builder, 1)) {
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
			case fieldNumberLegalDeliveryMessage:
				@SuppressWarnings("rawtypes")
				Vector vcLegalDeliveryMessage = reader.readMessages(fieldNumberLegalDeliveryMessage);
				for(int i = 0 ; i < vcLegalDeliveryMessage.size(); i++) {
					byte[] eachBinData = (byte[]) vcLegalDeliveryMessage.elementAt(i);
					LegalDeliveryMessage.Builder builderLegalDeliveryMessage = LegalDeliveryMessage.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolLegalDeliveryMessage = true;
					int nestedFieldLegalDeliveryMessage = -1;
					while(boolLegalDeliveryMessage) {
						nestedFieldLegalDeliveryMessage = getNextFieldNumber(innerInputReader);
						boolLegalDeliveryMessage = LegalDeliveryMessage.populateBuilderWithField(innerInputReader, builderLegalDeliveryMessage, nestedFieldLegalDeliveryMessage);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementLegalDeliveryMessage(builderLegalDeliveryMessage.build());
				}
				break;
		default:
			fieldFound = false;
		}
		return fieldFound;
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		LegalDeliveryMessages.unknownTagHandler = unknownTagHandler;
	}

	public static LegalDeliveryMessages parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static LegalDeliveryMessages parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static LegalDeliveryMessages parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}