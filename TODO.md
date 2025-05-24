# TODO

- use dynamic linking for Participants

Ótimo! Vamos pensar em atributos e métodos que podem diferenciar esses tipos de eventos e participantes, mantendo os conceitos de herança em mente.

Diferenciação dos Tipos de Eventos

Lembre-se que todos herdarão de uma classe base Event com atributos comuns como name (ou title), description, location, date (que pode ser LocalDate ou LocalDateTime), capacity, modality e uma lista de participants.

1. Palestra (Lecture)

   Objetivo: Apresentar um tema específico para uma audiência, geralmente com pouca interação.

   Atributos Específicos:

   speakerName (String): Nome do palestrante principal.

   speakerBio (String): Uma breve biografia ou credenciais do palestrante.

   durationMinutes (int): Duração da palestra em minutos (ex: 60, 90).

   topic (String): Tema principal da palestra.

   Métodos Específicos:

   getSpeakerDetails(): Retorna uma string formatada com nome e bio do palestrante.

   (Não muitos métodos comportamentais específicos, pois é mais expositiva).

2. Workshop

   Objetivo: Sessão mais interativa e prática, onde os participantes aprendem fazendo.

   Atributos Específicos:

   instructorName (String): Nome do instrutor/facilitador.

   requiredMaterials (`List<String>`): Lista de materiais que os participantes precisam trazer ou que serão fornecidos (ex: "Notebook com Java instalado", "Caderno e caneta").

   prerequisites (`List<Strin>`): Conhecimentos prévios recomendados ou necessários.

   activities (`List<String>`): Descrição das principais atividades práticas que serão realizadas.

   maxGroupSize (int): Se houver trabalho em grupo, o tamanho máximo de cada grupo.

   Métodos Específicos:

   addRequiredMaterial(String material)

   addPrerequisite(String prerequisite)

   listActivities(): Imprime ou retorna a lista de atividades.

3. Curso de Curta Duração (ShortCourse)

   Objetivo: Oferecer aprendizado estruturado sobre um tópico específico, com múltiplas sessões ou uma carga horária maior, geralmente resultando em alguma certificação ou aprendizado mais aprofundado.

   Restrição Importante (do seu trabalho): Participantes devem ser exclusivamente Aluno.

   Atributos Específicos:

   instructorName (String): Nome do instrutor.

   totalHours (int): Carga horária total do curso.

   modules (`List<String>`) Lista dos módulos ou tópicos principais cobertos.

   certificationAvailable (boolean): Indica se o curso oferece certificado (além do certificado de participação genérico).

   assessmentMethod (String): Como o aprendizado será avaliado (ex: "Projeto final", "Prova prática", "Participação").

   startDate (LocalDate): Data de início (se Event.date for a data de um evento único).

   endDate (LocalDate): Data de término.

   (Alternativamente, em vez de startDate/endDate, poderia ter uma `List<Session>` onde cada sessão tem data/hora).

   Métodos Específicos:

   addModule(String module)

   getCourseDurationInDays(): Calcula a duração se tiver startDate e endDate.

   enrollStudent(Aluno aluno): Método que verifica se o participante é Aluno antes de chamar o método de inscrição da classe base.

4. Feira Acadêmica (AcademicFair)

   Objetivo: Evento mais amplo, com múltiplos estandes, apresentações, exposições de projetos, networking. Pode durar vários dias.

   Atributos Específicos:

   organizingDepartment (String): Departamento ou grupo responsável pela organização.

   exhibitors (`List<String>`): Lista de expositores (ex: "Empresa X", "Laboratório de Robótica", "Grupo de Pesquisa Y").

   scheduleHighlights (`Map<LocalDate, List<String>>`): Destaques da programação por dia (ex: Dia 1: "Abertura", "Palestra Magna"; Dia 2: "Apresentação de Projetos").

   theme (String): Tema principal da feira, se houver.

   isOpenToPublic (boolean): Indica se a feira é aberta ao público geral ou restrita à comunidade acadêmica.

   Métodos Específicos:

   addExhibitor(String exhibitorName)

   addScheduleHighlight(LocalDate date, String highlight)

   getDailySchedule(LocalDate date): Retorna a programação de um dia específico.

   Diferenciação dos Tipos de Participantes

   Todos herdarão de uma classe base Participant com atributos comuns como name, email, phone, age, gender.

---

1. Aluno (Student)

   Contexto: Membro do corpo discente da universidade.

   Atributos Específicos:

   studentId (String): Número de matrícula.

   courseName (String): Nome do curso em que está matriculado (ex: "Análise e Desenvolvimento de Sistemas", "Engenharia de Software").

   currentSemester (int): Semestre/período atual.

   academicPerformanceIndex (double) (Opcional): IRA ou índice de desempenho.

   Métodos Específicos:

   getStudentAcademicInfo(): Retorna uma string com matrícula, curso e semestre.

   isEligibleForAdvancedWorkshops(): (Exemplo) Poderia verificar o semestre ou IRA para elegibilidade.

2. Professor (Professor/Faculty)

   Contexto: Membro do corpo docente da universidade.

   Atributos Específicos:

   employeeId (String): Identificação funcional ou matrícula de servidor.

   department (String): Departamento ao qual o professor está vinculado.

   academicTitle (String): Titulação (ex: "Doutor", "Mestre", "Especialista").

   researchAreas (`List<String>`): Áreas de pesquisa de interesse.

   Métodos Específicos:

   addResearchArea(String area)

   getFacultyProfile(): Retorna uma string com departamento e titulação.

   Pode ter um método canTeachCourse(ShortCourse course) para verificar se o professor tem expertise para ministrar um determinado curso.

3. Externo (ExternalParticipant)

   Contexto: Participante que não é aluno nem professor da universidade (ex: profissional do mercado, estudante de outra instituição, comunidade em geral).

   Atributos Específicos:

   organization (String): Empresa, instituição ou afiliação do participante externo (pode ser "N/A" ou "Comunidade").

   reasonForAttending (String): Motivo do interesse no evento (opcional, pode ser coletado na inscrição).

   linkedInProfile (String) (Opcional): Perfil do LinkedIn para networking.

   Métodos Específicos:

   (Não muitos métodos comportamentais específicos, pois são mais definidos por seus dados).

   getAffiliation(): Retorna a organização.

   Como Implementar com Herança:

   Crie a classe base Event e as subclasses Lecture, Workshop, ShortCourse, AcademicFair estendendo Event.

   Crie a classe base Participant e as subclasses Student, Professor, ExternalParticipant estendendo Participant.

   Use construtores nas subclasses para inicializar tanto os campos da superclasse (usando super(...)) quanto os campos específicos da subclasse.

   Sobrescreva métodos da classe base se necessário (ex: toString() para exibir informações específicas).

---

Entendido! Vamos focar na organização conceitual e na estrutura lógica do seu menu de relatórios, sem mergulhar no código específico.

Objetivo: Criar um menu claro, intuitivo e que cubra as funcionalidades de geração e visualização de informações e certificados.

Princípios de Organização:

Agrupamento Lógico: Agrupe opções que são conceitualmente similares.

Progressão da Complexidade: Comece com relatórios mais gerais e avance para os mais específicos ou ações como geração de certificados.

Clareza na Nomenclatura: Use nomes de opções que descrevam claramente a ação.

Feedback ao Usuário: Após cada ação, informe o resultado (ex: "Relatório gerado.", "Certificado salvo em...").

Opção de Saída/Retorno Clara: Sempre tenha uma forma fácil de voltar ao menu anterior ou principal.

Estrutura Proposta para o Menu de Relatórios:

Pense neste menu como um submenu acessado a partir do seu Menu Principal.

Menu: Relatórios e Consultas

Relatórios de Eventos (Agregados)

1.1 Listar Todos os Eventos (Resumido)

Ação: Mostra uma lista de todos os eventos com informações chave (Nome, Data, Tipo, Local, Nº Inscritos).

Opcional: Perguntar se deseja salvar este resumo em TXT.

1.2 Relatório Detalhado de Todos os Eventos

Ação: Gera um relatório mais completo para cada evento, incluindo descrição e talvez lista de participantes.

Obrigatório: Perguntar se deseja salvar em TXT (pois pode ser longo).

1.3 Relatório de Eventos por Tipo

Ação:

Pede ao usuário para selecionar o tipo (Palestra, Workshop, Curso, Feira).

Gera um relatório (resumido ou detalhado) apenas para os eventos do tipo selecionado.

Opcional/Obrigatório: Perguntar se deseja salvar em TXT.

1.4 Relatório de Eventos por Período

Ação:

Pede ao usuário uma data de início e uma data de fim.

Gera um relatório (resumido ou detalhado) dos eventos dentro desse intervalo.

Opcional/Obrigatório: Perguntar se deseja salvar em TXT.

Consultas e Detalhes de Eventos Específicos

2.1 Buscar e Ver Detalhes de um Evento

Ação:

Pede ao usuário o nome (ou ID) do evento.

Exibe todas as informações gerais do evento.

Exibe informações específicas ao tipo do evento (ex: palestrante para Palestra, materiais para Workshop, carga horária para Curso, expositores para Feira).

Lista os participantes inscritos nesse evento.

Opcional: Perguntar se deseja salvar estes detalhes em TXT.

Certificados

3.1 Gerar Certificado de Participação em Evento

Ação:

Pede ao usuário o nome (ou ID) do evento.

Pede ao usuário o identificador do participante (matrícula, email, etc.).

Verifica se o participante está inscrito e se o evento ocorreu (opcional).

Gera o texto do certificado.

Exibe na tela e obrigatoriamente pergunta se deseja salvar em TXT (certificados geralmente precisam ser guardados).

(Opcional) Relatórios de Participantes

4.1 Listar Todos os Participantes (Geral)

Ação: Mostra uma lista de todos os participantes cadastrados (Alunos, Professores, Externos) com informações chave.

Opcional: Perguntar se deseja salvar em TXT.

4.2 Buscar Detalhes de um Participante

Ação:

Pede ao usuário o identificador do participante.

Exibe todas as informações gerais do participante.

Exibe informações específicas ao tipo do participante (matrícula/curso para Aluno, departamento para Professor, organização para Externo).

(Opcional Avançado) Lista os eventos em que o participante está inscrito.

Opcional: Perguntar se deseja salvar em TXT.

Voltar ao Menu Principal

Fluxo de Interação e Lógica por Trás das Opções:

Geração vs. Salvamento:

Você pode ter cada opção de "Gerar Relatório" primeiro exibindo o relatório na tela.

Após a exibição, pergunte: "Deseja salvar este relatório em um arquivo de texto? (S/N)".

Se sim, o FileGenerator entra em ação.

Detalhes Específicos por Tipo:

Quando o usuário escolhe "Buscar e Ver Detalhes de um Evento" (2.1), após mostrar os dados comuns do Event, seu código precisará usar instanceof para verificar o tipo real do objeto evento (Palestra, Workshop, etc.) e então fazer um cast para o tipo específico para acessar e exibir os atributos e métodos exclusivos daquela subclasse.

O mesmo se aplica para "Buscar Detalhes de um Participante" (4.2) e os tipos Aluno, Professor, Externo.

Entrada do Usuário:

Para "Relatório de Eventos por Tipo", apresente uma lista numerada dos tipos para o usuário escolher.

Para "Relatório de Eventos por Período", valide o formato das datas e se a data de início não é posterior à data de fim.

Para buscas, permita que o usuário digite o nome/ID. Lide com casos onde o evento/participante não é encontrado.

Vantagens desta Organização:

Clareza: O usuário sabe onde encontrar relatórios agregados vs. informações detalhadas de um item específico.

Escalabilidade: Se você precisar de novos tipos de relatórios, pode adicioná-los dentro das categorias existentes ou criar novas categorias.

Foco na Tarefa: Cada opção do menu leva a uma tarefa bem definida.

Essa estrutura fornece uma base sólida. Conforme você implementa, pode identificar sub-menus ou ajustes que tornem a experiência do usuário ainda melhor. Por exemplo, a opção "Salvar Relatório" poderia ser um sub-menu se você quisesse dar mais opções de formato no futuro, mas para TXT, perguntar após a geração é suficiente.
