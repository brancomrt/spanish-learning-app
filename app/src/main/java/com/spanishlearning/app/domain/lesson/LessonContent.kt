package com.spanishlearning.app.domain.lesson

import com.spanishlearning.app.domain.model.QuestionType

/**
 * Conteúdo de uma MiniLesson gramatical para um tipo de pergunta específico em Espanhol de TI.
 *
 * Cobre todos os 13 QuestionTypes com foco na comunicação técnica profissional na América Latina.
 */
sealed class LessonContent(
    val questionType: QuestionType,
    val titlePt: String,
    val ruleExplanationPt: String,
    val structuralFormula: String,
    val examples: List<String>
) {
    // ---- Wh-questions ----

    object WhatLesson : LessonContent(
        questionType = QuestionType.WH_WHAT,
        titlePt = "Perguntas com QUÉ",
        ruleExplanationPt = "Usamos 'Qué' para perguntar sobre coisas, especificações ou ações em geral. " +
            "Em espanhol, o verbo conjugado vem logo em seguida da partícula interrogativa. " +
            "A palavra 'qué' leva acento quando é interrogativa.",
        structuralFormula = "¿Qué + verbo + (sujeito) + complemento?",
        examples = listOf(
            "¿Qué especificaciones tiene tu computadora?",
            "¿Qué hiciste en la daily de hoy?"
        )
    )

    object WhereLesson : LessonContent(
        questionType = QuestionType.WH_WHERE,
        titlePt = "Perguntas com DÓNDE",
        ruleExplanationPt = "Usamos 'Dónde' ou 'Adónde' para perguntar sobre lugar, repositório, branch ou servidores. " +
            "Leva acento na função interrogativa.",
        structuralFormula = "¿Dónde + verbo + (sujeito) + complemento?",
        examples = listOf(
            "¿Dónde está el ticket en el tablero?",
            "¿Dónde está el cuello de botella?"
        )
    )

    object WhenLesson : LessonContent(
        questionType = QuestionType.WH_WHEN,
        titlePt = "Perguntas com CUÁNDO",
        ruleExplanationPt = "Usamos 'Cuándo' ou 'A qué hora' para perguntar sobre tempo, prazos de sprint ou momentos de deploy. " +
            "Combina-se com verbos no presente, pretérito ou futuro.",
        structuralFormula = "¿Cuándo + verbo + complemento? / ¿A qué hora + verbo?",
        examples = listOf(
            "¿Cuándo haremos el despliegue a producción?",
            "¿A qué hora comienza la reunión diaria?"
        )
    )

    object WhoLesson : LessonContent(
        questionType = QuestionType.WH_WHO,
        titlePt = "Perguntas com QUIÉN / QUIÉNES",
        ruleExplanationPt = "Usamos 'Quién' (singular) ou 'Quiénes' (plural) para identificar responsáveis por tarefas, PRs ou incidentes. " +
            "Concorda em número com o sujeito da oração.",
        structuralFormula = "¿Quién / Quiénes + verbo + complemento?",
        examples = listOf(
            "¿Quién revisó mi pull request?",
            "¿Quiénes lideran el squad de backend?"
        )
    )

    object WhyLesson : LessonContent(
        questionType = QuestionType.WH_WHY,
        titlePt = "Perguntas com POR QUÉ",
        ruleExplanationPt = "Usamos 'Por qué' (duas palavras e com acento) para perguntar a causa de falhas, exceções ou decisões de arquitetura. " +
            "Para responder afirmativamente a causa, usa-se 'porque' (junto e sem acento).",
        structuralFormula = "¿Por qué + verbo + complemento?",
        examples = listOf(
            "¿Por qué se cayó el servidor?",
            "¿Por qué falló la prueba unitaria?"
        )
    )

    object HowLesson : LessonContent(
        questionType = QuestionType.WH_HOW,
        titlePt = "Perguntas com CÓMO",
        ruleExplanationPt = "Usamos 'Cómo' para perguntar sobre processos, métodos de implementação ou passos para reprodução de bugs. " +
            "Leva acento na função interrogativa.",
        structuralFormula = "¿Cómo + verbo + complemento?",
        examples = listOf(
            "¿Cómo se reproduce el bug?",
            "¿Cómo podemos desacoplar los microservicios?"
        )
    )

    object HowMuchLesson : LessonContent(
        questionType = QuestionType.WH_HOW_MUCH,
        titlePt = "Perguntas com CUÁNTO / CUÁNTA",
        ruleExplanationPt = "Usamos 'Cuánto' ou 'Cuánta' para grandezas contínuas e não contáveis, como tempo de estimativa, memória ou latência. " +
            "Concorda em gênero com o substantivo.",
        structuralFormula = "¿Cuánto / Cuánta + substantivo + verbo?",
        examples = listOf(
            "¿Cuánto tiempo estiman para esta épica?",
            "¿Cuánta memoria RAM consume el contenedor?"
        )
    )

    object HowManyLesson : LessonContent(
        questionType = QuestionType.WH_HOW_MANY,
        titlePt = "Perguntas com CUÁNTOS / CUÁNTAS",
        ruleExplanationPt = "Usamos 'Cuántos' ou 'Cuántas' para quantidades contáveis, como número de sprints, tickets, commits ou servidores. " +
            "Concorda em gênero e número.",
        structuralFormula = "¿Cuántos / Cuántas + substantivo + verbo?",
        examples = listOf(
            "¿Cuántos sprints tomará el desarrollo?",
            "¿Cuántos pods están activos en el cluster?"
        )
    )

    object WhichLesson : LessonContent(
        questionType = QuestionType.WH_WHICH,
        titlePt = "Perguntas com CUÁL / CUÁLES",
        ruleExplanationPt = "Usamos 'Cuál' (ou 'Cuáles') para escolher entre opções ou perguntar por identificadores como branches, endpoints ou nomes. " +
            "Em espanhol, usa-se 'cuál es' em vez de 'qué es' quando se pergunta por uma informação específica.",
        structuralFormula = "¿Cuál / Cuáles + es/son + complemento?",
        examples = listOf(
            "¿Cuál es tu rama de trabajo?",
            "¿Cuál es el plan de acción del post-mortem?"
        )
    )

    // ---- Outros tipos ----

    object YesNoLesson : LessonContent(
        questionType = QuestionType.YES_NO,
        titlePt = "Perguntas de Sim/Não",
        ruleExplanationPt = "Perguntas diretas de confirmação cuja resposta esperada é 'Sí' ou 'No'. " +
            "Em espanhol, iniciam-se diretamente com o verbo conjugado, sem necessidade de auxiliares como o 'do' do inglês.",
        structuralFormula = "¿(Verbo conjugado) + (sujeito) + complemento?",
        examples = listOf(
            "¿Tienes acceso a la VPN?",
            "¿Pudiste revisar mi pull request?"
        )
    )

    object TagLesson : LessonContent(
        questionType = QuestionType.TAG,
        titlePt = "Tag Questions (Confirmação)",
        ruleExplanationPt = "Uma declaração afirmativa ou negativa seguida por uma confirmação curta ao final. " +
            "Em espanhol usa-se ', ¿verdad?', ', ¿no?' ou ', ¿cierto?'.",
        structuralFormula = "Afirmação + , ¿verdad? / , ¿no? / , ¿cierto?",
        examples = listOf(
            "Hiciste el deploy a develop, ¿verdad?",
            "El servidor ya está en línea, ¿no?"
        )
    )

    object IndirectLesson : LessonContent(
        questionType = QuestionType.INDIRECT,
        titlePt = "Perguntas Indiretas e Formais",
        ruleExplanationPt = "Usadas para soar mais cordial e respeitoso em reuniões ou mensagens com a equipe. " +
            "Iniciam-se com '¿Podrías decirme...?', '¿Sabes si...?' ou '¿Sería posible...?'.",
        structuralFormula = "¿Podrías decirme / Sabes si + oração afirmativa?",
        examples = listOf(
            "¿Podrías decirme dónde está la documentación?",
            "¿Sabes si ya aprobaron el pull request?"
        )
    )

    object ChoiceLesson : LessonContent(
        questionType = QuestionType.CHOICE,
        titlePt = "Perguntas de Escolha",
        ruleExplanationPt = "Oferecem duas ou mais opções claras usando a conjunção 'o'. " +
            "Muito frequentes em decisões de arquitetura e priorização de tarefas.",
        structuralFormula = "¿Prefieres [opção A] o [opção B]?",
        examples = listOf(
            "¿Usamos PostgreSQL o MongoDB para este servicio?",
            "¿Hacemos el deploy hoy o mañana por la mañana?"
        )
    )

    companion object {
        /**
         * Retorna a LessonContent correspondente a um [QuestionType].
         * Cobre exaustivamente todos os 13 QuestionTypes.
         */
        fun forType(type: QuestionType): LessonContent = when (type) {
            QuestionType.WH_WHAT     -> WhatLesson
            QuestionType.WH_WHERE    -> WhereLesson
            QuestionType.WH_WHEN     -> WhenLesson
            QuestionType.WH_WHO      -> WhoLesson
            QuestionType.WH_WHY      -> WhyLesson
            QuestionType.WH_HOW      -> HowLesson
            QuestionType.WH_HOW_MUCH -> HowMuchLesson
            QuestionType.WH_HOW_MANY -> HowManyLesson
            QuestionType.WH_WHICH    -> WhichLesson
            QuestionType.YES_NO      -> YesNoLesson
            QuestionType.TAG         -> TagLesson
            QuestionType.INDIRECT    -> IndirectLesson
            QuestionType.CHOICE      -> ChoiceLesson
        }
    }
}
