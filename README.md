## Alunos

- Davi Érico dos Santos
- Lucas Maia Rocha

## Objetivo da Refatoração com COMMAND

O padrão de projeto Command é utilizado para desacoplar a lógica de execução das ações do sistema da interface ou da classe principal que as dispara. 
Neste projeto que refatoramos, organizamos o código do Sistema de Matrícula separando cada ação (como matrícula, cadastro de professor, cancelamento de matrícula, etc.) em arquivos separados. Deixamos a Aplicação o menos complexo possível, deixando apenas a parte visual disponível.

## Alterações

Dentro da classe de Aplicação(PucTriculaApplication.java) havia um grande switch case:

![Aplicação antes da Refatoração](Diagramas/Aswitchcase.png)

Após a refatoração o código ficou desta forma:

![Aplicação atual](Diagramas/SwitchCase.png)

Podemos observar: 

- Houve um desacoplamento maior, separando a lógica dos comandos da classe principal
- Facilitou uma possível extenção do programa, separando a lógica da classe principal
- Facilitou a implementação de testes, onde cada código pode ser testado separadamente
- Ocorreu uma diminuição significativa no tamanho do código
- A manutenção do código ficou mais fácil. Com uma melhora na organização do código e facilidade de localizar determinada funcionalidade
- Ficou mais fácil adicionar melhorias. Exemplo: diferenciar as funcionalidades de administrador para de determinado usuário, fazendo x funcionalidades para quantos usuários forem necessários

## Conclusão

Com a refatoração do código utilizando o padrão COMMAND, melhorias significativas foram encontradas. Melhorias como: a escalabilidade do sistema, testabilidade, manutenção, lógica de execução, reutilização de códigos, entre outras melhorias puderam ser observadas utilizando este padrão dentro deste projeto.


## Estrutura do Código

- **Command:** Interface criada para implementar todos os comandos
- **CommandInvoker:** Classe que controla os comandos. Responsável por executar os comandos recebidos.
- **Classes de Comando:** Classes criadas para implementarem as lógicas do sistema, como os códigos presentes nas classes dos models.
- **PucTriculaApplication:** Aplicação principal. Criamos uma instancia da classe CommandInvoker para adicionarmos um comando a cada opção digitada pelo usuário, como se fosse um switch case.

## Como Executar

Certifique-se de ter o Java JDK 17 ou superior instalado.

1.Clone o repositório:

2.Compile e execute o projeto:

PucTriculaApplication.java

Endereço: LAB-01/src/main/java/com/example/PucTricula/application/PucTriculaApplication.java