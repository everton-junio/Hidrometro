# **Simulador de Hidrômetro Multithread**

**Status:** Projeto de Manutenção de Software em andamento

**Data:** 30 de setembro de 2025

Este repositório contém a versão evoluída de um simulador de hidrômetro, modificada como parte da atividade de manutenção de software. O objetivo principal foi refatorar a aplicação original para suportar a execução simultânea e independente de múltiplos simuladores utilizando `multithreading`.

## **Descrição do Projeto Original (V1)**

A versão original do projeto, desenvolvida por Everton, consistia em um simulador funcional de um único hidrômetro. Suas principais características eram:

* **Execução Única:** O sistema era capaz de simular um hidrômetro por vez.
* **Interface Interativa:** A aplicação era controlada por um menu de console (via `Scanner`), permitindo ao usuário visualizar o estado do hidrômetro em uma janela gráfica (Swing) ou encerrar a simulação.
* **Arquivos de I/O Fixos:** O simulador lia os dados de entrada de `entrada.txt`, as configurações de `config.txt` e salvava o relatório de saída em `saida.txt`. Esses caminhos eram fixos no código.
* **Visualização em Tempo Real:** A aplicação utilizava uma `Thread` secundária para o processamento dos dados, permitindo que a janela gráfica (`JFrame`) se atualizasse em tempo real sem travar a interface do menu.

## **Evolução Realizada (V2 - Versão Multithread)**

A tarefa de manutenção, realizada por mim, Ilana Maria dos Santos Costa, consistiu em evoluir o código para atender a um novo requisito: **a capacidade de instanciar e executar até 5 simuladores ao mesmo tempo**, com comportamentos de entrada, medição e saída completamente independentes.

Para alcançar este objetivo, foram realizadas as seguintes modificações arquiteturais:

### **1. Refatoração do Acesso a Arquivos (I/O)**

O principal obstáculo para a execução paralela era o uso de arquivos com nomes fixos. Todas as threads tentariam ler e escrever nos mesmos arquivos, causando inconsistência e corrupção de dados. A solução foi parametrizar todos os métodos de I/O:

* **`CarregarConfiguracaoHidrometro`**: O método `carregarConfiguracao` foi modificado para receber o caminho do arquivo de configuração como um parâmetro (`String configFile`).
* **`ProcessamentoHidrometro`**: A classe foi alterada para receber em seu construtor os caminhos dos arquivos de entrada, configuração e saída. A lógica interna foi atualizada para usar essas variáveis em vez de constantes.
* **`SaidaHidrometro`**: O método `salvarSaida` também foi modificado para receber o caminho do arquivo de relatório como um parâmetro (`String outputFile`).

### **2. Novo Ponto de Entrada (Orquestrador de Threads)**

O ponto de entrada original, `SimuladorHidrometro.java`, que continha o menu interativo, foi substituído por uma nova classe `Main.java`. Esta nova classe atua como um **orquestrador**:

* Ela ainda não possui interface interativa.
* Sua única responsabilidade é configurar e iniciar 5 threads.
* Dentro de um laço `for`, ela cria uma `Runnable` (tarefa) para cada simulador, definindo os caminhos de arquivo únicos para cada um (ex: `config_1.txt`, `entrada_1.txt`, etc.).
* Cada tarefa é então atribuída a uma nova `Thread` e iniciada.
* Ao final, o método `main` utiliza `thread.join()` para aguardar a finalização de todos os simuladores.

### **3. Geração de Saídas Independentes**

Como resultado das modificações, ao final da execução, o programa agora gera um conjunto de arquivos de saída para cada simulador:

* **Relatório em Texto:** `saida_1.txt`, `saida_2.txt`, ..., `saida_5.txt`.
* **Imagem Final:** `hidrometro_final_1.png`, `hidrometro_final_2.png`, ..., `hidrometro_final_5.png`.

### **4. Implementação da Geração de Imagens**

Um novo requisito para a saída de cada simulador foi adicionado: a geração de uma imagem com o estado final do hidrômetro. Para isso:

* Foi criado um novo método estático, `gerarImagemFinal`, na classe `DisplayHidrometro`.
* Este método reutiliza a lógica de desenho do `paintComponent` original. Ele carrega a imagem de fundo `ImagemHidrometro.png`, desenha os dados finais (leitura, pressão, vazão) sobre ela usando `Graphics2D`, e salva o resultado em um novo arquivo PNG.

## **Como Executar o Projeto**

### **Pré-requisitos**

* Java Development Kit (JDK) 11 ou superior.
* Uma IDE Java, como o IntelliJ IDEA.

### **Estrutura de Arquivos**

Para a correta execução, a pasta `src/main/java/org/ifpb/Arquivos/` deve conter os seguintes arquivos de entrada, um conjunto para cada simulador:

* **Arquivos de Configuração:** `config_1.txt`, `config_2.txt`, `config_3.txt`, `config_4.txt`, `config_5.txt`.
* **Arquivos de Entrada:** `entrada_1.txt`, `entrada_2.txt`, `entrada_3.txt`, `entrada_4.txt`, `entrada_5.txt`.
* **Imagem Base:** `ImagemHidrometro.png` deve estar presente nesta pasta.

### **Execução**

Para iniciar a simulação, execute o método `main` da classe `org.ifpb.Main.Main.java`.

## **Autoria**

* **Proprietário Original:** Everton Junio
* **Colaborador (Manutenção):** Ilana Costa - ilana.costa@academico.ifpb.edu.br
# Modelagem UML hidrômetro
- Ferramenta utlizada: draw.io

[Hidrômetro-UML.drawio.pdf](https://github.com/user-attachments/files/22501392/Hidrometro-UML.drawio.pdf)
