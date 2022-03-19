package geekbrians.slava_5655380.ui.fragments.mars

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import geekbrians.slava_5655380.R
import geekbrians.slava_5655380.TimberLogger
import geekbrians.slava_5655380.ui.views.CustomTypefaceSpan

class MarsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        val spannableString = SpannableString(getString(R.string.old_and_wise))
        val textView = view.findViewById<TextView>(R.id.text_view)
        textView.setText(spannableString, TextView.BufferType.SPANNABLE)
        val spannableText = textView.text as Spannable
        spannableText.setSpan(
            RelativeSizeSpan(3f),
            0, 12,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        val font = Typeface.createFromAsset(context?.assets, "fonts/FallingSky-JKwK.otf");
        spannableText.setSpan(
            CustomTypefaceSpan("", font),
            0, 12,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannableText.setSpan(
            CustomTypefaceSpan("", font),
            17, 44,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannableText.setSpan(
            RelativeSizeSpan(1.5f),
            17, 44,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        lifecycle.addObserver(TimberLogger(this))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message, menu)
    }

    companion object {
        fun newInstance(): MarsFragment {
            return MarsFragment()
        }
    }
}