# Movies App

Um aplicativo Android moderno para explorar filmes usando a API do TMDB (The Movie Database), desenvolvido com as mais recentes tecnologias e práticas de desenvolvimento Android.

## ✨ Funcionalidades

### 🏠 Tela Inicial
- Lista de filmes populares com paginação
- Lista de filmes mais bem avaliados
- Carregamento com estados de loading (skeletons)
- Navegação fluida com animações personalizadas

### 🔍 Busca
- Busca inteligente de filmes
- Validação de entrada (mínimo 4 caracteres)
- Resultados em tempo real com paginação
- Carregamento com estados de loading (skeletons)
- Feedback visual de validação

### 👤 Perfil
- Visualização de filmes favoritos
- Lista de filmes para assistir depois (Watchlist)
- Gerenciamento de filmes salvos (remover favoritos/watchlist)
- Informações do perfil do usuário
- Tabs para alternar entre favoritos e watchlist

### 🎬 Detalhes do Filme
- Informações detalhadas do filme
- Adicionar/remover dos favoritos com animação
- Adicionar/remover da watchlist com animação
- Recomendações de filmes similares
- Compartilhamento de URL da imagem (clique no banner)
- Download de imagens (pressione e segure no banner)
- Animação de expansão ao abrir detalhes

### ⚙️ Configurações
- Alternância entre tema claro/escuro

## 🏗️ Arquitetura

### Clean Architecture
O projeto é estruturado em três módulos:

#### 📱 App Module
- Apresentação (UI)
- ViewModels
- Composables
- Navegação
- DI Modules

#### 🧠 Domain Module
- Use Cases
- Models
- Repository Interfaces
- Business Rules

#### 💾 Data Module
- Repository Implementations
- Data Sources
- API Services
- DTOs e Mappers

### Padrões Implementados
- MVVM para a camada de apresentação
- Repository Pattern para abstração de dados
- Use Cases para regras de negócio
- State Hoisting para gerenciamento de estado em Compose

## 🛠️ Tecnologias Utilizadas

### UI/UX
- **Jetpack Compose**: Framework de UI moderno
- **Material Design 3**: Sistema de design
- **Compose Navigation**: Navegação entre telas
- **Coil**: Carregamento e cache de imagens
- **Splash Screen API**: Tela de inicialização personalizada
- **Animações personalizadas**: 
  - Transições entre telas
  - Efeitos de favoritar/watchlist
  - Expansão de detalhes

### Arquitetura e DI
- **Hilt**: Injeção de dependência
- **ViewModel**: Gerenciamento de estado
- **StateFlow**: Fluxos de dados reativos

### Networking
- **Retrofit**: Cliente HTTP
- **Moshi**: Parsing JSON
- **OkHttp**: Interceptadores e logging

### Dados
- **Paging 3**: Carregamento paginado
- **DataStore**: Persistência de preferências

### Testes
- **Testes Instrumentados**:
  - Navegação
- **Testes Unitários**:
  - ViewModels
  - Use Cases
  - Repositories

### Qualidade de Código
- **Ktlint**: Formatação de código
- **Detekt**: Análise estática
- **LeakCanary**: Detecção de memory leaks

## 🔄 Estados e Loading
- Skeletons para carregamento inicial
- Loading paginado

## 🎨 Temas
- Suporte nativo a tema escuro/claro
- Cores Material You
- Transições suaves entre temas
- Persistência da preferência

## 🧪 Testes
```bash
# Testes unitários
./gradlew test

# Testes instrumentados
./gradlew connectedAndroidTest

# Verificação de código
./gradlew ktlintCheck
./gradlew detekt
```

## 🔄 CI/CD

### GitHub Actions
- Build automático a cada PR
- Execução de testes unitários
- Execução de testes instrumentados
- Verificação de qualidade de código (ktlint e detekt)
- Proteção de chaves sensíveis usando secrets

### Segurança
- Chaves da API armazenadas de forma segura
- Variáveis sensíveis protegidas no CI/CD
- Validação de secrets no processo de build

## 🚀 Configuração do Projeto

### Requisitos
- Android Studio Hedgehog ou superior
- JDK 21
- Android SDK 34

### Configuração
1. Clone o repositório
2. Adicione suas chaves no `local.properties`:
```properties
TMDB_KEY=sua_chave_aqui
ACCOUNT_ID=seu_id_aqui
SESSION_ID=sua_sessao_aqui
```
3. Sincronize o projeto com o Gradle
4. Execute o app

### Variáveis de CI/CD
Configure as seguintes secrets no GitHub:
- `TMDB_KEY`: Chave da API do TMDB
- `ACCOUNT_ID`: ID da conta TMDB
- `SESSION_ID`: ID da sessão TMDB

## 📱 Compatibilidade
- Versão mínima: Android 8.0 (API 26)
- Versão alvo: Android 15 (API 35)

## 🎯 Próximos Passos
- [ ] Implementar cache offline
- [ ] Suporte a múltiplos idiomas
- [ ] Melhorar cobertura de testes
- [ ] Adicionar mais animações
- [ ] Implementar compartilhamento de filmes

## 📄 Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
