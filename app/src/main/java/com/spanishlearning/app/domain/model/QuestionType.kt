package com.spanishlearning.app.domain.model

/**
 * Tipos gramaticais de perguntas suportados pelo aplicativo.
 * Requisito 1.4: suporte a 13 tipos de perguntas.
 */
enum class QuestionType {
    WH_WHAT,      // What is your name?
    WH_WHERE,     // Where do you live?
    WH_WHEN,      // When did it happen?
    WH_WHO,       // Who called you?
    WH_WHY,       // Why are you late?
    WH_HOW,       // How do you feel?
    WH_HOW_MUCH,  // How much does it cost?
    WH_HOW_MANY,  // How many people are there?
    WH_WHICH,     // Which one do you prefer?
    YES_NO,       // Do you like coffee?
    TAG,          // You like coffee, don't you?
    INDIRECT,     // Could you tell me where he lives?
    CHOICE;       // Do you prefer tea or coffee?

    fun displayName(): String = when (this) {
        WH_WHAT     -> "Pregunta con 'Qué' (Wh-what)"
        WH_WHERE    -> "Pregunta con 'Dónde' (Wh-where)"
        WH_WHEN     -> "Pregunta con 'Cuándo' (Wh-when)"
        WH_WHO      -> "Pregunta con 'Quién' (Wh-who)"
        WH_WHY      -> "Pregunta con 'Por qué' (Wh-why)"
        WH_HOW      -> "Pregunta con 'Cómo' (Wh-how)"
        WH_HOW_MUCH -> "Pregunta con 'Cuánto/a' (Wh-how much)"
        WH_HOW_MANY -> "Pregunta con 'Cuántos/as' (Wh-how many)"
        WH_WHICH    -> "Pregunta con 'Cuál/es' (Wh-which)"
        YES_NO      -> "Pregunta Sí/No (Yes/No question)"
        TAG         -> "Pregunta de confirmación (Tag question)"
        INDIRECT    -> "Pregunta indirecta (Indirect question)"
        CHOICE      -> "Pregunta de opción (Choice question)"
    }
}
