# Movies App

Um aplicativo Android moderno para explorar e descobrir filmes, desenvolvido com as melhores práticas e tecnologias mais recentes do ecossistema Android.

## 🚀 Tecnologias

- **Kotlin** - Linguagem de programação principal
- **Jetpack Compose** - Framework moderno para UI
- **Material Design 3** - Sistema de design
- **Clean Architecture** - Arquitetura em camadas (app, domain, data)
- **Kotlin Coroutines & Flow** - Programação assíncrona
- **Paging 3** - Paginação de dados
- **Hilt** - Injeção de dependência
- **Unit Tests** - Testes unitários

## 📱 Funcionalidades

- Busca de filmes
- Visualização de filmes populares
- Detalhes dos filmes
- Navegação fluida com animações
- Paginação infinita
- Interface moderna e responsiva

## 🏗️ Arquitetura

O projeto segue a Clean Architecture com três módulos principais:

- **app**: Camada de apresentação com Jetpack Compose
- **domain**: Regras de negócio e casos de uso
- **data**: Implementação do repositório e fontes de dados

## 🛠️ Como Executar

1. Clone o repositório
2. Abra o projeto no Android Studio
3. Configure sua API Key do TMDB em `local.properties`:
   ```properties
   TMDB_API_KEY=sua_api_key_aqui
   ```
4. Execute o app em um emulador ou dispositivo físico

## 🧪 Testes

O projeto inclui testes unitários para as diferentes camadas:
- Testes de ViewModel
- Testes de Casos de Uso
- Testes de DataSource

Execute os testes usando:
```bash
./gradlew test
```

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
