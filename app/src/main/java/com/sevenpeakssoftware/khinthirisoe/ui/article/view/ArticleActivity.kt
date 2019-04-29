package com.sevenpeakssoftware.khinthirisoe.ui.article.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.sevenpeakssoftware.khinthirisoe.R
import com.sevenpeakssoftware.khinthirisoe.data.db.model.ArticleContent
import com.sevenpeakssoftware.khinthirisoe.di.App
import com.sevenpeakssoftware.khinthirisoe.di.component.AppComponent
import com.sevenpeakssoftware.khinthirisoe.di.component.DaggerActivityComponent
import com.sevenpeakssoftware.khinthirisoe.di.module.ActivityModule
import com.sevenpeakssoftware.khinthirisoe.ui.article.ArticleContract
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.Article
import com.sevenpeakssoftware.khinthirisoe.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_article.*
import timber.log.Timber
import javax.inject.Inject

class ArticleActivity : BaseActivity(), ArticleContract.View {

    @Inject
    lateinit var presenter: ArticleContract.Presenter

    private var articleAdapter: ArticleAdapter? = null

    private var connectivityDisposable: CompositeDisposable = CompositeDisposable()

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

        fetchArticles()
    }

    private fun initView() {

        supportActionBar?.title = getString(R.string.toolbar_title)

        val layoutManager = LinearLayoutManager(this)
        recycler_article.layoutManager = layoutManager

        articleAdapter = ArticleAdapter(null)
        recycler_article.adapter = articleAdapter

    }

    private fun fetchArticles() {

        connectivityDisposable.add(
            ReactiveNetwork
                .observeNetworkConnectivity(applicationContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ connectivity ->
                    if (connectivity.available()) {
                        txt_no_internet.visibility = View.GONE
                        fetchDataFromServer()
                    } else {
                        txt_no_internet.visibility = View.VISIBLE
                        fetchDataFromDatabase()
                    }
                }, { throwable ->
                    Timber.d(throwable.localizedMessage)
                })
        )
    }

    private fun fetchDataFromServer() {
        presenter.fetchArticleLists()
    }

    private fun fetchDataFromDatabase() {

        progressBar.visibility = View.VISIBLE

        val contents = App.mDaoSession!!.articleContentDao.queryBuilder().list()

        articleAdapter?.setContent(contents as MutableList<ArticleContent>)
        recycler_article.adapter = articleAdapter

        progressBar.visibility = View.GONE

    }

    override fun showArticleLists(article: Article) {

        saveArticle(article)
    }

    private fun saveArticle(article: Article) {

        if (article.status == "success") {
            for (content in article.content) {
                App.mDaoSession?.articleContentDao?.insertOrReplace(
                    ArticleContent(
                        content.id,
                        content.title,
                        content.dateTime,
                        content.ingress,
                        content.image
                    )
                )
            }
        }
        fetchDataFromDatabase()
    }

    override fun showMessage(message: String) {
        Timber.tag(message)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        connectivityDisposable.dispose()
    }

    override fun onDestroy() {
        connectivityDisposable.clear()
        super.onDestroy()
    }
}
