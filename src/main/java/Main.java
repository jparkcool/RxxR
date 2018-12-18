import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.subjects.PublishSubject;

import java.util.List;

public class Main {

    public static void zzz() {
        Observable<User> observable = UserRepository.getInstance().getUser();

        PublishSubject<User> subject = PublishSubject.create();
        subject.subscribe(item -> System.out.println("Sub A: " + item)); //Subscribe both subscribers on the publish subject
        subject.subscribe(item -> System.out.println("Sub B: " + item));

        observable.subscribe(subject);
    }

    public static void main(String[] args) {
        zzz();
    }

    private static void test02() {
        Observable<Integer> range = Observable.range(1, 3);
        PublishSubject<Integer> subject = PublishSubject.create(); //Create a publish subject
        subject.subscribe(item -> System.out.println("Sub A: " + item)); //Subscribe both subscribers on the publish subject
        subject.subscribe(item -> System.out.println("Sub B: " + item));
        range.subscribe(subject); //Subscribe the subject on the source observable
    }

    private static void test01() {
        ObservableOnSubscribe<Todo> source = new ObservableOnSubscribe<Todo>() {
            @Override
            public void subscribe(ObservableEmitter<Todo> emitter) throws Exception {
                try {
                    List<Todo> todos = RxJavaUnitTest.getTodos();
                    for (Todo todo : todos) {
                        emitter.onNext(todo);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        };
        Observable<Todo> observable = Observable.create(source);
    }
}
