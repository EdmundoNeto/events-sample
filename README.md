# events-sample

O aplicativo foi separado em 3 módulos, APP, DOMAIN e EVENTS.

- App: Responsável pela classe de Application
- Domain: Este é o módulo responsável por fazer a comunicação com o a API, os dados populados no módulo "Events" são oriundos das requisições realizadas pelo Domain
- Events: Neste módulo é onde esta toda UI, feature de listagem e detalhe de detalhe de evento.

Aplicação em um padrão de arquitetura MVVM, 
- Coroutines para as operações assíncronas, 
- Koin para injeção de dependências, 
- Retrofit para comunicação com API, 
- Glide para carregamento de imagens.

Foram realizados alguns testes unitários nas ViewModel e na Classe extensions.
