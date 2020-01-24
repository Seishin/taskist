package me.seishin.taskist.ui.tasks

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import me.seishin.taskist.data.entities.Task
import me.seishin.taskist.domain.repositories.TasksRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TasksPresenterImplTest {

    private val mockView = mock(TasksContract.TasksView::class.java)
    private val mockRepository = mock(TasksRepository::class.java)

    private val SUT: TasksContract.TasksPresenter = TasksPresenterImpl(mockView, mockRepository)

    @Test
    fun `getAllTasks() success onTasksListObtained() called`() {
        val task = Task("Do homework", false)
        val expectedArray = arrayListOf(task)

        whenever(mockRepository.getAllTasks()).thenReturn(Flowable.just(expectedArray))

        SUT.getAllTasks()

        argumentCaptor<ArrayList<Task>>().apply {
            verify(mockRepository, times(1)).getAllTasks()
            verify(mockView, times(1)).onTasksListObtained(capture())

            assertEquals(expectedArray, firstValue)
        }
    }

    @Test
    fun `getAllTasks() failure onError() called`() {
        val expectedErrorMessage = "Cannot obtain the tasks list!"

        whenever(mockRepository.getAllTasks()).thenReturn(Flowable.error(Throwable(expectedErrorMessage)))

        SUT.getAllTasks()

        argumentCaptor<String>().apply {
            verify(mockRepository, times(1)).getAllTasks()
            verify(mockView, times(1)).onError(capture())
            assertEquals(expectedErrorMessage, firstValue)
        }
    }

    @Test
    fun `createTask() correct task passed`() {
        val expectedTask = Task("Do homework", false)

        whenever(mockRepository.createTask(any())).thenReturn(Maybe.just(0))

        SUT.createTask(expectedTask)

        argumentCaptor<Task>().apply {
            verify(mockRepository, times(1)).createTask(capture())

            assertEquals(expectedTask, firstValue)
        }
    }

    @Test
    fun `createTask() success onTaskCreated() called`() {
        val expectedTask = Task("Do homework", false)

        whenever(mockRepository.createTask(any())).thenReturn(Maybe.just(0))

        SUT.createTask(expectedTask)

        argumentCaptor<Task>().apply {
            verify(mockRepository, times(1)).createTask(capture())
            verify(mockView, times(1)).onTaskCreated()

            assertEquals(expectedTask, firstValue)
        }
    }

    @Test
    fun `createTask() failure onError() called`() {
        val testTask = Task("Do homework", false)
        val expectedErrorMessage = "Cannot create the task!"

        whenever(mockRepository.createTask(any())).thenReturn(Maybe.error(Throwable(expectedErrorMessage)))

        SUT.createTask(testTask)

        argumentCaptor<String>().apply {
            verify(mockRepository, times(1)).createTask(any())
            verify(mockView, times(1)).onError(capture())

            assertEquals(expectedErrorMessage, firstValue)
        }
    }

    @Test
    fun `updateTask() correct task passed`() {
        val expectedTask = Task("Do homework", false)

        whenever(mockRepository.updateTask(any())).thenReturn(Maybe.just(0))

        SUT.updateTask(expectedTask)

        argumentCaptor<Task>().apply {
            verify(mockRepository, times(1)).updateTask(capture())

            assertEquals(expectedTask, firstValue)
        }
    }

    @Test
    fun `updateTask() success onTaskUpdated() called`() {
        val expectedTask = Task("Do homework", false)

        whenever(mockRepository.updateTask(any())).thenReturn(Maybe.just(0))

        SUT.updateTask(expectedTask)

        argumentCaptor<Task>().apply {
            verify(mockRepository, times(1)).updateTask(capture())
            verify(mockView, times(1)).onTaskUpdated(capture())

            assertEquals(expectedTask, firstValue)
            assertEquals(expectedTask, secondValue)
        }
    }

    @Test
    fun `updateTask() failure onError() called`() {
        val testTask = Task("Do homework", false)
        val expectedErrorMessage = "Cannot update the task!"

        whenever(mockRepository.updateTask(any())).thenReturn(Maybe.error(Throwable(expectedErrorMessage)))

        SUT.updateTask(testTask)

        argumentCaptor<String>().apply {
            verify(mockRepository, times(1)).updateTask(any())
            verify(mockView, times(1)).onError(capture())

            assertEquals(expectedErrorMessage, firstValue)
        }
    }

    // deleteTask correct task passed success
    @Test
    fun `deleteTask() correct task passed`() {
        val expectedTask = Task("Do homework", false)

        whenever(mockRepository.deleteTask(any())).thenReturn(Maybe.just(0))

        SUT.deleteTask(expectedTask)

        argumentCaptor<Task>().apply {
            verify(mockRepository, times(1)).deleteTask(capture())

            assertEquals(expectedTask, firstValue)
        }
    }

    @Test
    fun `deleteTask() success onTaskDeleted() called`() {
        val expectedTask = Task("Do homework", false)

        whenever(mockRepository.deleteTask(any())).thenReturn(Maybe.just(0))

        SUT.deleteTask(expectedTask)

        argumentCaptor<Task>().apply {
            verify(mockRepository, times(1)).deleteTask(capture())
            verify(mockView, times(1)).onTaskDeleted(capture())

            assertEquals(expectedTask, firstValue)
            assertEquals(expectedTask, secondValue)
        }
    }

    @Test
    fun `deleteTask() failure onError() called`() {
        val testTask = Task("Do homework", false)
        val expectedErrorMessage = "Cannot update the task!"

        whenever(mockRepository.deleteTask(any())).thenReturn(Maybe.error(Throwable(expectedErrorMessage)))

        SUT.deleteTask(testTask)

        argumentCaptor<String>().apply {
            verify(mockRepository, times(1)).deleteTask(any())
            verify(mockView, times(1)).onError(capture())

            assertEquals(expectedErrorMessage, firstValue)
        }
    }
}