<h1 align="center">
  Cosmus (Alpha) <br/>
  <img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=DESCONTINUADO&color=FF0000&style=for-the-badge"/>
</h1>

<div align="center">
  <img src="https://i.postimg.cc/W4W29xnn/Captura-de-tela-2024-01-28-171242.png" height="400"/>
</div>

<h3 align="center">
  ⚠️ATENÇÃO⚠️<br/>O sistema atual foi descontinuado. Ele está sendo desenvolvido no repositório <a href="https://github.com/mini-niko/cosmus-art" >cosmus-art</a>.
</h3>

# Índice 

* [Sobre](#sobre)
* [Como usar](#como-usar)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Features](#features)
* [Autores](#autores)
* [Considerações finais](#consideracoes-finais)


# Sobre

<p>O Cosmus é um projeto independente, iniciado em 2023, no curso Jovem Programador do SENAC, e que possui a proposta de tornar a arte mais visível para as pessoas. Funciona de maneira simples, basta criar uma conta ou entrar com uma existente e começar a postar suas diversas artes!</p>

# Dependências necessárias
- Docker e Docker Compose
- OpenJDK 17 (caso queira modificar e utilizar o projeto sem docker)

# Como Usar
1- Primeiramente, você deve baixar o repositório do projeto. Há duas maneiras de fazer isso:

- [Clicando aqui](https://github.com/mini-niko/Cosmus-Alpha/archive/refs/heads/main.zip) você baixa manualmente o projeto;

- Utilizando o git localmente, com o comando:
  ```git
  git clone https://github.com/mini-niko/Cosmus-Alpha.git
  ```
  e utilizando o seguinte comando para entrar no diretório:
  ```
  cd Cosmus-Alpha
  ```
---
2- Após isso, você deve dar o seguinte comando do docker para buildar e levantar o projeto:
  ```
  docker compose -f infra/compose.yaml up -d
  ```

3- Assim que o docker buildar e levantar o projeto, ele estará disponível localmente na porta 8080.

# Tecnologias Utilizadas

* `HTML`
* `CSS`
* `JavaScript`
* `OpenJDK 17 (Java)`
* `H2 Database`
* `Spring Boot 3.2.0-M3`
* `Docker e Docker Compose`

# Features
- [x] Login e cadastro
- [x] Troca de avatar
- [x] Posts
- [ ] Apagar próprios posts 
- [ ] Página de perfil
- [ ] Configurações da conta
- [ ] Curtidas
- [ ] Comentários
- [ ] FAQ
- [ ] Sobre nós

# Considerações finais
Este projeto foi feito no início dos meus estudos. Ele não está totalmente bem feito, não possui muitas funcionalidades. Porém, estou trabalhando em uma versão deste projeto em next.js. Caso se interesse, [clique aqui](https://github.com/mini-niko/cosmus-art) para ir até o repositório.

# Autores

| [<img loading="lazy" src="https://avatars.githubusercontent.com/u/119255200?v=4" width=115><br><sub>Maurício Xavier de Oliveira<br>(Miniko)</sub>](https://github.com/mini-niko) |
| :---: | 
