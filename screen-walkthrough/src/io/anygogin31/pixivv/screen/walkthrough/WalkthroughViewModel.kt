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

package io.anygogin31.pixivv.screen.walkthrough

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.anygogin31.pixivv.screen.walkthrough.models.page.AuthorizationPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.ClientPolicyPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.ServicePolicyPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.WelcomePage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

public class WalkthroughViewModel : ViewModel() {
    private val _state = MutableStateFlow(WalkthroughState())
    public val state: StateFlow<WalkthroughState> =
        _state
            .onStart {
                loadState()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = WalkthroughState(),
            )

    private fun loadState() {
        viewModelScope.launch {
            async {
                loadPages()
            }
        }
    }

    private suspend fun loadPages() {
        withContext(Dispatchers.IO) {
            _state.update {
                it.copy(
                    pages =
                        listOf(
                            WelcomePage,
                            ServicePolicyPage,
                            ClientPolicyPage,
                            AuthorizationPage,
                        ),
                    unlockedPages =
                        setOf(
                            WelcomePage,
                            ServicePolicyPage,
                        ),
                )
            }
        }
    }
}
