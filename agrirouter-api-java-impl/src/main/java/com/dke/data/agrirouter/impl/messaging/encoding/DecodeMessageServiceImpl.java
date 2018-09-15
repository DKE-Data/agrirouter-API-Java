package com.dke.data.agrirouter.impl.messaging.encoding;

import agrirouter.commons.MessageOuterClass;
import com.dke.data.agrirouter.api.dto.encoding.DecodeMessageResponse;
import com.dke.data.agrirouter.api.exception.CouldNotDecodeMessageException;
import com.dke.data.agrirouter.api.service.messaging.encoding.DecodeMessageService;
import com.dke.data.agrirouter.impl.NonEnvironmentalService;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.ObjectArrayMessage;

/** Internal service implementation. */
public class DecodeMessageServiceImpl extends NonEnvironmentalService
    implements DecodeMessageService {

  @Override
  public DecodeMessageResponse decode(String encodedResponse) {
    this.getNativeLogger().debug("BEGIN | Decode message response.");
    this.getNativeLogger().trace(new ObjectArrayMessage(encodedResponse));

    if (StringUtils.isBlank(encodedResponse)) {
      throw new IllegalArgumentException("Please provide a valid encoded response.");
    }
    try {

      this.getNativeLogger().trace("Decoding byte array.");
      byte[] decodedBytes = Base64.getDecoder().decode(encodedResponse);
      ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);

      this.getNativeLogger().trace("Parse response envelope.");
      agrirouter.response.Response.ResponseEnvelope responseEnvelope =
          agrirouter.response.Response.ResponseEnvelope.parseDelimitedFrom(inputStream);

      this.getNativeLogger().trace("Parse response payload wrapper.");
      agrirouter.response.Response.ResponsePayloadWrapper responsePayloadWrapper =
          agrirouter.response.Response.ResponsePayloadWrapper.parseDelimitedFrom(inputStream);
      DecodeMessageResponse decodeMessageResponse = new DecodeMessageResponse();
      decodeMessageResponse.setResponseEnvelope(responseEnvelope);
      decodeMessageResponse.setResponsePayloadWrapper(responsePayloadWrapper);

      this.getNativeLogger().trace(new ObjectArrayMessage(decodeMessageResponse));
      this.getNativeLogger().debug("END | Decode message response.");
      return decodeMessageResponse;
    } catch (IOException e) {
      throw new CouldNotDecodeMessageException(e);
    }
  }

  @Override
  public MessageOuterClass.Message decode(ByteString message) {
    try {
      this.getNativeLogger().debug("BEGIN | Decode message.");

      this.getNativeLogger().trace("Decoding byte string.");
      MessageOuterClass.Message decodedMessage = MessageOuterClass.Message.parseFrom(message);

      this.getNativeLogger().trace(new ObjectArrayMessage(decodedMessage));
      this.getNativeLogger().debug("BEGIN | Decode message.");
      return decodedMessage;
    } catch (InvalidProtocolBufferException e) {
      throw new CouldNotDecodeMessageException(e);
    }
  }
}
