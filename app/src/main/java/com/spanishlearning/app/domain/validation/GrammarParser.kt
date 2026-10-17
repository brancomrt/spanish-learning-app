package com.spanishlearning.app.domain.validation

import com.spanishlearning.app.domain.model.Correction
import com.spanishlearning.app.domain.model.ErrorType
import com.spanishlearning.app.domain.model.QuestionType

/**
 * Resultado da verificação de estrutura gramatical de uma pergunta.
 *
 * Requisito 2.4: GrammarParser verifica estrutura gramatical (verbo auxiliar, ordem das palavras).
 */
data class StructureCheckResult(
    val isValid: Boolean,
    val errorType: ErrorType?,
    val corrections: List<Correction>
)

/**
 * Analisa e verifica a estrutura gramatical das perguntas formuladas pelo usuário.
 *
 * Requisitos:
 * - 1.4: Suporte a todos os 13 tipos de perguntas
 * - 2.2: Detectar o tipo gramatical da pergunta submetida
 * - 2.4: Verificar estrutura gramatical (verbo auxiliar, ordem das palavras)
 */
class GrammarParser {

    /**
     * Mapeamento de palavras interrogativas para seus QuestionTypes.
     * Ordenado por comprimento decrescente para que "por que", "how much", etc.
     * sejam verificados antes de palavras menores.
     */
    private val whWords: Map<String, QuestionType> = mapOf(
        // Espanhol (normalizado sem acentos)
        "por que" to QuestionType.WH_WHY,
        "porque" to QuestionType.WH_WHY,
        "cuanto" to QuestionType.WH_HOW_MUCH,
        "cuanta" to QuestionType.WH_HOW_MUCH,
        "cuantos" to QuestionType.WH_HOW_MANY,
        "cuantas" to QuestionType.WH_HOW_MANY,
        "adonde" to QuestionType.WH_WHERE,
        "donde" to QuestionType.WH_WHERE,
        "cuando" to QuestionType.WH_WHEN,
        "quienes" to QuestionType.WH_WHO,
        "quien" to QuestionType.WH_WHO,
        "cuales" to QuestionType.WH_WHICH,
        "cual" to QuestionType.WH_WHICH,
        "como" to QuestionType.WH_HOW,
        "que" to QuestionType.WH_WHAT,

        // Inglês (compatibilidade)
        "what" to QuestionType.WH_WHAT,
        "where" to QuestionType.WH_WHERE,
        "when" to QuestionType.WH_WHEN,
        "who" to QuestionType.WH_WHO,
        "why" to QuestionType.WH_WHY,
        "how much" to QuestionType.WH_HOW_MUCH,
        "how many" to QuestionType.WH_HOW_MANY,
        "how" to QuestionType.WH_HOW,
        "which" to QuestionType.WH_WHICH
    )

    /**
     * Conjunto de verbos auxiliares e introdutores de pergunta (Espanhol e Inglês).
     */
    private val auxVerbs: Set<String> = setOf(
        // Espanhol
        "tienes", "tiene", "tienen", "tenemos", "puedes", "puede", "pueden", "podemos",
        "estas", "esta", "estan", "estamos", "eres", "es", "son", "somos", "hay",
        "hiciste", "hizo", "hicieron", "hacemos", "vamos", "va", "van", "debemos",
        "debo", "debe", "sabes", "sabe", "sabemos", "funciona", "fallo", "quieres", "quiere",
        // Inglês
        "do", "does", "did", "is", "are", "was", "were",
        "have", "has", "had", "will", "would", "can", "could",
        "should", "shall", "may", "might", "must"
    )

    /**
     * Detecta o tipo gramatical de uma pergunta normalizada.
     *
     * A verificação segue a seguinte ordem:
     * 1. Wh-questions (por comprimento decrescente para "how much/many" antes de "how")
     * 2. Tag questions (padrão regex: "..., aux + pronoun")
     * 3. Indirect questions (starters conhecidos)
     * 4. Yes/No questions (começa com verbo auxiliar)
     * 5. Fallback para YES_NO
     *
     * Precondição: normalized é string normalizada (lowercase, sem pontuação extra)
     */
    fun detectQuestionType(normalized: String): QuestionType {
        // Verificar Wh-questions (ordem por comprimento para "how much" antes de "how")
        whWords.entries
            .sortedByDescending { it.key.length }
            .forEach { (word, type) ->
                if (normalized.startsWith(word)) return type
            }

        // Verificar Tag questions (padrão: "..., aux + pronome?")
        if (isTagQuestion(normalized)) return QuestionType.TAG

        // Verificar Indirect questions (starters conhecidos)
        if (isIndirectQuestion(normalized)) return QuestionType.INDIRECT

        // Verificar Yes/No question (começa com verbo auxiliar)
        val firstWord = normalized.split(" ").firstOrNull() ?: ""
        return if (firstWord in auxVerbs) QuestionType.YES_NO
        else QuestionType.YES_NO // fallback
    }

    /**
     * Verifica a estrutura gramatical da pergunta com base no tipo esperado.
     *
     * Delega para métodos especializados por tipo de pergunta.
     *
     * Precondição: normalized é string normalizada (lowercase, sem pontuação extra)
     */
    fun checkStructure(
        normalized: String,
        expectedType: QuestionType
    ): StructureCheckResult {
        val tokens = normalized.split(" ")
        return when (expectedType) {
            QuestionType.YES_NO -> checkYesNoStructure(tokens)
            QuestionType.TAG -> checkTagStructure(tokens)
            QuestionType.INDIRECT -> checkIndirectStructure(tokens)
            else -> checkWhStructure(tokens, expectedType)
        }
    }

    /**
     * Verifica se a pergunta Yes/No possui estrutura correta.
     *
     * Estrutura esperada: AUX_VERB + SUBJECT + VERB + (complement)?
     * A pergunta deve começar com um verbo auxiliar.
     *
     * Precondição: tokens não vazio
     */
    private fun checkYesNoStructure(tokens: List<String>): StructureCheckResult {
        // Em espanhol e inglês, aceita se começar com verbo/auxiliar ou se tiver estrutura interrogativa válida
        val firstToken = tokens.firstOrNull() ?: return StructureCheckResult(
            isValid = false,
            errorType = ErrorType.MISSING_AUX_VERB,
            corrections = emptyList()
        )
        // Em espanhol perguntas sim/não podem ter verbo no início ou ordem tonal
        val isValid = firstToken in auxVerbs || tokens.size >= 2
        return StructureCheckResult(
            isValid = isValid,
            errorType = if (!isValid) ErrorType.MISSING_AUX_VERB else null,
            corrections = if (!isValid) listOf(
                Correction(
                    type = ErrorType.MISSING_AUX_VERB,
                    messageKey = "error_missing_aux_verb",
                    suggestion = "Formule a pergunta de sim/não claramente (ex: ¿Tienes acceso al repo?)."
                )
            ) else emptyList()
        )
    }

    /**
     * Verifica se a pergunta Wh- possui estrutura correta.
     */
    private fun checkWhStructure(
        tokens: List<String>,
        type: QuestionType
    ): StructureCheckResult {
        // Pergunta incompleta (em espanhol 2 palavras podem formar pergunta: "¿Qué haces?")
        if (tokens.size < 2) return StructureCheckResult(
            isValid = false,
            errorType = ErrorType.INVERTED_WORD_ORDER,
            corrections = listOf(
                Correction(
                    type = ErrorType.INVERTED_WORD_ORDER,
                    messageKey = "error_incomplete_question",
                    suggestion = "La pregunta está incompleta."
                )
            )
        )

        val whWordTokenCount = when (type) {
            QuestionType.WH_HOW_MUCH, QuestionType.WH_HOW_MANY, QuestionType.WH_WHY -> {
                // "por que", "how much", "how many" = 2 tokens se normalizado com espaço
                if (tokens.getOrNull(0) == "por" && tokens.getOrNull(1) == "que") 2
                else if (tokens.getOrNull(0) == "how" && (tokens.getOrNull(1) == "much" || tokens.getOrNull(1) == "many")) 2
                else 1
            }
            else -> 1
        }

        val afterWh = tokens.drop(whWordTokenCount)
        // Em espanhol o verbo segue a partícula interrogativa ou aceita auxiliares em inglês
        val hasContent = afterWh.isNotEmpty()

        return StructureCheckResult(
            isValid = hasContent,
            errorType = if (!hasContent) ErrorType.MISSING_AUX_VERB else null,
            corrections = if (!hasContent) listOf(
                Correction(
                    type = ErrorType.MISSING_AUX_VERB,
                    messageKey = "error_wh_missing_aux",
                    suggestion = "Após a palavra interrogativa, complete com o verbo e complemento."
                )
            ) else emptyList()
        )
    }

    /**
     * Verifica se a Tag question possui estrutura correta.
     */
    private fun checkTagStructure(tokens: List<String>): StructureCheckResult {
        val isValid = isTagQuestion(tokens.joinToString(" "))
        return StructureCheckResult(
            isValid = isValid,
            errorType = if (!isValid) ErrorType.INVERTED_WORD_ORDER else null,
            corrections = if (!isValid) listOf(
                Correction(
                    type = ErrorType.INVERTED_WORD_ORDER,
                    messageKey = "error_tag_structure",
                    suggestion = "Tag questions seguem o padrão de confirmação: afirmação + , ¿verdad? / , ¿no? / isn't it?"
                )
            ) else emptyList()
        )
    }

    /**
     * Verifica se a Indirect question possui estrutura correta.
     */
    private fun checkIndirectStructure(tokens: List<String>): StructureCheckResult {
        val isValid = tokens.size >= 3
        return StructureCheckResult(
            isValid = isValid,
            errorType = if (!isValid) ErrorType.INVERTED_WORD_ORDER else null,
            corrections = if (!isValid) listOf(
                Correction(
                    type = ErrorType.INVERTED_WORD_ORDER,
                    messageKey = "error_indirect_incomplete",
                    suggestion = "Perguntas indiretas devem ser mais completas. Ex: ¿Podrías decirme dónde está el servidor?"
                )
            ) else emptyList()
        )
    }

    /**
     * Verifica se o texto corresponde ao padrão de Tag question.
     */
    fun isTagQuestion(text: String): Boolean {
        // Espanhol: "..., ¿verdad?", "..., ¿no?", "..., ¿cierto?"
        val spanishTag = Regex(
            """.+,\s*(no|verdad|cierto|o no)\s*$""",
            RegexOption.IGNORE_CASE
        )
        if (spanishTag.containsMatchIn(text)) return true

        // Espanhol normalizado (sem vírgula): "hiciste el deploy verdad", "esta listo no"
        val spanishTagNormalized = Regex(
            """.*\s+(verdad|cierto|no)\s*$""",
            RegexOption.IGNORE_CASE
        )
        if (spanishTagNormalized.containsMatchIn(text) && text.split(" ").size >= 2) return true

        // Inglês
        val tagWithComma = Regex(
            """.+,\s*(isn't|aren't|doesn't|don't|didn't|won't|wouldn't|can't|couldn't|haven't|hasn't|hadn't|is|are|do|does|did|will|would|can|could)\s+(it|he|she|they|you|we|i)\s*$"""
        )
        if (tagWithComma.containsMatchIn(text)) return true

        val tagNormalized = Regex(
            """.*\s+(will not|is not|are not|do not|does not|did not|would not|could not|cannot|have not|has not|had not|was not|were not|might not|should not|is|are|do|does|did|will|would|can|could)\s+(it|he|she|they|you|we|i)\s*$"""
        )
        return tagNormalized.containsMatchIn(text)
    }

    /**
     * Verifica se o texto corresponde ao padrão de Indirect question.
     */
    fun isIndirectQuestion(text: String): Boolean {
        val indirectStarters = listOf(
            // Espanhol
            "podrias decirme", "podria decirme", "puedes decirme", "me puedes decir",
            "me podrias decir", "sabes si", "sabe si", "quisiera saber", "me gustaria saber",
            // Inglês
            "could you tell me", "do you know", "can you tell me", "i wonder",
            "would you mind telling me", "i'd like to know"
        )
        return indirectStarters.any { text.startsWith(it) }
    }

    /**
     * Gera sugestões de correção para o tipo de pergunta incorreto.
     *
     * Precondição: normalized é a pergunta normalizada do usuário
     * Postcondição: retorna lista com pelo menos uma Correction
     */
    fun suggestTypeCorrection(
        normalized: String,
        expectedType: QuestionType
    ): List<Correction> = listOf(
        Correction(
            type = ErrorType.WRONG_QUESTION_TYPE,
            messageKey = "error_wrong_type",
            suggestion = "Este exercício espera uma ${expectedType.displayName()}."
        )
    )
}
