package com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message;
// Generated by proto2javame, Wed Nov 21 12:25:56 IST 2012.

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message.HolidayMessage.Builder;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;

public final class HolidayMessages extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final java.util.Vector holidayMessage;
	private static final int fieldNumberHolidayMessage = 1;


	public static Builder newBuilder() {
		return new Builder();
	}

	private HolidayMessages(final Builder builder) {
		this.holidayMessage = builder.holidayMessage;
	}

	public static class Builder {
		private java.util.Vector holidayMessage = new java.util.Vector();
		private boolean hasHolidayMessage = false;


		private Builder() {
		}

		public Builder setHolidayMessage(final java.util.Vector holidayMessage) {
			if(!hasHolidayMessage) {
				hasHolidayMessage = true;
			}
			this.holidayMessage = holidayMessage;
			return this;
		}


		public Builder addElementHolidayMessage(final HolidayMessage element) {
			if(!hasHolidayMessage) {
				hasHolidayMessage = true;
			}
			holidayMessage.addElement(element);
			return this;
		}

		public HolidayMessages build() {
			return new HolidayMessages(this);
		}
	}

	public java.util.Vector getHolidayMessage() {
		return holidayMessage;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "holidayMessage = " + this.holidayMessage + TAB;
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
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberHolidayMessage, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, holidayMessage);

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeList(fieldNumberHolidayMessage, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, holidayMessage);
		writer.writeData();
	}

	static HolidayMessages parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final HolidayMessages.Builder builder = HolidayMessages.newBuilder();

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
			case fieldNumberHolidayMessage:
				Vector vcHolidayMessage = reader.readMessages(fieldNumberHolidayMessage);
				for(int i = 0 ; i < vcHolidayMessage.size(); i++) {
					byte[] eachBinData = (byte[]) vcHolidayMessage.elementAt(i);
					HolidayMessage.Builder builderHolidayMessage = HolidayMessage.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolHolidayMessage = true;
					int nestedFieldHolidayMessage = -1;
					while(boolHolidayMessage) {
						nestedFieldHolidayMessage = getNextFieldNumber(innerInputReader);
						boolHolidayMessage = HolidayMessage.populateBuilderWithField(innerInputReader, builderHolidayMessage, nestedFieldHolidayMessage);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementHolidayMessage(builderHolidayMessage.build());
				}
				break;
		default:
			fieldFound = false;
		}
		return fieldFound;
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		HolidayMessages.unknownTagHandler = unknownTagHandler;
	}

	public static HolidayMessages parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static HolidayMessages parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static HolidayMessages parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}