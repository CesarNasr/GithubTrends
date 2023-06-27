# GithubTrends

This is a sample app that includes screens to view : -List of trending github repos of expandable items

UX flow Description:
  - User can open the app
  - User sees a loading shimmer
  - App fetches repositories from remote data source
  - User can then view expand each item to view description
  - User sees an error message view in case of internet issue
  - User can press retry, and the flow will repeat


SDKs and Languages used :
- Kotlin
- Android SDK

Jetpack Library
- Coroutines, StateFlow
- Navigation Component Architecture
- ViewModels
- Data binding

Architecures and patterns :
- MVVM
- Repository pattern
- Dependency Injection using Dagger-Hilt, integrated with viewmodels and views(fragments and activities)
- A single layer of abstraction between Data layer and Presention Layer using interfaces (Reposiotry & RepositoryImpl)
- SOLID principles

Layouts and Designs:
- ConstraintLayout and other Layouts
- Material Design
- Data binding

Remote Data Source:
- API used : https://api.github.com/search/repositories?q=language=+sort:stars

What could have been added :
- Fully implementing Clean Architecture (added UseCase classes if needed)
- Better UX practices
- Better SOLID Principles
- Implementing Compose UI instead of XML layouts
- Adding more unit tests and UI tests
- Implemented a modular approach, where each feature is a separate module, with few common modules
- CI/CD pipelines (github actions / bitrise / etc...)

I hope you like my work, thank you.
