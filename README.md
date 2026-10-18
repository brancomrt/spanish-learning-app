# 🇲🇽 Español Latino — TI & Cotidiano (`v1.1.0`)

Aplicativo Android nativo para prática ativa de **Espanhol Latino-Americano** focado em **Tecnologia da Informação (TI)** e **Situações do Cotidiano**, cobrindo todos os **6 níveis CEFR (`A1`, `A2`, `A2+`, `B1`, `B2` e `C1`)**.

---

## 📦 Download Direto do APK (`v1.1.0`)

O APK compilado está disponível diretamente nesta branch (`main`) e nas Releases do repositório:

- 👉 **[Baixar `spanish-learning-app-v1.1.0.apk`](./spanish-learning-app-v1.1.0.apk?raw=true)** (`versionName = "1.1.0"`, `versionCode = 2`)

---

## ✨ Conteúdo Completo (TI + Cotidiano em Todos os Níveis)

| Nível CEFR | Categorias de TI (18) | Categorias do Cotidiano (10) |
| :--- | :--- | :--- |
| **1 — A1 (Iniciante)** | Suporte Técnico & Help Desk, Hardware & Equipamentos, Desenvolvimento Básico | Vida Diária & Rotina, Alimentação & Restaurantes |
| **2 — A2 (Elementar)** | Redes & Conectividade, Bancos de Dados & SQL, Testes & QA | Viagens & Transporte, Compras & Serviços, Saúde & Bem-Estar |
| **3 — A2+ (Pré-Intermediário)** | Controle de Versão & Git, APIs & Integrações, Cloud Computing | Finanças Pessoais, Moradia & Casa |
| **4 — B1 (Intermediário)** | DevOps & CI/CD, Segurança da Informação, Metodologias Ágeis & Scrum | Entretenimento & Lazer, Relações Sociais |
| **5 — B2 (Intermediário Superior)** | Arquitetura de Software, Microsserviços & Containers, Observabilidade & SRE | Cultura & Costumes Latinos |
| **6 — C1 (Avançado)** | Liderança Técnica & Gestão, System Design & Escalabilidade, Escrita Técnica & RFCs | Expressões Idiomáticas |

### Resumo do Banco de Dados (`spanish_learning_db`)
- **6 Níveis CEFR** (`A1` a `C1`, todos desbloqueados para estudo livre)
- **28 Categorias Temáticas** (18 de TI + 10 do Cotidiano)
- **229 Exercícios Interativos** (formulação de perguntas e respostas completas em espanhol latino, com 3 dicas progressivas em português)
- **575 Cartões de Vocabulário FSRS-4.5** (repetição espaçada com exemplos contextualizados e pronúncia TTS `es-MX` / `es-419`)
- **114 Drills Rápidos** (distribuídos nos *Stages 1 a 12* para memorização ativa de frases completas)
- **Teoria Gramatical 100% em Espanhol Latino** (*Presente*, *Estar + Gerundio*, *Pretérito Perfecto / Indefinido / Imperfecto*, *Futuro*, *Condicional*, *Subjuntivo*, *Voz Pasiva*, *Verbos Modales*, *Comparativos* e *Concesivas*)

---

## 🛠️ Compilação Local

```bash
./gradlew assembleDebug
```

O APK compilado é gerado em `app/build/outputs/apk/debug/spanish-learning-app.apk`.
