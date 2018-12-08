package com.dialogapp.dialog.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dialogapp.dialog.di.ViewModelKey
import com.dialogapp.dialog.ui.common.BaseListViewModel
import com.dialogapp.dialog.ui.common.BasePagedListViewModel
import com.dialogapp.dialog.ui.login.LoginViewModel
import com.dialogapp.dialog.ui.util.MicroblogViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BasePagedListViewModel::class)
    abstract fun bindBasePagedListViewModel(basePagedListViewModel: BasePagedListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BaseListViewModel::class)
    abstract fun bindBaseListViewModel(baseListViewModel: BaseListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MicroblogViewModelFactory): ViewModelProvider.Factory
}
