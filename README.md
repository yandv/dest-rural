# Setup da Aplicação

## Requisitos da aplicação

- Instalação do Java (versão 17 ou superior)
- Instalação do Maven
- Instalação do Tomcat (versão 11)

## Recomendações

> Use o SDKMAN para instalar o Java e Maven

Tutorial para instalação do SDKMAN: https://sdkman.io/install/ (Suporte para Linux e Windows)

### Para rodar no VSCode

- Instale a extensão "Java Extension Pack" (vscjava.vscode-java-pack)
- Instale a extensão "Community Server Connectors" (redhat.vscode-community-server-connector)
- Vá em Servers > Community Server Connector > Create New Server > Apache Tomcat > Selecione a versão 11
- Após isso, faça o build da aplicação utilizando o comando `mvn clean install` ou `mvn clean package`
- Clique com o botão direito no projeto e vá em "Run on Server"

### Para rodar no Eclipse

- Instale o Eclipse IDE for Enterprise Java and Web Developers
- Instale o Apache Tomcat (versão 11)
- Importe o projeto para o Eclipse
- Clique com o botão direito no projeto e vá em "Run As" > "Run on Server"

### Para rodar no Intellij

- Importe o projeto para o Intellij
- Clique com o botão direito no projeto e vá em "Run" > "Edit Configurations"
- Clique no botão "+" e selecione "Tomcat Server" > "Local"
- Selecione a versão do Tomcat (11) e clique em "Fix"
- Clique em "Apply" e depois em "Ok"
- Clique no botão de play para rodar a aplicação
