/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 https://www.spaceotechnologies.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.usend.comman


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.usend.repository.AuthenticationRepository
import com.usend.repository.UserRepository
import com.usend.viewmodels.*


/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelProviderFactory(application: Application) : ViewModelProvider.Factory {

    var app: Application = application

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                return SplashViewModel(
                    application = app
                ) as T
            }
            modelClass.isAssignableFrom(IntroViewModel::class.java) -> {
                return IntroViewModel(
                    application = app,
                    repository = AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                return SignInViewModel(
                    application = app,
                    repository = AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                return SignUpViewModel(
                    application = app,
                    repository = AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(BillingAddressViewModel::class.java) -> {
                return BillingAddressViewModel(
                    application = app,
                    repository = AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(PaymentInfoViewModel::class.java) -> {
                return PaymentInfoViewModel(
                    application = app,
                    repository = AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(RegionSelectionViewModel::class.java) -> {
                return RegionSelectionViewModel(
                    application = app,
                    repository = AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(ForgotPassViewModel::class.java) -> {
                return ForgotPassViewModel(
                    application = app,
                    repository = AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(MailboxViewModel::class.java) -> {
                return MailboxViewModel(
                    myApplication = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(PackageDetailViewModel::class.java) -> {
                return PackageDetailViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(AddNewAddressViewModel::class.java) -> {
                return AddNewAddressViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(ShippingMethodViewModel::class.java) -> {
                return ShippingMethodViewModel(
                    myApplication = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(ShippingDetailViewModel::class.java) -> {
                return ShippingDetailViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(AddCardViewModel::class.java) -> {
                return AddCardViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(CalculatorViewModel::class.java) -> {
                return CalculatorViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(CreateReqViewModel::class.java) -> {
                return CreateReqViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(EditProfileViewModel::class.java) -> {
                return EditProfileViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(ContactUsViewModel::class.java) -> {
                return ContactUsViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(USPSVerificationViewModel::class.java) -> {
                return USPSVerificationViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(MyProfileViewModel::class.java) -> {
                return MyProfileViewModel(
                    myApplication = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(ChangePasswordViewModel::class.java) -> {
                return ChangePasswordViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(OtpVerificationViewModel::class.java) -> {
                return OtpVerificationViewModel(
                    application = app,
                    repository = AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(ShipToAddressViewModel::class.java) -> {
                return ShipToAddressViewModel(
                    myApplication = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(OrderViewModel::class.java) -> {
                return OrderViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(ContentPageViewModel::class.java) -> {
                return ContentPageViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(ConciergeViewModel::class.java) -> {
                return ConciergeViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> {
                return NotificationViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(ShippingCostViewModel::class.java) -> {
                return ShippingCostViewModel(
                    application = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(SavedCardsViewModel::class.java) -> {
                return SavedCardsViewModel(
                    myApplication = app,
                    repository = UserRepository
                ) as T
            }
            modelClass.isAssignableFrom(AutoShipViewModel::class.java) -> {
                return AutoShipViewModel(
                    myApplication = app
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
