package com.spanishlearning.app.domain.usecase

import com.spanishlearning.app.domain.gamification.LevelProgressionAlgorithm
import com.spanishlearning.app.domain.model.AttemptResult
import com.spanishlearning.app.domain.model.LevelProgress
import com.spanishlearning.app.domain.model.SrsCardType
import com.spanishlearning.app.domain.model.ValidationStatus
import com.spanishlearning.app.domain.repository.ContentRepository
import com.spanishlearning.app.domain.repository.ProgressRepository
import com.spanishlearning.app.domain.repository.SrsRepository
import javax.inject.Inject

/**
 * Persiste o resultado de uma tentativa, atualiza o streak diário do usuário,
 * desbloqueia o próximo nível quando os critérios são atingidos e cria/obtém um
 * SRS card para exercícios respondidos corretamente.
 */
class UpdateProgressUseCase @Inject constructor(
    private val progressRepository: ProgressRepository,
    private val srsRepository: SrsRepository,
    private val contentRepository: ContentRepository?
) {

    constructor(
        progressRepository: ProgressRepository,
        srsRepository: SrsRepository
    ) : this(progressRepository, srsRepository, null)

    suspend operator fun invoke(
        userId: Long,
        levelId: Int,
        categoryId: Int,
        attemptResult: AttemptResult
    ) {
        progressRepository.updateProgressWithLevel(userId, levelId, categoryId, attemptResult)
        progressRepository.updateStreak(userId)

        // Requisitos 16.1, 18.1: criar/obter SrsCard do tipo GRAMMAR para o exercício
        // apenas quando o usuário acertou (CORRECT) ou chegou muito perto (TOO_CLOSE).
        val status = attemptResult.validationResult.status
        if (status == ValidationStatus.CORRECT || status == ValidationStatus.TOO_CLOSE) {
            srsRepository.getOrCreateCard(
                userId = userId,
                cardType = SrsCardType.GRAMMAR,
                refId = attemptResult.exerciseId
            )

            // Requisito 6.2: verificar se o usuário atingiu os critérios para desbloquear o próximo nível
            contentRepository?.let { contentRepo ->
                val allLevels = contentRepo.getLevels()
                val levelCategories = contentRepo.getCategories().filter { it.levelId == levelId }
                val progressList = levelCategories.map { cat ->
                    progressRepository.getLevelProgress(userId, levelId, cat.id)
                }
                val aggregated = LevelProgress(
                    userId = userId,
                    levelId = levelId,
                    categoryId = 0,
                    exercisesCompleted = progressList.sumOf { it.exercisesCompleted },
                    totalAttempts = progressList.sumOf { it.totalAttempts },
                    correctAnswers = progressList.sumOf { it.correctAnswers },
                    totalXpEarned = progressList.sumOf { it.totalXpEarned }
                )
                val nextLevelId = LevelProgressionAlgorithm.computeNextLevel(
                    currentLevelId = levelId,
                    progress = aggregated,
                    totalLevels = allLevels.size.coerceAtLeast(6)
                )
                if (nextLevelId != null) {
                    contentRepo.unlockLevel(nextLevelId)
                }
            }
        }
    }
}
