package me.panavtec.cleancontacts.presentation.invoker;

import java.util.concurrent.Future;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.presentation.InteractorResult;

public interface InteractorInvoker {

  <T extends InteractorResponse> Future<T> execute(Interactor<T> interactor);

  <T extends InteractorResponse> Future<T> execute(Interactor<T> interactor, int priority);

  <R, T extends InteractorResponse> Future<T> execute(Interactor<T> interactor, InteractorResult<R> output);

  <R, T extends InteractorResponse<R>> Future<T> execute(Interactor<T> interactor, InteractorResult<R> output, int priority);
}
