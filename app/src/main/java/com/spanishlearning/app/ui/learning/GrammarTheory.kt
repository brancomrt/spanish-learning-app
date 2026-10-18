package com.spanishlearning.app.ui.learning

/**
 * Conteúdo teórico para cada conceito gramatical.
 * Mapeado a partir do campo grammar_focus dos exercícios.
 */
data class GrammarTheory(
    val title: String,
    val explanation: String,
    val structure: String,
    val examples: List<String>,
    val tip: String? = null
)

object GrammarTheoryProvider {

    fun getTheory(grammarFocus: String): GrammarTheory {
        val key = grammarFocus.lowercase().trim()
        return theories.entries
            .firstOrNull { key.contains(it.key) }
            ?.value
            ?: defaultTheory(grammarFocus)
    }

    private fun defaultTheory(focus: String) = GrammarTheory(
        title = focus.split("-").first().trim()
            .split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } },
        explanation = "Este exercício foca em: $focus.",
        structure = "Consulte seu material de referência para mais detalhes.",
        examples = emptyList()
    )

    private val theories: Map<String, GrammarTheory> = mapOf(

        // ── Español para TI ───────────────────────────────────────────────
        "preguntas con qué" to GrammarTheory(
            title = "Perguntas com 'Qué' em Espanhol Técnico",
            explanation = "Usa-se '¿Qué + substantivo?' ou '¿Qué + verbo?' para perguntar sobre características, especificações ou ações do sistema.",
            structure = """
                (?) ¿Qué + verbo + complemento?
                (?) ¿Qué + substantivo + verbo?
            """.trimIndent(),
            examples = listOf(
                "¿Qué especificaciones tiene tu computadora?",
                "¿Qué hiciste en el sprint anterior?",
                "¿Qué error sale en la consola de logs?"
            ),
            tip = "A palavra 'qué' leva acento quando é interrogativa ou exclamativa (diferente da conjunção átona 'que')."
        ),

        "sí/no" to GrammarTheory(
            title = "Perguntas de Sim/Não em Espanhol",
            explanation = "Em espanhol, perguntas de confirmação não necessitam de verbos auxiliares (como 'do/does' em inglês). Basta conjugar o verbo no início da oração.",
            structure = """
                (?) ¿(Verbo conjugado) + (sujeito) + complemento?
            """.trimIndent(),
            examples = listOf(
                "¿Tienes acceso a la VPN corporativa?",
                "¿Pudiste revisar mi pull request?",
                "¿Ya terminaste la tarea de Jira?"
            ),
            tip = "Na escrita formal em espanhol, sempre se abre a pergunta com o ponto de interrogação invertido (¿)."
        ),

        "daily" to GrammarTheory(
            title = "Comunicação em Daily Standups (Metodologias Ágeis)",
            explanation = "Expressões fundamentais para responder às três perguntas clássicas do Scrum em equipes de TI da América Latina.",
            structure = """
                • Ayer terminé [tarefa]
                • Hoy voy a trabajar en [tarefa]
                • No tengo ningún blocker / Estoy trabado con [impedimento]
            """.trimIndent(),
            examples = listOf(
                "¿En qué estás trabajando hoy?",
                "¿Tienes algún blocker para avanzar?",
                "Estoy trabado con la autenticación OAuth."
            ),
            tip = "Na América Latina, termos como 'blocker', 'daily', 'sprint' e 'PR' são usados naturalmente misturados ao espanhol."
        ),

        "revisión de código" to GrammarTheory(
            title = "Revisão de Código (Code Review & PRs)",
            explanation = "Como comentar e solicitar revisões de forma cordial e colaborativa com engenheiros hispanofalantes.",
            structure = """
                (?) ¿Pudiste revisar mi pull request?
                (+) Te dejé dos comentarios sobre el tipado.
                (+) Ya aprobé los cambios para hacer merge.
            """.trimIndent(),
            examples = listOf(
                "¿Pudiste revisar mi pull request?",
                "Falta tipar esta variable antes de fusionar a develop.",
                "¿Ya tienes las aprobaciones necesarias?"
            ),
            tip = "Expressões frequentes: 'darle una mirada' ou 'echar un ojo' significam dar uma olhada rápida no código."
        ),

        "despliegues" to GrammarTheory(
            title = "Despliegues e DevOps (CI/CD)",
            explanation = "Vocabulário para gerenciamento de versões, pipelines e lançamentos em produção.",
            structure = """
                • Hacer el despliegue / pase a producción
                • Hacer un rollback a la versión anterior
                • Parchear una vulnerabilidad en caliente
            """.trimIndent(),
            examples = listOf(
                "¿Cuándo haremos el despliegue a producción?",
                "El servidor se cayó por una fuga de memoria.",
                "Tuvimos que hacer un rollback de inmediato."
            ),
            tip = "No espanhol latino usa-se 'despliegue' ou o verbo adaptado 'deployar'."
        ),

        "cuello de botella" to GrammarTheory(
            title = "Desempenho e Arquitetura de Sistemas",
            explanation = "Terminologia para discutir gargalos, latência, mensageria e escalabilidade.",
            structure = """
                • Cuello de botella (bottleneck)
                • Desacoplar componentes con colas de mensajes
                • Latencia promedio y percentiles
            """.trimIndent(),
            examples = listOf(
                "¿Dónde está el cuello de botella?",
                "Podemos desacoplar los microservicios usando colas de mensajes.",
                "La API tiene una latencia promedio de 40 milisegundos."
            ),
            tip = "'Cuello de botella' é a expressão pan-hispânica consagrada para gargalo de performance."
        ),

        "liderazgo" to GrammarTheory(
            title = "Liderança Técnica e Retrospectivas",
            explanation = "Comunicação para estimativas de esforço, análise de causa raiz e alinhamento com produto.",
            structure = """
                • Estimar en [puntos de historia / sprints]
                • Plan de acción del post-mortem
                • Pagar deuda técnica acumulada
            """.trimIndent(),
            examples = listOf(
                "¿Cuánto tiempo estiman para completar esta épica?",
                "¿Cuál es el plan de acción del post-mortem?",
                "Destinaremos el 20% del sprint a deuda técnica."
            ),
            tip = "Nas reuniões de post-mortem adota-se o 'análisis sin culpas' (blameless post-mortem)."
        ),

        // ── Presente de Indicativo ────────────────────────────────────────
        "presente" to GrammarTheory(
            title = "Presente de Indicativo (Rotinas, Hábitos e Fatos)",
            explanation = "Usado para ações habituais do cotidiano e do trabalho, verdades universais e estados atuais em espanhol.",
            structure = """
                • Verbos -AR (trabajar, hablar): trabajo, trabajas, trabaja, trabajamos, trabajan
                • Verbos -ER (comer, correr): como, comes, come, comemos, comen
                • Verbos -IR (vivir, subir): vivo, vives, vive, vivimos, viven
                (?) ¿(Palabra interrogativa) + verbo conjugado + complemento?
            """.trimIndent(),
            examples = listOf(
                "Trabajo desde casa todos los días.",
                "¿A qué hora empieza tu turno o la reunión?",
                "¿Dónde vives actualmente?",
                "Ella toma café por la mañana antes de programar."
            ),
            tip = "Na América Latina usa-se 'ustedes' tanto para contextos formais quanto informais no plural (nunca 'vosotros')."
        ),

        // ── Estar + Gerundio (Presente Contínuo) ──────────────────────────
        "gerundio" to GrammarTheory(
            title = "Estar + Gerundio (Ações em Andamento)",
            explanation = "Usado para descrever ações que estão acontecendo exatamente neste momento no dia a dia ou no trabalho.",
            structure = """
                (+) Sujeito + estoy/estás/está/estamos/están + verbo (-ando / -iendo)
                (-) Sujeito + no + estar + gerundio
                (?) ¿Qué + estar + gerundio?
            """.trimIndent(),
            examples = listOf(
                "Estoy revisando el incidente en producción ahora mismo.",
                "¿En qué estás trabajando hoy?",
                "Estamos preparando la cena en casa.",
                "El servidor está consumiendo demasiada memoria."
            ),
            tip = "Verbos em -AR formam gerúndio em '-ando' (trabajando) e verbos em -ER/-IR em '-iendo' (comiendo, viviendo)."
        ),

        // ── Pretérito Perfecto Compuesto (Haber + Participio) ─────────────
        "pretérito perfecto" to GrammarTheory(
            title = "Pretérito Perfecto (Experiências e Passado Recente)",
            explanation = "Conecta o passado ao presente. Usado para experiências de vida ('alguna vez', 'ya', 'todavía no') ou ações recentes.",
            structure = """
                (+) Sujeito + he / has / ha / hemos / han + particípio (-ado / -ido)
                (-) Sujeito + todavía no + haber + particípio
                (?) ¿Ya + haber + particípio + complemento?
            """.trimIndent(),
            examples = listOf(
                "Ya he subido los cambios al repositorio.",
                "¿Alguna vez has viajado a Ciudad de México?",
                "Todavía no hemos recibido la factura del proveedor.",
                "El pipeline ha fallado dos veces esta mañana."
            ),
            tip = "Particípios irregulares importantes: hacer→hecho, decir→dicho, ver→visto, escribir→escrito, romper→roto, abrir→abierto."
        ),

        // ── Pretérito Indefinido (Passado Simples) ────────────────────────
        "pretérito indefinido" to GrammarTheory(
            title = "Pretérito Indefinido (Ações Concluídas no Passado)",
            explanation = "Muito usado na América Latina para relatar ações concluídas em um momento específico do passado (ayer, anoche, la semana pasada).",
            structure = """
                • -AR: trabajé, trabajaste, trabajó, trabajamos, trabajaron
                • -ER/-IR: salí, saliste, salió, salimos, salieron
                • Irregulares comuns: fui/fue, hice/hizo, tuve/tuvo, pude/pudo, estuve/estuvo
            """.trimIndent(),
            examples = listOf(
                "El servidor se cayó anoche durante el mantenimiento.",
                "Ayer compré los boletos de avión para el viaje.",
                "¿Cuándo desplegaron la nueva versión?",
                "¿Qué hiciste el fin de semana pasado?"
            ),
            tip = "Na América Latina, o Pretérito Indefinido ('Ya comí', 'Ayer terminé') é preferido mesmo para o passado recente do mesmo dia!"
        ),

        // ── Pretérito Imperfecto ──────────────────────────────────────────
        "pretérito imperfecto" to GrammarTheory(
            title = "Pretérito Imperfecto (Contexto e Hábitos no Passado)",
            explanation = "Usado para descrever cenários no passado, rotinas antigas ou uma ação em curso quando outra aconteceu.",
            structure = """
                • -AR: trabajaba, trabajabas, trabajaba, trabajábamos, trabajaban
                • -ER/-IR: tenía, tenías, tenía, teníamos, tenían
            """.trimIndent(),
            examples = listOf(
                "Antes trabajábamos con un monolito, pero migramos a microservicios.",
                "Cuando vivía en Bogotá, caminaba al trabajo todos los días.",
                "¿Qué estabas haciendo cuando ocurrió el apagón?"
            ),
            tip = "Apenas três verbos são irregulares no Imperfecto: ser (era), ir (iba) e ver (veía)."
        ),

        // ── Voz Pasiva y Pasiva Refleja (Con 'Se') ────────────────────────
        "voz pasiva" to GrammarTheory(
            title = "Voz Passiva e Passiva Reflexa com 'Se'",
            explanation = "Em espanhol, além da passiva com 'ser + particípio', usa-se muito a passiva reflexa com 'se + verbo em 3ª pessoa' tanto em TI quanto no cotidiano.",
            structure = """
                • Pasiva con 'se': Se + verbo (3ª sing./plural) + sujeito paciente
                • Pasiva con 'ser': Sujeito + fue/fueron + particípio + por...
            """.trimIndent(),
            examples = listOf(
                "Se corrigió la vulnerabilidad durante la auditoría.",
                "Se alquilan departamentos amueblados en el centro.",
                "¿Cuándo se aprobó el presupuesto del proyecto?",
                "El incidente fue resuelto por el equipo de guardia."
            ),
            tip = "Na fala cotidiana e técnica em espanhol, 'Se desplegó la versión' soa muito mais natural que 'La versión fue desplegada'."
        ),

        // ── Verbos Modales y Perífrasis de Obligación ─────────────────────
        "verbos modales" to GrammarTheory(
            title = "Verbos Modais e Perífrases (Poder, Deber, Tener que, Hay que)",
            explanation = "Expressam capacidade, permissão, recomendação ou obrigação pessoal e impessoal em espanhol.",
            structure = """
                • Obrigação pessoal: Tener que + infinitivo / Deber + infinitivo
                • Obrigação impessoal: Hay que + infinitivo
                • Recomendação: Deberías / Convendría + infinitivo
                • Capacidade / Permissão: Poder + infinitivo
            """.trimIndent(),
            examples = listOf(
                "Tienes que reiniciar el contenedor para aplicar las variables.",
                "Hay que respaldar la base de datos todos los días.",
                "¿Puedes ayudarme a revisar esta consulta SQL?",
                "Deberías descansar un poco y tomar más agua."
            ),
            tip = "'Hay que + infinitivo' é excelente em ambiente de trabalho por indicar uma necessidade sem culpar ninguém diretamente."
        ),

        // ── Condicional y Subjuntivo (Hipóteses) ──────────────────────────
        "condicional" to GrammarTheory(
            title = "Condicional e Subjuntivo em Situações Hipotéticas",
            explanation = "Usado para expressar cortesia, conselhos e hipóteses sobre o presente, futuro ou passado.",
            structure = """
                • Hipótese presente/futura: Si + Imperfecto de Subjuntivo (-ra), + Condicional Simple (-ría)
                • Hipótese passada: Si + hubiera/hubiese + particípio, + habría + particípio
            """.trimIndent(),
            examples = listOf(
                "Si tuviéramos más pruebas automatizadas, detectaríamos los errores antes.",
                "Si hubiéramos configurado alertas, habríamos evitado la caída.",
                "¿Qué harías tú en esta situación?",
                "Me gustaría reservar una mesa para dos personas."
            ),
            tip = "Para pedidos educados no cotidiano ou em TI, use o condicional: '¿Podrías enviarme el enlace?' / 'Me gustaría...'."
        ),

        // ── Preguntas de Confirmación (Apéndices Interrogativos) ──────────
        "confirmación" to GrammarTheory(
            title = "Perguntas de Confirmação (¿verdad?, ¿no?, ¿cierto?)",
            explanation = "Em espanhol, confirmar uma informação é muito mais simples: basta adicionar '¿verdad?', '¿no?' ou '¿cierto?' ao final da frase.",
            structure = """
                • Afirmação + , + ¿verdad? / ¿no? / ¿cierto?
                • Negação + , + ¿verdad? / ¿cierto?
            """.trimIndent(),
            examples = listOf(
                "Ya desplegaste el parche en producción, ¿verdad?",
                "La reunión empieza a las diez, ¿no?",
                "Este restaurante tiene opciones vegetarianas, ¿cierto?",
                "No cambiaron el esquema de la base de datos, ¿verdad?"
            ),
            tip = "Na Colômbia, Chile e México, '¿cierto?' e '¿verdad?' são extremamente frequentes em conversas profissionais e cotidianas."
        ),

        // ── Comparativos y Superlativos ───────────────────────────────────
        "comparativos" to GrammarTheory(
            title = "Comparativos e Superlativos em Espanhol",
            explanation = "Usados para comparar desempenho técnico, preços, lugares, opções do cotidiano e tomar decisões.",
            structure = """
                • Superioridade: más + adjetivo + que
                • Inferioridade: menos + adjetivo + que
                • Igualdade: tan + adjetivo + como / tanto(a) + substantivo + como
                • Irregulares: bueno → mejor, malo → peor, grande → mayor
            """.trimIndent(),
            examples = listOf(
                "El disco SSD es mucho más rápido que el disco mecánico.",
                "Esta arquitectura es más fácil de mantener que la anterior.",
                "El transporte público aquí es tan eficiente como en Santiago.",
                "¿Cuál de las dos opciones te parece mejor?"
            ),
            tip = "Nunca diga 'más bueno' ou 'más malo' para qualidade; use sempre 'mejor' e 'peor'."
        ),

        // ── Oraciones Concesivas (Aunque / A pesar de) ────────────────────
        "concesivas" to GrammarTheory(
            title = "Orações Concessivas (Aunque, A pesar de, Si bien)",
            explanation = "Introduzem um contraste ou ressalva em argumentações avançadas de arquitetura, negócios e debates cotidianos.",
            structure = """
                • Aunque + Indicativo (fato real) / Subjuntivo (hipótese)
                • A pesar de (que) + substantivo / infinitivo / oração
                • Si bien + oração, + oração principal
            """.trimIndent(),
            examples = listOf(
                "Aunque la prueba pasó localmente, falló en el entorno de integración.",
                "A pesar del tráfico pesado, llegamos a tiempo al aeropuerto.",
                "Si bien el escalado horizontal mejora el rendimiento, aumenta el costo."
            ),
            tip = "'Aunque' + indicativo expressa um fato confirmado; 'Aunque' + subjuntivo expressa uma possibilidade ou concessão hipotética."
        ),

        // ── Futuro Simple y Perifrástico (Ir a + Infinitivo) ──────────────
        "futuro" to GrammarTheory(
            title = "Planos e Futuro (Ir a + Infinitivo e Futuro Simple)",
            explanation = "No espanhol latino-americano, 'ir a + infinitivo' domina os planos próximos, enquanto o Futuro Simple (-ré, -rás, -rá) é usado para previsões formais.",
            structure = """
                • Futuro próximo: Voy / Vas / Va / Vamos / Van + a + infinitivo
                • Futuro simple: Infinitivo + -é, -ás, -á, -emos, -án
            """.trimIndent(),
            examples = listOf(
                "Mañana vamos a lanzar la nueva funcionalidad.",
                "¿Cuándo vas a tomar tus vacaciones?",
                "La migración a la nube reducirá los costos operativos.",
                "¿Dónde van a cenar esta noche?"
            ),
            tip = "Irregulares no Futuro Simple têm a mesma raiz do Condicional: tener→tendré, poder→podré, hacer→haré, salir→saldré, decir→diré."
        )
    )
}
