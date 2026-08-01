# Vertex Android App Architecture

O app Android é a interface principal da plataforma multiagente. Ele não executa modelos localmente: atua como cliente para APIs REST, streaming e WebSocket do backend.

## Estrutura modular

- `app`: composição da aplicação, navegação, manifesto seguro e injeção de dependências manual inicial.
- `core:common`: modelos compartilhados de chat, agentes, projetos, arquivos, memórias e tarefas.
- `core:network`: contrato `MultiAgentApiClient` para REST/streaming/WebSocket e implementação demo substituível por Retrofit/OkHttp.
- `core:database`: contrato de cache local preparado para Room e sincronização offline.
- `core:designsystem`: tema Compose Material 3 com modo escuro AMOLED.
- `core:security`: fronteira de sessão segura, preparada para Android Keystore e armazenamento criptografado.
- `feature_chat`: MVI/MVVM, use cases, repository e UI de chat.
- `feature_agents`, `feature_projects`, `feature_memory`, `feature_files`, `feature_settings`, `feature_profile`: telas independentes por domínio.

## Fluxo de dados

```text
Compose UI -> ViewModel -> Use Case -> Repository -> API Client -> Backend Multiagente
```

## Preparação de produção

- Hilt pode substituir `AppGraph` sem alterar as features.
- Room pode implementar `LocalCache` para histórico, projetos, rascunhos e sincronização offline.
- Retrofit/OkHttp podem implementar `MultiAgentApiClient` com HTTPS obrigatório.
- WebSocket/SSE podem alimentar `streamTask` como `Flow<TaskUpdate>`.
- WorkManager pode sincronizar rascunhos, arquivos e memórias quando a conexão voltar.
