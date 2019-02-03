package jp.ne.sonet.blog.sora0125.customchoosersample

import android.content.pm.ResolveInfo
import android.widget.Toast
import com.github.zawadz88.activitychooser.MaterialActivityChooserActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import java.util.HashMap


class TrackingActivityChooserActivity: MaterialActivityChooserActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onActivityClicked(activity: ResolveInfo?) {
        Toast.makeText(this, "Application clicked: " + activity?.activityInfo?.packageName, Toast.LENGTH_SHORT).show()
        val hashTag = super.getIntent().getStringExtra(INTENT_KEY)
        Log.d("TrackingActivityChooser", "Intent.EXTRA_TEXT:$hashTag")

        val secondaryIntent = intent.getSerializableExtra(SECONDARY_INTENTS_KEY) as? HashMap<String, Intent>
        Log.d("TrackingActivityChooser", "secondaryIntent:$secondaryIntent")
        val aaa = secondaryIntent?.get("com.google.android.gm")
        Log.d("TrackingActivityChooser", "aaa:$aaa")
        val bbb = aaa?.getStringExtra("normal")
        Log.d("TrackingActivityChooser", "bbb:$bbb")

        val text = aaa?.getStringExtra("hashtag")
        Log.d("TrackingActivityChooser", "text:$text")

        if (activity?.activityInfo?.packageName == "com.facebook.katana") {
            val intent = Intent(Intent.ACTION_VIEW)
            val messsage = Uri.encode("テスト ＃コメント $bbb $text")
            intent.data = Uri.parse("twitter://post?message=$messsage")
            startActivity(intent)
        } else {
            super.onActivityClicked(activity)
        }
    }
}