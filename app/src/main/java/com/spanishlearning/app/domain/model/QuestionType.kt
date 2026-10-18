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
        WH_WHAT     -> "Pregunta con 'Qué'"
        WH_WHERE    -> "Pregunta con 'Dónde'"
        WH_WHEN     -> "Pregunta con 'Cuándo'"
        WH_WHO      -> "Pregunta con 'Quién'"
        WH_WHY      -> "Pregunta con 'Por qué'"
        WH_HOW      -> "Pregunta con 'Cómo'"
        WH_HOW_MUCH -> "Pregunta con 'Cuánto/a'"
        WH_HOW_MANY -> "Pregunta con 'Cuántos/as'"
        WH_WHICH    -> "Pregunta con 'Cuál/es'"
        YES_NO      -> "Pregunta de Sí/No"
        TAG         -> "Pregunta de confirmación (¿verdad? / ¿no?)"
        INDIRECT    -> "Pregunta indirecta"
        CHOICE      -> "Pregunta de opción"
    }
}
