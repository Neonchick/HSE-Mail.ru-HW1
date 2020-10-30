package kuznetsov.hw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), NumbListFragment.IListener
{
    companion object
    {
        const val DEFAULT_SIZE = 100
        const val EXTRAS_SIZE = "SIZE"
        const val TAG_LIST = "LIST"
        const val TAG_NUMB = "NUMB"
    }

    var listSize = DEFAULT_SIZE

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listSize = savedInstanceState?.getInt(EXTRAS_SIZE) ?: DEFAULT_SIZE
        if (savedInstanceState == null)
        {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_layout, NumbListFragment(), TAG_LIST)
                .commit()
        }
        else
        {
            val numbFragment = supportFragmentManager.findFragmentByTag(TAG_NUMB) as? NumbFragment
            if (numbFragment == null)
            {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_layout, NumbListFragment(), TAG_LIST)
                    .commit()
            }
            else
            {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_layout, numbFragment, TAG_NUMB)
                    .commit()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        outState.putInt(EXTRAS_SIZE, listSize)

        super.onSaveInstanceState(outState)
    }

    override fun onAddClicked()
    {
        listSize++
    }

    override fun onNumbClicked(position: Int)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_layout, NumbFragment.newInstance(position), TAG_NUMB)
            .addToBackStack(null)
            .commit()
    }
}