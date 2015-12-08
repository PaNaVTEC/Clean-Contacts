package me.panavtec.cleancontacts.presentation;

import me.panavtec.cleancontacts.domain.interactors.InteractorError;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.presentation.invoker.InteractorExecution;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

public class TestInteractorInvoker {

  public static InteractorInvoker create() {
    InteractorInvoker interactorInvoker = mock(InteractorInvoker.class);
    doAnswer(new Answer() {
      @Override public Object answer(InvocationOnMock invocation) throws Throwable {
        InteractorExecution execution = (InteractorExecution) invocation.getArguments()[0];
        InteractorResponse response = execution.getInteractor().call();
        Class<? extends InteractorError> errorClass = response.getError().getClass();
        if (response.hasError() && execution.getInteractorErrorResult(errorClass) != null) {
          execution.getInteractorErrorResult(errorClass).onResult(response);
        } else if (execution.getInteractorResult() != null) {
          execution.getInteractorResult().onResult(response);
        }
        return null;
      }
    }).when(interactorInvoker).execute(anyInteractorExecution());
    return interactorInvoker;
  }

  private static InteractorExecution<?> anyInteractorExecution() {
    return any();
  }
}
