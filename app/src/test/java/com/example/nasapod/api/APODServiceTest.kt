package com.example.nasapod.api

import android.os.Build
import com.example.nasapod.commons.data.remote.ApodService
import com.example.nasapod.test.DependencyProvider
import com.example.nasapod.utils.Constants.API_KEY
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.apache.maven.model.Dependency
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class APODServiceTest {

    private lateinit var apodService: ApodService
    private lateinit var mockWebServer: MockWebServer


    @Before
    fun init() {
        mockWebServer = MockWebServer()
        apodService = DependencyProvider
            .getRetrofit(mockWebServer.url("/"))
            .create(ApodService::class.java)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getAPODS() {
        queueResponse {
            setResponseCode(200)
            setBody(DependencyProvider.getResponseFromJson("apodlist"))
        }

        apodService
            .getAPODList(API_KEY, "2019-09-15", "2019-09-30")
            .test()
            .run {
                assertNoErrors()
                assertValueCount(1)
                Assert.assertEquals(values()[0].size, 16)
                Assert.assertEquals(values()[0][0].title, "A Long Storm System on Saturn")
                Assert.assertEquals(values()[0][0].date, "2019-09-15")
                Assert.assertEquals(values()[0][15].title, "Orion Rising over Brazil")
                Assert.assertEquals(values()[0][15].date, "2019-09-30")
            }
    }

    private fun queueResponse(block: MockResponse.() -> Unit) {
        mockWebServer.enqueue(MockResponse().apply(block))
    }

}