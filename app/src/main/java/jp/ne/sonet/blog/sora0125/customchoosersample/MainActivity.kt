package jp.ne.sonet.blog.sora0125.customchoosersample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.zawadz88.activitychooser.MaterialActivityChooserActivity
import com.github.zawadz88.activitychooser.MaterialActivityChooserBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TEXT_PLAIN_TYPE = "text/plain"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val someIntent = Intent(Intent.ACTION_SEND)
        someIntent.type = "text/plain"
        someIntent.putExtra("hashtag", "#test")
        someIntent.putExtra(Intent.EXTRA_TEXT, "extra_text shared link: http://www.google.com #test")

        text1.setOnClickListener {
            MaterialActivityChooserBuilder(this)
                .withIntent(someIntent)
                .withActivity(TrackingActivityChooserActivity::class.java)
                .withTitle("共有")
                .withSecondaryIntent(getSecondaryShareIntent(), "com.google.android.gm" )
                .show()
        }
    }

    private fun getSecondaryShareIntent(): Intent {
        val shareTextIntent = Intent(Intent.ACTION_SEND)
        shareTextIntent.type = TEXT_PLAIN_TYPE
        shareTextIntent.putExtra(Intent.EXTRA_TITLE, "Secondary text to share https://www.yahoo.co.jp/")
        shareTextIntent.putExtra(Intent.EXTRA_TEXT, "Secondary shared link: http://www.google.com")
        shareTextIntent.putExtra("normal", "normal shared link: http://www.google.com")
        shareTextIntent.putExtra("hashtag", "#aaa14")
        return shareTextIntent
    }
}
