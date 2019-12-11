# 1.3 RxJava 의 기본 구조  
  ## 1.3.1 기본 구조  
구분|생산자|소비자|구독  
Reactive Streams 지원|Flowable|Subscriber|Subscription  
Reactive Streams 미지원|Observable|Observer|Disposable  
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