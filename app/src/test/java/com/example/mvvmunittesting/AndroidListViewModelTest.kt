package com.example.mvvmunittesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmunittesting.model.ApiResponseModel
import com.example.mvvmunittesting.repo.RetrofitApiRepo
import com.example.mvvmunittesting.util.Resource
import com.example.mvvmunittesting.viewmodel.AndroidListViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.nio.charset.Charset

@ExperimentalCoroutinesApi
class AndroidListViewModelTest {
    @Mock
    lateinit var repo: RetrofitApiRepo

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var myViewModel: AndroidListViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        myViewModel = AndroidListViewModel(repo)
    }
    @After
    fun tearDown() {
        // Resets state of the [Dispatchers.Main] to the original main dispatcher.
        // For example, in Android Main thread dispatcher will be set as [Dispatchers.Main].
        Dispatchers.resetMain()

        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun checkViewModelNotNullTest() = runBlocking {
            Assert.assertNotNull(myViewModel)
    }


    @Test
    fun checkApiCallDataPassTest() {
        mockServiceResponse("wbservice_response.json")
        Assert.assertEquals(
                "Cupcake",
                myViewModel.users.value?.data?.android?.get(0)?.name
        )
        Assert.assertEquals("API level 3",
                myViewModel.users.value?.data?.android?.get(0)?.api
        )
        Assert.assertEquals("1.5",
                myViewModel.users.value?.data?.android?.get(0)?.ver
        )
        Assert.assertEquals(12,
                myViewModel.users.value?.data?.android?.size
        )

    }

    /**
     * Method to check api failed state
     */
    @Test
    fun checkApiCallDataFAILTest() {
        mockServiceResponse("error_mock.json")
        Assert.assertNotNull (myViewModel._users)
    }

    /**
     * Method to mock web service response
     */
    private fun mockServiceResponse(jsonFileName: String) {
        val content = this.javaClass.classLoader?.getResource(jsonFileName)?.readText(
            Charset.forName("UTF-8")
        )
        val jsonModel = Gson().fromJson(content, ApiResponseModel::class.java)
        myViewModel._users.value = Resource.success(jsonModel)
    }
}