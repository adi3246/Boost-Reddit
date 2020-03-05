package com.boost.reddit.module.topicDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boost.reddit.ApplicationConstants
import com.boost.reddit.R
import com.boost.reddit.base.BaseActivity
import com.boost.reddit.databinding.ActivityTopicDetailBinding
import com.boost.reddit.model.TopicModel
import kotlinx.android.synthetic.main.toolbar.view.*

class TopicDetailActivity : BaseActivity() {
    lateinit var viewModel: TopicDetailViewModel
    lateinit var activityBinding: ActivityTopicDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_topic_detail)

        if (intent.getBooleanExtra(ApplicationConstants.CREATE_NEW_TOPIC, false))
            activityBinding.toolbar.toolbar_title.text = resources.getString(R.string.new_topic).toUpperCase()
        else
            activityBinding.toolbar.toolbar_title.text = resources.getString(R.string.topic_detail).toUpperCase()

        setSupportActionBar(activityBinding.toolbar as Toolbar)
        (activityBinding.toolbar as Toolbar).setNavigationOnClickListener { onBackPressed() }

        if (savedInstanceState == null) {}

        viewModel = ViewModelProviders.of(this@TopicDetailActivity).get(TopicDetailViewModel::class.java)
        activityBinding.viewModel = viewModel

        setupViewModelData()

        setupObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.activity_topic_detail_menu, menu)

        menu.findItem(R.id.save_topic).isVisible = intent.getBooleanExtra(ApplicationConstants.CREATE_NEW_TOPIC, false)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_topic -> {
                showAlertOkBtnDialog("", getString(R.string.are_you_sure), object: OnAlertDialogActionListener{
                    override fun onOkClick() {
                        viewModel.saveTopic(activityBinding.textTopic.text.toString())
                    }
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (viewModel.updateTopicList)
            setResult(RESULT_OK, intent)
        super.onBackPressed()
    }

    private fun setupViewModelData(){
        if (intent.getSerializableExtra(ApplicationConstants.TOPIC_MODEL) != null)
            viewModel.setTopicDetail(intent.getSerializableExtra(ApplicationConstants.TOPIC_MODEL) as TopicModel)

        viewModel.setNewTopic(intent.getBooleanExtra(ApplicationConstants.CREATE_NEW_TOPIC, false))
    }

    private fun setupObserver(){
        viewModel.statusMessage.observe(this, Observer{
            if(it == "Success")
                showSnackBar(activityBinding.root, true, it)
        })
    }
}

fun Context.topicDetailIntent(topicModel: TopicModel?, isCreateNew: Boolean): Intent {
    return Intent(this, TopicDetailActivity::class.java).apply {
        putExtra(ApplicationConstants.TOPIC_MODEL, topicModel)
        putExtra(ApplicationConstants.CREATE_NEW_TOPIC, isCreateNew)
    }
}