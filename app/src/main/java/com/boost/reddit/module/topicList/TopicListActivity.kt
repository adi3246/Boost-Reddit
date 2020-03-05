package com.boost.reddit.module.topicList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.boost.reddit.ApplicationConstants
import com.boost.reddit.R
import com.boost.reddit.base.BaseActivity
import com.boost.reddit.databinding.ActivityTopicListBinding
import com.boost.reddit.model.TopicModel
import com.boost.reddit.module.topicDetail.topicDetailIntent
import kotlinx.android.synthetic.main.toolbar.view.*

class TopicListActivity : BaseActivity() {
    lateinit var viewModel: TopicListViewModel
    lateinit var activityBinding: ActivityTopicListBinding
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_topic_list)
        activityBinding.toolbar.toolbar_title.text = resources.getString(R.string.topic_list).toUpperCase()

        setSupportActionBar(activityBinding.toolbar as Toolbar)
        (activityBinding.toolbar as Toolbar).setNavigationOnClickListener { onBackPressed() }
        hideBackButton()

        viewModel = ViewModelProviders.of(this@TopicListActivity).get(TopicListViewModel::class.java)
        activityBinding.viewModel = viewModel

        setupRecyclerView()

        setupButtonListener()

        if (savedInstanceState == null) {
            viewModel.mockData()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ApplicationConstants.NEW_TOPIC && resultCode == Activity.RESULT_OK) {
            viewModel.addNewTopicList(data!!.getSerializableExtra(ApplicationConstants.NEW_TOPIC_MODEL_DATA) as TopicModel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.activity_recipe_list_menu, menu)
        menu.findItem(R.id.add_recipe).isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_recipe -> {
                startActivityForResult(topicDetailIntent(null, true),
                    ApplicationConstants.NEW_TOPIC)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView(){
        activityBinding.rvTopic.adapter =
            TopicListAdapter(
                this,
                object : TopicListAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        startActivity(topicDetailIntent(viewModel.topicListForm.topicList[position], false))
                    }
                })

        activityBinding.rvTopic.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !activityBinding.btnAddRecipe.isShown) {
                    activityBinding.btnAddRecipe.show()
                    menu.findItem(R.id.add_recipe).isVisible = false
                }else if (dy > 0 && activityBinding.btnAddRecipe.isShown) {
                    activityBinding.btnAddRecipe.hide()
                    menu.findItem(R.id.add_recipe).isVisible = true
                }
            }
        })

        activityBinding.rvTopic.addItemDecoration(DividerItemDecoration(activityBinding.rvTopic.context, DividerItemDecoration.VERTICAL))
    }

    private fun setupButtonListener(){
        activityBinding.btnAddRecipe.setOnClickListener {
            startActivityForResult(topicDetailIntent(null, true),
                ApplicationConstants.NEW_TOPIC)
        }
    }
}