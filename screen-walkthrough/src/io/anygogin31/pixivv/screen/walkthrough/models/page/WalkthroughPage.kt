/*
 * MIT License
 *
 * Copyright (c) 2025 AnyGogin31
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.anygogin31.pixivv.screen.walkthrough.models.page

import io.anygogin31.pixivv.screen.walkthrough.models.button.Agree
import io.anygogin31.pixivv.screen.walkthrough.models.button.Button
import io.anygogin31.pixivv.screen.walkthrough.models.button.OpenBrowser
import io.anygogin31.pixivv.screen.walkthrough.models.button.Transparent

public sealed interface WalkthroughPage {
    public val title: String
    public val description: String?
}

internal data object WelcomePage : WalkthroughPage {
    override val title: String = "Pixivv"
    override val description: String = "Welcome to a third-party client for Pixiv"
}

internal data object ServicePolicyPage : WalkthroughPage {
    override val title: String = "Privacy Policy"
    override val description: String = "Before using the Pixiv service, please review their privacy policy"
    public val buttons: List<Button> =
        listOf(
            Button("Read", OpenBrowser),
            Button("I Agree", Agree),
        )
}

internal data object ClientPolicyPage : WalkthroughPage {
    override val title: String = "Client Privacy Policy"
    override val description: String = "Your privacy matters. We do not collect, store, or share any personal data"
    public val buttons: List<Button> =
        listOf(
            Button("Read", OpenBrowser),
            Button("I Agree", Agree),
        )
}

internal data object AuthorizationPage : WalkthroughPage {
    override val title: String = "Authorization"
    override val description: String? = null
    public val buttons: List<Button> =
        listOf(
            Button("Login", OpenBrowser),
            Button("Don't have an account?", OpenBrowser, Transparent),
        )
}
