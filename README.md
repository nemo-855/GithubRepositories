# GithubRepositories

Githubのpublicリポジトリを一覧表示するサンプルアプリ

## アプリ外観
<img width="300" src="https://user-images.githubusercontent.com/77588574/168717496-43bd3315-0898-408a-ae6c-ff2c951ce592.gif"/>

## アーキテクチャ

- MVVM + マルチモジュール + Repositoryパターン
- domain層より下の階層の内容は[KMMライブラリ](https://github.com/nemo-855/GithubRepositories-KMM)として切り出して、使用している

## 開発規則

### ブランチ戦略

GitHub flow

### コミット規則

- feat: 新しい機能
- fix: バグの修正
- docs: ドキュメントのみの変更
- style: 空白、フォーマット、セミコロン追加など
- refactor: 仕様に影響がないコード改善(リファクタ)
- perf: パフォーマンス向上関連
- test: テスト関連
- chore: ビルド、補助ツール、ライブラリ関連

## 使用技術

### Kotlin
- Coroutines
- Serialization

### DIコンテナ
- Dagger Hilt
- (KMMライブラリ側)Koin

### UIライブラリ
- Groupie

### Http通信
- (KMMライブラリ側)Ktor Client

### Jetpack
- Navigation Component

### Unit Test
- Mockk
- Truth
- Junit4

 
    
