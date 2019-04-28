package com.sevenpeakssoftware.khinthirisoe.ui.article.presenter

import com.sevenpeakssoftware.khinthirisoe.ui.article.ArticleContract
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.Article
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.ArticleRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class ArticlePresenter @Inject

constructor(var repository: ArticleRepository) : ArticleContract.Presenter {

    private var view: ArticleContract.View? = null
    private var disposable: CompositeDisposable? = null

    override fun fetchArticleLists() {
        repository.getArticleLists()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                if (view != null) view?.showProgress()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                view?.hideProgress()
            }
            .subscribe(object : SingleObserver<Article> {
                override fun onSuccess(response: Article) {
                    view?.showArticleLists(response)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable?.add(d)
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val responseBody = (e).response().errorBody()
                        if (view != null) {
                            view?.showMessage(responseBody.toString())
                            view?.hideProgress()
                        }
                    }
                }

            })
    }

    override fun onAttachView(view: ArticleContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable?.clear()
        this.view = null
    }
}