package com.dke.data.agrirouter.test.messaging.rest;

import static com.dke.data.agrirouter.impl.messaging.rest.MessageFetcher.DEFAULT_INTERVAL;
import static com.dke.data.agrirouter.impl.messaging.rest.MessageFetcher.MAX_TRIES_BEFORE_FAILURE;

import com.dke.data.agrirouter.api.cancellation.CancellationToken;
import com.dke.data.agrirouter.api.cancellation.DefaultCancellationToken;
import com.dke.data.agrirouter.api.dto.messaging.FetchMessageResponse;
import com.dke.data.agrirouter.api.service.messaging.http.FetchMessageService;
import com.dke.data.agrirouter.impl.messaging.rest.FetchMessageServiceImpl;
import com.dke.data.agrirouter.impl.messaging.rest.MessageFetcher;
import com.dke.data.agrirouter.test.AbstractIntegrationTest;
import com.dke.data.agrirouter.test.OnboardingResponseRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/** Testing the behavior of the cancellation token. */
class CancellationTokenPollingTest extends AbstractIntegrationTest {

  @Test
  @Timeout(value = 15)
  @SuppressWarnings("deprecation")
  void
      givenExistingImplementationOfTheCancellationTokenWhenPollingMessagesThenTheDefaultParametersShouldStillInfluenceTheBehavior()
          throws IOException {
    FetchMessageService fetchMessageService = new FetchMessageServiceImpl();
    final Optional<List<FetchMessageResponse>> fetchMessageResponses =
        fetchMessageService.fetch(
            OnboardingResponseRepository.read(
                OnboardingResponseRepository.Identifier.FARMING_SOFTWARE),
            MessageFetcher.MAX_TRIES_BEFORE_FAILURE,
            MessageFetcher.DEFAULT_INTERVAL);
    Assertions.assertFalse(fetchMessageResponses.isPresent());
  }

  @Test
  @Timeout(value = 1)
  @SuppressWarnings("deprecation")
  void
      givenExistingImplementationOfTheCancellationTokenWhenPollingMessagesThenTheCustomParametersShouldStillInfluenceTheBehavior()
          throws IOException {
    FetchMessageService fetchMessageService = new FetchMessageServiceImpl();
    final Optional<List<FetchMessageResponse>> fetchMessageResponses =
        fetchMessageService.fetch(
            OnboardingResponseRepository.read(
                OnboardingResponseRepository.Identifier.FARMING_SOFTWARE),
            0,
            0);
    Assertions.assertFalse(fetchMessageResponses.isPresent());
  }

  @Test
  @Timeout(15)
  void
      givenDefaultImplementationOfTheCancellationTokenWhenPollingMessagesThenTheCancellationTokenShouldInfluenceTheBehavior()
          throws IOException {
    FetchMessageService fetchMessageService = new FetchMessageServiceImpl();
    final Optional<List<FetchMessageResponse>> fetchMessageResponses =
        fetchMessageService.fetch(
            OnboardingResponseRepository.read(
                OnboardingResponseRepository.Identifier.FARMING_SOFTWARE),
            new DefaultCancellationToken(MAX_TRIES_BEFORE_FAILURE, DEFAULT_INTERVAL));
    Assertions.assertFalse(fetchMessageResponses.isPresent());
  }

  @Test
  @Timeout(1)
  void
      givenCustomImplementationOfTheCancellationTokenWhenPollingMessagesThenTheCancellationTokenShouldInfluenceTheBehavior()
          throws IOException {
    FetchMessageService fetchMessageService = new FetchMessageServiceImpl();
    final Optional<List<FetchMessageResponse>> fetchMessageResponses =
        fetchMessageService.fetch(
            OnboardingResponseRepository.read(
                OnboardingResponseRepository.Identifier.FARMING_SOFTWARE),
            new CancellationToken() {

              @Override
              public boolean isNotCancelled() {
                return false;
              }

              @Override
              public void step() {
                // NOP
              }

              @Override
              public void waitBeforeStartingNextStep() {
                // NOP
              }
            });
    Assertions.assertFalse(fetchMessageResponses.isPresent());
  }
}
