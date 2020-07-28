package com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message;
// Generated by proto2javame, Wed Nov 21 12:25:56 IST 2012.

import java.io.IOException;
import java.io.InputStream;
import net.jarlehansen.protobuf.javame.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.output.OutputWriter;

public final class ServerStatusMessage extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final String ServerStatus;
	private static final int fieldNumberServerStatus = 1;	

	public static Builder newBuilder() {
		return new Builder();
	}

	private ServerStatusMessage(final Builder builder) {
		if (builder.hasServerStatus ) {
			this.ServerStatus = builder.ServerStatus;
		} else {
			throw new UninitializedMessageException("Not all required fields were included (false = not included in message), " + 
				" ServerStatus:" + builder.hasServerStatus + "");
		}
	}

	public static class Builder {
		private String ServerStatus;
		private boolean hasServerStatus = false;


		private Builder() {
		}

		public Builder setServerStatus(final String ServerStatus) {
			this.ServerStatus = ServerStatus;
			this.hasServerStatus = true;
			return this;
		}

		public ServerStatusMessage build() {
			return new ServerStatusMessage(this);
		}
	}

	public String getServerStatus() {
		return ServerStatus;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "ServerStatus = " + this.ServerStatus + TAB;
		retValue += ")";

		return retValue;
	}

	// Override
	public int computeSize() {
		int totalSize = 0;
		totalSize += ComputeSizeUtil.computeStringSize(fieldNumberServerStatus, ServerStatus);
		totalSize += computeNestedMessageSize();

		return totalSize;
	}

	private int computeNestedMessageSize() {
		int messageSize = 0;

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeString(fieldNumberServerStatus, ServerStatus);
		writer.writeData();
	}

	static ServerStatusMessage parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final ServerStatusMessage.Builder builder = ServerStatusMessage.newBuilder();

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
			case fieldNumberServerStatus:
				builder.setServerStatus(reader.readString(fieldNumber));
				break;
		default:
			fieldFound = false;
		}
		return fieldFound;
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		ServerStatusMessage.unknownTagHandler = unknownTagHandler;
	}

	public static ServerStatusMessage parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static ServerStatusMessage parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static ServerStatusMessage parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}
