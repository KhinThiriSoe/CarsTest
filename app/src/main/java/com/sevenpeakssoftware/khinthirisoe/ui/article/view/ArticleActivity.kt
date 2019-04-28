package com.sevenpeakssoftware.khinthirisoe.ui.article.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sevenpeakssoftware.khinthirisoe.R
import com.sevenpeakssoftware.khinthirisoe.di.App
import com.sevenpeakssoftware.khinthirisoe.di.component.AppComponent
import com.sevenpeakssoftware.khinthirisoe.di.component.DaggerActivityComponent
import com.sevenpeakssoftware.khinthirisoe.di.module.ActivityModule
import com.sevenpeakssoftware.khinthirisoe.ui.article.ArticleContract
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.Article
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.Content
import com.sevenpeakssoftware.khinthirisoe.ui.base.BaseActivity
import com.sevenpeakssoftware.khinthirisoe.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_article.*
import javax.inject.Inject

class ArticleActivity : BaseActivity(), ArticleContract.View {

    @Inject
    lateinit var presenter: ArticleContract.Presenter

    private var articleAdapter: ArticleAdapter? = null

    override fun setupComponent(appComponent: AppComponent) {
        val component = DaggerActivityComponent.builder().appComponent(App.appComponent)
            .activityModule(ActivityModule(this)).build()
        component.inject(this)
        presenter.onAttachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        initView()

        fetchData()
    }

    private fun fetchData() {
        if (NetworkUtils.hasNetwork(this))
            presenter.fetchLists()
    }

    private fun initView() {

        supportActionBar?.title = getString(R.string.toolbar_title)

        val layoutManager = LinearLayoutManager(this)

        recycler_car_item.layoutManager = layoutManager

        articleAdapter = ArticleAdapter(null)
        recycler_car_item.adapter = articleAdapter

    }

    override fun showLists(article: Article) {

        articleAdapter?.setContent(article.content as MutableList<Content>)
        recycler_car_item.adapter = articleAdapter
    }

    override fun showMessage(message: String) {
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    companion object {

        val TAG = ArticleActivity::class.java.simpleName
    }
}
