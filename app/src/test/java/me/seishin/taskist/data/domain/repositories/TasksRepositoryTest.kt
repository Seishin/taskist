package me.seishin.taskist.data.domain.repositories

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert.assertEquals
import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.data.daos.TasksDao
import me.seishin.taskist.data.entities.Task
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockitokotlin2.any as anyObj

@RunWith(MockitoJUnitRunner::class)
class TasksRepositoryTest {

    private val dbMock: AppDatabase.Database = mock(AppDatabase.Database::class.java)
    private val taskDaoMock: TasksDao = mock(TasksDao::class.java)

    private val testScheduler: TestScheduler = TestScheduler()

    private val SUT: TasksRepository = TasksRepositoryImpl(dbMock, testScheduler, testScheduler)

    @Before
    fun setUp() {
        `when`(dbMock.tasksDao()).thenReturn(taskDaoMock)
    }

    @Test
    fun `getAllTasks() Success`() {
        val testObserver = TestSubscriber<List<Task>>()

        val testTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())
        val expectedList = listOf(testTask)
        `when`(taskDaoMock.getAllTasks()).thenReturn(Flowable.just(expectedList))

        SUT.getAllTasks().subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertValue(expectedList)
    }

    @Test
    fun `getAllTasks() Failure`() {
        val testSubscriber = TestSubscriber<List<Task>>()

        val expectedError = Throwable("Cannot obtain the tasks list!")
        `when`(taskDaoMock.getAllTasks()).thenReturn(Flowable.error(expectedError))

        SUT.getAllTasks().subscribe(testSubscriber)
        testScheduler.triggerActions()

        testSubscriber.assertError(expectedError)
    }

    @Test
    fun `getTask() correct id passed`() {
        val expectedId = 0

        `when`(taskDaoMock.getTask(anyInt())).thenReturn(Single.never())

        SUT.getTask(expectedId).subscribe()
        testScheduler.triggerActions()

        argumentCaptor<Int>().apply {
            verify(taskDaoMock, times(1)).getTask(capture())
            assertEquals(expectedId, firstValue)
        }
    }

    @Test
    fun `getTask() task return success`() {
        val testObserver = TestObserver<Task>()

        val expectedTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())

        `when`(taskDaoMock.getTask(anyInt())).thenReturn(Single.just(expectedTask))

        SUT.getTask(0).subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertValue(expectedTask)
    }

    @Test
    fun `getTask() task return failure`() {
        val testObserver = TestObserver<Task>()

        val expectedError = Throwable("The task cannot be returned!")

        `when`(taskDaoMock.getTask(anyInt())).thenReturn(Single.error(expectedError))

        SUT.getTask(1).subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertError(expectedError)
    }

    // createTask() correct task passed success
    @Test
    fun `createTask() correct task passed`() {
        val expectedTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())

        `when`(taskDaoMock.createTask(anyObj())).thenReturn(Completable.complete())

        SUT.createTask(expectedTask).subscribe()
        testScheduler.triggerActions()

        argumentCaptor<Task>().apply {
            verify(taskDaoMock, times(1)).createTask(capture())
            assertEquals(expectedTask, firstValue)
        }
    }

    @Test
    fun `createTask() success returned`() {
        val testObserver = TestObserver<Any>()

        val testTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())

        `when`(taskDaoMock.createTask(anyObj())).thenReturn(Completable.complete())

        SUT.createTask(testTask).subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertComplete()
    }

    @Test
    fun `createTask() failure returned`() {
        val testObserver = TestObserver<Any>()

        val testTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())
        val expectedError = Throwable("Cannot update the task!")

        `when`(taskDaoMock.createTask(anyObj())).thenReturn(Completable.error(expectedError))

        SUT.createTask(testTask).subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertError(expectedError)
    }

    @Test
    fun `updateTask() correct task passed`() {
        val expectedTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())

        `when`(taskDaoMock.updateTask(anyObj())).thenReturn(Completable.complete())

        SUT.updateTask(expectedTask).subscribe()
        testScheduler.triggerActions()

        argumentCaptor<Task>().apply {
            verify(taskDaoMock, times(1)).updateTask(capture())
            assertEquals(expectedTask, firstValue)
        }
    }

    @Test
    fun `updateTask() success returned`() {
        val testObserver = TestObserver<Any>()

        val testTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())

        `when`(taskDaoMock.updateTask(anyObj())).thenReturn(Completable.complete())

        SUT.updateTask(testTask).subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertComplete()
    }

    @Test
    fun `updateTask() failure returned`() {
        val testObserver = TestObserver<Any>()

        val testTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())
        val expectedError = Throwable("Cannot update the task!")

        `when`(taskDaoMock.updateTask(anyObj())).thenReturn(Completable.error(expectedError))

        SUT.updateTask(testTask).subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertError(expectedError)
    }

    @Test
    fun `deleteTask() correct task passed`() {
        val expectedTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())

        `when`(taskDaoMock.deleteTask(anyObj())).thenReturn(Maybe.empty())

        SUT.deleteTask(expectedTask).subscribe()
        testScheduler.triggerActions()

        argumentCaptor<Task>().apply {
            verify(taskDaoMock, times(1)).deleteTask(capture())
            assertEquals(expectedTask, firstValue)
        }
    }

    @Test
    fun `deleteTask() success returned`() {
        val testObserver = TestObserver<Int>()

        val testTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())
        val expectedResult = 1

        `when`(taskDaoMock.deleteTask(anyObj())).thenReturn(Maybe.just(expectedResult))

        SUT.deleteTask(testTask).subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertValue(expectedResult)
    }

    @Test
    fun `deleteTask() failure returned`() {
        val testObserver = TestObserver<Any>()

        val testTask = Task("Do homework", false, System.currentTimeMillis(), System.currentTimeMillis())
        val expectedError = Throwable("Cannot delete the task!")

        `when`(taskDaoMock.deleteTask(anyObj())).thenReturn(Maybe.error(expectedError))

        SUT.deleteTask(testTask).subscribe(testObserver)
        testScheduler.triggerActions()

        testObserver.assertError(expectedError)
    }
}