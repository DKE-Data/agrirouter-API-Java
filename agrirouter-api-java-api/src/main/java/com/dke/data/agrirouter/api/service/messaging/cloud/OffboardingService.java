package com.dke.data.agrirouter.api.service.messaging.cloud;

import com.dke.data.agrirouter.api.service.parameters.CloudOffboardingParameters;

public interface OffboardingService {
  void offboard(CloudOffboardingParameters parameters);
}