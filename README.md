# Vertex Android

Vertex é um aplicativo Android Kotlin que funciona como cliente para uma plataforma multiagente de IA. O app foi estruturado para evoluir como produto comercial: modular, seguro, responsivo, preparado para streaming e com arquitetura de camadas.

## Módulos

```text
app
core:common
core:network
core:database
core:designsystem
core:security
feature_chat
feature_agents
feature_projects
feature_memory
feature_files
feature_settings
feature_profile
```

## Arquitetura

Fluxo das features:

```text
Compose UI -> ViewModel/MVI State -> Use Cases -> Repository -> API Client -> Backend Multiagente
```

A implementação inicial inclui:

- Home com saudação, projetos ativos, agentes ativos e tarefas recentes.
- Chat multiagente com mensagens, comandos rápidos, plano de execução e estado de streaming.
- Visualização de progresso dos agentes.
- Telas base para projetos, memória, arquivos, configurações e perfil.
- Camadas core para modelos compartilhados, rede, cache local, design system e segurança.
- Comunicação simulada por `DemoMultiAgentApiClient`, mantendo contrato para REST, WebSocket e streaming reais.

## Segurança

- `android:usesCleartextTraffic="false"` no manifesto.
- Tokens passam por `SecureSessionManager`, que representa a fronteira para integração com EncryptedSharedPreferences/Keystore.
- O app é cliente: modelos e execução principal de agentes permanecem no backend.

## Ambiente Android

Se o SDK não estiver instalado, execute:

```bash
scripts/setup_android_sdk.sh
```

Mais detalhes estão em `docs/android_environment.md`.

## Build

```bash
gradle :app:assembleDebug
```
