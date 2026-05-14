package com.bhst.dailydango.domain.usecase.lock

import com.bhst.dailydango.domain.repository.lock.LockRepository
import javax.inject.Inject

class LockUseCase @Inject constructor(
    private val lockRepository: LockRepository
) {
    suspend operator fun invoke() = lockRepository.getLockState()
}