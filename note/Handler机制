# Handler机制
## Message 【继承Parcelable】
`封装了任务携带的参数和处理该任务的Handler`

    /**
     * User-defined message code so that the recipient can identify
     * what this message is about. Each {@link Handler} has its own name-space
     * for message codes, so you do not need to worry about yours conflicting
     * with other handlers.
     */
    public int what; //标识位，用来区别消息

    /**
     * arg1 and arg2 are lower-cost alternatives to using
     * {@link #setData(Bundle) setData()} if you only need to store a
     * few integer values.
     */
    public int arg1; //用来存储一些整数值，替代setData()存储

    /**
     * arg1 and arg2 are lower-cost alternatives to using
     * {@link #setData(Bundle) setData()} if you only need to store a
     * few integer values.
     */
    public int arg2; //用来存储一些整数值，替代setData()存储

    /**
     * An arbitrary object to send to the recipient.  When using
     * {@link Messenger} to send the message across processes this can only
     * be non-null if it contains a Parcelable of a framework class (not one
     * implemented by the application).   For other data transfer use
     * {@link #setData}.
     *
     * <p>Note that Parcelable objects here are not supported prior to
     * the {@link android.os.Build.VERSION_CODES#FROYO} release.
     */
    //发送给收件人的任意对象。当使用{@link Messenger}跨进程发送消息时，
    //如果它包含框架类的Parcelable（不是应用程序实现的框架类），则它只能是非null。
    //对于其他数据传输，请使用{@link #setData}
    //2.2版本之前不支持实现Parceable的实体类
    public Object obj;


* Message的创建（2中方法）
    1. 设置Message的参数
    2. Message.obtain() --【推荐】
       因为它允许我们在许多情况下避免分配新的对象；避免重复创建Message

* Message注意点：
    1. 尽量通过Message.obtain()的方式构建Message对象，防止Message的多次创建；
    2. 仅有int型参数时 最好使用arg1和arg2，减少内存的使用；


## Looper：消息通道（使普通线程变成Looper线程）
在Activity中，系统会自动启动Looper对象；而在自定义类中，需要自己手动调用；
这是Looper线程实现的典型示例（源码中的注释）：

    class LooperThread extends Thread {
      public Handler mHandler;
      public void run() {
          Looper.prepare();

          mHandler = new Handler() {
              public void handleMessage(Message msg) {
                  // process incoming messages here
              }
          };

          Looper.loop();
      }
    }


关于Looper.prepare()系统源码：

    /** Initialize the current thread as a looper.
      * This gives you a chance to create handlers that then reference
      * this looper, before actually starting the loop. Be sure to call
      * {@link #loop()} after calling this method, and end it by calling
      * {@link #quit()}.
      */
    //将当前线程初始化为looper；确保在调用prepare方法之后，调用loop方法；然后结束时调用quit方法；
    public static void prepare() {
        prepare(true);
    }

    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    //一个Thread只能有一个Looper对象
    private static void prepare(boolean quitAllowed) {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper(quitAllowed));
    }
    //初始化时会创建一个MessageQueue对象和线程
    private Looper(boolean quitAllowed) {
            mQueue = new MessageQueue(quitAllowed);
            mThread = Thread.currentThread();
    }

    /**
     * Initialize the current thread as a looper, marking it as an
     * application's main looper. The main looper for your application
     * is created by the Android environment, so you should never need
     * to call this function yourself.  See also: {@link #prepare()}
     */
    //这个方法在ActivityThread中使用，即表明在Activity是自动创建Looper对象的。（我们永远不需要自己调用这个方法【很抽象的翻译】）
    public static void prepareMainLooper() {
        prepare(false);
        synchronized (Looper.class) {
            if (sMainLooper != null) {
                throw new IllegalStateException("The main Looper has already been prepared.");
            }
            sMainLooper = myLooper();
        }
    }

关于Looper.loop()系统源码：

    /**
     * Run the message queue in this thread. Be sure to call
     * {@link #quit()} to end the loop.
     */
    //在此线程中运行消息队列,确保在结束loop后调用quit
    public static void loop() {
        final Looper me = myLooper();//获取当前looper线程
        //判断是否调用了prepare
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        final MessageQueue queue = me.mQueue;//获取线程中的消息队列

        // Make sure the identity of this thread is that of the local process,
        // and keep track of what that identity token actually is.
        Binder.clearCallingIdentity();
        final long ident = Binder.clearCallingIdentity();

        // Allow overriding a threshold with a system prop. e.g.
        // adb shell 'setprop log.looper.1000.main.slow 1 && stop && start'
        final int thresholdOverride =
                SystemProperties.getInt("log.looper."
                        + Process.myUid() + "."
                        + Thread.currentThread().getName()
                        + ".slow", 0);

        boolean slowDeliveryDetected = false;

        //for(;;)和while(true)  for (；；)指令少，不占用寄存器，而且没有判断跳转
        for (;;) {
            Message msg = queue.next(); // might block
            if (msg == null) {
                // No message indicates that the message queue is quitting.
                return;
            }

            // This must be in a local variable, in case a UI event sets the logger
            final Printer logging = me.mLogging;
            if (logging != null) {
                logging.println(">>>>> Dispatching to " + msg.target + " " +
                        msg.callback + ": " + msg.what);
            }

            final long traceTag = me.mTraceTag;
            long slowDispatchThresholdMs = me.mSlowDispatchThresholdMs;
            long slowDeliveryThresholdMs = me.mSlowDeliveryThresholdMs;
            if (thresholdOverride > 0) {
                slowDispatchThresholdMs = thresholdOverride;
                slowDeliveryThresholdMs = thresholdOverride;
            }
            final boolean logSlowDelivery = (slowDeliveryThresholdMs > 0) && (msg.when > 0);
            final boolean logSlowDispatch = (slowDispatchThresholdMs > 0);

            final boolean needStartTime = logSlowDelivery || logSlowDispatch;
            final boolean needEndTime = logSlowDispatch;

            if (traceTag != 0 && Trace.isTagEnabled(traceTag)) {
                Trace.traceBegin(traceTag, msg.target.getTraceName(msg));
            }

            final long dispatchStart = needStartTime ? SystemClock.uptimeMillis() : 0;
            final long dispatchEnd;
            try {
                //交给Message对应的Handler处理消息
                msg.target.dispatchMessage(msg);
                dispatchEnd = needEndTime ? SystemClock.uptimeMillis() : 0;
            } finally {
                if (traceTag != 0) {
                    Trace.traceEnd(traceTag);
                }
            }
            if (logSlowDelivery) {
                if (slowDeliveryDetected) {
                    if ((dispatchStart - msg.when) <= 10) {
                        Slog.w(TAG, "Drained");
                        slowDeliveryDetected = false;
                    }
                } else {
                    if (showSlowLog(slowDeliveryThresholdMs, msg.when, dispatchStart, "delivery",
                            msg)) {
                        // Once we write a slow delivery log, suppress until the queue drains.
                        slowDeliveryDetected = true;
                    }
                }
            }
            if (logSlowDispatch) {
                showSlowLog(slowDispatchThresholdMs, dispatchStart, dispatchEnd, "dispatch", msg);
            }

            if (logging != null) {
                logging.println("<<<<< Finished to " + msg.target + " " + msg.callback);
            }

            // Make sure that during the course of dispatching the
            // identity of the thread wasn't corrupted.
            final long newIdent = Binder.clearCallingIdentity();
            if (ident != newIdent) {
                Log.wtf(TAG, "Thread identity changed from 0x"
                        + Long.toHexString(ident) + " to 0x"
                        + Long.toHexString(newIdent) + " while dispatching to "
                        + msg.target.getClass().getName() + " "
                        + msg.callback + " what=" + msg.what);
            }

            msg.recycleUnchecked();
        }
    }
总结：
1）一个Thread只能有一个Looper对象，

2）Looper内部有一个消息队列，loop()方法调用后线程开始不断从队列中取出消息执行

3）Looper使一个线程变成Looper线程。

## MessageQueue：
根据源码中的注释翻译如下：
保存由{@link Looper}分派的消息列表的低级类。消息不会直接添加到MessageQueue，而是通过与Looper关联的{@link Handler}对象添加。
您可以使用{@link Looper＃myQueue（）Looper.myQueue（）}检索当前线程的MessageQueue。

    /**
     Low-level class holding the list of messages to be dispatched by a
     {@link Looper}.  Messages are not added directly to a MessageQueue,
     but rather through {@link Handler} objects associated with the Looper.

     <p>You can retrieve the MessageQueue for the current thread with
     {@link Looper#myQueue() Looper.myQueue()}.
     */
    public final class MessageQueue {
        ...
        //获取下一个message方法
        Message next(){
            ...
        }
        //message添加到消息队列的方法
        boolean enqueueMessage(Message msg, long when) {
            ...
        }
    }

## Handler：消息操作类【重点】
Handler允许您发送和处理与线程{@link MessageQueue}关联的{@link Message}和Runnable对象。
每个Handler实例都与一个线程和该线程的消息队列相关联。
当您创建一个新的Handler时，它被绑定到正在创建它的线程的线程/消息队列 - 从那时起，它将消息和runnables传递给该消息队列并在消息出来时执行它们队列。
<p> Handler有两个主要用途：（1）将消息和runnable安排在将来的某个点上执行; （2）将要在不同于自己的线程上执行的动作排入队列。

默认的构造方法会 关联一个looper

    /**
     * Use the {@link Looper} for the current thread with the specified callback interface
     * and set whether the handler should be asynchronous.
     *
     * Handlers are synchronous by default unless this constructor is used to make
     * one that is strictly asynchronous.
     *
     * Asynchronous messages represent interrupts or events that do not require global ordering
     * with respect to synchronous messages.  Asynchronous messages are not subject to
     * the synchronization barriers introduced by {@link MessageQueue#enqueueSyncBarrier(long)}.
     *
     * @param callback The callback interface in which to handle messages, or null.
     * @param async If true, the handler calls {@link Message#setAsynchronous(boolean)} for
     * each {@link Message} that is sent to it or {@link Runnable} that is posted to it.
     *
     * @hide
     */
    public Handler(Callback callback, boolean async) {
        if (FIND_POTENTIAL_LEAKS) {
            final Class<? extends Handler> klass = getClass();
            if ((klass.isAnonymousClass() || klass.isMemberClass() || klass.isLocalClass()) &&
                    (klass.getModifiers() & Modifier.STATIC) == 0) {
                Log.w(TAG, "The following Handler class should be static or leaks might occur: " +
                    klass.getCanonicalName());
            }
        }
        //关联looper，不能为null
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                "Can't create handler inside thread " + Thread.currentThread()
                        + " that has not called Looper.prepare()");
        }
        //直接获取looper的消息队列，
        mQueue = mLooper.mQueue;
        mCallback = callback;
        mAsynchronous = async;
    }

handler发送消息： 可传递参数包括Message和Runnable，但即使传递Runnable对象，最终也被处理为Message对象，然后执行sendMessageAtTime（）方法

    /**
     * Enqueue a message into the message queue after all pending messages
     * before the absolute time (in milliseconds) <var>uptimeMillis</var>.
     * <b>The time-base is {@link android.os.SystemClock#uptimeMillis}.</b>
     * Time spent in deep sleep will add an additional delay to execution.
     * You will receive it in {@link #handleMessage}, in the thread attached
     * to this handler.
     *
     * @param uptimeMillis The absolute time at which the message should be
     *         delivered, using the
     *         {@link android.os.SystemClock#uptimeMillis} time-base.
     *
     * @return Returns true if the message was successfully placed in to the
     *         message queue.  Returns false on failure, usually because the
     *         looper processing the message queue is exiting.  Note that a
     *         result of true does not mean the message will be processed -- if
     *         the looper is quit before the delivery time of the message
     *         occurs then the message will be dropped.
     */
    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        MessageQueue queue = mQueue;
        if (queue == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            Log.w("Looper", e.getMessage(), e);
            return false;
        }
        return enqueueMessage(queue, msg, uptimeMillis);
    }

handler消息的处理 dispatchMessage(msg);此方法在Looper.loop()中调用msg.target.dispatchMessage（）；这里的msg.traget就是指向当前的handler；
然后Handler中调用handleMessage(msg)方法，来触发我们需要实现的具体逻辑。

##### 自我理解： [图解](https://www.processon.com/view/link/5c2494c7e4b0beb2485ee880)

1. 首先会有一个Looper已经存在，并处于轮询的状态；
相对应的代码，Looper.prepare()-->Looper.loop()

2. Handler发送Message,加入到MQ中
各种发送消息的方法-->equeueMessage()-->MessageQueue.equeueMessage

3. 轮询取出MQ的队头Message，通过Handler进行回调
MessageQueue.next()-->(handler)target.dispatchMessage()-->handleMessage()[自定义TODO]