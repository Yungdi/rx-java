# 1.3 RxJava 의 기본 구조  
## 1.3.1 기본 구조  
구분|생산자|소비자|구독
---|---|---|---
RxJava 1.x|Flowable|Subscriber|Subscription
RxJava 2.x|Observable|Observer|Disposal
## 1.3.2 연산자  
    불필요 데이터 삭제(filter) or 소비자가 사용하기 쉽게 데이터 변환(map) 하여 새로운 Flowable 생성  
## 1.3.3 비동기 처리  
    데이터 생성/통지하는 측과 데이터를 받 측의 처리 범위를 분리하여 각각의 처리를 서로 다른 스레드에서 실행  
    스레드를 관리하는 스케줄러 제공  
    외부 변수 참조 위험  
## 1.3.4 Cold 생산자와 Hot 생산자  
    Cold 생산자: 1개의 소비자와 구독 관계를 맺음. 구독할 때마다 데이터 타임라인 생성  
    Hot 생산자: 여러 소비자와 구독 관계를 맺음. 이미 생선한 타임라인에 나중에 소비자가 참가  
## 1.3.5 ConnectableFlowable/ConnectableObservable (Hot)  
    여러 Subscriber/Observer 동시 구독 가능  
    Cold 와 달리 connect 메서드를 호출해야 처리 시작  
    refCount: ConnectableFlowable 에서 새로운 Flowable 생성. 이미 구독자가 있다면 같은 타임라인에서 생성 데이터 통지  
    autoConnect: 지정한 개수의 구독이 시작된 시점에서 처리를 시작하는 Flowable 생성. 인자 없이 생성하면 subscribe 호출 시정에서 처리  
## 1.3.6 Flowable (Cold to Hot)  
    publish(): Flowable(Cold) -> ConnectableFlowable(Hot)   
               처리 시작한 뒤에 구독하면 구독한 이후에 생성된 데이터부터 새로운 소비자에게 통지  
    replay(): Flowable(Cold) -> ConnectableFlowable(Hot)  
              통지한 데이터 캐시, 처리 시작한 뒤 구독하면 캐시 데이터 먼저 새로 구독한 소비자에게 통지  
    share(): 여러 소비자가 구독할 수 있는 Flowable 생성 (ConnectableFlowable X) = flowable.publish().refCount()  
# 1.4 마블 다이어그램  
    시간 경과에 따른 데이터 변화 시각화  
# 1.5 RxJava 예제  
## 1.5.1 환경 구축  
## 1.5.2 Flowable 사용 예제  
    Flowable: 배압과 onSubscribe 호출은 어디에서 하는 지 알 수 없음  
    Subscriber: Flowable 이 request 파라미터 숫자만큼 데이터를 전달하도록 배압을 관리함...?  
    observeOn: 어떤 스레드에서 실행할지를 파라미터로 지정 가능  
    subscribe 메서드: Publisher 와 Subscriber 사이의 상호 작용이 외부로부터 영향을 받지 않게 설계. 그러므로 구독 해지도 onNext 호출 시점에서 판단해야함?  
  ## 1.5.3 Observable 사용 예제  
    rxJava 1.x vs 2.x -> 배압기능 유무  
    Flowable 예제와 같지만 배압기능이 없기 때문제 request 메서드가 없음  
    Observable:   
    Observer: 구독해지 하려면 Observable 로 부터 전달받은 Disposable 상태값을 가져야함  
# 1.6 RxJava 의 전체 구성  
## 1.6.1 RxJava 의 기본 구성  
    소비자(Subscriber/Observer)가 생산자(Flowable/Observable)를 구독하는 형태  
    RxJava 1.x: Observable - Observer - Disposable  
    RxJava 2.x: Flowable - Subscriber - Subscription
    FlowableProcessor 클래스 다이어그램  
## 1.6.2 Flowable/Observable
    통지 규칙  
      1. null 통지 X  
      2. 데이터 통지는 해도 되고 안해도 된다. ?  
      3. Flowable/Observable 의 처리를 끝낼 때는 완료나 에러 통지를 해야 하며, 둘 다 통지하지 않는다. ?  
      4. 완료나 에러 통지를 한 뒤에는 다른 통지를 해서는 안 된다.  
      5. 통지할 때는 1건씩 순차적으로 통지하며, 동시에 통지하면 안된다. ?  
        어떻게 동기화를 구현할까 ? -> onNext 동기화? onNext 호출을 동기화?
    Subscriber/Observer  
      호출 순서: onSubscribe -> onNext -> onComplete  
      데이터가 통지될 때마다 onNext 를 호출  
      ?: 데이터 통지란 생산자가 소비자에게 onNext 호출하는게 통지 아냐?  
      안전한 통지를 하는 Flowable에서 데이터 받는 동안 onNext 는 동시에 실행되지 않는다. (호출하는 부분 동기화)  
      각 메서드는 하나씩 순서대로 실행되지만 onSubscribe 는 예외  
    Subscription  
    Disposable  
    FlowableProcessor/Subject  
    DisposableSubscriber/DisposableObserver: Disposable 과 Subscriber 두 기능을 다 가지고 있음  
    ... 여러 종류의 구현체가 있구나..........  
    
# 3.1 RxJava 와 디자인 패턴  
## 3.1.1 옵저버 패턴  
    객체들 간의 1:N 관계  
    특정 객체가 변경되면 그 오브젝트를 의존하고 있는 객체들에게 알려줌  
## 3.1.2 이터레레이터 패턴  
       
# 3.2 비동기 처리  
    어떤 작업을 처리하는 도중에 다른 작업도 처리할 수 있는 방법  
# 3.2.1 RxJava 에서 비동기 처리  
     RxJava 에서는 개발자가 직접 비동기 처리를 하도록 설정하거나 연산자 내에서 시간을 다루는 작업을 하지 않는 한   
     생산자의 처리 작업을 실행하는 스레드에서 각 연산자의 처리 작업과 소비자의 처리 작업이 실행된다.  
     하지만 개발자가 직접 비동기 처리를 하도록 설정하면 생산자와 연산자, 소비자가 처리 작업을 실행할 스레드를 분리할 수 있다.  
     
     스레드 종류 설정  
     subscribeOn: 생산자  
     observeOn: 소비자  
     스케줄러  
     RxJava 에서 제공하는 스레드 관리 클래스  
     computation: 논리 프로세서 수만큼 스레드 생성  
     io: i/o 처리작업은 대기 시간이 발생할 가능성이 커서 논리프로세스 수를 초과하는 스레드를 생성해 작업 처리함.  
     상태를 고유할 경우 동기화가 필요함.  
     별도의 스레드 처리를 하지 않아도 기본으로 별도의 스레드에서 처리작업을 하는 생산자: timer, interval
     
     
     
     
     
     
     
         
# 3.3 에러 처리  
    에러 대응 방법은 에러가 회복 가능한 지 아닌 지에 따라 다름.  
    RxJava 의 에러 대응 방법
    1. 소비자(Subscriber/Obserber)에게 에러 통지하기  
    2. 처리 작업 재시도(retry)  
    3. 대체 데이터 통지
## 3.3.1 소비자에게 에러 통지하기  
     명시적으로 에러 처리를 구현하지 않아도 소비자에게 발생한 에러를 통지한다    
     에러 통지 시 어떤 처리를 할 지는 구현해야한다  
## 3.3.2 처리 재시도
     에러 발생 시 재시도 횟수만큼 생산 처리부터 다시 시작한다(재시도 시에는 에러 통지 안함)  
     회복 가능성이 있을 때만 재시도를 사용해야 한다  
     재시도를 판단하는 함수형 인터페이스(Predicate.test)를 인자로 받은 메서드도 존재한다
     boolean retryUntil(BooleanSupplier stop) 반환값이 거짓일 때만 재시도한다
     retryWhen
## 3.3.3 대체 데이터 통지
     에러 발생 시 대체 데이터 통지하는 방법 제공(onError or onException)
     DisposableSubscriber: onSubscribe 가 구현되어 있는 추상클래스 
     에러 발생시 에러 통지 없이 대체 데이터 통지 후 종료
# 3.4 리소스 관리
     특정 리소스로부터 데이터 통지하는 생산자를 생성했을 때 생산자와 리소스의 라이프 사이클을 맞추는 방법
     example) db connection, file resource
## 3.4.1 using 메서드
# 3.5 배압
     Reactive Streams 에서 필수 기능
     데이터 통지량을 제어하는 기능 (Flowable 제공, Observable 제공 X)
     데이터를 통지하는 측(Flowable)과 데이터를 처리하는 측(Subscriber)이 서로 다른 스레드에서 처리할 때, 
     통지 속도가 처리 속도보다 빠를 때 문제가 발생(처리를 기다리는 데이터가 쌓여감)
     해결책 -> 처리할 수 있는 데이터 개수를 생산자에게 요청
     배압의 처리 흐름
     1. 소비자: 생산자에게 지정한 개수의 데이터 요청
     2. 생산자: 요청받은 수 만큼 데이터 통지
     3. 생산자: <통지 후 데이터 생산은 지속됨>
     4. 소비자: 데이터 처리 후 다시 요청
     5. 생산자: 요청받은 수 만큼 데이터 통지
     ....
     
     BackpressureStrategy: 생산자가 통지 후 생산된 데이터를 어떻게 다룰 것인지를 정하는 것
     1. 모든 데이터를 버퍼에 쌓음
     2. 통지 대기 데이터는 모두 파기
     3. 마지막으로 통지한 데이터만 버퍼에 쌓음
     4. 지정한 수만큼 버퍼에 쌓고 이를 넘으면 에러로 처리
## 3.5.1 request 메서드
     데이터를 받는 측의 속도에 맞춰 데이터 통지를 진행
     request(Long.MAX_VALUE) 데이터 개수의 제한이 없음
## 3.5.2 observeOn 메서드와 배압
     Flowable 과 Subscirber 가 서로 다른 스레드를 사용할 때 onObserveOn 메서드로 스케줄러를 설정함
     이때 buffer size 를 지정해 데이터를 요청함
     데이터 요청의 주체는 buffer 이며 구독자의 request 와는 다름
## 3.5.3 MissingBackPressureException
     버퍼에 쌓아둔 데이터가 너무 많아지면 예외 발생
     
     BackpressureStrategy
     1. BUFFER
     2. DROP
     3. LATEST: request 수 만큼만 최신 데이터 유지
     4. ERROR: 버퍼 사이즈 초과 시 예외 발생
     5. NONE
     
## 3.5.4 메서드로 통지할 데이터양 제어
     통지 처리를 배압기능으로 제어하는 것이 아니라 통지하는 데이터를 별도 방법으로 제어할 수 있다
     filter 메서드
     시간으로 통지
     모든 후 통지해 통지 횟수 제어
     스트롤링: 지정된 기간 조건에 맞는 데이터만 통지 ???????????
     buffer 메서드와 window 메서드: 통지할 데이터를 모아 한꺼번지 통지(처리 횟수 줄임)
     
# 4 Flowable 과 Observable 의 연산자
     연산자: Flowable 과 Observable 을 생성하는 메서드
     
# 4.1 Flowable/Observable 을 생성하는 연산자
## 4.1.1 just
     인자의 데이터를 통지하는 Flowable/Observable 생성
     인자는 최대 10개, 왼쪽부터 순서대로 통지
## 4.1.2 fromArray/fromIterable
     인자의 타입이 배열 or 리스트
## 4.1.3 fromCallable
     인자가 Callable
## 4.1.4 range/rangeLong
     인자: 지정한 숫자부터 지정한 개수만큼 하나씩 순서대로 증가
## 4.1.5 interval
     지정한 간격마다 숫자 통지
## 4.1.6 timer
     지정 시간 경과후 0 통지
## 4.1.7 defer
     구독될 때마다 새로운 Flowable 생성
## 4.1.8 empty
    빈 데이터 생성
## 4.1.9 error
    에러 바생
## 4.1.10 never 
    통지 X
    
## 4.2.2 flatMap
하나의 데이터를 여러 데이터로 변환
원본 통지 Flowable, 데이터 변환 후 통지 Flowable
패턴
1. 서로 다른 Flowable 일 경우 비동기로 통지(순서 보장 X)
2. 원본 Flowable ,변환 Flowable 각각 통지한 데이터로 새로운 Flowable?  
3. 에러 통지와 완료 통지에 대한 Flowable 각각 생성
4. 조건에 맞지 않는 데이터일 때 빈 Flowable 반환(혹시 empty 메서드??)
flatMap(mapper)
flatMap(mapper, combiner)
flatMap(onNextMapper, onErrorMapper, onCompleteSupplier)
 ## 4.2.3 concatMap
 하나의 데이터를 여러 데이터로 변환,순서 보장
 concatMap/concatMapDelayError
 concatMapEager: 순서는 보장되야 하지만 좋은 성능을 필요로 할 떄?
 concatMapEagerDelayError: 모든 처리가 끝내고 에러 발생
 ## 4.2.5 buffer
 데이터를 매번 통지 하는 것이 아니라 모아서 컬렉션에 담아 통지
 모으는 단위 or 시간 간격 지정 가능
 ## 4.2.6 toList
 통지할 데이터 모두 리스트에 담아서 통지하는 연산자
 ## 4.2.7 toMap
 통지할 데이터를 key value 로 담아 통지
 변환 데이터가 key 가 됨
 같은 key 데이터가 생성되면 덮어씀
 
# 4.4 Flowable/Observable 을 결합하는 연산자
## 4.4.1 merge/mergeDelayError/mergeArray/mergeArrayDelayError/mergeWith
여러 Flowable 을 하나로 병합하여 하나의 Flowable 로 통지
## 4.4.2 concat/concatDelayError/concatArray/concatArrayDelayError/concatWith
여러 개의 Flowable 을 하나씩(순차적으로) 실행
## 4.4.3 concatEager/concatArrayEager
여러 개의 Flowable 을 결합해 동시 실행하고 한 건씩 통지
실행과 통지가 분리, 실행은 동시에 하지만 통지는 순서대
## 4.4.4 startWith/startWithArray
인자의 데이터를 통지한 후 자신의 데이터(전달받은 원본 데이터) 통지
## 4.4.5 zip/zipWith
여러 Flowable 모아 새로운 데이터 통지
## 4.4.6 combineLatest/combineLatestDelayError
여러 Flowable 에서 데이터를 받을 때마다 새로운 데이터 생성 통지

# 4.5 Flowable/Observable 상태를 통지하는 연산자
## 4.5.1 isEmpty
Flowable 이 통지할 데이터가 있는 지 판단
## 4.5.2 contains
Flowable 이 지정한 데이터를 포함하는 지 판단

# 4.6 Flowable/Observable 데이터를 집계하는 연산자
## 4.6.1 reduce/reduceWith
데이터를 계산하고 최종 집계 결과만 통지
reduce(seed:R, reducer:BiFunction<R,? super T,R>):Single 초기값 있음
reduce(reducer:BiFunction<R,? super T,R>):Maybe 초기값 없음
reduceWith(seedSupplier:Callable<R>, reducer:BiFunction<R, ? super T,R>):Single
## 4.6.2 scan
데이터를 계산하고 각 계산 결과를 통지
scan(accumulator:BiFunction<T,T,T>) 초기값 없음
scan(initialValue:R, accumulator:BiFunction<R,?superT,R>) 초기값 있음

# 4.7 유틸리티 연산자
## 4.7.1 repeat
데이터 통지를 처음부터 반복
## 4.7.2 repeatUntil
지정한 조건이 될 때까지 데이터 통지를 순서대로 반복
## 4.7.3 repeatWhen
지정한 시점까지 데이터 통지를 순서대로 반복
????????????????
## 4.7.4 delay
통지 시점 지연
## 4.7.5 delaySubscription
처리 시작 지연
## 4.7.6 timeout
데이터 통지의 타임아웃 설정
데이터 통지 간격이 설정한 타임아웃 시간을 지나면 에러 통지

# 5.1 Processor/Subject
## 5.1.1 Processor/Subject 란
Reactive Streams 에서 정의한 Publisher 와 Subscriber 를 모두 상속하는 인터페이스
## 5.1.2 PublishProcessor
이미 통지한 데이터를 캐시하지 않고 구독한 뒤 받은 데이터만 통지
## 5.1.3 BehaviorProcessor
마지막으로 통지한 데이터만 캐시
## 5.1.4 ReplayProcessor
모두 또는 지정한 범위까지 캐시
## 5.1.5 AsyncProcessor
완료 했을 때 마지막 데이터와 완료만 통지
## 5.1.6 UnicastProcessor
1개의 소비자만 구독 가능

# 6.1 디버깅과 테스트
'do'로 시작하는 메서드: 통지 시점에 미리 지정한 처리를 수행
'blocking'으로 시작하는 메서드: Flowable 이 호출자의 스레드에서 결과값을 받음
TestSubscriber
TestScheduler: 실행 시간 기다리지않고 내부적으로 계산해 예상 통지 데이터 결과를 빠르게 받음????????

# 6.2 'do'로 시작하는 메서드
통지할 때 어떻게 로그를 출력하는 지
## 6.2.1 doOnNext
## 6.2.2 doOnComplete
## 6.2.3 doOnError
## 6.2.4 doOnSubscribe
## 6.2.5 doOnRequest
## 6.2.6 doOnCancel/doOnDispose

# 6.3 'blocking'으로 시작하는 메서드
비동기 처리 결과를 받아 테스트하는 방법
## 6.3.1 blockingFirst
첫 번째 통지 데이터를 받을 때까지 대기
## 6.3.2 blockingLast
완료 통지(onComplete) 시점까지 blocking
## 6.3.3 blockingIterable
통지하는 모든 데이터을 Iterable 에 적재
완료 통지를 하지 않으면 hasNext = true // 주기적으로 next 호출??
## 6.3.4 blockingSubscribe

# 6.4 TestSubscriber/TestObserver
통지 내용을 테스트
## 6.4.1 TestSubscriber/TestObserver
assert, await

# 6.5 TestScheduler

# 1 왜 리액티브 인가?
