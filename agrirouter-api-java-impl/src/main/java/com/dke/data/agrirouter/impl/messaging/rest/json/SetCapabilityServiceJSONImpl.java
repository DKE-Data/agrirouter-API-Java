package com.dke.data.agrirouter.impl.messaging.rest.json;

import com.dke.data.agrirouter.api.env.Environment;
import com.dke.data.agrirouter.impl.messaging.encoding.json.EncodeMessageServiceJSONImpl;
import com.dke.data.agrirouter.impl.messaging.rest.SetCapabilityServiceImpl;

public class SetCapabilityServiceJSONImpl extends SetCapabilityServiceImpl {
  public SetCapabilityServiceJSONImpl(Environment environment) {
    super(environment, new EncodeMessageServiceJSONImpl(), new MessageSenderJSONImpl());
  }
}