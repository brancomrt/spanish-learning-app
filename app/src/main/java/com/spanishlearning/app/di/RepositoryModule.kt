package com.spanishlearning.app.di

import com.spanishlearning.app.data.repository.ContentRepositoryImpl
import com.spanishlearning.app.data.repository.ProgressRepositoryImpl
import com.spanishlearning.app.data.repository.SrsRepositoryImpl
import com.spanishlearning.app.domain.gamification.StreakCalculator
import com.spanishlearning.app.domain.repository.ContentRepository
import com.spanishlearning.app.domain.repository.ProgressRepository
import com.spanishlearning.app.domain.repository.SrsRepository
import com.spanishlearning.app.domain.validation.GrammarParser
import com.spanishlearning.app.domain.validation.QuestionValidationEngine
import com.spanishlearning.app.domain.validation.QuestionValidationEngineImpl
import com.spanishlearning.app.domain.validation.SemanticMatcher
import com.spanishlearning.app.domain.validation.TextNormalizer
import dagger.Binds
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module que vincula as interfaces de repositório às suas implementações
 * concretas e provê o motor de validação e seus colaboradores.
 *
 * Requisito 9.4: persistência e acesso offline via repositórios vinculados aqui.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Vincula [ContentRepositoryImpl] à interface [ContentRepository].
     *
     * Requisito 9.4: ContentRepository provê acesso offline a níveis, categorias e exercícios.
     */
    @Binds
    @Singleton
    abstract fun bindContentRepository(impl: ContentRepositoryImpl): ContentRepository

    /**
     * Vincula [ProgressRepositoryImpl] à interface [ProgressRepository].
     *
     * Requisito 9.4: ProgressRepository persiste tentativas, streaks e conquistas.
     */
    @Binds
    @Singleton
    abstract fun bindProgressRepository(impl: ProgressRepositoryImpl): ProgressRepository

    /**
     * Vincula [SrsRepositoryImpl] à interface [SrsRepository].
     *
     * Requisito 16.1: SrsRepository gerencia criação e agendamento de SRS cards.
     * Requisito 16.6: observable de cards devidos para badge na HomeScreen.
     * Requisito 18.6: persistência offline completa.
     */
    @Binds
    @Singleton
    abstract fun bindSrsRepository(impl: SrsRepositoryImpl): SrsRepository

    companion object {

        /**
         * Provê [QuestionValidationEngineImpl] como implementação de [QuestionValidationEngine].
         *
         * Requisitos 2.1–2.10: motor de validação com pipeline de normalização, parsing
         * gramatical e matching semântico.
         */
        @Provides
        @Singleton
        fun provideQuestionValidationEngine(
            textNormalizer: TextNormalizer,
            grammarParser: GrammarParser,
            semanticMatcher: SemanticMatcher
        ): QuestionValidationEngine {
            return QuestionValidationEngineImpl(textNormalizer, grammarParser, semanticMatcher)
        }

        @Provides
        @Singleton
        fun provideTextNormalizer(): TextNormalizer = TextNormalizer()

        @Provides
        @Singleton
        fun provideGrammarParser(): GrammarParser = GrammarParser()

        @Provides
        @Singleton
        fun provideSemanticMatcher(): SemanticMatcher = SemanticMatcher()

        @Provides
        @Singleton
        fun provideStreakCalculator(): StreakCalculator = StreakCalculator()
    }
}
