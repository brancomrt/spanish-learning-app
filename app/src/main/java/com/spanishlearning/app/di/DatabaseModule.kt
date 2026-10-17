package com.spanishlearning.app.di

import android.content.Context
import androidx.room.Room
import com.spanishlearning.app.data.database.SpanishLearningDatabase
import com.spanishlearning.app.data.database.dao.AcceptedAnswerDao
import com.spanishlearning.app.data.database.dao.AchievementDao
import com.spanishlearning.app.data.database.dao.CategoryDao
import com.spanishlearning.app.data.database.dao.ExerciseDao
import com.spanishlearning.app.data.database.dao.HintDao
import com.spanishlearning.app.data.database.dao.LevelDao
import com.spanishlearning.app.data.database.dao.ProgressDao
import com.spanishlearning.app.data.database.dao.SrsDao
import com.spanishlearning.app.data.database.dao.StreakDao
import com.spanishlearning.app.data.database.dao.UserProfileDao
import com.spanishlearning.app.data.database.dao.VocabularyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module que provê o [SpanishLearningDatabase] e todos os seus DAOs
 * no escopo Singleton da aplicação.
 *
 * Inclui a migration [SpanishLearningDatabase.MIGRATION_1_2] que adiciona as
 * tabelas `srs_cards` e `vocabulary_cards` sem destruir dados existentes.
 *
 * Requisito 9.2: banco de dados Room inicializado como Singleton com
 * DatabaseSeeder chamado de forma assíncrona na inicialização.
 * Requisito 16.2, 18.6: migration para versão 2 com suporte a SRS.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Cria e fornece o banco de dados Room como Singleton.
     *
     * A migration [SpanishLearningDatabase.MIGRATION_1_2] é registrada explicitamente
     * para preservar dados de usuários ao adicionar as novas tabelas SRS.
     * [fallbackToDestructiveMigration] cobre quaisquer outras transições de esquema
     * não mapeadas durante o desenvolvimento.
     *
     * Requisito 9.2: banco Room configurado como fonte de dados local única.
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SpanishLearningDatabase {
        return Room.databaseBuilder(
            context,
            SpanishLearningDatabase::class.java,
            SpanishLearningDatabase.DATABASE_NAME
        )
            .addMigrations(SpanishLearningDatabase.MIGRATION_1_2, SpanishLearningDatabase.MIGRATION_2_3, SpanishLearningDatabase.MIGRATION_3_4)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideLevelDao(db: SpanishLearningDatabase): LevelDao = db.levelDao()

    @Provides
    fun provideCategoryDao(db: SpanishLearningDatabase): CategoryDao = db.categoryDao()

    @Provides
    fun provideExerciseDao(db: SpanishLearningDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    fun provideAcceptedAnswerDao(db: SpanishLearningDatabase): AcceptedAnswerDao =
        db.acceptedAnswerDao()

    @Provides
    fun provideHintDao(db: SpanishLearningDatabase): HintDao = db.hintDao()

    @Provides
    fun provideProgressDao(db: SpanishLearningDatabase): ProgressDao = db.progressDao()

    @Provides
    fun provideStreakDao(db: SpanishLearningDatabase): StreakDao = db.streakDao()

    @Provides
    fun provideAchievementDao(db: SpanishLearningDatabase): AchievementDao = db.achievementDao()

    @Provides
    fun provideUserProfileDao(db: SpanishLearningDatabase): UserProfileDao = db.userProfileDao()

    /** Provê [SrsDao] para operações com SRS cards. Requisito 16.2. */
    @Provides
    fun provideSrsDao(db: SpanishLearningDatabase): SrsDao = db.srsDao()

    /** Provê [VocabularyDao] para operações com vocabulary cards. Requisito 16.9. */
    @Provides
    fun provideVocabularyDao(db: SpanishLearningDatabase): VocabularyDao = db.vocabularyDao()
}
