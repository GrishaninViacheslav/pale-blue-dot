package geekbrians.slava_5655380

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class TimberLogger(private val lifecycleOwner: LifecycleOwner) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Timber.i("onResume:%s", lifecycleOwner.javaClass.simpleName)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Timber.i("onPause:%s", lifecycleOwner.javaClass.simpleName)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Timber.i("onStart:%s", lifecycleOwner.javaClass.simpleName)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Timber.i("onStop:%s", lifecycleOwner.javaClass.simpleName)
    }
}